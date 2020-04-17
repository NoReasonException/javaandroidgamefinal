package uk.ac.reading.sis05kol.engine.game.core.object.drawables.towers;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import uk.ac.reading.sis05kol.engine.game.core.interfaces.MapAwareAction;
import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.map.path.Path;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;
import uk.ac.reading.sis05kol.engine.game.core.object.animator.DrawableAnimator;
import uk.ac.reading.sis05kol.engine.game.core.object.drawables.ghost.Ghost;
import uk.ac.reading.sis05kol.engine.game.core.object.drawables.projectiles.Bullet;
import uk.ac.reading.sis05kol.engine.game.core.renderer.BulletSystem;
import uk.ac.reading.sis05kol.engine.game.core.utils.CoordinateSystemUtils;
import uk.ac.reading.sis05kol.engine.menuanimators.elements.Element;

public class FireTower extends Tower {
    public FireTower(Context context, LevelInfo levelInfo, Position absolutePosition, BulletSystem bulletSystem) {
        super(context, Arrays.asList(
                new DrawableAnimator(Element.FIREIDLE, context, levelInfo),
                new DrawableAnimator(Element.FIREATTACK, context, levelInfo)
        ), levelInfo, absolutePosition, bulletSystem,0.1);
    }
}
