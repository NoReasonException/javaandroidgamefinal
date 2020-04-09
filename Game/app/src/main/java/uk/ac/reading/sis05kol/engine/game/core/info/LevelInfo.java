package uk.ac.reading.sis05kol.engine.game.core.info;

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
