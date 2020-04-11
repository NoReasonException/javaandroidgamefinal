package uk.ac.reading.sis05kol.engine.game.core.interfaces;

import android.arch.core.util.Function;

import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;

public interface ActionAble {
    default public Function<Drawable,Void> getOnCollisionHandler() {
        return new Function<Drawable, Void>() {
            @Override
            public Void apply(Drawable input) {
                return null;
            }
        };
    }
}
