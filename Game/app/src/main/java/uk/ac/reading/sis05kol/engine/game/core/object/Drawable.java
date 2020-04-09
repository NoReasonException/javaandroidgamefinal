package uk.ac.reading.sis05kol.engine.game.core.object;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

import uk.ac.reading.sis05kol.engine.game.core.interfaces.Actionable;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.object.animator.DrawableAnimator;

abstract public class Drawable implements Actionable {
    protected final int DEFAULT_ANIMATOR=0;
    protected Position absolutePosition;
    protected List<DrawableAnimator> animators =new ArrayList<>();


    public Drawable(DrawableAnimator animator,Position absolutePosition) {
        this.animators.add(animator);
        this.absolutePosition=absolutePosition;
    }
    public Drawable(List<DrawableAnimator> animators,Position absolutePosition) {
        this.animators =animators;
        this.absolutePosition=absolutePosition;
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
}
