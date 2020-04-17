package uk.ac.reading.sis05kol.engine.game.core.utils;

import android.content.Context;

import java.util.Random;

import uk.ac.reading.sis05kol.engine.game.core.info.LevelInfo;
import uk.ac.reading.sis05kol.engine.game.core.map.Position;
import uk.ac.reading.sis05kol.engine.game.core.object.drawables.ghost.BlueGhost;
import uk.ac.reading.sis05kol.engine.game.core.object.drawables.ghost.Ghost;
import uk.ac.reading.sis05kol.engine.game.core.object.drawables.ghost.GreenGhost;
import uk.ac.reading.sis05kol.engine.game.core.object.drawables.ghost.PurpleGhost;
import uk.ac.reading.sis05kol.engine.game.core.object.drawables.ghost.RedGhost;

public class ChooseGhostUtils {
    private static Random random=new Random();
    private static int maxrange=1000;
    public static class GhostPropabilities{
        private double blueGhost;
        private double greenGhost;
        private double redGhost;
        private double purpleGhost;

        public GhostPropabilities(double blueGhost, double greenGhost, double redGhost, double purpleGhost) {
            this.blueGhost = blueGhost;
            this.greenGhost = greenGhost;
            this.redGhost = redGhost;
            this.purpleGhost = purpleGhost;
        }

    }

    public static Ghost getRandomGhostBasedOnPropabilities(GhostPropabilities ghostPropabilities, Context context, LevelInfo levelInfo, Position absolutePosition){
        //range , 0-$maxrange
        int randomInteger = Math.abs(random.nextInt(maxrange));
        if(randomInteger<maxrange*ghostPropabilities.blueGhost){
            return new BlueGhost(context,levelInfo,absolutePosition);
        }else if(randomInteger<maxrange*ghostPropabilities.blueGhost+maxrange*ghostPropabilities.greenGhost){
            return new GreenGhost(context,levelInfo,absolutePosition);
        }else if(randomInteger<maxrange*ghostPropabilities.blueGhost+maxrange*ghostPropabilities.greenGhost+maxrange*ghostPropabilities.redGhost){
            return new RedGhost(context,levelInfo,absolutePosition);
        }else{
            return new PurpleGhost(context,levelInfo,absolutePosition);
        }
    }

}
