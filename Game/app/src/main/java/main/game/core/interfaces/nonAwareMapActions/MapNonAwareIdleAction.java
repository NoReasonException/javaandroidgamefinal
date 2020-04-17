package main.game.core.interfaces.nonAwareMapActions;

import android.arch.core.util.Function;

import main.game.core.info.LevelInfo;
import main.game.core.info.RendererInfo;
import main.game.core.interfaces.MapNonAwareAction;
import main.game.core.interfaces.nonAwareMapActions.mapNonAwareActionResult.MapNonAwareActionResult;
import main.game.core.map.Map;

public class MapNonAwareIdleAction extends MapNonAwareAction {
    public MapNonAwareIdleAction(Function<Void, Void> onSuccessCallback, Function<Void, Void> onFailureCallback) {
        super(onSuccessCallback, onFailureCallback);
    }

    @Override
    public MapNonAwareActionResult performNonMapAwareAction(Map map, RendererInfo rendererInfo, LevelInfo levelInfo) {
        return MapNonAwareActionResult.buildActionDone();
    }
}
