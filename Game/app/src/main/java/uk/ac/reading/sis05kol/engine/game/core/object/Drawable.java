package uk.ac.reading.sis05kol.engine.game.core.object;

import android.graphics.Bitmap;

import java.util.Objects;

import uk.ac.reading.sis05kol.engine.game.core.map.Position;

public class Drawable {
    private Position position;
    private Bitmap bitmap;

    public Drawable(Position p,Bitmap bitmap) {
        this.position = p;
        this.bitmap=bitmap;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position p) {
        this.position = p;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drawable drawable = (Drawable) o;
        return Objects.equals(position, drawable.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public String toString() {
        return "Drawable{" +
                "position=" + position +
                '}';
    }
}
