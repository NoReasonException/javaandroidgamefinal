package uk.ac.reading.sis05kol.engine.game.core.object.drawables.towers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.MapAwareAction;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.map.path.Path;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;
import uk.ac.reading.sis05kol.engine.game.core.object.animator.DrawableAnimator;
import uk.ac.reading.sis05kol.engine.game.core.object.drawables.ghost.Ghost;
import uk.ac.reading.sis05kol.engine.game.core.object.drawables.projectiles.Bullet;
import uk.ac.reading.sis05kol.engine.game.core.renderer.BulletSystem;
import uk.ac.reading.sis05kol.engine.game.core.utils.CoordinateSystemUtils;
import uk.ac.reading.sis05kol.engine.menuanimators.elements.Element;

public class Tower extends Drawable {
    private static final int IDLEINDEX=0;
    private static final int ATTACKINDEX=1;
    private String loggerTAG  ="TOWER";

    private LevelInfo levelInfo;

    private int testState=0;
    private int testMaxState=100;

    private BulletSystem bulletSystem;

    public Tower(Context context,List<DrawableAnimator> animators, LevelInfo levelInfo, Position absolutePosition, BulletSystem bulletSystem,double difficulty) {
        super(animators, absolutePosition);
        this.levelInfo=levelInfo;
        this.bulletSystem=bulletSystem;
        this.testMaxState=Double.valueOf(((1-difficulty)*testMaxState)).intValue();
    }

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

    @Override
    public MapAwareAction getNextMapAwareAction(Path p, Map map, Context context) {
        testState = (testState + 1) % testMaxState;
        Drawable atPosition;
        if(testState==1) {
            List<Position> view = getViewRange(1);
            for (Position position : view) {
                if (map.existsObjectAtPosition(position)) {
                    atPosition=map.getDrawableAtPosition(position);
                    if (map.getDrawableAtPosition(position) instanceof Ghost) {
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
