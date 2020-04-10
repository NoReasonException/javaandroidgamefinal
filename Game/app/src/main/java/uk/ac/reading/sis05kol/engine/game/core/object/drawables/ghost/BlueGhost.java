package uk.ac.reading.sis05kol.engine.game.core.object.drawables.ghost;

import android.content.Context;

import java.util.Arrays;
import java.util.List;

import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.object.animator.DrawableAnimator;
import uk.ac.reading.sis05kol.engine.menuactivity.animations.elements.Element;

public class BlueGhost extends Ghost {
    public BlueGhost(Context context, LevelInfo levelInfo, Position absolutePosition) {
        super(context, levelInfo, absolutePosition,
                Arrays.asList(
                        new DrawableAnimator(Element.GHOSTBLUEDOWN, context, levelInfo),
                        new DrawableAnimator(Element.GHOSTBLUEUP, context, levelInfo),
                        new DrawableAnimator(Element.GHOSTBLUERIGHT, context, levelInfo),
                        new DrawableAnimator(Element.GHOSTBLUELEFT, context, levelInfo)));
    }
}
