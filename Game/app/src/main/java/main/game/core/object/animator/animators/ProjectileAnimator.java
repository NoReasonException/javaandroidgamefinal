package main.game.core.object.animator.animators;

import android.content.Context;

import java.util.stream.Collectors;

import main.game.core.info.LevelInfo;
import main.game.core.object.animator.DrawableAnimator;
import main.game.core.utils.BitmapUtils;
import main.menuanimators.elements.Element;

public class ProjectileAnimator extends DrawableAnimator {
    private float angle;
    public ProjectileAnimator(Element element, Context context, LevelInfo levelInfo) {
        super(element, context,levelInfo);
        this.angle=angle;
    }

    public ProjectileAnimator(Element element, Context context, LevelInfo levelInfo,float angle) {
        super(element, context, levelInfo);
        this.angle=angle;
        rotateBitmaps();
    }

    /**
     * rotates the bitmaps by the given angle
     */
    protected void rotateBitmaps() {
        bitmapArray=bitmapArray.stream().map(e->BitmapUtils.rotateBitmap(e,angle)).collect(Collectors.toList());
    }
}
