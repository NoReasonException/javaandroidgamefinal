package uk.ac.reading.sis05kol.engine.game.core.object.drawables.staticdrawables;

import android.content.Context;

import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;
import uk.ac.reading.sis05kol.engine.game.core.object.animator.DrawableAnimator;
import uk.ac.reading.sis05kol.engine.menuactivity.animations.elements.Element;

public class BluePortal extends Drawable {
    public BluePortal(Context context,Position absolutePosition) {
        super(new DrawableAnimator(Element.BLUEPORTAL,context,0.2f),absolutePosition);
    }

}
