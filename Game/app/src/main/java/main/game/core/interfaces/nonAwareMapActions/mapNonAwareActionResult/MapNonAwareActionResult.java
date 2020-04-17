package main.game.core.interfaces.nonAwareMapActions.mapNonAwareActionResult;

import main.game.core.object.Drawable;

public class MapNonAwareActionResult {

    public static MapNonAwareActionDone buildActionDone(){
        return new MapNonAwareActionDone();
    }
    public static MapNonAwareCollisionDetectedResult buildCollicionDetectedAction(Drawable d){
        return new MapNonAwareCollisionDetectedResult(d);
    }
}
