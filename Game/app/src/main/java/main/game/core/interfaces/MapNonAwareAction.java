package main.game.core.interfaces;

import android.arch.core.util.Function;

import main.game.core.info.LevelInfo;
import main.game.core.info.RendererInfo;
import main.game.core.interfaces.nonAwareMapActions.MapNonAwareDeleteMeAction;
import main.game.core.interfaces.nonAwareMapActions.MapNonAwareIdleAction;
import main.game.core.interfaces.nonAwareMapActions.MapNonAwareMoveAction;
import main.game.core.interfaces.nonAwareMapActions.mapNonAwareActionResult.MapNonAwareActionResult;
import main.game.core.map.Map;
import main.game.core.map.Position;
import main.game.core.object.Drawable;
import main.game.core.renderer.BulletSystem;

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
