package uk.ac.reading.sis05kol.engine.game.core.interfaces.nonAwareMapActions;

import android.arch.core.util.Function;

import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.info.RendererInfo;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.MapNonAwareAction;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.nonAwareMapActions.mapNonAwareActionResult.MapNonAwareActionResult;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;

public class MapNonAwareIdleAction extends MapNonAwareAction {
    public MapNonAwareIdleAction(Function<Void, Void> onSuccessCallback, Function<Void, Void> onFailureCallback) {
        super(onSuccessCallback, onFailureCallback);
    }

    @Override
    public MapNonAwareActionResult performNonMapAwareAction(Map map, RendererInfo rendererInfo, LevelInfo levelInfo) {
        return MapNonAwareActionResult.buildActionDone();
    }
}
