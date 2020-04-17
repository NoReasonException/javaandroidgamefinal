package main.game.core.object.drawables.projectiles;

import android.arch.core.util.Function;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import main.game.core.interfaces.MapAwareAction;
import main.game.core.info.LevelInfo;
import main.game.core.interfaces.MapNonAwareAction;
import main.game.core.interfaces.MapNonAwareActionAble;
import main.game.core.map.Map;
import main.game.core.map.Position;
import main.game.core.map.path.Path;
import main.game.core.object.Drawable;
import main.game.core.object.animator.animators.ProjectileAnimator;
import main.game.core.renderer.BulletSystem;
import main.menuanimators.elements.Element;

public class Bullet extends Drawable implements MapNonAwareActionAble {
    private int speed = 5;
    private float rotation;
    private Position absoluteTarget;
    private String loggerTag="FIREPROJECTILE";
    private boolean toDestruct=false;
    private int state=0;
    private int acceptablePixelDelta=10;
    private BulletSystem bulletSystem;
    private Handler handler=new android.os.Handler(Looper.getMainLooper());

    public static double calculateRotation(Position from, Position to) {
        return (-1f)*((from.getY()-to.getY())) / ((from.getX()-to.getX()));
    }

    public Bullet(Context context, Position absolutePosition, Position absoluteTarget, LevelInfo levelInfo, BulletSystem bulletSystem) {
        super(new ProjectileAnimator(Element.FIREPROJECTILE,
                        context,
                        levelInfo,
                        (float) Math.toDegrees(calculateRotation(absolutePosition, absoluteTarget))), //this not working
                absolutePosition);
        this.rotation = (float) Math.toDegrees(calculateRotation(absolutePosition, absoluteTarget));//this not working
        this.absoluteTarget = absoluteTarget;
        this.bulletSystem=bulletSystem;
        //TODO thereis a bug with bullet system , this is a temponary fix, every bullet has maximum lifespan of 5 seconds!
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setToDestruct(true);
            }
        },5000);

    }

    public void setToDestruct(boolean toDestruct) {
        this.toDestruct = toDestruct;
    }

    private Void onFailCallback(Void v){
        toDestruct=true;
        return null;
    }


    /**
     * calculates the path of the bullet untill the destination
     * @param p         the Path object
     * @param m         the Map object
     * @param context   the Context taken from mGameView
     * @return          a mapAware function
     */
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
    public Function<Drawable, Void> getOnCollisionHandler() {
        return new Function<Drawable, Void>() {
            @Override
            public Void apply(Drawable input) {
                setToDestruct(true);
                return null;
            }
        };
    }
}
