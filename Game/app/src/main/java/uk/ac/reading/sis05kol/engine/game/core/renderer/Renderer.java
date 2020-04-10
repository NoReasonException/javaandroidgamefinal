package uk.ac.reading.sis05kol.engine.game.core.renderer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import uk.ac.reading.sis05kol.engine.R;
import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.info.RendererInfo;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.MapAwareAction;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.MapNonAwareActionAble;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.map.path.Path;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;
import uk.ac.reading.sis05kol.engine.game.core.schenario.Schenario;
import uk.ac.reading.sis05kol.engine.game.core.utils.CoordinateSystemUtils;

public class Renderer {
    private Pair<Integer,Integer> screenSize;   //screenSize given
    private Pair<Integer,Integer> tileCountXY;  //given only x , y must be calculated
    private Pair<Integer,Integer> tileSizeXY;   //must be calculated
    private Context context;
    private Bitmap test;
    private int x = 0;
    private String loggerTag="RENDERER";
    private HashMap<Pair<Integer,Integer>,Integer> randomGrassTileIndex =new HashMap<>();
    private HashMap<Position,Integer> randomSandTileIndex =new HashMap<>();
    private Random random =new Random();
    private LevelInfo levelInfo;
    private RendererInfo rendererInfo;



    public Renderer(Pair<Integer, Integer> screenSize, LevelInfo levelInfo, Context context) {
        this.screenSize = screenSize;
        this.context=context;
        this.levelInfo=levelInfo;
        test=BitmapFactory.decodeResource
                (context.getResources(),
                        R.drawable.small_red_ball);


        tileCountXY=tileCountXYCalculate(screenSize,levelInfo.getTileCountX());
        tileSizeXY=tileSizeXYCalculate(screenSize,tileCountXY);
        CoordinateSystemUtils.initInstance(tileCountXY,tileSizeXY);

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
        canvas.drawBitmap(backgroundTile.get(getSandTileIndex(currNode.getPosition(),backgroundTile.size())),currNode.getPosition().getX()*tileSizeXY.first,currNode.getPosition().getY()*tileSizeXY.second,null);
        for (Path.Node n :currNode.getLinks()) {
            _drawPath(canvas,backgroundTile,n);
        }
    }
    private Integer getSandTileIndex(Position tilePosition, int max){
        if(randomSandTileIndex.containsKey(tilePosition)){
            return randomSandTileIndex.get(tilePosition);
        }else{
            int newIndex=Math.abs(random.nextInt(max));
            randomSandTileIndex.put(tilePosition,newIndex);
            return newIndex;

        }
    }

    public void drawMap(Canvas canvas, Map map){
        for (Position p: map.getDrawableObjects()) {
            Drawable d=map.getDrawableAtPosition(p);
            canvas.drawBitmap(d.getBitmap(),d.getAbsolutePosition().getX(),d.getAbsolutePosition().getY(),null);
        }
    }
    public void drawBullets(Canvas canvas,BulletSystem bulletSystem){
        bulletSystem.syncWithBulletSystem(new Function<ArrayList<Drawable>, Void>() {
            @Override
            public Void apply(ArrayList<Drawable> drawables) {
                for (Drawable bullet: new ArrayList<>(drawables)) {
                    canvas.drawBitmap(bullet.getBitmap(),bullet.getAbsolutePosition().getX(),bullet.getAbsolutePosition().getY(),null);
                }
                return null;
            }
        });
    }
    public void updateBullets(Map map,Path path,BulletSystem bulletSystem){
        bulletSystem.syncWithBulletSystem(new Function<ArrayList<Drawable>, Void>() {
            @Override
            public Void apply(ArrayList<Drawable> drawables) {
                MapNonAwareActionAble curr;
                for (Drawable bullet: new ArrayList<>(drawables)) {
                    if(bullet instanceof MapNonAwareActionAble){
                        curr=(MapNonAwareActionAble) bullet;
                        curr.getNextNonMapAwareAction(path,map,context).performNonMapAwareAction(map,rendererInfo,levelInfo);
                    }
                }
                return null;
            }
        });
    }
    public void updateMoveables(Map map,Path path) {
        for (Position p : map.getDrawableObjects()) {
            Drawable d = map.getDrawableAtPosition(p);
            //TODO bug fixme! https://app.asana.com/0/1168799679896241/1169218700246419
            if (d != null) {
                d.getNextMapAwareAction(
                        path,
                        map,
                        context)
                        .performMapAwareAction(map,getInfo(),levelInfo);
            }
        }
    }
    public void updateSchenario(Schenario schenario, Handler canvasThreadHandler, Map map){
        schenario.trigger(map,context,canvasThreadHandler).performMapAwareAction(map,rendererInfo,levelInfo);
    }

    public RendererInfo getInfo(){
        return rendererInfo==null?(rendererInfo=new RendererInfo(screenSize,tileCountXY,tileSizeXY)):rendererInfo;
    }


    public void scheduleAction(MapAwareAction action, Map map){
        action.performMapAwareAction(map,rendererInfo,levelInfo);
    }


    public Pair<Integer, Integer> getTileSizeXY() {
        return tileSizeXY;
    }

    public Pair<Integer, Integer> getTileCountXY() {
        return tileCountXY;
    }
}
