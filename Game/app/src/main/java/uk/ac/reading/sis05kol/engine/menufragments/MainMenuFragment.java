package uk.ac.reading.sis05kol.engine.menufragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.arch.core.util.Function;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import uk.ac.reading.sis05kol.engine.R;
import uk.ac.reading.sis05kol.engine.animations.MainMenuButtonAnimator;
import uk.ac.reading.sis05kol.engine.animations.MenuTowerAnimator;
import uk.ac.reading.sis05kol.engine.animations.elements.Element;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenuFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private MenuTowerAnimator silentAnimator;
    private boolean silentStatus=true;
    private MenuTowerAnimator difficultyAnimator;

    private Function<Integer,Drawable> getDrawable;
    private Function<Void,Void>playButtonCallback;
    private Function<Void,Void>exitButtonCallback;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainMenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance(String param1, String param2,Function<Integer,Drawable>getDrawable,Function<Void,Void>playButtonCallback,Function<Void,Void>exitButtonCallback) {

        MainMenuFragment fragment = new MainMenuFragment();
        Bundle args = new Bundle();
        fragment.getDrawable=getDrawable;
        fragment.playButtonCallback=playButtonCallback;
        fragment.exitButtonCallback=exitButtonCallback;
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View i = inflater.inflate(R.layout.fragment_main_menu, container, false);

        this.setUpListeners(i);
        this.setUpAnimators(i);
        return i;
    }


    @SuppressLint("ClickableViewAccessibility")
    public void setUpListeners(View i){
        ImageView play = (ImageView)i.findViewById(R.id.play);
        ImageView exit = (ImageView)i.findViewById(R.id.options);

        play.setOnTouchListener(new MainMenuButtonAnimator(getResources(),playButtonCallback));
        play.setOnClickListener(new View.OnClickListener() {

            private Function<Void,Void>playButtonCallback;
            public View.OnClickListener init(Function<Void,Void>playButtonCallback){
                this.playButtonCallback=playButtonCallback;
                return this;
            }
            @Override
            public void onClick(View view) {

                this.playButtonCallback.apply(null);
            }
        }.init(this.playButtonCallback));
        exit.setOnTouchListener(new MainMenuButtonAnimator(getResources(),exitButtonCallback));
    }
    public void setUpAnimators(View i){
        ImageView silentTower=i.findViewById(R.id.tower);

        silentTower.setOnClickListener((new View.OnClickListener() {

            private Function<Integer, Drawable> func;
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
        }).init(getDrawable));
    }
}