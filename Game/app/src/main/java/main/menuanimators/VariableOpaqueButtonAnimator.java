package main.menuanimators;

import android.arch.core.util.Function;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class VariableOpaqueButtonAnimator implements View.OnTouchListener {

    private View v;
    private float x;
    private float y;
    private boolean pressed=false;
    private Function<Void,Void> callback;
    public VariableOpaqueButtonAnimator(View v, Function<Void,Void>callback) {
        this.x=v.getScaleX();
        this.y=v.getScaleY();
        this.callback=callback;

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        ImageView view1 = (ImageView) view;
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            view1.setAlpha(1f);
            view.setScaleX(x);
            view.setScaleY(y);
            pressed=false;
            this.callback.apply(null);

        } else if(!pressed) {
            view1.setAlpha(0.5f);
            view.setScaleX(view.getScaleX()*0.95f);
            view.setScaleY(view.getScaleY()*0.95f);
            pressed=true;
        }
        return true;
    }
}
