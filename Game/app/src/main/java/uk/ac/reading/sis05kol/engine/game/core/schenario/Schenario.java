package uk.ac.reading.sis05kol.engine.game.core.schenario;

import android.content.Context;
import android.os.Handler;

import java.util.function.Function;

import uk.ac.reading.sis05kol.engine.game.core.interfaces.actions.Action;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;

public interface Schenario {
    public Action trigger(Map map, Context context, Handler canvasThreadHandler);

}
