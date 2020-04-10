package uk.ac.reading.sis05kol.engine.game.core.interfaces;

import android.arch.core.util.Function;

import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.info.RendererInfo;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.mapAwareActions.mapAwareActionResult.MapAwareActionResult;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.nonAwareMapActions.DeleteMeAction;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.nonAwareMapActions.IdleAction;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.nonAwareMapActions.MoveAction;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.nonAwareMapActions.mapNonAwareActionResult.MapNonAwareActionDone;
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

    public static DeleteMeAction buildDeleteMeAction(Function<Void, Void> onSuccessCallback, Function<Void, Void> onFailureCallback,Drawable entity, BulletSystem bulletSystem){
        return new DeleteMeAction(onSuccessCallback,onFailureCallback,entity,bulletSystem);
    }
    public static IdleAction buildIdleAction(Function<Void, Void> onSuccessCallback, Function<Void, Void> onFailureCallback){
        return new IdleAction(onSuccessCallback,onFailureCallback);
    }

    public static MoveAction buildMoveAction(Function<Void, Void> onSuccessCallback, Function<Void, Void> onFailureCallback, Position newAbsolutePosition,Drawable entity){
        return new MoveAction(onSuccessCallback,onFailureCallback,newAbsolutePosition,entity);
    }
}
