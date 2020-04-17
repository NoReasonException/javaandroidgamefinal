package main.menuanimators;

import android.annotation.SuppressLint;
import android.arch.core.util.Function;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ImageView;

import main.menuanimators.elements.Element;

public class MenuTowerAnimator  implements Runnable {
    private Handler handler = new Handler(Looper.getMainLooper());
    private long ms = 80;
    @SuppressLint("StaticFieldLeak")
    private ImageView menuTower;
    private int state = 0;
    private boolean repeat = true;
    private int[] ids;
    private int max;
    private Element drawableElement;

    private Function<Integer, Drawable> getDrawable;

    public MenuTowerAnimator(ImageView menuTower, Function<Integer, Drawable> getDrawable, Element drawableElement) {
        //activate me as periodical task
        this.drawableElement = drawableElement;
        this.ids = drawableElement.ids;
        this.max = drawableElement.max;
        this.menuTower = menuTower;
        this.getDrawable = getDrawable;
        handler.postDelayed(this, ms);
    }

    public void kill() {
        synchronized (this) {
            repeat = false;
        }
    }

    public void start() {
        synchronized (this) {
            repeat = true;
        }
        handler.postDelayed(this, ms);
    }

    @Override
    public void run() {
        //submit to run as asyncTask
        state = ((state + 1) % max);
        int term = drawableElement.terminal;
        synchronized (this) {
            if (repeat) {
                menuTower.setImageDrawable(getDrawable.apply(ids[state]));
                handler.postDelayed(this, ms);
            } else {
                Log.e("HERE","ok");
                menuTower.setImageDrawable(getDrawable.apply(ids[term]));
            }
        }
    }
}