package main.game.core.score;

import android.arch.core.util.Function;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

abstract public class LifesSystem {
    private static LifesSystem instance;
    private boolean isInitialized=false;
    private boolean isGameOver=false;
    private boolean tick=false;
    private View background;
    private ArrayList<View>lifes;
    private View timer;
    private View moneyCounter;
    private Handler mGameHandler;
    private int timerseconds=0;
    private int money=12;



    public static void setInstance(LifesSystem lifesSystem){
        instance= lifesSystem;
    }
    public static LifesSystem getInstance() throws IllegalStateException{
        if(instance==null){
            throw new IllegalStateException("instance has not been set");
        }
       return instance;
    }

    public LifesSystem(Handler mGameHandler,View background, ArrayList<View>lifes,View timer,View moneyCounter) {
        this.mGameHandler=mGameHandler;
        this.background=background;
        this.lifes=lifes;
        this.timer=timer;
        this.moneyCounter=moneyCounter;
        init();
        startTick();
        updateMoneyCounter();
    }

    /**
     * starts the clock
     */
    public void startTick(){
        tick=true;
        tick();
    }

    /**
     * stops the clock
     */
    public void endTick(){
        tick=false;
    }

    /**
     * ticks the clock
     */
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

    /**
     * adds money to your account
     * @param amount
     */
    public void addMoney(int amount){
        money+=amount;
        updateMoneyCounter();

    }

    /**
     * retrieves the money from your account
     * @return
     */
    public int getMoney() {
        return money;
    }

    /**
     * remove money from your account
     * @param amount
     */
    public void removeMoney(int amount){
        money-=amount;
        updateMoneyCounter();
    }

    /**
     * when there is change in your account ,this updates the view
     */
    public void updateMoneyCounter(){
        mGameHandler.post(new Runnable() {
            @Override
            public void run() {
                ((TextView)moneyCounter).setText(money+"£");
            }
        });
    }

    /**
     * in every tick() , the view is updated
     */
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

    /**
     * initialization of LifesSystem
     */
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

    /**
     * callback when a life is lost
     */
    public void looseLife(){
        setLifes(getLifes()-1);
        if(getLifes()<0){
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

    //deprecated
    public void onMakeViewInvisibleCallback(){
        background.setAlpha(0.6f);
        timer.setAlpha(0.6f);
        lifes.stream().forEach(life->life.setAlpha(0.6f));

    }
    //deprecated
    public void onMakeViewVisibleCallback(){
        background.setAlpha(1f);
        timer.setAlpha(1f);
        lifes.stream().forEach(life->life.setAlpha(1f));
    }

    /**
     * gets the timer as shown on View
     * @return
     */
    public String getTimer() {
        return toTimerString(timerseconds);
    }
}
