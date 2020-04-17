package uk.ac.reading.sis05kol.engine.game.engine;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.arch.core.util.Function;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import uk.ac.reading.sis05kol.engine.R;
import uk.ac.reading.sis05kol.engine.game.TheGame;
import uk.ac.reading.sis05kol.engine.game.core.etc.DifficultyLevel;
import uk.ac.reading.sis05kol.engine.game.core.map.path.Path;
import uk.ac.reading.sis05kol.engine.game.core.object.drawables.towers.Tower;
import uk.ac.reading.sis05kol.engine.menuanimators.VariableOpaqueButtonAnimator;

public class GameActivity extends Activity {

    private static final int MENU_RESUME = 1;
    private static final int MENU_START = 2;
    private static final int MENU_STOP = 3;
    private String loggerTag="GAMEACTIVITY";

    private GameThread mGameThread;
    private GameView mGameView;
    private View progressViewBackground;
    private ArrayList<View> progressViewlifes;
    private View progressViewtimer;
    private View moneyCounter;
    private View fragmentView;
    private Handler handler=new Handler();

    private boolean onTowerSelection=false;

    private DifficultyLevel difficultyLevel=DifficultyLevel.NORMAL;
    private Path path;


    /** Called when the activity is first created. */
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.game_main);

        init(savedInstanceState);

    }

    void init(Bundle savedInstanceState){
        Bundle bundle = getIntent().getExtras();
        int id=bundle.getInt("difficultyLevel",-1);
        if(bundle!=null && id!=-1){
            difficultyLevel=DifficultyLevel.getDifficultyLevelFromID(id);
        }
        int pathid=bundle.getInt("pathId",-1);
        if(bundle!=null && id!=-1){
            path= Path.PathType.fromIdToPath(pathid).getPath();
        }
        //get references to all needed views
        mGameView = (GameView)findViewById(R.id.gamearea);
        mGameView.setStatusView((TextView)findViewById(R.id.text));
        mGameView.setScoreView((TextView)findViewById(R.id.score));
        progressViewBackground=findViewById(R.id.timerbackground);
        progressViewlifes=new ArrayList<>(Arrays.asList(findViewById(R.id.life1),findViewById(R.id.life2),findViewById(R.id.life3)));
        progressViewtimer=findViewById(R.id.timer);
        moneyCounter=findViewById(R.id.money);
        fragmentView=findViewById(R.id.youLostContainer);





        this.startGame(mGameView, null, savedInstanceState);
    }

    /**
     * starts the game by creating a new game thread and assign it to gameView
     * @param gView
     * @param gThread
     * @param savedInstanceState
     */
    private void startGame(GameView gView, GameThread gThread, Bundle savedInstanceState) {

        //Set up a new game, we don't care about previous states
        mGameThread = new TheGame(path,
                mGameView,
                progressViewBackground,
                progressViewlifes,
                progressViewtimer,
                moneyCounter,
                fragmentView,
                getFragmentManager().findFragmentById(R.id.youLostContainer),
                difficultyLevel,
                this::selectTowerDialogStart);
        mGameView.setThread(mGameThread);
        mGameThread.setState(GameThread.STATE_READY);
    }

    /*
     * Activity state functions
     */

    @Override
    protected void onPause() {
        super.onPause();
        if(mGameThread.getMode() == GameThread.STATE_RUNNING) {
            mGameThread.setState(GameThread.STATE_PAUSE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mGameThread.getMode() == GameThread.STATE_PAUSE) {
            mGameThread.setState(GameThread.STATE_RUNNING);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void destruct(){


        mGameView.cleanup();
        mGameThread = null;
        mGameView = null;
    }

    /*
     * UI Functions
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        menu.add(0, MENU_START, 0, R.string.menu_start);
        menu.add(0, MENU_STOP, 0, R.string.menu_stop);
        menu.add(0, MENU_RESUME, 0, R.string.menu_resume);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_START:
                mGameThread.doStart();
                return true;
            case MENU_STOP:
                mGameThread.setState(GameThread.STATE_LOSE,  getText(R.string.message_stopped));
                return true;
            case MENU_RESUME:
                mGameThread.unpause();
                return true;
        }

        return false;
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // Do nothing if nothing is selected
    }
    public Void selectTowerDialogStart(Function<Tower.TowerType,Void>onTowerSelectionCallback){
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(!onTowerSelection){
                    onTowerSelection=true;

                    LayoutInflater li = LayoutInflater.from(mGameView.getContext());
                    View view = li.inflate(R.layout.select_tower, null);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mGameView.getContext());

                    // set prompts.xml to alertdialog builder
                    alertDialogBuilder.setView(view);

                    final EditText userInput = (EditText) view
                            .findViewById(R.id.editTextDialogUserInput);

                    View fireView=view.findViewById(R.id.fireTower);
                    View poisonView=view.findViewById(R.id.poisonTower);
                    View stormView=view.findViewById(R.id.stormTower);
                    View iceView=view.findViewById(R.id.iceTower);



                    // set dialog message
                    alertDialogBuilder
                            .setCancelable(false);

                    // create alert dialog
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    fireView.setOnTouchListener(new VariableOpaqueButtonAnimator(fireView, new Function<Void, Void>() {
                        @Override
                        public Void apply(Void input) {
                            onTowerSelectionCallback.apply(Tower.TowerType.FIRE);
                            alertDialog.cancel();
                            setOnTowerSelection(false);
                            return null;
                        }
                    }));
                    poisonView.setOnTouchListener(new VariableOpaqueButtonAnimator(fireView, new Function<Void, Void>() {
                        @Override
                        public Void apply(Void input) {
                            onTowerSelectionCallback.apply(Tower.TowerType.POISON);
                            alertDialog.cancel();
                            setOnTowerSelection(false);
                            return null;
                        }
                    }));
                    stormView.setOnTouchListener(new VariableOpaqueButtonAnimator(fireView, new Function<Void, Void>() {
                        @Override
                        public Void apply(Void input) {
                            onTowerSelectionCallback.apply(Tower.TowerType.STORM);
                            alertDialog.cancel();
                            setOnTowerSelection(false);
                            return null;
                        }
                    }));
                    iceView.setOnTouchListener(new VariableOpaqueButtonAnimator(fireView, new Function<Void, Void>() {
                        @Override
                        public Void apply(Void input) {
                            onTowerSelectionCallback.apply(Tower.TowerType.ICE);
                            alertDialog.cancel();
                            setOnTowerSelection(false);
                            return null;
                        }
                    }));
                    // show it
                    alertDialog.show();

                }


            }
        });
        return null;
    }

    public void setOnTowerSelection(boolean onTowerSelection) {
        this.onTowerSelection = onTowerSelection;
    }
}

// This file is part of the course "Begin Programming: Build your first mobile game" from futurelearn.com
// Copyright: University of Reading and Karsten Lundqvist
// It is free software: you can redistribute it and/or modify
// it under the terms of the GNU General Public License as published by
// the Free Software Foundation, either version 3 of the License, or
// (at your option) any later version.
//
// It is is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
//
// You should have received a copy of the GNU General Public License
// along with it.  If not, see <http://www.gnu.org/licenses/>.