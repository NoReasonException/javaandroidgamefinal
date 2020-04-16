package uk.ac.reading.sis05kol.engine.game.core.interfaces.mapAwareActions.mapAwareActionResult;

import android.arch.core.util.Function;

import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;

public class MapAwareActionResult {
    public static MapAwareActionResult buildActionDone(){
        return new MapAwareActionDone();
    }
    public static MapAwareActionResult buildCollisionDetectedResult(Drawable object, Drawable withObject){

        return new MapAwareCollisionDetected(object,withObject);
    }
}
