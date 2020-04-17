package main.game.core.object.drawables.monster;

import android.content.Context;

import java.util.Arrays;

import main.game.core.info.LevelInfo;
import main.game.core.map.Position;
import main.game.core.object.animator.DrawableAnimator;
import main.menuanimators.elements.Element;

/**
 * the purple monster
 */
public class PurpleMonster extends Monster {
    public PurpleMonster(Context context, LevelInfo levelInfo, Position absolutePosition) {
        super(context, levelInfo, absolutePosition, Arrays.asList(
                new DrawableAnimator(Element.GHOSTPURPLEDOWN, context, levelInfo),
                new DrawableAnimator(Element.GHOSTPURPLEUP, context, levelInfo),
                new DrawableAnimator(Element.GHOSTPURPLERIGHT, context, levelInfo),
                new DrawableAnimator(Element.GHOSTPURPLELEFT, context, levelInfo)),12,8);
    }
}
