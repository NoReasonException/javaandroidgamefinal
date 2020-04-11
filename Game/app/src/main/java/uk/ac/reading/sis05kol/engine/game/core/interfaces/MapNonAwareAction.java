package uk.ac.reading.sis05kol.engine.game.core.interfaces;

import android.arch.core.util.Function;

import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.info.RendererInfo;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.nonAwareMapActions.MapNonAwareDeleteMeAction;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.nonAwareMapActions.MapNonAwareIdleAction;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.nonAwareMapActions.MapNonAwareMoveAction;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.nonAwareMapActions.mapNonAwareActionResult.MapNonAwareActionResult;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;
import uk.ac.reading.sis05kol.engine.game.core.renderer.BulletSystem;

abstract public class MapNonAwareAction extends Action {

    public MapNonAwareAction(Function<Void, Void> onSuccessCallback, Function<Void, Void> onFailureCallback) {
        super(onSuccessCallback, onFailureCallback);
    }

    abstract public MapNonAwareActionResult performNonMapAwareAction(Map map, RendererInfo rendererInfo, LevelInfo levelInfo);

    public static MapNonAwareDeleteMeAction buildDeleteMeAction(Function<Void, Void> onSuccessCallback, Function<Void, Void> onFailureCallback, Drawable entity, BulletSystem bulletSystem){
        return new MapNonAwareDeleteMeAction(onSuccessCallback,onFailureCallback,entity,bulletSystem);
    }
    public static MapNonAwareIdleAction buildIdleAction(Function<Void, Void> onSuccessCallback, Function<Void, Void> onFailureCallback){
        return new MapNonAwareIdleAction(onSuccessCallback,onFailureCallback);
    }

    public static MapNonAwareMoveAction buildMoveAction(Function<Void, Void> onSuccessCallback, Function<Void, Void> onFailureCallback, Position newAbsolutePosition, Drawable entity){
        return new MapNonAwareMoveAction(onSuccessCallback,onFailureCallback,newAbsolutePosition,entity);
    }
}
