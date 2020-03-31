package uk.ac.reading.sis05kol.engine.game.core.interfaces.actions;

import android.arch.core.util.Function;

import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;

public class EmplaceObjectAction extends Action {
    private Drawable entity;

    private Function<Position,Position> fromAbsoluteToTilePosition;
    private Function<Position,Position>fromTileToAbsolutePosition;

    public EmplaceObjectAction(
                      Drawable entity,
                      Function<Position,Position>fromAbsoluteToTilePosition,
                      Function<Position,Position>fromTileToAbsolutePosition) {
        this.entity = entity;
        this.fromAbsoluteToTilePosition=fromAbsoluteToTilePosition;
        this.fromTileToAbsolutePosition=fromTileToAbsolutePosition;
    }

    @Override
    public boolean performAction(Map map) {
        if(map.existsDrawableAtPosition(fromAbsoluteToTilePosition.apply(entity.getAbsolutePosition())))
            return false;
        else
            map.setDrawableAtPosition(
                    fromAbsoluteToTilePosition.apply(entity.getAbsolutePosition()),entity);
        return true;
    }
}
