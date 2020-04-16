package uk.ac.reading.sis05kol.engine.game.core.object.drawables.portals;

import android.content.Context;

import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.object.animator.DrawableAnimator;
import uk.ac.reading.sis05kol.engine.menuanimators.elements.Element;

public class BluePortal extends Portal {
    public BluePortal(Context context, Position absolutePosition, LevelInfo levelInfo) {
        super(context,absolutePosition,levelInfo,new DrawableAnimator(Element.BLUEPORTAL,context,levelInfo));
    }
}
