package uk.ac.reading.sis05kol.engine;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.arch.core.util.Function;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import uk.ac.reading.sis05kol.engine.menufragments.MainMenuFragment;
import uk.ac.reading.sis05kol.engine.menufragments.SelectLevelFragment;

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
        tr.add(R.id.menuContainer, MainMenuFragment.newInstance("A", "B", this::getDrawable,this::playButtonAction,this::exitButtonAction), mainMenuTag);
        tr.commitNow();

        final Function<Integer, Drawable> func = this::getDrawable;


    }
    public Void playButtonAction(Void b){
        new Handler().postDelayed(new Runnable() {
            @SuppressLint("ResourceType")
            @Override
            public void run() {
                FrameLayout l = findViewById(R.id.menuContainer);
                FragmentTransaction tr = getFragmentManager().beginTransaction();
                tr.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
                Fragment fragment =getFragmentManager().findFragmentById(R.id.menuContainer);
                Fragment replace = SelectLevelFragment.newInstance("A","B");
                tr.replace(R.id.menuContainer,replace);

                tr.commitNow();
            }
        },50);
        return null;
    }
    public Void exitButtonAction(Void b){
        new Handler().postDelayed(new Runnable() {
            @SuppressLint("ResourceType")
            @Override
            public void run() {
                finishAndRemoveTask();
            }
        },50);
        return null;
    }
}
