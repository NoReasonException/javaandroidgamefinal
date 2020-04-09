package uk.ac.reading.sis05kol.engine.game.core.object.drawables.projectiles;

import android.arch.core.util.Function;
import android.content.Context;
import android.util.Log;

import uk.ac.reading.sis05kol.engine.game.core.interfaces.actions.Action;
import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.map.path.Path;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;
import uk.ac.reading.sis05kol.engine.game.core.object.animator.animators.ProjectileAnimator;
import uk.ac.reading.sis05kol.engine.game.core.utils.CoordinateSystemUtils;
import uk.ac.reading.sis05kol.engine.menuactivity.animations.elements.Element;

public class FireProjectile extends Drawable {
    private int speed = 1;
    private float rotation;
    private Position target;
    private Function<Integer, Integer> ballisticFunction;
    private String loggerTag="FIREPROJECTILE";
    private boolean toDestruct=false;
    private int state=0;
    public static double calculateRotation(Position from, Position to) {
        return (-1f)*((from.getY()-to.getY())) / ((from.getX()-to.getX()));
    }

    public FireProjectile(Context context,Position absolutePosition, Position target,LevelInfo levelInfo) {
        super(new ProjectileAnimator(Element.FIREPROJECTILE,
                        context,
                        levelInfo,
                        (float) Math.toDegrees(calculateRotation(absolutePosition, target))),
                absolutePosition);
        this.rotation = (float) Math.toDegrees(calculateRotation(absolutePosition, target));
        this.target = target;
        this.ballisticFunction = generateBallisticFunction();


        Log.i(loggerTag,"from "+absolutePosition.toString() +" to "+target.toString()+" with m="+rotation);
    }


    private Function<Integer, Integer> generateBallisticFunction() {
        return new Function<Integer, Integer>() {
            public double slope = calculateRotation(absolutePosition, target);
            public int x0 = absolutePosition.getX();
            public int y0 = absolutePosition.getY();

            @Override
            public Integer apply(Integer input) {
                return Double.valueOf(slope * (input - x0) + y0).intValue();
            }
        };
    }

    private Void onFailCallback(Void v){
        toDestruct=true;
        return null;
    }

    public Function<Integer, Integer> getBallisticFunction() {
        return ballisticFunction;
    }

    @Override
    public Action getNextAction(Path p, Map map, Context context) {
        state=(state+1)%10;
        if(state%2==0) {
            if (toDestruct) {
                return Action.buildDeleteMeAction(null, null, CoordinateSystemUtils.getInstance().fromAbsoluteToTilePosition(absolutePosition), this);
            }
            int nextX0 = absolutePosition.getX();
            int nextY0 = absolutePosition.getY();

            int xC = absolutePosition.getX() / (target.getX() == 0 ? 1 : target.getX());
            int yC = absolutePosition.getY() / (target.getX() == 0 ? 1 : target.getX());

            if (target.getX() < absolutePosition.getX()) {
                nextX0 = getAbsolutePosition().getX() - speed;//*xC;

            } else if (target.getX() >= absolutePosition.getX()) {
                nextX0 = getAbsolutePosition().getX() + speed;//*xC;
            }

            if (target.getY() < absolutePosition.getY()) {
                nextY0 = getAbsolutePosition().getY() - speed;//*yC;

            } else if (target.getY() >= absolutePosition.getY()) {
                nextY0 = getAbsolutePosition().getY() + speed;//*yC;
            }

            //int nextY0 = getBallisticFunction().apply(nextX0);
            Position nextPosition = getAbsolutePosition().setX(nextX0).setY(nextY0);
            if (xC == absolutePosition.getX() && yC == absolutePosition.getY()) {
                return Action.buildDeleteMeAction(null, this::onFailCallback, CoordinateSystemUtils.getInstance().fromAbsoluteToTilePosition(absolutePosition), this);
            }
            return Action.buildMoveAction(null, this::onFailCallback, getAbsolutePosition(), nextPosition, this);
        }else {
            return Action.buildIdleAction(null,null);
        }
    }
}
