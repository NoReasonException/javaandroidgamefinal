package uk.ac.reading.sis05kol.engine.game.core.score;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Timer;
import java.util.function.Function;

abstract public class LifesSystem {
    private static LifesSystem instance;
    private boolean isInitialized=false;
    private boolean isGameOver=false;
    private boolean tick=false;
    private View background;
    private ArrayList<View>lifes;
    private View timer;
    private Handler mGameHandler;
    private int timerseconds=0;



    public static void setInstance(LifesSystem lifesSystem){
        instance= lifesSystem;
    }
    public static LifesSystem getInstance() throws IllegalStateException{
        if(instance==null){
            throw new IllegalStateException("instance has not been set");
        }
       return instance;
    }

    public LifesSystem(Handler mGameHandler,View background, ArrayList<View>lifes,View timer) {
        this.mGameHandler=mGameHandler;
        this.background=background;
        this.lifes=lifes;
        this.timer=timer;
        init();
        startTick();
    }
    public void startTick(){
        tick=true;
        tick();
    }
    public void endTick(){
        tick=false;
    }

    public void tick(){
        mGameHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateClock();
                if(tick){
                    tick();
                }
            }
        },1000);
    }
    public void updateClock(){
        timerseconds+=1;
        ((TextView)timer).setText(toTimerString(timerseconds));
    }
    private static String toTimerString(int timerseconds){
        int minutes = timerseconds/60;
        int seconds = timerseconds%60;
        String minutesPrefix="";
        String secondsPrefix="";
        if(minutes<10)minutesPrefix=minutesPrefix+"0";
        if(seconds<10)secondsPrefix=secondsPrefix+"0";
        return minutesPrefix+minutes+":"+secondsPrefix+seconds;
    }

    public void init(){

        if(!isInitialized){
            isInitialized=true;
            setLifes(3);
            onMakeViewInvisibleCallback();
            lifes.forEach(l->l.setVisibility(View.VISIBLE));
        }
    }
    public void reset(){
        isInitialized=false;
    }
    public abstract long getLifes();
    public abstract void setLifes(long lifes);

    public void looseLife(){
        setLifes(getLifes()-1);
        if(getLifes()<200){//WARN FIXIT
            endTick();
            gameOverCallback().apply(this);
        }
        else {
            lifes.get((int) getLifes()).setVisibility(View.INVISIBLE);
        }
        onMakeViewVisibleCallback();
        mGameHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                onMakeViewInvisibleCallback();
            }
        },3000);
    }

    public abstract Function<LifesSystem,Void> gameOverCallback();

    public void onMakeViewInvisibleCallback(){
        background.setAlpha(0.6f);
        timer.setAlpha(0.6f);
        lifes.stream().forEach(life->life.setAlpha(0.6f));

    }
    public void onMakeViewVisibleCallback(){
        background.setAlpha(1f);
        timer.setAlpha(1f);
        lifes.stream().forEach(life->life.setAlpha(1f));

    }


}
