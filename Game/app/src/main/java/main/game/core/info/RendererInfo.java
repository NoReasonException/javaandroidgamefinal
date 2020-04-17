package main.game.core.info;

import android.util.Pair;

/**
 * various information regarding screen and tile sizes needed by Renderer
 */
public class RendererInfo {

    private Pair<Integer,Integer> screenSize;   //screenSize given
    private Pair<Integer,Integer> tileCountXY;  //given only x , y must be calculated
    private Pair<Integer,Integer> tileSizeXY;   //must be calculated
    private Pair<Integer,Integer> initialPositionOfObjectOffset;

    public RendererInfo(Pair<Integer, Integer> screenSize, Pair<Integer, Integer> tileCountXY, Pair<Integer, Integer> tileSizeXY) {
        this.screenSize = screenSize;
        this.tileCountXY = tileCountXY;
        this.tileSizeXY = tileSizeXY;
        this.initialPositionOfObjectOffset=new Pair<Integer,Integer>(Double.valueOf(this.tileSizeXY.first*0.1).intValue(),Double.valueOf(this.tileSizeXY.second*0.1).intValue());
    }

    public Pair<Integer, Integer> getScreenSize() {
        return screenSize;
    }

    public Pair<Integer, Integer> getTileCountXY() {
        return tileCountXY;
    }

    public Pair<Integer, Integer> getTileSizeXY() {
        return tileSizeXY;
    }

    public Pair<Integer, Integer> getInitialPositionOfObjectOffset() {
        return initialPositionOfObjectOffset;
    }
}
