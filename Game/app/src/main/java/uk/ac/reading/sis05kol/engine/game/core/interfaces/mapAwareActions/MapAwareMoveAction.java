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

public class MapAwareMoveAction extends MapAwareAction {
    private Position oldAbsolutePosition;
    private Position newAbsolutePosition;
    private Drawable entity;

    public MapAwareMoveAction(
            Function<Void,Void> onSuccessCallback, Function<Void,Void>onFailureCallback,
            Position oldAbsolutePosition,
                      Position newAbsolutePosition,
                      Drawable entity) {
        super(onSuccessCallback,onFailureCallback);
        this.oldAbsolutePosition = oldAbsolutePosition;
        this.newAbsolutePosition = newAbsolutePosition;
        this.entity = entity;
    }

    @Override
    public MapAwareActionResult performMapAwareAction(Map map, RendererInfo rendererInfo, LevelInfo levelInfo) {
        if(map.moveDrawable(CoordinateSystemUtils.getInstance().fromAbsoluteToTilePosition(entity.getAbsolutePosition())
                ,CoordinateSystemUtils.getInstance().fromAbsoluteToTilePosition(newAbsolutePosition),
                entity)){

            entity.setAbsolutePosition(newAbsolutePosition);
            informSubscribersAndCleanup(true);
            return MapAwareActionResult.buildActionDone();
        }else{

            return MapAwareActionResult
                    .buildCollisionDetectedResult(
                            map.getDrawableAtPosition(
                                    CoordinateSystemUtils
                                            .getInstance()
                                            .fromAbsoluteToTilePosition(entity.getAbsolutePosition())),
                            map.getDrawableAtPosition(
                                    CoordinateSystemUtils
                                            .getInstance()
                                            .fromAbsoluteToTilePosition(newAbsolutePosition)));
        }
    }
}
