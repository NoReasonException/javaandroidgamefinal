package uk.ac.reading.sis05kol.engine.game.core.object.drawables.dynamicdrawables;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import uk.ac.reading.sis05kol.engine.game.core.interfaces.Moveable;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.map.path.Path;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;
import uk.ac.reading.sis05kol.engine.game.core.object.animator.DrawableAnimator;
import uk.ac.reading.sis05kol.engine.game.core.renderer.Renderer;
import uk.ac.reading.sis05kol.engine.menuactivity.animations.elements.Element;

public class BlueGhost extends Drawable implements Moveable {
    private static final int DOWNINDEX=0;
    private static final int UPINDEX=1;
    private static final int RIGHTINDEX=2;
    private static final int LEFTINDEX=3;
    private int currentIndex=RIGHTINDEX;

    public BlueGhost(Context context, Position absolutePosition) {
        super(Arrays.asList(
                new DrawableAnimator(Element.GHOSTBLUEDOWN, context,0.2f),
                new DrawableAnimator(Element.GHOSTBLUEUP, context,0.2f),
                new DrawableAnimator(Element.GHOSTBLUERIGHT, context,0.2f),
                new DrawableAnimator(Element.GHOSTBLUELEFT, context,0.2f)),absolutePosition);
    }

    @Override
    public Bitmap getBitmap() {
        return animators.get(currentIndex).getBitmap();
    }

    @Override
    public Position nextMove(Path p, Function<Position, Position> fromAbsoluteToTileConversion, Function<Position, Position> fromTileToAbsoluteConversion) {
        return absolutePosition.setX(absolutePosition.getX()+4);
    }
}
