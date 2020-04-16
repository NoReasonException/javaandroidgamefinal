package uk.ac.reading.sis05kol.engine.game.core.interfaces;
import android.content.Context;

import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.path.Path;

public interface MapAwareActionAble extends ActionAble{

    public MapAwareAction getNextMapAwareAction(Path p,
                                                Map m,
                                                Context context);


}
