package main.game.core.interfaces;

import android.arch.core.util.Function;

import main.game.core.info.LevelInfo;
import main.game.core.info.RendererInfo;
import main.game.core.interfaces.mapAwareActions.MapAwareDeleteMeAction;
import main.game.core.interfaces.mapAwareActions.MapAwareEmplaceObjectAction;
import main.game.core.interfaces.mapAwareActions.MapAwareIdleAction;
import main.game.core.interfaces.mapAwareActions.MapAwareMoveAction;
import main.game.core.interfaces.mapAwareActions.MapAwareSubcribeBulletAction;
import main.game.core.interfaces.mapAwareActions.mapAwareActionResult.MapAwareActionResult;
import main.game.core.map.Map;
import main.game.core.map.Position;
import main.game.core.object.Drawable;
import main.game.core.renderer.BulletSystem;

abstract public class MapAwareAction extends Action {


    public MapAwareAction(Function<Void, Void> onSuccessCallback, Function<Void, Void> onFailureCallback) {
        super(onSuccessCallback,onFailureCallback);
    }

    /**
     * perform the action
     * @param map               The Map Object
     * @param rendererInfo      The RendererInfo instance
     * @param levelInfo         The LevelInfo instance
     * @return a MapAwareResult
     */
    abstract public MapAwareActionResult performMapAwareAction(Map map, RendererInfo rendererInfo, LevelInfo levelInfo);

    ///Builders
    public static MapAwareAction buildMoveAction(Function<Void,Void> onSuccessCallback, Function<Void,Void>onFailureCallback, Position oldAbsolutePosition, Position newAbsolutePosition, Drawable entity){
        return new MapAwareMoveAction(onSuccessCallback,onFailureCallback,oldAbsolutePosition,newAbsolutePosition,entity);
    }
    public static MapAwareAction buildEmplaceObjectAction(
            Function<Void,Void> onSuccessCallback, Function<Void,Void>onFailureCallback,
            Drawable entity){
        return new MapAwareEmplaceObjectAction(onSuccessCallback,onFailureCallback,entity);

    }
    public static MapAwareAction buildDeleteMeAction(Function<Void,Void> onSuccessCallback, Function<Void,Void>onFailureCallback, Position myAbsolutePosition, Drawable entity){
        return new MapAwareDeleteMeAction(onSuccessCallback,onFailureCallback,myAbsolutePosition,entity);
    }

    public static MapAwareAction buildSubscribeBulletAction(Function<Void,Void> onSuccessCallback, Function<Void,Void>onFailureCallback, Position absolutePosition, Drawable drawable, BulletSystem bulletSystem){
        return new MapAwareSubcribeBulletAction(onSuccessCallback,onFailureCallback,absolutePosition,drawable,bulletSystem);
    }
    public static MapAwareAction buildIdleAction(Function<Void,Void> onSuccessCallback, Function<Void,Void>onFailureCallback){
        return new MapAwareIdleAction(onSuccessCallback,onFailureCallback);
    }
}


