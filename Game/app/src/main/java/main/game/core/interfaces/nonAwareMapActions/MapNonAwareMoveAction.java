package main.game.core.interfaces.nonAwareMapActions;

import android.arch.core.util.Function;

import main.game.core.info.LevelInfo;
import main.game.core.info.RendererInfo;
import main.game.core.interfaces.MapNonAwareAction;
import main.game.core.interfaces.nonAwareMapActions.mapNonAwareActionResult.MapNonAwareActionResult;
import main.game.core.map.Map;
import main.game.core.map.Position;
import main.game.core.object.Drawable;
import main.game.core.utils.CoordinateSystemUtils;

public class MapNonAwareMoveAction extends MapNonAwareAction {
    private Drawable entity;
    private Position newAbsolutePosition;

    public MapNonAwareMoveAction(Function<Void, Void> onSuccessCallback, Function<Void, Void> onFailureCallback, Position newAbsolutePosition, Drawable entity) {
        super(onSuccessCallback, onFailureCallback);
        this.entity = entity;
        this.newAbsolutePosition=newAbsolutePosition;
    }

    /**
     * performs a MapNonAwareMoveAction
     * @param map               The Map object
     * @param rendererInfo      The RendererInfo instance
     * @param levelInfo         The LevelInfo instance
     */
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
