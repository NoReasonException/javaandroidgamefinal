package uk.ac.reading.sis05kol.engine.game.core.object.drawables.portals;

import android.content.Context;

import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.MapAwareAction;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.map.path.Path;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;
import uk.ac.reading.sis05kol.engine.game.core.object.animator.DrawableAnimator;
import uk.ac.reading.sis05kol.engine.menuactivity.animations.elements.Element;

public class Portal extends Drawable {
    public Portal(Context context, Position absolutePosition, LevelInfo levelInfo,DrawableAnimator animator) {
        super(animator,absolutePosition);
    }

    @Override
    public MapAwareAction getNextMapAwareAction(Path p, Map map, Context context) {
        return MapAwareAction.buildIdleAction(null,null);
    }
}
