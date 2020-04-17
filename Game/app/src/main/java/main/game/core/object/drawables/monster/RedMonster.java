package main.game.core.object.drawables.monster;

import android.content.Context;

import java.util.Arrays;

import main.game.core.info.LevelInfo;
import main.game.core.map.Position;
import main.game.core.object.animator.DrawableAnimator;
import main.menuanimators.elements.Element;

/**
 * the red monster
 */
public class RedMonster extends Monster {
    public RedMonster(Context context, LevelInfo levelInfo, Position absolutePosition) {
        super(context, levelInfo, absolutePosition,Arrays.asList(
                new DrawableAnimator(Element.GHOSTREDDOWN, context, levelInfo),
                new DrawableAnimator(Element.GHOSTREDUP, context, levelInfo),
                new DrawableAnimator(Element.GHOSTREDRIGHT, context, levelInfo),
                new DrawableAnimator(Element.GHOSTREDLEFT, context, levelInfo)),9,5);
    }
}
