package uk.ac.reading.sis05kol.engine.game.core.object.drawables.towers;

import android.content.Context;
import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import uk.ac.reading.sis05kol.engine.game.core.interfaces.MapAwareAction;
import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.map.path.Path;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;
import uk.ac.reading.sis05kol.engine.game.core.object.animator.DrawableAnimator;
import uk.ac.reading.sis05kol.engine.game.core.object.drawables.ghost.Ghost;
import uk.ac.reading.sis05kol.engine.game.core.object.drawables.projectiles.FireProjectile;
import uk.ac.reading.sis05kol.engine.game.core.renderer.BulletSystem;
import uk.ac.reading.sis05kol.engine.game.core.utils.CoordinateSystemUtils;
import uk.ac.reading.sis05kol.engine.menuanimators.elements.Element;

public class FireTower extends Drawable {
    private static final int IDLEINDEX=0;
    private static final int ATTACKINDEX=1;
    private String loggerTAG  ="FIRETOWER";

    private LevelInfo levelInfo;

    private int testState=0;
    private int testMaxState=60;

    private BulletSystem bulletSystem;

    public FireTower(Context context, LevelInfo levelInfo, Position absolutePosition, BulletSystem bulletSystem) {
        super(Arrays.asList(
                new DrawableAnimator(Element.FIREIDLE,context,levelInfo),
                new DrawableAnimator(Element.FIREATTACK,context,levelInfo)
        ), absolutePosition);
        this.levelInfo=levelInfo;
        this.bulletSystem=bulletSystem;
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
        if(testState==42) {
            List<Position> view = getViewRange(1);
            for (Position position : view) {
                if (map.existsObjectAtPosition(position)) {
                    atPosition=map.getDrawableAtPosition(position);
                    if (map.getDrawableAtPosition(position) instanceof Ghost) {
                        return MapAwareAction.buildSubscribeBulletAction(null, null, getAbsolutePosition(),
                                new FireProjectile(context, getAbsolutePosition(), atPosition.getAbsolutePosition(), levelInfo, bulletSystem), bulletSystem);
                    }
                }
            }
        }
        return MapAwareAction.buildIdleAction(null, null);
    }

    @Override
    public Bitmap getBitmap() {
        return animators.get(ATTACKINDEX).getBitmap();
    }
}
