package main.game.core.interfaces.mapAwareActions;

import android.arch.core.util.Function;

import main.game.core.info.LevelInfo;
import main.game.core.info.RendererInfo;
import main.game.core.interfaces.MapAwareAction;
import main.game.core.interfaces.mapAwareActions.mapAwareActionResult.MapAwareActionResult;
import main.game.core.map.Map;
import main.game.core.map.Position;
import main.game.core.object.Drawable;
import main.game.core.utils.CoordinateSystemUtils;

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

    /**
     * moves an object from position A to position B
     * @param map               the Map object
     * @param rendererInfo      the RendererInfo instance
     * @param levelInfo         the Levelnfo instance
     * @return                  a MapAwareMoveAction
     */
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
