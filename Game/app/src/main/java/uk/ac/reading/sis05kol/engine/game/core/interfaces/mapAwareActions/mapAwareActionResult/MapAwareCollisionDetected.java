package uk.ac.reading.sis05kol.engine.game.core.interfaces.mapAwareActions.mapAwareActionResult;

import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;

public class MapAwareCollisionDetected extends MapAwareActionResult {
    private Drawable object;
    private Drawable withObject;

    public MapAwareCollisionDetected(Drawable object, Drawable withObject) {
        this.object = object;
        this.withObject = withObject;
        object.getOnCollisionHandler().apply(withObject);
        withObject.getOnCollisionHandler().apply(object);
    }
}
