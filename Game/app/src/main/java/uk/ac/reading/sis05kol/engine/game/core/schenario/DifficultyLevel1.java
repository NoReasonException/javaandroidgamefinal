package uk.ac.reading.sis05kol.engine.game.core.schenario;

import android.content.Context;
import android.os.Handler;

import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.actions.Action;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.object.drawables.ghost.BlueGhost;
import uk.ac.reading.sis05kol.engine.game.core.utils.CoordinateSystemUtils;

public class DifficultyLevel1 implements Schenario {
    private int state=0;
    private int max=500;
    private LevelInfo levelInfo;
    public static String loggerTag ="DIFFICULTYLEVEL1";

    public DifficultyLevel1(LevelInfo levelInfo) {
        this.levelInfo = levelInfo;
    }

    @Override
    public Action trigger(Map map, Context context, Handler canvasThreadHandler) {
        state=(state+1)%max;

        if(state==42){
            /*canvasThreadHandler.post(new Runnable() {
                private Map mapRef;
                private Context contextRef;
                private LevelInfo levelInfoRef;

                public Runnable init(Map map, Context context,LevelInfo levelInfo){
                    this.mapRef=map;
                    this.contextRef=context;
                    this.levelInfoRef=levelInfo;
                    return this;
                }

                @Override
                public void run() {
                    mapRef.setDrawableAtPosition(new Position(1,0),

                }
            }.init(map,context,levelInfo));*/
            return Action.buildEmplaceObjectAction(null,null,
                    new BlueGhost(context,levelInfo, CoordinateSystemUtils.getInstance().fromTileToAbsolutePosition(new Position(1,1))));
        }
        return Action.buildIdleAction(null,null);
    }
}
