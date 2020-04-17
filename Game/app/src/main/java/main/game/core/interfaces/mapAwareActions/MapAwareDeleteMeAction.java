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

/**
 * used to delete objects from the Map
 */
public class MapAwareDeleteMeAction extends MapAwareAction {

    private Drawable drawable;
    private Position absolutePosition;

    public MapAwareDeleteMeAction(Function<Void,Void> onSuccessCallback, Function<Void,Void>onFailureCallback, Position absolutePosition, Drawable drawable){
        super(onSuccessCallback,onFailureCallback);
        this.drawable=drawable;
        this.absolutePosition =absolutePosition;
    }

    /**
     * detects collisions and informs(or not) the involving objects
     * @param map           the Map
     * @param rendererInfo  the RendererInfo instance
     * @param levelInfo     the LevelInfo instance
     * @return              a MapAwareDeleteMeAction
     */
    @Override
    public MapAwareActionResult performMapAwareAction(Map map, RendererInfo rendererInfo, LevelInfo levelInfo) {
        Position tilePosition = CoordinateSystemUtils.getInstance().fromAbsoluteToTilePosition(absolutePosition);
        boolean result =map.removeDrawable(tilePosition,drawable);
        informSubscribersAndCleanup(result);
        if(result){
            return MapAwareActionResult.buildActionDone();
        }
        /*return MapAwareActionResult.buildCollisionDetectedResult(
                map.getDrawableAtPosition(absolutePosition), );*/ //TODO fix that!
        return MapAwareActionResult.buildActionDone();
    }
}
