package main.game.core.schenario;

import android.content.Context;
import android.os.Handler;

import java.util.Random;

import main.game.core.info.LevelInfo;
import main.game.core.interfaces.MapAwareAction;
import main.game.core.map.Map;
import main.game.core.map.Position;
import main.game.core.utils.ChooseGhostUtils;
import main.game.core.utils.CoordinateSystemUtils;

/**
 * Triggers events and generates monsters!
 */
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

    /**
     * the main function , runs every frame
     * @param map                   The Map Object
     * @param context               The Context object
     * @param canvasThreadHandler   The CanvasThreadHandler
     * @return                      an MapAwareAction
     */

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
