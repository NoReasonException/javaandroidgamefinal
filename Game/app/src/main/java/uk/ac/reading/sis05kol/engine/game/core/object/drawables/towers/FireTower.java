package uk.ac.reading.sis05kol.engine.game.core.object.drawables.towers;

import android.arch.core.util.Function;
import android.content.Context;
import android.graphics.Bitmap;

import java.util.Arrays;
import java.util.List;

import uk.ac.reading.sis05kol.engine.game.core.interfaces.actions.Action;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.map.path.Path;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;
import uk.ac.reading.sis05kol.engine.game.core.object.animator.DrawableAnimator;
import uk.ac.reading.sis05kol.engine.game.core.object.drawables.projectiles.FireProjectile;
import uk.ac.reading.sis05kol.engine.menuactivity.animations.elements.Element;

public class FireTower extends Drawable {
    private static final int IDLEINDEX=0;
    private static final int ATTACKINDEX=1;

    int testState=0;
    int testMaxState=599;

    public FireTower(Context context, Position absolutePosition) {
        super(Arrays.asList(
                new DrawableAnimator(Element.FIREIDLE,context,0.16f),
                new DrawableAnimator(Element.FIREATTACK,context,0.16f)
        ), absolutePosition);
    }

    @Override
    public Action getNextAction(Path p,Context context, Function<Position, Position> fromAbsoluteToTileConversion, Function<Position, Position> fromTileToAbsoluteConversion) {
        testState=(testState+1)%testMaxState;
        if(testState==42){
            return Action.buildEmplaceObjectAction(
                    new FireProjectile(context,fromTileToAbsoluteConversion
                            .apply(new Position(2,5)),2,45),
                    fromAbsoluteToTileConversion,
                    fromTileToAbsoluteConversion
            );
        }
        return Action.buildIdleAction();
    }

    @Override
    public Bitmap getBitmap() {
        return animators.get(ATTACKINDEX).getBitmap();
    }
}
