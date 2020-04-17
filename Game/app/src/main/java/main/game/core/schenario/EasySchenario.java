package main.game.core.schenario;

import main.game.core.info.LevelInfo;
import main.game.core.utils.ChooseGhostUtils;

public class EasySchenario extends Schenario {
    public static String loggerTag ="NORMALSCHENARIO";

    public EasySchenario(LevelInfo levelInfo) {
        super(levelInfo, new ChooseGhostUtils.GhostPropabilities(
                0.8,0.1,0.05,0.05
        ),280);
    }


}