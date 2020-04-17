package main.game.core.interfaces.mapAwareActions;

import android.arch.core.util.Function;

import main.game.core.info.LevelInfo;
import main.game.core.info.RendererInfo;
import main.game.core.interfaces.MapAwareAction;
import main.game.core.interfaces.mapAwareActions.mapAwareActionResult.MapAwareActionResult;
import main.game.core.map.Map;

public class MapAwareIdleAction extends MapAwareAction {
    public MapAwareIdleAction(Function<Void, Void> onSuccessCallback, Function<Void, Void> onFailureCallback) {
        super(onSuccessCallback, onFailureCallback);
    }

    /**
     * DoNothingActon
     * @param map           the Map
     * @param rendererInfo  the RendererInfo instance
     * @param levelInfo     the LevelInfo instance
     * @return              a MapAwareIdleActon
     */
    @Override
    public MapAwareActionResult performMapAwareAction(Map map, RendererInfo rendererInfo, LevelInfo levelInfo) {
        informSubscribersAndCleanup(true);
        return MapAwareActionResult.buildActionDone();
    }
}
