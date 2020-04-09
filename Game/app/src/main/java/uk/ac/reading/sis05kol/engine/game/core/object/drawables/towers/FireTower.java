package uk.ac.reading.sis05kol.engine.game.core.object.drawables.towers;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import uk.ac.reading.sis05kol.engine.game.core.interfaces.actions.Action;
import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.map.path.Path;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;
import uk.ac.reading.sis05kol.engine.game.core.object.animator.DrawableAnimator;
import uk.ac.reading.sis05kol.engine.game.core.object.drawables.ghost.BlueGhost;
import uk.ac.reading.sis05kol.engine.game.core.object.drawables.projectiles.FireProjectile;
import uk.ac.reading.sis05kol.engine.game.core.utils.CoordinateSystemUtils;
import uk.ac.reading.sis05kol.engine.menuactivity.animations.elements.Element;

public class FireTower extends Drawable {
    private static final int IDLEINDEX=0;
    private static final int ATTACKINDEX=1;
    private String loggerTAG  ="FIRETOWER";
    private int bulletSpeed=10;
    private LevelInfo levelInfo;

    int testState=0;
    int testMaxState=599;

    public FireTower(Context context, LevelInfo levelInfo, Position absolutePosition) {
        super(Arrays.asList(
                new DrawableAnimator(Element.FIREIDLE,context,levelInfo),
                new DrawableAnimator(Element.FIREATTACK,context,levelInfo)
        ), absolutePosition);
        this.levelInfo=levelInfo;
    }

    private List<Position> getViewRange(int radius){
        List<Position> p = new ArrayList<>();
        Position tilePosition = CoordinateSystemUtils.getInstance().fromAbsoluteToTilePosition(absolutePosition);
        Position curr;
        for (int i = tilePosition.getX()-radius; i < tilePosition.getX()+radius; i++) {
            for (int j = tilePosition.getY()-radius; j < tilePosition.getY()+radius; j++) {
                if(tilePosition.getX()!=i&&tilePosition.getY()!=j){
                    p.add(new Position(i,j));
                }


            }
        }
        return p.stream().filter((position)->position.getX()>0&&position.getY()>0).collect(Collectors.toList());
    }
    public Pair<Position,Double> findBulletsFirstPosition(List<Position> view,Map map){
        double minDistance=Double.MAX_VALUE;
        Position minPosition=new Position(0,0);
        double curr;
        for (Position v:view) {
            curr=distance(v,CoordinateSystemUtils.getInstance().fromAbsoluteToTilePosition(getAbsolutePosition()));
            if(curr<minDistance && !(map.existsObjectAtPosition(v))){
                minDistance=curr;
                minPosition=v;
            }
        }
        return new Pair<>(minPosition,minDistance);

    }
    public double distance(Position one,Position two){
        return Math.sqrt(Math.pow(two.getX()-one.getX(),2)-Math.pow(two.getY()-one.getY(),2));
    }

    public Action shootObject(Position tileTarget,Position bulletInitialPosition,Context context){
        Position tileLocation = CoordinateSystemUtils.getInstance().fromAbsoluteToTilePosition(getAbsolutePosition());

        return Action.buildEmplaceObjectAction(null,null,
                new FireProjectile(context,CoordinateSystemUtils.getInstance().fromTileToAbsolutePosition
                        (bulletInitialPosition),tileTarget,levelInfo)
        );
    }

    @Override
    public Action getNextAction(Path p, Map map, Context context) {
        testState = (testState + 1) % testMaxState;

        List<Position>          view = getViewRange(2);
        Pair<Position,Double>   bulletInitialPosition = findBulletsFirstPosition(view,map);


        for (Position position : view) {
            if (map.existsObjectAtPosition(position)) {
                if(map.getDrawableAtPosition(position) instanceof BlueGhost) {
                    //return shootObject(position,bulletInitialPosition.first, context); //shoot mechanism sucks
                }
            }
        }

        return Action.buildIdleAction(null, null);
    }

    @Override
    public Bitmap getBitmap() {
        return animators.get(ATTACKINDEX).getBitmap();
    }
}
