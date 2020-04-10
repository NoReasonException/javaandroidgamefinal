package uk.ac.reading.sis05kol.engine.game.core.interfaces.nonAwareMapActions;

import android.arch.core.util.Function;

import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.info.RendererInfo;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.MapNonAwareAction;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.nonAwareMapActions.mapNonAwareActionResult.MapNonAwareActionResult;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;
import uk.ac.reading.sis05kol.engine.game.core.utils.CoordinateSystemUtils;

public class MoveAction extends MapNonAwareAction {
    private Drawable entity;
    private Position newAbsolutePosition;

    public MoveAction(Function<Void, Void> onSuccessCallback, Function<Void, Void> onFailureCallback, Position newAbsolutePosition, Drawable entity) {
        super(onSuccessCallback, onFailureCallback);
        this.entity = entity;
        this.newAbsolutePosition=newAbsolutePosition;
    }

    @Override
    public MapNonAwareActionResult performNonMapAwareAction(Map map, RendererInfo rendererInfo, LevelInfo levelInfo) {
        Position newTilePosition= CoordinateSystemUtils.getInstance().fromAbsoluteToTilePosition(newAbsolutePosition);
        if(map.existsObjectAtPosition(newTilePosition)){
            Drawable d = map.getDrawableAtPosition(newTilePosition);
            return MapNonAwareActionResult.buildCollicionDetectedAction(d);
        }
        entity.setAbsolutePosition(newAbsolutePosition);
        return MapNonAwareActionResult.buildActionDone();
    }
}
