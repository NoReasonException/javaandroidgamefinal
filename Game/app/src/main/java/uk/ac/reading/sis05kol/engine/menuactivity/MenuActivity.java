package uk.ac.reading.sis05kol.engine.menuactivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.arch.core.util.Function;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import uk.ac.reading.sis05kol.engine.R;
import uk.ac.reading.sis05kol.engine.game.engine.GameActivity;
import uk.ac.reading.sis05kol.engine.menuactivity.menufragments.HandlersSet.MainMenuFragmentHandlers;
import uk.ac.reading.sis05kol.engine.menuactivity.menufragments.HandlersSet.SelectLevelFragmentHandlers;
import uk.ac.reading.sis05kol.engine.menuactivity.menufragments.MainMenuFragment;
import uk.ac.reading.sis05kol.engine.menuactivity.menufragments.SelectLevelFragment;

public class MenuActivity extends Activity {


    private String mainMenuTag="MAINMENUFRAGMENTTAG";
    private Fragment mainMenuFragment;
    private Fragment selectLevelFragment;
    private MainMenuFragmentHandlers mainMenuHandlers;
    private SelectLevelFragmentHandlers selectLevelHandlers;
    private Boolean allowCreateNewActivity=true;
    @SuppressLint({"ClickableViewAccessibility", "SourceLockedOrientationActivity"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main_activity);

        this.mainMenuHandlers = new MainMenuFragmentHandlers(
                this::playButtonAction,
                this::exitButtonAction
        );
        this.selectLevelHandlers = new SelectLevelFragmentHandlers(
                this::backButtonAction,
                this::level1ButtonAction,
                this::level2ButtonAction,
                this::level3ButtonAction,
                this::levelAutogenAction
        );

        this.mainMenuFragment=MainMenuFragment.newInstance(mainMenuHandlers,this::getDrawable);
        this.selectLevelFragment=SelectLevelFragment.newInstance(selectLevelHandlers);
        FrameLayout l = findViewById(R.id.menuContainer);
        FragmentTransaction tr = getFragmentManager().beginTransaction();
        tr.add(R.id.menuContainer, mainMenuFragment, mainMenuTag);
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
                //tr.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
                tr.replace(R.id.menuContainer,selectLevelFragment);

                tr.commitNow();
            }
        },50);
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        allowCreateNewActivity=true;
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
    public Void backButtonAction(Void b){
        new Handler().postDelayed(new Runnable() {
            @SuppressLint("ResourceType")
            @Override
            public void run() {
                FrameLayout l = findViewById(R.id.menuContainer);
                FragmentTransaction tr = getFragmentManager().beginTransaction();
                //tr.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left);
                tr.replace(R.id.menuContainer,mainMenuFragment);

                tr.commitNow();
            }
        },50);
        return null;
    }
    public Void level1ButtonAction(Void b){
        new Handler().postDelayed(new Runnable() {
            @SuppressLint("ResourceType")
            @Override
            public void run() {
                synchronized (this) {
                    if (allowCreateNewActivity) {
                        startActivity(new Intent(getApplicationContext(), GameActivity.class));
                        allowCreateNewActivity = false;
                    }
                }

            }
        },50);
        return null;
    }
    public Void level2ButtonAction(Void b){
        new Handler().postDelayed(new Runnable() {
            @SuppressLint("ResourceType")
            @Override
            public void run() {
                synchronized (this) {
                    if (allowCreateNewActivity) {
                        startActivity(new Intent(getApplicationContext(), GameActivity.class));
                        allowCreateNewActivity = false;
                    }
                }

            }
        },50);
        return null;
    }
    public Void level3ButtonAction(Void b){
        new Handler().postDelayed(new Runnable() {
            @SuppressLint("ResourceType")
            @Override
            public void run() {
                synchronized (this) {
                    if (allowCreateNewActivity) {
                        startActivity(new Intent(getApplicationContext(), GameActivity.class));
                        allowCreateNewActivity = false;
                    }
                }

            }
        },50);
        return null;
    }
    public Void levelAutogenAction(Void b){
        new Handler().postDelayed(new Runnable() {
            @SuppressLint("ResourceType")
            @Override
            public void run() {
                synchronized (this) {
                    if (allowCreateNewActivity) {
                        startActivity(new Intent(getApplicationContext(), GameActivity.class));
                        allowCreateNewActivity = false;
                    }
                }

            }
        },50);
        return null;
    }
}
