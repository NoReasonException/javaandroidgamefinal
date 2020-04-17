package main.game.core.utils;

import android.util.Pair;

import java.util.Random;

import main.game.core.map.Position;


/***
 * WARNING! DO NOT CALL .getInstance before initialization of Renderer
 */
public class CoordinateSystemUtils {

    private Pair<Integer,Integer> tileCountXY;  //given only x , y must be calculated
    private Pair<Integer,Integer> tileSizeXY;   //must be calculated
    private Random random =new Random();

    private static CoordinateSystemUtils instance;

    /**
     * convert from absolute to Tile positional systems
     * @param absolutePosition
     * @return the tile position , @note there is information loss
     */
    public Position fromAbsoluteToTilePosition(Position absolutePosition){
        return new Position(
                absolutePosition.getX()/tileSizeXY.first,
                absolutePosition.getY()/tileSizeXY.second
        );
    }
    /**
     * convert from tile to absolute positional systems
     * @param tilePosition
     * @return the absolute position ,@note there is information loss
     */
    public Position fromTileToAbsolutePosition(Position tilePosition){
        return new Position(
                tilePosition.getX()*tileSizeXY.first,
                tilePosition.getY()*tileSizeXY.second
        );
    }
    //deprecated
    public Position fromTileToAbsolutePositionWithRedundancy(Position tilePosition){
        return new Position(
                tilePosition.getX()*tileSizeXY.first+random.nextInt(15),
                tilePosition.getY()*tileSizeXY.second+random.nextInt(15)
        );
    }
    public static CoordinateSystemUtils initInstance(Pair<Integer, Integer> tileCountXY, Pair<Integer, Integer> tileSizeXY) {
        return instance==null?(instance=new CoordinateSystemUtils(tileCountXY,tileSizeXY)):instance;
    }
    public static CoordinateSystemUtils getInstance(){
        return instance;
    }
    private CoordinateSystemUtils(Pair<Integer, Integer> tileCountXY, Pair<Integer, Integer> tileSizeXY) {
        this.tileCountXY = tileCountXY;
        this.tileSizeXY = tileSizeXY;
    }
}
