package main.game.core.interfaces.nonAwareMapActions;

import android.arch.core.util.Function;

import main.game.core.info.LevelInfo;
import main.game.core.info.RendererInfo;
import main.game.core.interfaces.MapNonAwareAction;
import main.game.core.interfaces.nonAwareMapActions.mapNonAwareActionResult.MapNonAwareActionResult;
import main.game.core.map.Map;

public class MapNonAwareIdleAction extends MapNonAwareAction {
    /**
     *
     * @param onSuccessCallback called when the action is completed
     * @param onFailureCallback called when the action is failed
     */
    public MapNonAwareIdleAction(Function<Void, Void> onSuccessCallback, Function<Void, Void> onFailureCallback) {
        super(onSuccessCallback, onFailureCallback);
    }

    /**
     * idle , do nothing
     * @param map               The Map object
     * @param rendererInfo      The RendererInfo instance
     * @param levelInfo         the LevelInfo    instance
     * @return a brand-new MapNonAwareActionDone
     */
    @Override
    public MapNonAwareActionResult performNonMapAwareAction(Map map, RendererInfo rendererInfo, LevelInfo levelInfo) {
        return MapNonAwareActionResult.buildActionDone();
    }
}
