package uk.ac.reading.sis05kol.engine.game.core.interfaces;

import android.arch.core.util.Function;

import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.info.RendererInfo;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.mapAwareActions.DeleteMeAction;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.mapAwareActions.EmplaceObjectAction;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.mapAwareActions.IdleAction;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.mapAwareActions.MoveAction;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.mapAwareActions.SubcribeBulletAction;
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

    public static MapAwareAction buildMoveAction(Function<Void,Void> onSuccessCallback, Function<Void,Void>onFailureCallback, Position oldPos, Position newPos, Drawable entity){
        return new MoveAction(onSuccessCallback,onFailureCallback,oldPos,newPos,entity);
    }
    public static MapAwareAction buildEmplaceObjectAction(
            Function<Void,Void> onSuccessCallback, Function<Void,Void>onFailureCallback,
            Drawable entity){
        return new EmplaceObjectAction(onSuccessCallback,onFailureCallback,entity);

    }
    public static MapAwareAction buildDeleteMeAction(Function<Void,Void> onSuccessCallback, Function<Void,Void>onFailureCallback, Position myPosition, Drawable entity){
        return new DeleteMeAction(onSuccessCallback,onFailureCallback,myPosition,entity);
    }

    public static MapAwareAction buildSubscribeBulletAction(Function<Void,Void> onSuccessCallback, Function<Void,Void>onFailureCallback, Position absolutePosition, Drawable drawable, BulletSystem bulletSystem){
        return new SubcribeBulletAction(onSuccessCallback,onFailureCallback,absolutePosition,drawable,bulletSystem);
    }
    public static MapAwareAction buildIdleAction(Function<Void,Void> onSuccessCallback, Function<Void,Void>onFailureCallback){
        return new IdleAction(onSuccessCallback,onFailureCallback);
    }
}


