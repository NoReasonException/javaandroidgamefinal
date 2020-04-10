package uk.ac.reading.sis05kol.engine.game.core.interfaces.mapAwareActions.mapAwareActionResult;

import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;

public class MapAwareActionResult {
    public static MapAwareActionResult buildActionDone(){
        return new MapAwareActionDone();
    }
    public static MapAwareActionResult buildCollisionDetectedResult(Drawable withObject){

        return new MapAwareCollisionDetected(withObject);
    }
}
