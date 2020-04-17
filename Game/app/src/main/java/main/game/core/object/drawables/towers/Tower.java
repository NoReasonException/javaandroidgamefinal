package main.game.core.object.drawables.towers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import main.game.core.info.LevelInfo;
import main.game.core.interfaces.MapAwareAction;
import main.game.core.map.Map;
import main.game.core.map.Position;
import main.game.core.map.path.Path;
import main.game.core.object.Drawable;
import main.game.core.object.animator.DrawableAnimator;
import main.game.core.object.drawables.monster.Monster;
import main.game.core.object.drawables.projectiles.Bullet;
import main.game.core.renderer.BulletSystem;
import main.game.core.utils.CoordinateSystemUtils;

public class Tower extends Drawable {

    public static enum TowerType{
        FIRE,POISON,STORM,ICE;
    }
    private static final int IDLEINDEX=0;
    private static final int ATTACKINDEX=1;
    private String loggerTAG  ="TOWER";
    private LevelInfo levelInfo;
    private int testState=0;
    private int testMaxState=150;
    private BulletSystem bulletSystem;
    private int cost;

    public Tower(Context context,List<DrawableAnimator> animators, LevelInfo levelInfo, Position absolutePosition, BulletSystem bulletSystem,double power,int cost) {
        super(animators, absolutePosition);
        this.levelInfo=levelInfo;
        this.bulletSystem=bulletSystem;
        this.testMaxState=Double.valueOf(((1-power)*testMaxState)).intValue();
        this.cost=cost;
    }

    public int getCost() {
        return cost;
    }

    /**
     * detects enemies in the given radius
     * @param radius the radius
     * @return a list of all positions inside radius
     */
    private List<Position> getViewRange(int radius){
        List<Position> p = new ArrayList<>();
        Position tilePosition = CoordinateSystemUtils.getInstance().fromAbsoluteToTilePosition(absolutePosition);
        Position curr;
        for (int i = tilePosition.getX()-radius; i <= tilePosition.getX()+radius; i++) {
            for (int j = tilePosition.getY()-radius; j<= tilePosition.getY()+radius; j++) {
                if(tilePosition.getX()!=i&&tilePosition.getY()!=j){
                    p.add(new Position(i,j));
                }
            }
        }
        return p.stream().filter((position)->position.getX()>0&&position.getY()>0).collect(Collectors.toList());
    }

    /**
     * detects enemies and shoots at them!
     * @param p             The Path Object
     * @param map           The Map Object
     * @param context       The Context taken from mGameView
     * @return
     */
    @Override
    public MapAwareAction getNextMapAwareAction(Path p, Map map, Context context) {
        testState = (testState + 1) % testMaxState;
        Drawable atPosition;
        if(testState==1) {
            List<Position> view = getViewRange(1);
            for (Position position : view) {
                if (map.existsObjectAtPosition(position)) {
                    atPosition=map.getDrawableAtPosition(position);
                    if (map.getDrawableAtPosition(position) instanceof Monster) {
                        return MapAwareAction.buildSubscribeBulletAction(null, null, getAbsolutePosition(),
                                new Bullet(context, getAbsolutePosition(), atPosition.getAbsolutePosition(), levelInfo, bulletSystem), bulletSystem);
                    }
                }
            }
        }
        return MapAwareAction.buildIdleAction(null, null);
    }

    @Override
    public Pair<Bitmap, Paint> getBitmap() {
        return new Pair<Bitmap, Paint>( animators.get(ATTACKINDEX).getBitmap(),null);
    }
}
