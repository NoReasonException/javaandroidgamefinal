package main.game.core.info;

/**
 * various information about the screen size and the scale of the bitmaps
 */
public class LevelInfo {
    int tileCountX;
    float scale;
    public LevelInfo(int tileCountX, float scale) {
        this.tileCountX = tileCountX;
        this.scale = scale;

    }

    public int getTileCountX() {
        return tileCountX;
    }

    public float getScale() {
        return scale;
    }
}
