package uk.ac.reading.sis05kol.engine.game.core.object.drawables.projectiles;

import android.arch.core.util.Function;
import android.content.Context;

import uk.ac.reading.sis05kol.engine.game.core.interfaces.actions.Action;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.map.path.Path;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;
import uk.ac.reading.sis05kol.engine.game.core.object.animator.DrawableAnimator;
import uk.ac.reading.sis05kol.engine.game.core.object.animator.animators.ProjectileAnimator;
import uk.ac.reading.sis05kol.engine.menuactivity.animations.elements.Element;

public class FireProjectile extends Drawable {
    private int speed;
    private float rotation;
    public FireProjectile(Context context, Position absolutePosition,int speed,float rotation) {
        super(new ProjectileAnimator(Element.FIREPROJECTILE,context,0.6f,rotation),absolutePosition);
        this.speed=speed;
        this.rotation=rotation;
    }


    @Override
    public Action getNextAction(Path p, Context context,Function<Position, Position> fromAbsoluteToTileConversion, Function<Position, Position> fromTileToAbsoluteConversion) {
        return Action.buildIdleAction();
    }
}
