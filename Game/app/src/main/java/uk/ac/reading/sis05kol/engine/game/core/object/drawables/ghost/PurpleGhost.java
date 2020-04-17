package uk.ac.reading.sis05kol.engine.game.core.object.drawables.ghost;

import android.content.Context;

import java.util.Arrays;

import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.object.animator.DrawableAnimator;
import uk.ac.reading.sis05kol.engine.menuanimators.elements.Element;

public class PurpleGhost extends Ghost {
    public PurpleGhost(Context context, LevelInfo levelInfo, Position absolutePosition) {
        super(context, levelInfo, absolutePosition, Arrays.asList(
                new DrawableAnimator(Element.GHOSTPURPLEDOWN, context, levelInfo),
                new DrawableAnimator(Element.GHOSTPURPLEUP, context, levelInfo),
                new DrawableAnimator(Element.GHOSTPURPLERIGHT, context, levelInfo),
                new DrawableAnimator(Element.GHOSTPURPLELEFT, context, levelInfo)),8);
    }
}
