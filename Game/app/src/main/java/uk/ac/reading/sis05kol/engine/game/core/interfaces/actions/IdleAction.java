package uk.ac.reading.sis05kol.engine.game.core.interfaces.actions;

import android.arch.core.util.Function;

import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.info.RendererInfo;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.actionResult.ActionResult;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;

public class IdleAction extends Action {
    public IdleAction(Function<Void, Void> onSuccessCallback, Function<Void, Void> onFailureCallback) {
        super(onSuccessCallback, onFailureCallback);
    }

    @Override
    public ActionResult performAction(Map map, RendererInfo rendererInfo, LevelInfo levelInfo) {
        informSubscribersAndCleanup(true);
        return ActionResult.buildActionDone();
    }
}
