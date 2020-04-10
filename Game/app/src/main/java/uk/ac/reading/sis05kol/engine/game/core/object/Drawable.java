package uk.ac.reading.sis05kol.engine.game.core.object;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import uk.ac.reading.sis05kol.engine.game.core.interfaces.MapAwareActionAble;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.object.animator.DrawableAnimator;

abstract public class Drawable implements MapAwareActionAble {


    protected static int ID=0;
    protected int myID=0;
    protected final int DEFAULT_ANIMATOR=0;
    protected Position absolutePosition;
    protected List<DrawableAnimator> animators =new ArrayList<>();


    public Drawable(DrawableAnimator animator,Position absolutePosition) {
        this.animators.add(animator);
        this.absolutePosition=absolutePosition;
        this.myID=(ID+=1);
    }
    public Drawable(List<DrawableAnimator> animators,Position absolutePosition) {
        this.animators =animators;
        this.absolutePosition=absolutePosition;
        this.myID=(ID+=1);
    }

    @Override
    public String toString() {
        return "Drawable{" +
                "element=" + animators +
                '}';
    }

    public Position getAbsolutePosition() {
        return absolutePosition;
    }

    /**
     * Assuming that every Drawable as AT LEAST one drawable
     * @return
     */
    public Bitmap getBitmap(){
        return animators.get(DEFAULT_ANIMATOR).getBitmap();
    }

    public void setAbsolutePosition(Position aposition) {
        this.absolutePosition = aposition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Drawable drawable = (Drawable) o;
        return myID == drawable.myID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(myID);
    }
}
