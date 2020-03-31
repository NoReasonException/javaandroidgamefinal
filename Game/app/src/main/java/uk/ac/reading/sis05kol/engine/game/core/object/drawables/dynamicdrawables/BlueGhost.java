package uk.ac.reading.sis05kol.engine.game.core.object.drawables.dynamicdrawables;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import uk.ac.reading.sis05kol.engine.game.core.interfaces.Moveable;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.map.path.Path;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;
import uk.ac.reading.sis05kol.engine.game.core.object.animator.DrawableAnimator;
import uk.ac.reading.sis05kol.engine.game.core.renderer.Renderer;
import uk.ac.reading.sis05kol.engine.menuactivity.animations.elements.Element;

public class BlueGhost extends Drawable implements Moveable {
    private static final int DOWNINDEX=0;
    private static final int UPINDEX=1;
    private static final int RIGHTINDEX=2;
    private static final int LEFTINDEX=3;
    private int currentIndex=DOWNINDEX;
    private static String loggerTag="BLUEGHOST";
    private Random random=new Random();
    public BlueGhost(Context context, Position absolutePosition) {
        super(Arrays.asList(
                new DrawableAnimator(Element.GHOSTBLUEDOWN, context,0.2f),
                new DrawableAnimator(Element.GHOSTBLUEUP, context,0.2f),
                new DrawableAnimator(Element.GHOSTBLUERIGHT, context,0.2f),
                new DrawableAnimator(Element.GHOSTBLUELEFT, context,0.2f)),absolutePosition);
    }

    @Override
    public Bitmap getBitmap() {
        return animators.get(currentIndex).getBitmap();
    }

    @Override
    public Position nextMove(Path path, Function<Position, Position> fromAbsoluteToTileConversion, Function<Position, Position> fromTileToAbsoluteConversion) {
        Position tilePosition = fromAbsoluteToTileConversion.apply(getAbsolutePosition());
        Path.Node currNode=path.getNodeByPosition(tilePosition);

        Log.i(loggerTag,"i am in"+getAbsolutePosition()+" node "+String.valueOf(currNode));
        if(currNode==null)return getAbsolutePosition().setX(getAbsolutePosition().getX()+random.nextInt(10)).setY(
                getAbsolutePosition().getY()+random.nextInt(10)
        );
        currentIndex=currNode.getAnimatorIndex();
        List<Path.Node> nodes = currNode.getLinks();
        if(nodes==null||nodes.size()==0)return getAbsolutePosition();
        Path.Node next=nodes.get(0);
        Log.i(loggerTag,"i am in node "+String.valueOf(currNode)+" and next node is"+next);
        Position tilePositionNext=next.getPosition();
        Position absolutePositionNext=fromTileToAbsoluteConversion.apply(tilePositionNext);
        return getAbsolutePosition().
                setX(getAbsolutePosition().getX()+ (absolutePositionNext.getX() > getAbsolutePosition().getX() ? 4 : -4))
                .setY(getAbsolutePosition().getY()+(absolutePositionNext.getY()>getAbsolutePosition().getY()?4:-4));



    }
}
