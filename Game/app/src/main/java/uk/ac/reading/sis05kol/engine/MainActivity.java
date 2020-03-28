package uk.ac.reading.sis05kol.engine;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.arch.core.util.Function;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

import uk.ac.reading.sis05kol.engine.animations.ButtonClickedAnimation;
import uk.ac.reading.sis05kol.engine.animations.MenuTowerAnimator;
import uk.ac.reading.sis05kol.engine.animations.elements.Element;
import uk.ac.reading.sis05kol.engine.menufragments.MainMenuFragment;

public class MainActivity extends Activity {


    private String mainMenuTag="MAINMENUFRAGMENTTAG";
    @SuppressLint({"ClickableViewAccessibility", "SourceLockedOrientationActivity"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main_activity);

        FrameLayout l = findViewById(R.id.menuContainer);
        FragmentTransaction tr = getFragmentManager().beginTransaction();
        tr.add(R.id.menuContainer, MainMenuFragment.newInstance("A","B",this::getDrawable),mainMenuTag);
        tr.commitNow();





    }
}
