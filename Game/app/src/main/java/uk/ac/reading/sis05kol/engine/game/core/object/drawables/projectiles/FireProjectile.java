package uk.ac.reading.sis05kol.engine.game.core.object.drawables.projectiles;

import android.arch.core.util.Function;
import android.content.Context;

import uk.ac.reading.sis05kol.engine.game.core.interfaces.MapAwareAction;
import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.MapNonAwareAction;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.MapNonAwareActionAble;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.map.path.Path;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;
import uk.ac.reading.sis05kol.engine.game.core.object.animator.animators.ProjectileAnimator;
import uk.ac.reading.sis05kol.engine.game.core.renderer.BulletSystem;
import uk.ac.reading.sis05kol.engine.menuactivity.animations.elements.Element;

public class FireProjectile extends Drawable implements MapNonAwareActionAble {
    private int speed = 5;
    private float rotation;
    private Position absoluteTarget;
    private String loggerTag="FIREPROJECTILE";
    private boolean toDestruct=false;
    private int state=0;
    private int acceptablePixelDelta=10;
    private BulletSystem bulletSystem;

    public static double calculateRotation(Position from, Position to) {
        return (-1f)*((from.getY()-to.getY())) / ((from.getX()-to.getX()));
    }

    public FireProjectile(Context context, Position absolutePosition, Position absoluteTarget, LevelInfo levelInfo, BulletSystem bulletSystem) {
        super(new ProjectileAnimator(Element.FIREPROJECTILE,
                        context,
                        levelInfo,
                        (float) Math.toDegrees(calculateRotation(absolutePosition, absoluteTarget))), //this not working
                absolutePosition);
        this.rotation = (float) Math.toDegrees(calculateRotation(absolutePosition, absoluteTarget));//this not working
        this.absoluteTarget = absoluteTarget;
        this.bulletSystem=bulletSystem;

    }

    private Void onFailCallback(Void v){
        toDestruct=true;
        return null;
    }


    @Override
    public MapNonAwareAction getNextNonMapAwareAction(Path p, Map m, Context context) {
        int nextX0=getAbsolutePosition().getX();
        int nextY0=getAbsolutePosition().getY();

        if (toDestruct) {
            return MapNonAwareAction.buildDeleteMeAction(null, null,this,bulletSystem);
        }

        int dx = absolutePosition.getX() - absoluteTarget.getX();
        int dy = absolutePosition.getY() - absoluteTarget.getY();

        int distancex = Math.abs(absolutePosition.getX() - absoluteTarget.getX());
        int distancey = Math.abs(absolutePosition.getY() - absoluteTarget.getY());

        if(distancex<acceptablePixelDelta&&distancey<acceptablePixelDelta){
            return MapNonAwareAction.buildDeleteMeAction(null,null,this,bulletSystem);
        }
        if(dx>0)nextX0-=speed;
        else nextX0+=speed;
        if(dy>0)nextY0-=speed;
        else nextY0+=speed;

        Position nextPosition = getAbsolutePosition().setX(nextX0).setY(nextY0);


        return MapNonAwareAction.buildMoveAction(null,null,nextPosition,this);
    }

    @Override
    public MapAwareAction getNextMapAwareAction(Path p, Map map, Context context) {
        return MapAwareAction.buildIdleAction(null,null);
    }

    @Override
    public Function<Void, Void> getOnCollisionHandler() {
        return new Function<Void, Void>() {
            @Override
            public Void apply(Void input) {
                toDestruct=true;
                return null;
            }
        };
    }
}
