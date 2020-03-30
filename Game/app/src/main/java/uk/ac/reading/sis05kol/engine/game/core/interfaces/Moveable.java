package uk.ac.reading.sis05kol.engine.game.core.interfaces;

import java.util.function.Function;

import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.map.path.Path;

public interface Moveable {
    public Position nextMove(Path p, Function<Position,Position> fromAbsoluteToTileConversion,Function<Position,Position> fromTileToAbsoluteConversion);
}
