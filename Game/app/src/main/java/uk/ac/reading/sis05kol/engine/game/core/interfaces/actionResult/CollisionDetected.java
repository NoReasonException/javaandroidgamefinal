package uk.ac.reading.sis05kol.engine.game.core.interfaces.actionResult;

import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;

public class CollisionDetected extends ActionResult{
    private Drawable withObject;

    public CollisionDetected(Drawable withObject) {
        this.withObject = withObject;
        withObject.getOnCollisionHandler().apply(null);
    }
}
