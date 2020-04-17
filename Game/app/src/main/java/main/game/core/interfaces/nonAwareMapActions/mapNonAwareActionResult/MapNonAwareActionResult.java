package main.game.core.interfaces.nonAwareMapActions.mapNonAwareActionResult;

import main.game.core.object.Drawable;

public class MapNonAwareActionResult {

    /**
     *
     * @return a MapNonAwareActionDone
     */
    public static MapNonAwareActionDone buildActionDone(){
        return new MapNonAwareActionDone();
    }
    /**
     *
     * @return a MapNonAwareCollisionDetectedResult
     */
    public static MapNonAwareCollisionDetectedResult buildCollicionDetectedAction(Drawable d){
        return new MapNonAwareCollisionDetectedResult(d);
    }
}
