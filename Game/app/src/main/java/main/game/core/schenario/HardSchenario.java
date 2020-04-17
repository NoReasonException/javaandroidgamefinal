package main.game.core.schenario;

import main.game.core.info.LevelInfo;
import main.game.core.utils.ChooseGhostUtils;

public class HardSchenario extends Schenario {
    public static String loggerTag ="NORMALSCHENARIO";

    public HardSchenario(LevelInfo levelInfo) {
        super(levelInfo, new ChooseGhostUtils.GhostPropabilities(
                0.1,0.3,0.2,0.4
        ),120);
    }


}