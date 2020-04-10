package uk.ac.reading.sis05kol.engine.game.core.interfaces.mapAwareActions;


import android.arch.core.util.Function;

import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.info.RendererInfo;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.MapAwareAction;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.mapAwareActions.mapAwareActionResult.MapAwareActionResult;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;

public class DeleteMeAction extends MapAwareAction {

    private Drawable drawable;
    private Position position;

    public DeleteMeAction(Function<Void,Void> onSuccessCallback, Function<Void,Void>onFailureCallback, Position p, Drawable drawable){
        super(onSuccessCallback,onFailureCallback);
        this.drawable=drawable;
        this.position=p;
    }

    @Override
    public MapAwareActionResult performMapAwareAction(Map map, RendererInfo rendererInfo, LevelInfo levelInfo) {
        boolean result =map.removeDrawable(position,drawable);
        informSubscribersAndCleanup(result);
        if(result){
            return MapAwareActionResult.buildActionDone();
        }
        return MapAwareActionResult.buildCollisionDetectedResult(map.getDrawableAtPosition(position));
    }
}
