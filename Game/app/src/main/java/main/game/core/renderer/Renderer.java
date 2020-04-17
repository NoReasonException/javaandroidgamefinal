package main.game.core.renderer;

import android.arch.core.util.Function;
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

import uk.ac.reading.sis05kol.engine.R;
import main.game.core.info.LevelInfo;
import main.game.core.info.RendererInfo;
import main.game.core.interfaces.MapNonAwareActionAble;
import main.game.core.map.Map;
import main.game.core.map.Position;
import main.game.core.map.path.Path;
import main.game.core.object.Drawable;
import main.game.core.schenario.Schenario;
import main.game.core.utils.CoordinateSystemUtils;

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

    /**
     * calculates the amount of tiles to fit the screen
     * @param screenSize        the size of screen in pixels
     * @param tileCountX         the X tile count
     * @return the amount of tiles in a pair
     */
    public Pair<Integer,Integer> tileCountXYCalculate(Pair<Integer,Integer>screenSize,int tileCountX){
        float x_To_yRatio=screenSize.second/((1.0f)*screenSize.first);
        Pair<Integer,Integer>tileCountXY=new Pair<>(tileCountX,Double.valueOf(tileCountX*x_To_yRatio).intValue());
        Log.i(loggerTag,"Renderer Initialization : screenSize "+screenSize+" and requested tilecountX: "+tileCountX+" produced tileCountXY: "+tileCountXY);
        return tileCountXY;
    }

    /**
     * calculates the size of the given tiles given the tilecount and the screensize
     * @param screenSize        the screenSize in pixels as pair
     * @param tileCountXY       the amount of tiles in pair
     * @return                  a pair with the dimensions of a single tile
     */
    public Pair<Integer,Integer> tileSizeXYCalculate(Pair<Integer,Integer>screenSize,Pair<Integer,Integer>tileCountXY){
        float screen_x_to_x_ratio=screenSize.first/((1.0f)*tileCountXY.first);
        float screen_y_to_y_ratio=screenSize.second/((1.0f)*tileCountXY.second);
        Pair<Integer,Integer>tileSizeXY=new Pair<Integer,Integer>(Double.valueOf(screen_x_to_x_ratio).intValue(),Double.valueOf(screen_y_to_y_ratio).intValue());
        Log.i(loggerTag,"Renderer Initialization : screenSize "+screenSize+" and requested tileSizeXY: "+tileCountXY+" produced tileSizeXY: "+tileSizeXY);
        return tileSizeXY;
    }

    /**
     * draws the background
     * @param canvas            The Canvas Object
     * @param backgroundTile    The Background Tile
     */
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

    /**
     * returns a random grass tile to draw on screen
     * @param screenPosition        the screenPosition as TILEPOSITION
     * @param max                   the maximum amount of available tiles (in grass is 4)
     * @return
     */
    private Integer genRandomGrassTileIndex(Pair<Integer,Integer>screenPosition, int max){
        int newIndex=Math.abs(random.nextInt(max));
        randomGrassTileIndex.put(screenPosition,newIndex);
        return newIndex;

    }

    /**
     * draws the defined path
     * @param canvas            The Canvas Object
     * @param backgroundTile    The BackgroundTile list of bitmaps
     * @param path              The defined Path
     */
    public void drawPath(Canvas canvas, List<Bitmap> backgroundTile, Path path){

        _drawPath(canvas,backgroundTile,path.getFirst());
    }

    /**
     * recursive utillity , please see @drawPath
     */
    private void _drawPath(Canvas canvas, List<Bitmap> backgroundTile, Path.Node currNode){
        canvas.drawBitmap(backgroundTile.get(getSandTileIndex(currNode.getPosition(),backgroundTile.size())),currNode.getPosition().getX()*tileSizeXY.first,currNode.getPosition().getY()*tileSizeXY.second,null);
        for (Path.Node n :currNode.getLinks()) {
            _drawPath(canvas,backgroundTile,n);
        }
    }

    /**
     * generates a random tile index
     * @param tilePosition the tiles position
     * @param max          the maximum amount of sand tiles(is 2 for sand)
     * @return             a random but valid index
     */
    private Integer getSandTileIndex(Position tilePosition, int max){
        if(randomSandTileIndex.containsKey(tilePosition)){
            return randomSandTileIndex.get(tilePosition);
        }else{
            int newIndex=Math.abs(random.nextInt(max));
            randomSandTileIndex.put(tilePosition,newIndex);
            return newIndex;

        }
    }

    /**
     * Draws the given map
     * @param canvas        The Canvas
     * @param map           The Map
     */
    public void drawMap(Canvas canvas, Map map){
        for (Position p: map.getDrawableObjects()) {
            Drawable d=map.getDrawableAtPosition(p);
            canvas.drawBitmap(d.getBitmap().first,d.getAbsolutePosition().getX(),d.getAbsolutePosition().getY(),d.getBitmap().second);
        }
    }

    /**
     * draws the subscribed bullets , @see BulletSystem
     * @param canvas            The Canvas Object
     * @param bulletSystem      The BulletSystem
     */
    public void drawBullets(Canvas canvas,BulletSystem bulletSystem){
        bulletSystem.syncWithBulletSystem(new Function<ArrayList<Drawable>, Void>() {
            @Override
            public Void apply(ArrayList<Drawable> drawables) {
                for (Drawable bullet: new ArrayList<>(drawables)) {
                    canvas.drawBitmap(bullet.getBitmap().first,bullet.getAbsolutePosition().getX(),bullet.getAbsolutePosition().getY(),bullet.getBitmap().second);
                }
                return null;
            }
        });
    }

    /**
     * updates the bullets trajectory
     * @param map           The Map Object
     * @param path          The Path Object
     * @param bulletSystem  The Bullets System
     */
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

    /**
     * update all drawables
     * @param map   The Map Object
     * @param path  The Path Object
     */
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

    /**
     * triggers the scenario in a separate thread
     * @param schenario                 The schenario Object
     * @param canvasThreadHandler       The CanvasThreadHandler object
     * @param map                       The Map
     */
    public void updateSchenario(Schenario schenario, Handler canvasThreadHandler, Map map){
        canvasThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                //TODO this is ugly , refactor it
                schenario.trigger(map,context,canvasThreadHandler).performMapAwareAction(map,rendererInfo,levelInfo);
            }
        });

    }


    public RendererInfo getInfo(){
        return rendererInfo==null?(rendererInfo=new RendererInfo(screenSize,tileCountXY,tileSizeXY)):rendererInfo;
    }

    public Pair<Integer, Integer> getTileSizeXY() {
        return tileSizeXY;
    }

    public Pair<Integer, Integer> getTileCountXY() {
        return tileCountXY;
    }
}
