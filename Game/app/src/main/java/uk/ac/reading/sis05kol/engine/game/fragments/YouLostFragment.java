package uk.ac.reading.sis05kol.engine.game.fragments;

import android.app.AlertDialog;
import android.arch.core.util.Function;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Optional;

import uk.ac.reading.sis05kol.engine.R;
import uk.ac.reading.sis05kol.engine.connectivity.ScoreServerDriver;
import uk.ac.reading.sis05kol.engine.menuanimators.MainMenuButtonAnimator;
import uk.ac.reading.sis05kol.engine.menuanimators.MainMenuButtonAnimatorOnce;

public class YouLostFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private Function<Void,Void> playAgainCallback;
    private static String loggerTag="YOULOSTFRAGMENT";
    private Handler handler=new Handler();
    private String currentScore="00:00";

    private View parentView;
    public Function<Void, Void> getPlayAgainCallback() {
        return playAgainCallback;
    }

    public void setPlayAgainCallback(Function<Void, Void> playAgainCallback) {
        this.playAgainCallback = playAgainCallback;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View i = inflater.inflate(R.layout.fragment_you_lost, container, false);
        this.parentView=i;
        initializeHandlers(i);
        initializeScores(i);
        return i;
    }

    public void initializeHandlers(View i){
        /*View playAgainBtn = i.findViewById(R.id.again);*/
        View syncBtn = i.findViewById(R.id.sync);

        /*playAgainBtn.setOnTouchListener(
                new MainMenuButtonAnimator(
                        i.getResources(),
                        Optional
                                .ofNullable(playAgainCallback)
                                .orElse(new Function<Void,Void>(){

                                    @Override
                                    public Void apply(Void input) {
                                        Log.i(loggerTag,"IGNORE CALL");
                                        return null;
                                    }
                                })));*/
        syncBtn.setOnTouchListener(new MainMenuButtonAnimator(i.getResources(), new Function<Void, Void>() {
            @Override
            public Void apply(Void input) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        LayoutInflater li = LayoutInflater.from(getContext());
                        View promptsView = li.inflate(R.layout.prompts, null);
                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(parentView.getContext());

                        // set prompts.xml to alertdialog builder
                        alertDialogBuilder.setView(promptsView);

                        final EditText userInput = (EditText) promptsView
                                .findViewById(R.id.editTextDialogUserInput);

                        // set dialog message
                        alertDialogBuilder
                                .setCancelable(false)
                                .setPositiveButton("OK",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,int id) {

                                                ScoreServerDriver.setScore(userInput.getText().toString(),currentScore);
                                                handler.postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        initializeScores(parentView);
                                                    }
                                                },1000);

                                                updateCurrentUsersInfo(parentView,userInput.getText().toString(),currentScore);
                                            }
                                        })
                                .setNegativeButton("Cancel",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                dialog.cancel();
                                            }
                                        });

                        // create alert dialog
                        AlertDialog alertDialog = alertDialogBuilder.create();

                        // show it
                        alertDialog.show();

                    }
                });
                return null;
            }
        }));
    }

    public void updateCurrentUsersInfo(View i,String name,String time){

        TextView yourPositionName= i.findViewById(R.id.playerYourname);
        TextView yourPositionScore= i.findViewById(R.id.playerYourscore);
        yourPositionName.setText(name);
        yourPositionScore.setText(time);
    }

    public void initializeScores(View i){
        TextView firstPositionName = i.findViewById(R.id.player1name);
        TextView firstPositionScore= i.findViewById(R.id.player1score);
        TextView secondPositionName= i.findViewById(R.id.player2name);
        TextView secondPositionScore= i.findViewById(R.id.player2score);
        TextView thirdPositionName= i.findViewById(R.id.player3name);
        TextView thirdPositionScore= i.findViewById(R.id.player3score);



        Log.i(loggerTag,"update scores");
        ScoreServerDriver.getScores(handler, new Function<ArrayList<Pair<String, String>>, Void>() {
            @Override
            public Void apply(ArrayList<Pair<String, String>> scores) {


                if(scores.size()>0){
                    firstPositionName.setText("1)"+scores.get(0).first);
                    firstPositionScore.setText(scores.get(0).second);
                }else {
                    firstPositionName.setText("1)Empty");
                    firstPositionScore.setText("");
                }
                if(scores.size()>1){

                    secondPositionName.setText("2)"+scores.get(1).first);
                    secondPositionScore.setText(scores.get(1).second);
                }else {

                    secondPositionName.setText("2)Empty");
                    secondPositionScore.setText("");
                }
                if(scores.size()>2){

                    thirdPositionName.setText("3)"+scores.get(2).first);
                    thirdPositionScore.setText(scores.get(2).second);
                }else {
                    thirdPositionName.setText("3)Empty");
                    thirdPositionScore.setText("");
                }
                return null;
            }
        }, new Function<String, Void>() {
            @Override
            public Void apply(String input) {
                Log.e(loggerTag,"Failure to retrieve leaderboard :"+input);
                return null;
            }
        });
    }

    public void setCurrentScore(String currentScore) {
        this.currentScore = currentScore;
    }
}
