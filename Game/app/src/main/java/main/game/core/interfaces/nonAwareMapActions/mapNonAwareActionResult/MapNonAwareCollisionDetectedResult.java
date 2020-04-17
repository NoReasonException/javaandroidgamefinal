package main.game.core.interfaces.nonAwareMapActions.mapNonAwareActionResult;

import main.game.core.object.Drawable;

public class MapNonAwareCollisionDetectedResult extends MapNonAwareActionResult{
    private Drawable withObject;

    public MapNonAwareCollisionDetectedResult(Drawable withObject) {
        this.withObject = withObject;
        withObject.getOnCollisionHandler().apply(withObject);
    }
}
