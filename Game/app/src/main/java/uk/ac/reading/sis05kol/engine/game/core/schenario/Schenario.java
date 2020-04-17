package uk.ac.reading.sis05kol.engine.game.core.schenario;

import android.content.Context;
import android.os.Handler;

import java.util.Random;

import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.interfaces.MapAwareAction;
import uk.ac.reading.sis05kol.engine.game.core.map.Map;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.utils.ChooseGhostUtils;
import uk.ac.reading.sis05kol.engine.game.core.utils.CoordinateSystemUtils;

abstract public class Schenario {
    protected LevelInfo levelInfo;
    protected ChooseGhostUtils.GhostPropabilities ghostPropabilities;
    protected Random random=new Random();
    protected int state=0;
    protected int max;
    public static String loggerTag ="SCHENARIO";

    public Schenario(LevelInfo levelInfo, ChooseGhostUtils.GhostPropabilities ghostPropabilities,int max) {
        this.levelInfo = levelInfo;
        this.ghostPropabilities = ghostPropabilities;
        this.max=max;
    }


    public MapAwareAction trigger(Map map, Context context, Handler canvasThreadHandler) {
        state=(state+1)%max;

        if(state==42){
            return MapAwareAction
                    .buildEmplaceObjectAction(
                            null,
                            null,
                            ChooseGhostUtils.getRandomGhostBasedOnPropabilities(
                                    ghostPropabilities,
                                    context,
                                    levelInfo,
                                    CoordinateSystemUtils.getInstance()
                                            .fromTileToAbsolutePosition(new Position(2,0))));
        }
        return MapAwareAction.buildIdleAction(null,null);
    }


}
