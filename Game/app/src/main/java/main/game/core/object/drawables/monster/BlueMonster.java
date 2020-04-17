package main.game.core.object.drawables.monster;

import android.content.Context;

import java.util.Arrays;

import main.game.core.info.LevelInfo;
import main.game.core.map.Position;
import main.game.core.object.animator.DrawableAnimator;
import main.menuanimators.elements.Element;

public class BlueMonster extends Monster {
    public BlueMonster(Context context, LevelInfo levelInfo, Position absolutePosition) {
        super(context, levelInfo, absolutePosition,
                Arrays.asList(
                        new DrawableAnimator(Element.GHOSTBLUEDOWN, context, levelInfo),
                        new DrawableAnimator(Element.GHOSTBLUEUP, context, levelInfo),
                        new DrawableAnimator(Element.GHOSTBLUERIGHT, context, levelInfo),
                        new DrawableAnimator(Element.GHOSTBLUELEFT, context, levelInfo)),5,1);
    }
}
