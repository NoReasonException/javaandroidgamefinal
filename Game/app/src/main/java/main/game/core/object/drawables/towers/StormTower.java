package main.game.core.object.drawables.towers;

import android.content.Context;

import java.util.Arrays;

import main.game.core.info.LevelInfo;
import main.game.core.map.Position;
import main.game.core.object.animator.DrawableAnimator;
import main.game.core.renderer.BulletSystem;
import main.menuanimators.elements.Element;

/**
 * StormTower
 */
public class StormTower extends Tower {
    public StormTower(Context context, LevelInfo levelInfo, Position absolutePosition, BulletSystem bulletSystem) {
        super(context, Arrays.asList(
                new DrawableAnimator(Element.STORMIDLE, context, levelInfo),
                new DrawableAnimator(Element.STORMIDLE, context, levelInfo)
        ), levelInfo, absolutePosition, bulletSystem,0.6,9);
    }
}
