package uk.ac.reading.sis05kol.engine.game.core.object.animator.animators;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.util.function.Function;
import java.util.stream.Collectors;

import uk.ac.reading.sis05kol.engine.game.core.object.animator.DrawableAnimator;
import uk.ac.reading.sis05kol.engine.game.core.utils.BitmapUtils;
import uk.ac.reading.sis05kol.engine.menuactivity.animations.elements.Element;

public class ProjectileAnimator extends DrawableAnimator {
    private float angle;
    public ProjectileAnimator(Element element, Context context,float angle) {
        super(element, context);
        this.angle=angle;
    }

    public ProjectileAnimator(Element element, Context context, float scale,float angle) {
        super(element, context, scale);
        Log.e("CCC","executed at angle "+angle);
        this.angle=angle;
        rotateBitmaps();
    }

    protected void rotateBitmaps() {
        bitmapArray=bitmapArray.stream().map(e->BitmapUtils.rotateBitmap(e,angle)).collect(Collectors.toList());
    }
}
