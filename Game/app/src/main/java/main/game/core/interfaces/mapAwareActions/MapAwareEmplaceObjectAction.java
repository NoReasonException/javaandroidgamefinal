package main.game.core.interfaces.mapAwareActions;
import android.arch.core.util.Function;
import android.util.Log;

import main.game.core.info.LevelInfo;
import main.game.core.info.RendererInfo;
import main.game.core.interfaces.MapAwareAction;
import main.game.core.interfaces.mapAwareActions.mapAwareActionResult.MapAwareActionResult;
import main.game.core.map.Map;
import main.game.core.map.Position;
import main.game.core.object.Drawable;
import main.game.core.utils.CoordinateSystemUtils;

public class MapAwareEmplaceObjectAction extends MapAwareAction {
    private Drawable entity;
    private String loggerTag="EMPLACEOBJECTACTION";
    public MapAwareEmplaceObjectAction(
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
            return MapAwareActionResult.buildCollisionDetectedResult(map.getDrawableAtPosition(entityPosition),entity);
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
