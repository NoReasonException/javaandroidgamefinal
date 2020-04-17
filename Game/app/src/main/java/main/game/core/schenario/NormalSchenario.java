package main.game.core.schenario;

import main.game.core.info.LevelInfo;
import main.game.core.utils.ChooseGhostUtils;

public class NormalSchenario extends Schenario {
    public static String loggerTag ="NORMALSCHENARIO";

    public NormalSchenario(LevelInfo levelInfo  ) {
        super(levelInfo, new ChooseGhostUtils.GhostPropabilities(
                0.2,0.5,0.2,0.1
        ),170);
    }


}