package uk.ac.reading.sis05kol.engine.game.core.interfaces.actionResult;

import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;

public class ActionResult {
    public static ActionResult buildActionDone(){
        return new ActionDone();
    }
    public static ActionResult buildCollisionDetectedResult(Drawable withObject){

        return new CollisionDetected(withObject);
    }
}
