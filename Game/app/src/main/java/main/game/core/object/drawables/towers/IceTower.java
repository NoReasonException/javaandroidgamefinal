package main.game.core.object.drawables.towers;

import android.content.Context;

import java.util.Arrays;

import main.game.core.info.LevelInfo;
import main.game.core.map.Position;
import main.game.core.object.animator.DrawableAnimator;
import main.game.core.renderer.BulletSystem;
import main.menuanimators.elements.Element;

public class IceTower extends Tower {
    public IceTower(Context context, LevelInfo levelInfo, Position absolutePosition, BulletSystem bulletSystem) {
        super(context, Arrays.asList(
                new DrawableAnimator(Element.ICEIDLE, context, levelInfo),
                new DrawableAnimator(Element.ICEATTACK, context, levelInfo)
        ), levelInfo, absolutePosition, bulletSystem,0.8,15);
    }
}
