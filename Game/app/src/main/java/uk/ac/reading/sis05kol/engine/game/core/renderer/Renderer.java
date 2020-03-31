package uk.ac.reading.sis05kol.engine.game.core.renderer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.util.Pair;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import uk.ac.reading.sis05kol.engine.R;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.Moveable;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.map.path.Path;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;

public class Renderer {
    private Pair<Integer,Integer> screenSize;   //screenSize given
    private Pair<Integer,Integer> tileCountXY;  //given only x , y must be calculated
    private Pair<Integer,Integer> tileSizeXY;   //must be calculated
    private Context context;
    private Bitmap test;
    private int x = 0;


    private String loggerTag="RENDERER";

    private HashMap<Pair<Integer,Integer>,Integer> randomGrassTileIndex =new HashMap<>();
    private Random random =new Random();


    public Renderer(Pair<Integer, Integer> screenSize,int tileCountX,Context context) {
        this.screenSize = screenSize;
        this.context=context;
        test=BitmapFactory.decodeResource
                (context.getResources(),
                        R.drawable.small_red_ball);

        tileCountXY=tileCountXYCalculate(screenSize,tileCountX);
        tileSizeXY=tileSizeXYCalculate(screenSize,tileCountXY);

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
                randomTileIndexCurr= randomGrassTileIndex.get(screenPosition);
                if(randomTileIndexCurr==null){
                    randomTileIndexCurr= genRandomGrassTileIndex(screenPosition,backgroundTile.size());
                }
                canvas.drawBitmap(backgroundTile.get(randomTileIndexCurr),screenPosition.first,screenPosition.second,null);
            }
        }

    }
    private Integer genRandomGrassTileIndex(Pair<Integer,Integer>screenPosition, int max){
        int newIndex=Math.abs(random.nextInt(max));
        randomGrassTileIndex.put(screenPosition,newIndex);
        return newIndex;

    }
    public void drawPath(Canvas canvas, List<Bitmap> backgroundTile, Path path){

        _drawPath(canvas,backgroundTile,path.getFirst());
    }
    public void _drawPath(Canvas canvas,List<Bitmap> backgroundTile,Path.Node currNode){
        canvas.drawBitmap(backgroundTile.get(0),currNode.getPosition().getX()*tileSizeXY.first,currNode.getPosition().getY()*tileSizeXY.second,null);
        for (Path.Node n :currNode.getLinks()) {
            _drawPath(canvas,backgroundTile,n);
        }
    }

    public void drawMap(Canvas canvas, Map map){
        for (Position p: map.getDrawableObjects()) {
            Drawable d=map.getDrawableAtPosition(p);
            canvas.drawBitmap(d.getBitmap(),d.getAbsolutePosition().getX(),d.getAbsolutePosition().getY(),null);
        }

    }
    public void updateMoveables(Map map,Path path){
        Position newPosition;
        Moveable curr;
        for (Position p: map.getDrawableObjects()) {

            Drawable d=map.getDrawableAtPosition(p);
            if(d instanceof Moveable){
                curr=(Moveable)d;
                newPosition=curr.nextMove(path,this::fromAbsoluteToTilePosition,this::fromTileToAbsolutePositionWithRedundancy);
                d.setAbsolutePosition(newPosition);
            }

        }
    }

    public Position fromAbsoluteToTilePosition(Position absolutePosition){
        return new Position(
                absolutePosition.getX()/tileSizeXY.first,
                absolutePosition.getY()/tileSizeXY.second
        );
    }
    public Position fromTileToAbsolutePosition(Position tilePosition){
        return new Position(
                tilePosition.getX()*tileSizeXY.first,
                tilePosition.getY()*tileSizeXY.second
        );
    }
    public Position fromTileToAbsolutePositionWithRedundancy(Position tilePosition){
        return new Position(
                tilePosition.getX()*tileSizeXY.first+random.nextInt(20),
                tilePosition.getY()*tileSizeXY.second+random.nextInt(20)
        );
    }

    public Pair<Integer, Integer> getTileSizeXY() {
        return tileSizeXY;
    }

    public Pair<Integer, Integer> getTileCountXY() {
        return tileCountXY;
    }
}
