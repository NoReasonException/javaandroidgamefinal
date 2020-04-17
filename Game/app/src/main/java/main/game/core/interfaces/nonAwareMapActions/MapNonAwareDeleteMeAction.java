package main.game.core.interfaces.nonAwareMapActions;

import android.arch.core.util.Function;

import main.game.core.info.LevelInfo;
import main.game.core.info.RendererInfo;
import main.game.core.interfaces.MapNonAwareAction;
import main.game.core.interfaces.nonAwareMapActions.mapNonAwareActionResult.MapNonAwareActionResult;
import main.game.core.map.Map;
import main.game.core.object.Drawable;
import main.game.core.renderer.BulletSystem;

public class MapNonAwareDeleteMeAction extends MapNonAwareAction {
    private Drawable entity;
    private BulletSystem bulletSystem;


    public MapNonAwareDeleteMeAction(Function<Void, Void> onSuccessCallback, Function<Void, Void> onFailureCallback, Drawable entity, BulletSystem bulletSystem) {
        super(onSuccessCallback, onFailureCallback);
        this.entity = entity;
        this.bulletSystem = bulletSystem;
    }

    /**
     * removes a bullet actually (only a bullet triggers a MapNonAwareDeleteMeAction)
     * TODO :this may cause problems if more than one Drawable children fires this event , ClassCastException, refactor it
     * @param map               the Map instance
     * @param rendererInfo      the RendererInfo instance
     * @param levelInfo         the LevelInfo instance
     * @return                  a MapNonAwareActionResult
     */
    @Override
    public MapNonAwareActionResult performNonMapAwareAction(Map map, RendererInfo rendererInfo, LevelInfo levelInfo) {
        bulletSystem.removeBullet(entity);
        return MapNonAwareActionResult.buildActionDone();
    }
}
