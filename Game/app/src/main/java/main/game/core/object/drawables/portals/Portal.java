package main.game.core.object.drawables.portals;

import android.content.Context;

import main.game.core.info.LevelInfo;
import main.game.core.interfaces.MapAwareAction;
import main.game.core.map.Map;
import main.game.core.map.Position;
import main.game.core.map.path.Path;
import main.game.core.object.Drawable;
import main.game.core.object.animator.DrawableAnimator;

public class Portal extends Drawable {
    public Portal(Context context, Position absolutePosition, LevelInfo levelInfo,DrawableAnimator animator) {
        super(animator,absolutePosition);
    }

    /**
     * i am a portal  ,i am IDLE all the time!
     */
    @Override
    public MapAwareAction getNextMapAwareAction(Path p, Map map, Context context) {
        return MapAwareAction.buildIdleAction(null,null);
    }
}
