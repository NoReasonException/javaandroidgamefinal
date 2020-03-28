package uk.ac.reading.sis05kol.engine.animations;

import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import uk.ac.reading.sis05kol.engine.R;

public class SelectLevelButtonAnimator implements View.OnTouchListener {

    private View v;
    private float x;
    private float y;
    private boolean pressed=false;
    public SelectLevelButtonAnimator(View v) {
        this.x=v.getScaleX();
        this.y=v.getScaleY();

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        ImageView view1 = (ImageView) view;
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            view1.setAlpha(1f);
            view.setScaleX(x);
            view.setScaleY(y);
            pressed=false;

        } else if(!pressed) {
            view1.setAlpha(0.5f);
            view.setScaleX(view.getScaleX()*0.95f);
            view.setScaleY(view.getScaleY()*0.95f);
            pressed=true;
        }
        return true;
    }
}
