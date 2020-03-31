package uk.ac.reading.sis05kol.engine.game.core.interfaces.actions;

import android.arch.core.util.Function;

import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;

abstract public class Action {

    abstract public boolean performAction(Map map);

    public static Action buildMoveAction(Position oldPos, Position newPos, Drawable entity, Function<Position,Position> fromAbsoluteToTilePosition,
                                         Function<Position,Position>fromTileToAbsolutePosition){
        return new MoveAction(oldPos,newPos,entity,fromAbsoluteToTilePosition,fromTileToAbsolutePosition);
    }
    public static Action buildEmplaceObjectAction(){
        return new EmplaceObjectAction();
    }
    public static Action buildIdleAction(){
        return new IdleAction();
    }
}
