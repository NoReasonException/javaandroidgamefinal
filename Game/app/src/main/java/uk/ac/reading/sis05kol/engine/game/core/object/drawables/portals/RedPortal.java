package uk.ac.reading.sis05kol.engine.game.core.object.drawables.portals;

import android.arch.core.util.Function;
import android.content.Context;

import uk.ac.reading.sis05kol.engine.game.core.interfaces.actions.Action;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.map.path.Path;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;
import uk.ac.reading.sis05kol.engine.game.core.object.animator.DrawableAnimator;
import uk.ac.reading.sis05kol.engine.menuactivity.animations.elements.Element;

public class RedPortal extends Drawable {
    public RedPortal(Context context,Position absolutePosition) {
        super(new DrawableAnimator(Element.REDPORTAL,context,0.2f),absolutePosition);
    }

    @Override
    public Action getNextAction(Path p,Context context, Function<Position, Position> fromAbsoluteToTileConversion, Function<Position, Position> fromTileToAbsoluteConversion) {
        return Action.buildIdleAction();
    }
}
