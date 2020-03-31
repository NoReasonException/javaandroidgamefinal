package uk.ac.reading.sis05kol.engine.game.core.schenario;

import android.content.Context;
import android.os.Handler;

import java.util.function.Function;

import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.object.drawables.ghost.BlueGhost;

public class DifficultyLevel1 implements Schenario {
    private int state=0;
    private int max=500;
    @Override
    public void trigger(Map map, Context context, Handler canvasThreadHandler,Function<Position,Position> fromAbsoluteToTilePosition, Function<Position,Position>fromTileToAbsolutePosition) {
        state=(state+1)%500;

        if(state==42){
            canvasThreadHandler.post(new Runnable() {
                private Map mapRef;
                private Context contextRef;
                private Function<Position,Position> fromTileToAbsolutePositionRef;

                public Runnable init(Map map, Context context,Function<Position,Position>fromTileToAbsolutePosition){
                    this.mapRef=map;
                    this.contextRef=context;
                    this.fromTileToAbsolutePositionRef=fromTileToAbsolutePosition;
                    return this;
                }

                @Override
                public void run() {
                    map.setDrawableAtPosition(new Position(1,0),
                            new BlueGhost(context,fromTileToAbsolutePosition.apply(new Position(1,0))));
                }
            }.init(map,context,fromTileToAbsolutePosition));
        }

    }

    @Override
    public void ghostOnEndPortal(Map map, Context context, Function<Position, Position> fromAbsoluteToTilePosition, Function<Position, Position> fromTileToAbsolutePosition) {

    }
}
