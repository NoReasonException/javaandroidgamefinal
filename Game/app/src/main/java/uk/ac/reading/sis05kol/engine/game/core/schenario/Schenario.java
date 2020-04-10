package uk.ac.reading.sis05kol.engine.game.core.schenario;

import android.content.Context;
import android.os.Handler;

import uk.ac.reading.sis05kol.engine.game.core.interfaces.MapAwareAction;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;

public interface Schenario {
    public MapAwareAction trigger(Map map, Context context, Handler canvasThreadHandler);

}
