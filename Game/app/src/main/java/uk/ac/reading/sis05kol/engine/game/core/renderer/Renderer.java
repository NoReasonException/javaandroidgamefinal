package uk.ac.reading.sis05kol.engine.game.core.renderer;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;

public class Renderer {
    private Map map;
    private Pair<Integer,Integer> screenSize;   //screenSize given
    private Pair<Integer,Integer> tileCountXY;  //given only x , y must be calculated
    private Pair<Integer,Integer> tileSizeXY;   //must be calculated

    private String loggerTag="RENDERER";

    private HashMap<Pair<Integer,Integer>,Integer> randomTileIndex=new HashMap<>();
    Random r=new Random();


    public Renderer(Map map, Pair<Integer, Integer> screenSize,int tileCountX) {
        this.map = map;
        this.screenSize = screenSize;
        tileCountXY=tileCountXYCalculate(screenSize,tileCountX);
        tileSizeXY=tileSizeXYCalculate(screenSize,tileCountXY);

    }

    public void init(Pair<Integer,Integer>screenSize,int tileCountX){

    }
    public Pair<Integer,Integer> tileCountXYCalculate(Pair<Integer,Integer>screenSize,int tileCount){
        float x_To_yRatio=screenSize.second/((1.0f)*screenSize.first);
        Pair<Integer,Integer>tileCountXY=new Pair<>(tileCount,Double.valueOf(tileCount*x_To_yRatio).intValue());
        Log.i(loggerTag,"Renderer Initialization : screenSize "+screenSize+" and requested tilecountX: "+tileCount+" produced tileCountXY: "+tileCountXY);
        return tileCountXY;
    }
    public Pair<Integer,Integer> tileSizeXYCalculate(Pair<Integer,Integer>screenSize,Pair<Integer,Integer>tileCountXY){
        float screen_x_to_x_ratio=screenSize.first/((1.0f)*tileCountXY.first);
        float screen_y_to_y_ratio=screenSize.second/((1.0f)*tileCountXY.second);
        Pair<Integer,Integer>tileSizeXY=new Pair<Integer,Integer>(Double.valueOf(screen_x_to_x_ratio).intValue(),Double.valueOf(screen_y_to_y_ratio).intValue());
        Log.i(loggerTag,"Renderer Initialization : screenSize "+screenSize+" and requested tileSizeXY: "+tileCountXY+" produced tileSizeXY: "+tileSizeXY);
        return tileSizeXY;
    }
    public void drawBackground(Canvas canvas, List<Bitmap> backgroundTile){


        Pair<Integer,Integer>screenPosition;
        Integer randomTileIndexCurr;

        for (int i = 0; i < tileCountXY.first; i++) {
            for (int j = 0; j < tileCountXY.second; j++) {
                screenPosition=new Pair<>(
                        i*tileSizeXY.first,
                        j*tileSizeXY.second
                );
                randomTileIndexCurr=randomTileIndex.get(screenPosition);
                if(randomTileIndexCurr==null){
                    randomTileIndexCurr=genRandomTileIndex(screenPosition,backgroundTile.size());
                }
                canvas.drawBitmap(backgroundTile.get(randomTileIndexCurr),screenPosition.first,screenPosition.second,null);
            }
        }

    }
    private Integer genRandomTileIndex(Pair<Integer,Integer>screenPosition,int max){
        int newIndex=Math.abs(r.nextInt(max));
        randomTileIndex.put(screenPosition,newIndex);
        return newIndex;

    }

    public Pair<Integer, Integer> getTileSizeXY() {
        return tileSizeXY;
    }
}
