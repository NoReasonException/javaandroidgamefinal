package main.game.core.interfaces.mapAwareActions.mapAwareActionResult;

import main.game.core.object.Drawable;

public class MapAwareActionResult {
    public static MapAwareActionResult buildActionDone(){
        return new MapAwareActionDone();
    }
    public static MapAwareActionResult buildCollisionDetectedResult(Drawable object, Drawable withObject){

        return new MapAwareCollisionDetected(object,withObject);
    }
}
