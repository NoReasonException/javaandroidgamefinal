package uk.ac.reading.sis05kol.engine.animations;

import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import uk.ac.reading.sis05kol.engine.R;

public class ButtonClickedAnimation implements View.OnTouchListener {

    private Resources resources;
    public ButtonClickedAnimation(Resources r) {
        this.resources=r;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        ImageView view1 = (ImageView) view;
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            view1.setImageDrawable(resources.getDrawable(R.drawable.button1));
        } else {
            view1.setImageDrawable(resources.getDrawable(R.drawable.button2));
        }
        return true;
    }
}
