package main.game.core.interfaces.mapAwareActions;


import android.arch.core.util.Function;

import main.game.core.info.LevelInfo;
import main.game.core.info.RendererInfo;
import main.game.core.interfaces.MapAwareAction;
import main.game.core.interfaces.mapAwareActions.mapAwareActionResult.MapAwareActionResult;
import main.game.core.map.Map;
import main.game.core.map.Position;
import main.game.core.object.Drawable;
import main.game.core.renderer.BulletSystem;

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
