package uk.ac.reading.sis05kol.engine.game.core.interfaces;

import android.arch.core.util.Function;

import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.info.RendererInfo;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.mapAwareActions.MapAwareDeleteMeAction;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.mapAwareActions.MapAwareEmplaceObjectAction;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.mapAwareActions.MapAwareIdleAction;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.mapAwareActions.MapAwareMoveAction;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.mapAwareActions.MapAwareSubcribeBulletAction;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.mapAwareActions.mapAwareActionResult.MapAwareActionResult;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;
import uk.ac.reading.sis05kol.engine.game.core.renderer.BulletSystem;

abstract public class MapAwareAction extends Action {


    public MapAwareAction(Function<Void, Void> onSuccessCallback, Function<Void, Void> onFailureCallback) {
        super(onSuccessCallback,onFailureCallback);
    }

    abstract public MapAwareActionResult performMapAwareAction(Map map, RendererInfo rendererInfo, LevelInfo levelInfo);

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


