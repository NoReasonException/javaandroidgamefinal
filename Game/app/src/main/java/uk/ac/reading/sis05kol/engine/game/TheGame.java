package uk.ac.reading.sis05kol.engine.game;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import uk.ac.reading.sis05kol.engine.R;
import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.info.RendererInfo;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.map.path.Path;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;
import uk.ac.reading.sis05kol.engine.game.core.object.drawables.portals.BluePortal;
import uk.ac.reading.sis05kol.engine.game.core.object.drawables.towers.FireTower;
import uk.ac.reading.sis05kol.engine.game.core.object.drawables.portals.RedPortal;
import uk.ac.reading.sis05kol.engine.game.core.renderer.BulletSystem;
import uk.ac.reading.sis05kol.engine.game.core.renderer.Renderer;
import uk.ac.reading.sis05kol.engine.game.core.schenario.DifficultyLevel1;
import uk.ac.reading.sis05kol.engine.game.core.schenario.Schenario;
import uk.ac.reading.sis05kol.engine.game.core.score.LifesSystem;
import uk.ac.reading.sis05kol.engine.game.core.utils.CoordinateSystemUtils;
import uk.ac.reading.sis05kol.engine.game.engine.GameThread;
import uk.ac.reading.sis05kol.engine.game.engine.GameView;


public class TheGame extends GameThread {


    private Map map;
    private Position bottomLeftMaxPosition;
    private Renderer renderer;
    private String loggerTag="THEGAME";

    private List<Bitmap> grass;
    private List<Bitmap> sand;
    private Path path;

    private Handler handler=new Handler();
    private Schenario schenario;
    private LevelInfo levelInfo;
    private RendererInfo rendererInfo;
    private BulletSystem bulletSystem;
    private  View progressBackground;
    private ArrayList<View>lifes;
    private View timer;

    //This is run before anything else, so we can prepare things here
    public TheGame(GameView gameView, View progressBackground,ArrayList<View>lifes,View timer,View youLostContainer) {
        //House keeping
        super(gameView,youLostContainer);
        this.progressBackground=progressBackground;
        this.lifes=lifes;
        this.timer=timer;

        levelInfo=new LevelInfo(7,0.08f);
        grass= loadGrass(gameView);
        sand=loadSand(gameView);
        schenario=new DifficultyLevel1(levelInfo);
        bulletSystem=BulletSystem.getInstance();

    }
    //This is run before a new game (also after an old game)
    @Override
    public void setupBeginning() {
        LifesSystem.setInstance(new LifesSystem(
                handler,
                progressBackground,
                lifes,
                timer) {
            @Override
            public long getLifes() {
                return getScore();
            }

            @Override
            public void setLifes(long lifes) {
                setScore(lifes);
            }

            @Override
            public Function<LifesSystem, Void> gameOverCallback() {
                return new Function<LifesSystem, Void>() {
                    @Override
                    public Void apply(LifesSystem aVoid) {
                        setState(STATE_LOSE);
                        aVoid.reset();
                        return null;
                    }
                };
            }
        });

        Pair<Integer,Integer>screenSize=new Pair<Integer,Integer>(
                Double.valueOf(mCanvasWidth).intValue(),
                Double.valueOf(mCanvasHeight).intValue());



        renderer=new Renderer(screenSize,levelInfo,mGameView.getContext());
        rendererInfo=renderer.getInfo();
        //levelInfo=levelInfo.setInitialPositionOfObjectOffset(new Pair<Integer,Integer>(Double.valueOf(renderer.getTileSizeXY().first*0.1).intValue(),Double.valueOf(renderer.getTileSizeXY().second*0.1).intValue()));

        grass=scaleTiles(grass,renderer.getTileSizeXY());
        sand=scaleTiles(sand,renderer.getTileSizeXY());
        path=Path.getWithTileCountX7();


        Drawable portal = new BluePortal(mGameView.getContext(), CoordinateSystemUtils.getInstance().fromTileToAbsolutePosition(new Position(0,0)),levelInfo);
        Drawable portalend = new RedPortal(mGameView.getContext(),CoordinateSystemUtils.getInstance().fromTileToAbsolutePosition(new Position(rendererInfo.getTileCountXY().first-1,rendererInfo.getTileCountXY().second-1)),levelInfo);

        map = new Map(portal,portalend,renderer.getTileCountXY());


        Log.i(loggerTag,".setupBeginning() Complete");
    }


    public List<Bitmap> loadGrass(GameView gameView){

        return new ArrayList<Bitmap>(Arrays.asList(
                BitmapFactory.decodeResource
                (gameView.getContext().getResources(),
                        R.drawable.grass1),
                BitmapFactory.decodeResource
                        (gameView.getContext().getResources(),
                                R.drawable.grass2),
                BitmapFactory.decodeResource
                        (gameView.getContext().getResources(),
                                R.drawable.grass3),
                BitmapFactory.decodeResource
                        (gameView.getContext().getResources(),
                                R.drawable.grass4)));

    }
    public List<Bitmap> loadSand(GameView gameView){

        return new ArrayList<Bitmap>(Arrays.asList(
                BitmapFactory.decodeResource
                        (gameView.getContext().getResources(),
                                R.drawable.sand1),
                BitmapFactory.decodeResource
                        (gameView.getContext().getResources(),
                                R.drawable.sand2)));

    }
    public List<Bitmap> scaleTiles(List<Bitmap> resources, Pair<Integer,Integer>tileSize){
        return resources.stream().
                map((b)->Bitmap.createScaledBitmap(b, tileSize.first, tileSize.second, true)).
                collect(Collectors.toList());
    }


    @Override
    protected void doDraw(Canvas canvas) {
        super.doDraw(canvas); //clear canvas
            if (grass == null || renderer == null) return;
            renderer.drawBackground(canvas, grass);
            renderer.drawPath(canvas, sand, path);
            renderer.drawMap(canvas, map);
            renderer.drawBullets(canvas,bulletSystem);
    }


    //This is run whenever the phone is touched by the user
    @Override
    protected void actionOnTouch(float x, float y) {
        if(renderer==null||map==null||mGameView==null)return;
        handler.post(new Runnable() {
            @Override
            public void run() {
                Position tilePosition=CoordinateSystemUtils.getInstance().fromAbsoluteToTilePosition(new Position(
                        Float.valueOf(x).intValue(),Float.valueOf(y).intValue()));
                Position absolutePosition = CoordinateSystemUtils.getInstance().fromTileToAbsolutePosition(tilePosition);
                absolutePosition=absolutePosition.addX(rendererInfo.getInitialPositionOfObjectOffset().first).addY(rendererInfo.getInitialPositionOfObjectOffset().second);
                if(!path.existsInPath(tilePosition) && !map.existsObjectAtPosition(tilePosition)){
                    Drawable d = new FireTower(mGameView.getContext(),levelInfo,absolutePosition,bulletSystem);
                    map.setDrawableAtPosition(tilePosition,d);
                    Log.i(loggerTag,"actionOnTouch added element ("+x+"-"+y+") tilePosition "+tilePosition+" to absolutePosition "+absolutePosition);
                }else {
                    Log.i(loggerTag,"actionOnTouch not added element ("+x+"-"+y+") tilePosition "+tilePosition+" to absolutePosition "+absolutePosition+" exists? "+map.getDrawableAtPosition(tilePosition));
                }

            }
        });

    }


    //This is run whenever the phone moves around its axises
    @Override
    protected void actionWhenPhoneMoved(float xDirection, float yDirection, float zDirection) {


    }


    //This is run just before the game "scenario" is printed on the screen
    @Override
    protected void updateGame(float secondsElapsed) {

        renderer.updateBullets(map,path,bulletSystem);
        renderer.updateMoveables(map, path);
        renderer.updateSchenario(schenario,handler,map);

    }

    //Collision control between mBall and another big ball
    private boolean updateBallCollision(float x, float y) {
        return false;
    }
}

// This file is part of the course "Begin Programming: Build your first mobile game" from futurelearn.com
// Copyright: University of Reading and Karsten Lundqvist
// It is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// It is is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
//
// You should have received a copy of the GNU General Public License
// along with it.  If not, see <http://www.gnu.org/licenses/>.
