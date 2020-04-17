package uk.ac.reading.sis05kol.engine.menuanimators;

import android.arch.core.util.Function;
import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import uk.ac.reading.sis05kol.engine.R;

public class MainMenuButtonAnimatorOnce extends MainMenuButtonAnimator {
    private Resources resources;
    private Function<Void,Void> callback;
    private boolean alreadyCalled=false;
    public MainMenuButtonAnimatorOnce(Resources r,Function<Void,Void> callback) {
        super(r,callback);
        this.resources=r;
        this.callback=callback;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        ImageView view1 = (ImageView) view;
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN && !alreadyCalled) {
            alreadyCalled=true;
            view1.setImageDrawable(resources.getDrawable(R.drawable.button2));
            callback.apply(null);
        }
        return true;
    }
}
