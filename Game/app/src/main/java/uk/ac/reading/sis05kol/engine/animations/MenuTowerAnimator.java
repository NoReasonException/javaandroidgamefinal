package uk.ac.reading.sis05kol.engine.animations;

import android.annotation.SuppressLint;
import android.arch.core.util.Function;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.ImageView;

import uk.ac.reading.sis05kol.engine.R;
import uk.ac.reading.sis05kol.engine.animations.elements.Element;

public class MenuTowerAnimator  implements Runnable {
    private Handler handler = new Handler();
    private long ms = 50;
    @SuppressLint("StaticFieldLeak")
    private ImageView menuTower;
    private int state=0;
    private boolean repeat=true;
    private int[]ids;
    private int max;
    private Element drawableElement;

    private Function<Integer,Drawable> getDrawable;
    public MenuTowerAnimator(ImageView menuTower, Function<Integer,Drawable> getDrawable,Element drawableElement) {
        //activate me as periodical task
        this.drawableElement=drawableElement;
        this.ids=drawableElement.ids;
        this.max=drawableElement.max;
        this.menuTower=menuTower;
        this.getDrawable=getDrawable;
        handler.postDelayed(this,ms);
    }

    public void kill(){
        synchronized (this){
            repeat=false;
        }

    }

    @Override
    public void run() {
        //submit to run as asyncTask
        state=((state+1)%max);
        new MenuAnimatorAsyncTask().execute(state,menuTower,getDrawable.apply(ids[state]));
        synchronized (this){
            if(repeat){
                handler.postDelayed(this,ms);
            }
        }


    }


}
class MenuAnimatorAsyncTask  extends AsyncTask<Object,Void,Void>{
    @Override
    protected Void doInBackground(Object... params) {
        int state = (Integer)params[0];
        ImageView menuTower=(ImageView)params[1];
        Drawable drawable=(Drawable)params[2];
        menuTower.setImageDrawable(drawable);
        return null;
    }
}