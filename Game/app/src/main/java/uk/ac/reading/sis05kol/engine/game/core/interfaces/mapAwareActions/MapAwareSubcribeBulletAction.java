package uk.ac.reading.sis05kol.engine.game.core.interfaces.mapAwareActions;


import android.arch.core.util.Function;

import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.info.RendererInfo;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.MapAwareAction;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.mapAwareActions.mapAwareActionResult.MapAwareActionResult;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;
import uk.ac.reading.sis05kol.engine.game.core.renderer.BulletSystem;

public class MapAwareSubcribeBulletAction extends MapAwareAction {

    private Drawable drawable;
    private Position absolutePosition;
    private BulletSystem bulletSystem;

    public MapAwareSubcribeBulletAction(Function<Void,Void> onSuccessCallback, Function<Void,Void>onFailureCallback, Position absolutePosition, Drawable drawable, BulletSystem bulletSystem){
        super(onSuccessCallback,onFailureCallback);
        this.drawable=drawable;
        this.absolutePosition =absolutePosition;
        this.bulletSystem=bulletSystem;
    }

    @Override
    public MapAwareActionResult performMapAwareAction(Map map, RendererInfo rendererInfo, LevelInfo levelInfo) {
        bulletSystem.subscribeBullet(drawable);
        return MapAwareActionResult.buildActionDone();
    }
}
