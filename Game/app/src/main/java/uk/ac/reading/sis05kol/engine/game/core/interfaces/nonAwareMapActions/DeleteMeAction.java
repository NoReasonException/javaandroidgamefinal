package uk.ac.reading.sis05kol.engine.game.core.interfaces.nonAwareMapActions;

import android.arch.core.util.Function;
import android.util.Log;

import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.info.RendererInfo;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.MapNonAwareAction;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.nonAwareMapActions.mapNonAwareActionResult.MapNonAwareActionResult;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;
import uk.ac.reading.sis05kol.engine.game.core.renderer.BulletSystem;

public class DeleteMeAction extends MapNonAwareAction {
    private Drawable entity;
    private BulletSystem bulletSystem;


    public DeleteMeAction(Function<Void, Void> onSuccessCallback, Function<Void, Void> onFailureCallback, Drawable entity, BulletSystem bulletSystem) {
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
