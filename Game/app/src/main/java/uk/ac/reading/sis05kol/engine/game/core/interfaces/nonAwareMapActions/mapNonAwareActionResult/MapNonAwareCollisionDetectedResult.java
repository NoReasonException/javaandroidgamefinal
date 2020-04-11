package uk.ac.reading.sis05kol.engine.game.core.interfaces.nonAwareMapActions.mapNonAwareActionResult;

import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;

public class MapNonAwareCollisionDetectedResult extends MapNonAwareActionResult{
    private Drawable withObject;

    public MapNonAwareCollisionDetectedResult(Drawable withObject) {
        this.withObject = withObject;
        withObject.getOnCollisionHandler().apply(withObject);
    }
}
