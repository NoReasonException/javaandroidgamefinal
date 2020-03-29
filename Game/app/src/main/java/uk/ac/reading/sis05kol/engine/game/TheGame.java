package uk.ac.reading.sis05kol.engine.game;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import uk.ac.reading.sis05kol.engine.R;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.renderer.Renderer;
import uk.ac.reading.sis05kol.engine.game.engine.GameThread;
import uk.ac.reading.sis05kol.engine.game.engine.GameView;

public class TheGame extends GameThread {


    private Map map;
    private Position bottomLeftMaxPosition;
    private Renderer renderer;
    private int tileCountX;
    private String loggerTag="THEGAME";

    private List<Bitmap> grass;

    //This is run before anything else, so we can prepare things here
    public TheGame(GameView gameView) {
        //House keeping
        super(gameView);

        grass=loadResources(gameView);



    }

    public List<Bitmap> loadResources(GameView gameView){

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
    public List<Bitmap> scaleResources(List<Bitmap> resources, Pair<Integer,Integer>tileSize){
        return resources.stream().
                map((b)->Bitmap.createScaledBitmap(b, tileSize.first, tileSize.second, true)).
                collect(Collectors.toList());
    }

    //This is run before a new game (also after an old game)
    @Override
    public void setupBeginning() {
        map = new Map();
        Pair<Integer,Integer>screenSize=new Pair<Integer,Integer>(
                Double.valueOf(mCanvasWidth).intValue(),
                Double.valueOf(mCanvasHeight).intValue());
        tileCountX=10;
        renderer=new Renderer(map,screenSize,tileCountX);

        grass=scaleResources(grass,renderer.getTileSizeXY());


        Log.i(loggerTag,".setupBeginning() Complete");

    }

    @Override
    protected void doDraw(Canvas canvas) {
        super.doDraw(canvas); //clear canvas
        if(grass ==null||renderer==null)return;
        renderer.drawBackground(canvas, grass);
    }


    //This is run whenever the phone is touched by the user
    @Override
    protected void actionOnTouch(float x, float y) {

    }


    //This is run whenever the phone moves around its axises
    @Override
    protected void actionWhenPhoneMoved(float xDirection, float yDirection, float zDirection) {


    }


    //This is run just before the game "scenario" is printed on the screen
    @Override
    protected void updateGame(float secondsElapsed) {


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
