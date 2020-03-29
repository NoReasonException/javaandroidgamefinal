package uk.ac.reading.sis05kol.engine.game.core.object;

import android.animation.Animator;
import android.graphics.Bitmap;

import java.util.Objects;

import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.object.animator.DrawableAnimator;

public class Drawable {
    private Position            position;
    private DrawableAnimator    animator;


    public Drawable(Position p, DrawableAnimator animator) {
        this.position = p;
        this.animator=animator;
    }

    public Position getPosition() {
        return position;
    }

    public DrawableAnimator getAnimator() { return animator; }

    public void setPosition(Position p) {
        this.position = p;
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
