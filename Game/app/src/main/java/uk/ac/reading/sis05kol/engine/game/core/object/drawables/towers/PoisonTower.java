package uk.ac.reading.sis05kol.engine.game.core.object.drawables.towers;

import android.content.Context;

import java.util.Arrays;

import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.object.animator.DrawableAnimator;
import uk.ac.reading.sis05kol.engine.game.core.renderer.BulletSystem;
import uk.ac.reading.sis05kol.engine.menuanimators.elements.Element;

public class PoisonTower extends Tower {
    public PoisonTower(Context context, LevelInfo levelInfo, Position absolutePosition, BulletSystem bulletSystem) {
        super(context, Arrays.asList(
                new DrawableAnimator(Element.POISONIDLE, context, levelInfo),
                new DrawableAnimator(Element.POISONATTACK, context, levelInfo)
        ), levelInfo, absolutePosition, bulletSystem,0.4);
    }
}
