package uk.ac.reading.sis05kol.engine.game.core.object.drawables.staticdrawables;

import android.content.Context;

import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;
import uk.ac.reading.sis05kol.engine.game.core.object.animator.DrawableAnimator;
import uk.ac.reading.sis05kol.engine.menuactivity.animations.elements.Element;

public class RedPortal extends Drawable {
    public RedPortal(Context context,Position absolutePosition) {
        super(new DrawableAnimator(Element.REDPORTAL,context,0.2f),absolutePosition);
    }
}
