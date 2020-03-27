package uk.ac.reading.sis05kol.engine;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import uk.ac.reading.sis05kol.engine.animations.ButtonClickedAnimation;
import uk.ac.reading.sis05kol.engine.animations.MenuTowerAnimator;
import uk.ac.reading.sis05kol.engine.animations.elements.Element;

public class MainActivity extends Activity {

    @SuppressLint({"ClickableViewAccessibility", "SourceLockedOrientationActivity"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.main_activity);

        ImageView play = (ImageView)findViewById(R.id.play);
        ImageView options = (ImageView)findViewById(R.id.options);
        play.setOnTouchListener(new ButtonClickedAnimation(getResources()));
        options.setOnTouchListener(new ButtonClickedAnimation(getResources()));

        ImageView menuTower=findViewById(R.id.tower);
        MenuTowerAnimator animator = new MenuTowerAnimator(menuTower, this::getDrawable, Element.POISONATTACK);
        ImageView menuTower2=findViewById(R.id.tower2);
        MenuTowerAnimator animator2 = new MenuTowerAnimator(menuTower2, this::getDrawable, Element.STORMIDLE);




    }
}
