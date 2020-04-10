package uk.ac.reading.sis05kol.engine.game.core.interfaces;

import android.arch.core.util.Function;
import android.content.Context;

import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.path.Path;

public interface MapAwareActionAble {
    public MapAwareAction getNextMapAwareAction(Path p,
                                                Map m,
                                                Context context);

    default public Function<Void,Void> getOnCollisionHandler() {
        return new Function<Void, Void>() {
            @Override
            public Void apply(Void input) {
                return null;
            }
        };
    }
}
