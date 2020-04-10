package uk.ac.reading.sis05kol.engine.game.core.interfaces.mapAwareActions;
import android.arch.core.util.Function;
import android.util.Log;

import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.info.RendererInfo;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.MapAwareAction;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.mapAwareActions.mapAwareActionResult.MapAwareActionResult;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;
import uk.ac.reading.sis05kol.engine.game.core.utils.CoordinateSystemUtils;

public class EmplaceObjectAction extends MapAwareAction {
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
        public MapAwareActionResult performMapAwareAction(Map map, RendererInfo rendererInfo, LevelInfo levelInfo) {
        Position entityPosition= CoordinateSystemUtils.getInstance().fromAbsoluteToTilePosition(entity.getAbsolutePosition());
        if(map.existsObjectAtPosition(entityPosition)) {
            informSubscribersAndCleanup(false);
            Log.d(loggerTag,"EmplaceObjectAction failed on object"+entity+" at position"+entityPosition+"exists something there");
            return MapAwareActionResult.buildCollisionDetectedResult(map.getDrawableAtPosition(entityPosition));
        }
        else {
            Log.d(loggerTag,"EmplaceObjectAction completed on object"+entity+" at position"+entityPosition+"something there = no");
            map.setDrawableAtPosition(CoordinateSystemUtils.getInstance().fromAbsoluteToTilePosition(entity.getAbsolutePosition()), entity);
            //entity.setAbsolutePosition(CoordinateSystemUtils.getInstance().fromAbsoluteToTilePosition(entity.getAbsolutePosition())); //reset to tile position
            entity.setAbsolutePosition(atTheCentrerOfYourTile(entity.getAbsolutePosition(),rendererInfo));
            informSubscribersAndCleanup(true);
            return MapAwareActionResult.buildActionDone();
        }
    }
    public Position atTheCentrerOfYourTile(Position absoluteTilePosition,RendererInfo rendererInfo){
        return absoluteTilePosition.addX(rendererInfo.getInitialPositionOfObjectOffset().first).addY(rendererInfo.getInitialPositionOfObjectOffset().second);
    }
}
