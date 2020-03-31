package uk.ac.reading.sis05kol.engine.game.core.interfaces.actions;

import android.arch.core.util.Function;

import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;

public class MoveAction extends Action {
    private Position oldPos;
    private Position newPos;
    private Drawable entity;
    private Function<Position,Position>fromAbsoluteToTilePosition;
    private Function<Position,Position>fromTileToAbsolutePosition;

    public MoveAction(Position oldPos,
                      Position newPos,
                      Drawable entity,
                      Function<Position,Position>fromAbsoluteToTilePosition,
                      Function<Position,Position>fromTileToAbsolutePosition) {
        this.oldPos = oldPos;
        this.newPos = newPos;
        this.entity = entity;
        this.fromAbsoluteToTilePosition=fromAbsoluteToTilePosition;
        this.fromTileToAbsolutePosition=fromTileToAbsolutePosition;
    }

    @Override
    public boolean performAction(Map map) {
        map.moveDrawable(fromAbsoluteToTilePosition.apply(entity.getAbsolutePosition()),fromAbsoluteToTilePosition.apply(newPos),entity);
        entity.setAbsolutePosition(newPos);
        return true;
    }
}
