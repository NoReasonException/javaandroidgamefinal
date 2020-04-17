package main.game;
import android.app.Fragment;
import android.arch.core.util.Function;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;

import android.util.Log;
import android.util.Pair;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import uk.ac.reading.sis05kol.engine.R;
import main.game.core.etc.DifficultyLevel;
import main.game.core.info.LevelInfo;
import main.game.core.info.RendererInfo;
import main.game.core.map.Map;
import main.game.core.map.Position;
import main.game.core.map.path.Path;
import main.game.core.object.Drawable;
import main.game.core.object.drawables.portals.BluePortal;
import main.game.core.object.drawables.towers.FireTower;
import main.game.core.object.drawables.portals.RedPortal;
import main.game.core.object.drawables.towers.IceTower;
import main.game.core.object.drawables.towers.PoisonTower;
import main.game.core.object.drawables.towers.StormTower;
import main.game.core.object.drawables.towers.Tower;
import main.game.core.renderer.BulletSystem;
import main.game.core.renderer.Renderer;
import main.game.core.schenario.EasySchenario;
import main.game.core.schenario.HardSchenario;
import main.game.core.schenario.NormalSchenario;
import main.game.core.schenario.Schenario;
import main.game.core.score.LifesSystem;
import main.game.core.utils.CoordinateSystemUtils;
import main.game.engine.GameThread;
import main.game.engine.GameView;


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
    private View moneyCounter;
    private int hasPressedStart=0;
    private DifficultyLevel difficultyLevel = DifficultyLevel.NORMAL;
    protected Function<Function<Tower.TowerType,Void>,Void> selectTowerCallback;
    protected int levelID;

    //This is run before anything else, so we can prepare things here
    public TheGame(Path path,GameView gameView,
                   View progressBackground,
                   ArrayList<View>lifes,
                   View timer,
                   View moneyCounter,
                   View youLostContainer,
                   Fragment youLostFragment,
                   DifficultyLevel difficultyLevel,
                   Function<Function<Tower.TowerType,Void>,Void>selectTowerCallback) {
        //House keeping
        super(gameView,youLostContainer,youLostFragment);
        this.progressBackground=progressBackground;
        this.lifes=lifes;
        this.timer=timer;
        this.moneyCounter=moneyCounter;
        this.difficultyLevel=difficultyLevel;
        this.path=path;

        this.selectTowerCallback=selectTowerCallback;
        levelInfo=new LevelInfo(7,0.08f);
        grass= loadGrass(gameView);
        sand=loadSand(gameView);
        schenario=new EasySchenario(levelInfo); //always initialize the variables , in case of any error
        bulletSystem=BulletSystem.getInstance();
        Log.i(loggerTag,"difficultyLevel is set to "+difficultyLevel);

        initializeSchenario();



    }
    private void initializeSchenario(){
        switch (difficultyLevel){
            case EASY:
                schenario=new EasySchenario(levelInfo);
                break;
            case NORMAL:
                schenario=new NormalSchenario(levelInfo);
                break;
            case HARD:
                schenario=new HardSchenario(levelInfo);
                break;
        }
    }
    //This is run before a new game (also after an old game)
    @Override
    public void setupBeginning() {
        LifesSystem.setInstance(new LifesSystem(
                handler,
                progressBackground,
                lifes,
                timer,moneyCounter) {
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
        if(getMode()!=STATE_RUNNING)return;
        //TODO this is terrible , but is temponary workaround, i am exhausted, sorry future me (or dear reviewer)
        if(hasPressedStart<5){
            hasPressedStart+=1;
            return;
        }
        if (renderer == null || map == null || mGameView == null) return;

        Position tilePosition = CoordinateSystemUtils.getInstance().fromAbsoluteToTilePosition(new Position(
                Float.valueOf(x).intValue(), Float.valueOf(y).intValue()));


        if (!path.existsInPath(tilePosition) && !map.existsObjectAtPosition(tilePosition)) {

            selectTowerCallback.apply(new Function<Tower.TowerType, Void>() {
                @Override
                public Void apply(Tower.TowerType input) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Position absolutePosition = CoordinateSystemUtils.getInstance().fromTileToAbsolutePosition(tilePosition);
                            absolutePosition = absolutePosition.addX(rendererInfo.getInitialPositionOfObjectOffset().first).addY(rendererInfo.getInitialPositionOfObjectOffset().second);
                            Tower tower;
                            switch (input) {

                                case FIRE:
                                    tower = new FireTower(mGameView.getContext(), levelInfo, absolutePosition, bulletSystem);

                                    break;
                                case POISON:
                                    tower = new PoisonTower(mGameView.getContext(), levelInfo, absolutePosition, bulletSystem);
                                    break;
                                case STORM:
                                    tower = new StormTower(mGameView.getContext(), levelInfo, absolutePosition, bulletSystem);
                                    break;
                                case ICE:
                                    tower = new IceTower(mGameView.getContext(), levelInfo, absolutePosition, bulletSystem);
                                    break;

                                default:
                                    tower = new FireTower(mGameView.getContext(), levelInfo, absolutePosition, bulletSystem);

                            }
                            if(LifesSystem.getInstance().getMoney()>=tower.getCost()){
                                LifesSystem.getInstance().removeMoney(tower.getCost());
                                map.setDrawableAtPosition(tilePosition, tower);
                            }


                        }
                    });
                    return null;
                }
            });

            Log.i(loggerTag, "actionOnTouch will add element (" + x + "-" + y + ") tilePosition " + tilePosition );
        } else {
            Log.i(loggerTag, "actionOnTouch will not added element (" + x + "-" + y + ") tilePosition " + tilePosition + " exists? " + map.getDrawableAtPosition(tilePosition));
        }


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
