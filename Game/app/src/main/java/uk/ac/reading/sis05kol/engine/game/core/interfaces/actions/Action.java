package uk.ac.reading.sis05kol.engine.game.core.interfaces.actions;

import android.arch.core.util.Function;

import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.info.RendererInfo;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.actionResult.ActionResult;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;

abstract public class Action {

    Function<Void,Void> onSuccessCallback;
    Function<Void,Void> onFailureCallback;

    public boolean informSubscribersAndCleanup(boolean result){
        if(result&&onSuccessCallback!=null){
            onSuccessCallback.apply(null);
        }else if((!result)&&onFailureCallback!=null) {
            onFailureCallback.apply(null);
        }
        return result;
    }

    public Action(Function<Void, Void> onSuccessCallback, Function<Void, Void> onFailureCallback) {
        this.onSuccessCallback = onSuccessCallback;
        this.onFailureCallback = onFailureCallback;
    }

    abstract public ActionResult performAction(Map map, RendererInfo rendererInfo, LevelInfo levelInfo);

    public static Action buildMoveAction(Function<Void,Void> onSuccessCallback, Function<Void,Void>onFailureCallback,Position oldPos, Position newPos, Drawable entity){
        return new MoveAction(onSuccessCallback,onFailureCallback,oldPos,newPos,entity);
    }
    public static Action buildEmplaceObjectAction(
            Function<Void,Void> onSuccessCallback, Function<Void,Void>onFailureCallback,
            Drawable entity){
        return new EmplaceObjectAction(onSuccessCallback,onFailureCallback,entity);

    }
    public static Action buildDeleteMeAction(Function<Void,Void> onSuccessCallback, Function<Void,Void>onFailureCallback,Position myPosition,Drawable entity){
        return new DeleteMeAction(onSuccessCallback,onFailureCallback,myPosition,entity);
    }
    public static Action buildIdleAction(Function<Void,Void> onSuccessCallback, Function<Void,Void>onFailureCallback){
        return new IdleAction(onSuccessCallback,onFailureCallback);
    }
}


