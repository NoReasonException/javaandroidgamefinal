package main.game.core.interfaces.mapAwareActions.mapAwareActionResult;

import main.game.core.object.Drawable;


public class MapAwareActionResult {
    /**
     * builds an MapAwareActionDone Object
     */
    public static MapAwareActionResult buildActionDone(){
        return new MapAwareActionDone();
    }

    /**
     * builds an MapAwareCollisionDetected
     * @param object first involved object
     * @param withObject second involved object
     * @return an MapAwareCollisionDetected
     */
    public static MapAwareActionResult buildCollisionDetectedResult(Drawable object, Drawable withObject){

        return new MapAwareCollisionDetected(object,withObject);
    }
}
