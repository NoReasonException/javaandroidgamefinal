package main.game.core.utils;

import android.content.Context;

import java.util.Random;

import main.game.core.info.LevelInfo;
import main.game.core.map.Position;
import main.game.core.object.drawables.monster.BlueMonster;
import main.game.core.object.drawables.monster.Monster;
import main.game.core.object.drawables.monster.GreenMonster;
import main.game.core.object.drawables.monster.PurpleMonster;
import main.game.core.object.drawables.monster.RedMonster;

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

    /**
     * based on the given propability , we spawn ghosts under the assumption that the random.randint() follows a uniform distribution
     * @param ghostPropabilities        The Propabillities
     * @param context                   The Context Object
     * @param levelInfo                 The Level Info
     * @param absolutePosition          The Absolute Position
     * @return                          a generated monster
     */
    public static Monster getRandomGhostBasedOnPropabilities(GhostPropabilities ghostPropabilities, Context context, LevelInfo levelInfo, Position absolutePosition){
        //range , 0-$maxrange
        int randomInteger = Math.abs(random.nextInt(maxrange));
        if(randomInteger<maxrange*ghostPropabilities.blueGhost){
            return new BlueMonster(context,levelInfo,absolutePosition);
        }else if(randomInteger<maxrange*ghostPropabilities.blueGhost+maxrange*ghostPropabilities.greenGhost){
            return new GreenMonster(context,levelInfo,absolutePosition);
        }else if(randomInteger<maxrange*ghostPropabilities.blueGhost+maxrange*ghostPropabilities.greenGhost+maxrange*ghostPropabilities.redGhost){
            return new RedMonster(context,levelInfo,absolutePosition);
        }else{
            return new PurpleMonster(context,levelInfo,absolutePosition);
        }
    }

}
