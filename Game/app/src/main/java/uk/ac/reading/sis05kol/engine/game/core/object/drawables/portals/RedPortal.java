package uk.ac.reading.sis05kol.engine.game.core.object.drawables.portals;

import android.content.Context;

import uk.ac.reading.sis05kol.engine.game.core.interfaces.actions.Action;
import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.map.path.Path;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;
import uk.ac.reading.sis05kol.engine.game.core.object.animator.DrawableAnimator;
import uk.ac.reading.sis05kol.engine.menuactivity.animations.elements.Element;

public class RedPortal extends Drawable {
    public RedPortal(Context context, Position absolutePosition, LevelInfo levelInfo) {
        super(new DrawableAnimator(Element.REDPORTAL,context,levelInfo),absolutePosition);
    }

    @Override
    public Action getNextAction(Path p, Map map, Context context) {
        return Action.buildIdleAction(null,null);
    }
}
