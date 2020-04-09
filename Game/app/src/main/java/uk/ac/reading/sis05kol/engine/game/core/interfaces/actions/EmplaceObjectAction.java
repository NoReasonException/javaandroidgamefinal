package uk.ac.reading.sis05kol.engine.game.core.interfaces.actions;
import android.arch.core.util.Function;
import android.util.Log;

import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.info.RendererInfo;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.actionResult.ActionResult;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;
import uk.ac.reading.sis05kol.engine.game.core.utils.CoordinateSystemUtils;

public class EmplaceObjectAction extends Action {
    private Drawable entity;
    private String loggerTag="EMPLACEOBJECTACTION";
    public EmplaceObjectAction(
            Function<Void,Void> onSuccessCallback,
            Function<Void,Void>onFailureCallback,
                      Drawable entity) {
        super(onSuccessCallback,onFailureCallback);
        this.entity = entity;
    }

    @Override
        public ActionResult performAction(Map map, RendererInfo rendererInfo, LevelInfo levelInfo) {
        Position entityPosition= CoordinateSystemUtils.getInstance().fromAbsoluteToTilePosition(entity.getAbsolutePosition());
        if(map.existsObjectAtPosition(entityPosition)) {
            informSubscribersAndCleanup(false);
            Log.d(loggerTag,"EmplaceObjectAction failed on object"+entity+" at position"+entityPosition+"exists something there");
            return ActionResult.buildCollisionDetectedResult(map.getDrawableAtPosition(entityPosition));
        }
        else {
            Log.d(loggerTag,"EmplaceObjectAction completed on object"+entity+" at position"+entityPosition+"something there = no");
            map.setDrawableAtPosition(CoordinateSystemUtils.getInstance().fromAbsoluteToTilePosition(entity.getAbsolutePosition()), entity);
            //entity.setAbsolutePosition(CoordinateSystemUtils.getInstance().fromAbsoluteToTilePosition(entity.getAbsolutePosition())); //reset to tile position
            entity.setAbsolutePosition(atTheCentrerOfYourTile(entity.getAbsolutePosition(),rendererInfo));
            informSubscribersAndCleanup(true);
            return ActionResult.buildActionDone();
        }
    }
    public Position atTheCentrerOfYourTile(Position absoluteTilePosition,RendererInfo rendererInfo){
        return absoluteTilePosition.addX(rendererInfo.getInitialPositionOfObjectOffset().first).addY(rendererInfo.getInitialPositionOfObjectOffset().second);
    }
}
