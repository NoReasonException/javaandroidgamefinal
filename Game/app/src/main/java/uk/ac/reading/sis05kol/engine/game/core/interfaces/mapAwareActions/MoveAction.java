package uk.ac.reading.sis05kol.engine.game.core.interfaces.mapAwareActions;

import android.arch.core.util.Function;

import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.info.RendererInfo;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.MapAwareAction;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.mapAwareActions.mapAwareActionResult.MapAwareActionResult;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;
import uk.ac.reading.sis05kol.engine.game.core.utils.CoordinateSystemUtils;

public class MoveAction extends MapAwareAction {
    private Position oldPos;
    private Position newPos;
    private Drawable entity;

    public MoveAction(
            Function<Void,Void> onSuccessCallback, Function<Void,Void>onFailureCallback,
            Position oldPos,
                      Position newPos,
                      Drawable entity) {
        super(onSuccessCallback,onFailureCallback);
        this.oldPos = oldPos;
        this.newPos = newPos;
        this.entity = entity;
    }

    @Override
    public MapAwareActionResult performMapAwareAction(Map map, RendererInfo rendererInfo, LevelInfo levelInfo) {
        if(map.moveDrawable(CoordinateSystemUtils.getInstance().fromAbsoluteToTilePosition(entity.getAbsolutePosition()),CoordinateSystemUtils.getInstance().fromAbsoluteToTilePosition(newPos),entity)){
            entity.setAbsolutePosition(newPos);
            informSubscribersAndCleanup(true);
            return MapAwareActionResult.buildActionDone();
        }
        return MapAwareActionResult
                .buildCollisionDetectedResult(map
                        .getDrawableAtPosition(CoordinateSystemUtils.getInstance().fromAbsoluteToTilePosition(entity.getAbsolutePosition())));
    }
}
