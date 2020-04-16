package uk.ac.reading.sis05kol.engine.menuanimators;

import android.arch.core.util.Function;
import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import uk.ac.reading.sis05kol.engine.R;

public class MainMenuButtonAnimator implements View.OnTouchListener {

    private Resources resources;
    private Function<Void,Void> callback;
    public MainMenuButtonAnimator(Resources r,Function<Void,Void> callback) {
        this.resources=r;
        this.callback=callback;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        ImageView view1 = (ImageView) view;
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            view1.setImageDrawable(resources.getDrawable(R.drawable.button1));
        } else {
            view1.setImageDrawable(resources.getDrawable(R.drawable.button2));
            callback.apply(null);
        }
        return true;
    }
}
