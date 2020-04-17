package main.game.core.interfaces;
import android.content.Context;

import main.game.core.map.Map;
import main.game.core.map.path.Path;

public interface MapAwareActionAble extends ActionAble{

    public MapAwareAction getNextMapAwareAction(Path p,
                                                Map m,
                                                Context context);


}
