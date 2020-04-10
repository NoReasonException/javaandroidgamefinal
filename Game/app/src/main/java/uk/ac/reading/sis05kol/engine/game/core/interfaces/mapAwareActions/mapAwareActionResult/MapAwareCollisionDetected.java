package uk.ac.reading.sis05kol.engine.game.core.interfaces.mapAwareActions.mapAwareActionResult;

import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;

public class MapAwareCollisionDetected extends MapAwareActionResult {
    private Drawable withObject;

    public MapAwareCollisionDetected(Drawable withObject) {
        this.withObject = withObject;
        withObject.getOnCollisionHandler().apply(null);
    }
}
