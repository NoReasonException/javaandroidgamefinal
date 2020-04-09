package uk.ac.reading.sis05kol.engine.game.core.object.drawables.ghost;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import android.arch.core.util.Function;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.actions.Action;
import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.map.path.Path;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;
import uk.ac.reading.sis05kol.engine.game.core.object.animator.DrawableAnimator;
import uk.ac.reading.sis05kol.engine.game.core.utils.CoordinateSystemUtils;
import uk.ac.reading.sis05kol.engine.menuactivity.animations.elements.Element;

public class BlueGhost extends Drawable{
    private static final int DOWNINDEX=0;
    private static final int UPINDEX=1;
    private static final int RIGHTINDEX=2;
    private static final int LEFTINDEX=3;
    private int currentIndex=DOWNINDEX;
    private static String loggerTag="BLUEGHOST";
    private Random random=new Random();
    private int acceptablePixelDeviation=5;
    private int speed=3;
    private int plusSpeedOffset=1;

    private Path.Node currentNode;

    private boolean toDestruct=false;
    public BlueGhost(Context context, LevelInfo levelInfo, Position absolutePosition) {
        super(Arrays.asList(
                new DrawableAnimator(Element.GHOSTBLUEDOWN, context,levelInfo),
                new DrawableAnimator(Element.GHOSTBLUEUP, context,levelInfo),
                new DrawableAnimator(Element.GHOSTBLUERIGHT, context,levelInfo),
                new DrawableAnimator(Element.GHOSTBLUELEFT, context,levelInfo)),absolutePosition);
    }

    @Override
    public Bitmap getBitmap() {
        return animators.get(currentIndex).getBitmap();
    }

    @Override
    public Action getNextAction(Path path, Map map, Context context) {

        if(toDestruct){
            return Action.buildDeleteMeAction(null,null, CoordinateSystemUtils.getInstance().fromAbsoluteToTilePosition(getAbsolutePosition()),this);
        }

        Position tilePosition = CoordinateSystemUtils.getInstance().fromAbsoluteToTilePosition(getAbsolutePosition());
        Path.Node currNode=path.getNodeByPosition(tilePosition);

        if(currentNode==null){
            currentNode=currNode;
        }
        else if(currNode!=null && currentNode.getLinks().contains(currNode)){
            currentNode=currNode;
            Log.i(loggerTag,"i am in"+getAbsolutePosition()+" node "+String.valueOf(currentNode));
        }







        Log.i(loggerTag,"i am in"+getAbsolutePosition()+" node "+String.valueOf(currentNode));

        currentIndex=currentNode.getAnimatorIndex();
        List<Path.Node> nodes = currentNode.getLinks();
        if(nodes==null||nodes.size()==0){
            Position newPosition = getAbsolutePosition();
            return Action.buildMoveAction(null,null,tilePosition,newPosition,this);
        }
        Path.Node next=nodes.get(0);
        Log.i(loggerTag,"i am in node "+String.valueOf(currentNode)+" and next node is"+next);
        Position tilePositionNext=next.getPosition();
        Position absolutePositionNext=CoordinateSystemUtils.getInstance().fromTileToAbsolutePosition(tilePositionNext);

        int xDeviation=Math.abs(absolutePositionNext.getX()-getAbsolutePosition().getX());
        int yDeviation=Math.abs(absolutePositionNext.getY()-getAbsolutePosition().getY());
        Position newPosition;
        if(xDeviation<=acceptablePixelDeviation){
            if(yDeviation>acceptablePixelDeviation) {
                newPosition= getAbsolutePosition()
                        .setY(getAbsolutePosition().getY()+(absolutePositionNext.getY()>getAbsolutePosition().getY()?speed+plusSpeedOffset:-speed));
            }else {
                newPosition= getAbsolutePosition().
                        setX(getAbsolutePosition().getX()+ (absolutePositionNext.getX() > getAbsolutePosition().getX() ? speed +plusSpeedOffset: -speed))
                        .setY(getAbsolutePosition().getY()+(absolutePositionNext.getY()>getAbsolutePosition().getY()?speed+plusSpeedOffset:-speed));
            }
        }else if(yDeviation<=acceptablePixelDeviation){
            if(xDeviation>acceptablePixelDeviation) {
                newPosition= getAbsolutePosition().
                        setX(getAbsolutePosition().getX()+ (absolutePositionNext.getX() > getAbsolutePosition().getX() ? speed+plusSpeedOffset : -speed));
            }
            else {
                newPosition= getAbsolutePosition().
                        setX(getAbsolutePosition().getX()+ (absolutePositionNext.getX() > getAbsolutePosition().getX() ? speed +plusSpeedOffset: -speed))
                        .setY(getAbsolutePosition().getY()+(absolutePositionNext.getY()>getAbsolutePosition().getY()?speed+plusSpeedOffset:-speed));
            }
        }
        else {
            newPosition= getAbsolutePosition().
                    setX(getAbsolutePosition().getX()+ (absolutePositionNext.getX() > getAbsolutePosition().getX() ? speed +plusSpeedOffset: -speed))
                    .setY(getAbsolutePosition().getY()+(absolutePositionNext.getY()>getAbsolutePosition().getY()?speed+plusSpeedOffset:-speed));
        }



        return Action.buildMoveAction(null,null,tilePosition,newPosition,this);

    }

    public void setToDestruct(boolean toDestruct) {
        this.toDestruct = toDestruct;
    }

    @Override
    public Function<Void, Void> getOnCollisionHandler() {


        return new Function<Void, Void>() {
            BlueGhost drawable;

            @Override
            public Void apply(Void input) {
                //drawable.setToDestruct(true);
                return null;

            }
            public Function<Void, Void>init(BlueGhost drawable){
                this.drawable=drawable;
                return this;
            }
        }.init(this);
    }
}
