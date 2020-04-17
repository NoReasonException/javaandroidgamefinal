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

    @Override
    public MapNonAwareActionResult performNonMapAwareAction(Map map, RendererInfo rendererInfo, LevelInfo levelInfo) {
        bulletSystem.removeBullet(entity);
        return MapNonAwareActionResult.buildActionDone();
    }
}
