package main.game.core.object.drawables.monster;

import android.content.Context;

import java.util.Arrays;

import main.game.core.info.LevelInfo;
import main.game.core.map.Position;
import main.game.core.object.animator.DrawableAnimator;
import main.menuanimators.elements.Element;

public class GreenMonster extends Monster {
    public GreenMonster(Context context, LevelInfo levelInfo, Position absolutePosition) {
        super(context, levelInfo, absolutePosition,
                        Arrays.asList(
                                new DrawableAnimator(Element.GHOSTGREENDOWN, context, levelInfo),
                                new DrawableAnimator(Element.GHOSTGREENUP, context, levelInfo),
                                new DrawableAnimator(Element.GHOSTGREENRIGHT, context, levelInfo),
                                new DrawableAnimator(Element.GHOSTGREENLEFT, context, levelInfo)),7,3);
    }
}

