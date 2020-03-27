package uk.ac.reading.sis05kol.engine;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.core.util.Function;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import uk.ac.reading.sis05kol.engine.animations.ButtonClickedAnimation;
import uk.ac.reading.sis05kol.engine.animations.MenuTowerAnimator;
import uk.ac.reading.sis05kol.engine.animations.elements.Element;

public class MainActivity extends Activity {

    private MenuTowerAnimator silentAnimator;
    private boolean silentStatus=true;
    private MenuTowerAnimator difficultyAnimator;

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

        ImageView silentTower=findViewById(R.id.tower);

        silentTower.setOnClickListener((new View.OnClickListener() {

            private Function<Integer,Drawable> func;
            public View.OnClickListener init(android.arch.core.util.Function<Integer,Drawable> func){
                this.func=func;
                silentAnimator=new MenuTowerAnimator(silentTower, func, Element.STORMIDLE);
                return this;
            }
            @Override
            public void onClick(View view) {
                if(silentStatus){
                    silentStatus=false;
                    silentAnimator.kill();
                }else{
                    silentStatus=true;
                    silentAnimator.start();
                }
            }
        }).init(this::getDrawable));







    }
}
