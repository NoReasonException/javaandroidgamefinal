package uk.ac.reading.sis05kol.engine.game.core.interfaces.actions;


import android.arch.core.util.Function;

import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.info.RendererInfo;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.actionResult.ActionResult;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;

public class DeleteMeAction extends Action {

    private Drawable drawable;
    private Position position;

    DeleteMeAction(Function<Void,Void> onSuccessCallback, Function<Void,Void>onFailureCallback, Position p, Drawable drawable){
        super(onSuccessCallback,onFailureCallback);
        this.drawable=drawable;
        this.position=p;
    }

    @Override
    public ActionResult performAction(Map map, RendererInfo rendererInfo, LevelInfo levelInfo) {
        boolean result =map.removeDrawable(position,drawable);
        informSubscribersAndCleanup(result);
        if(result){
            return ActionResult.buildActionDone();
        }
        return ActionResult.buildCollisionDetectedResult(map.getDrawableAtPosition(position));
    }
}
