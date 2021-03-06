package main.menuactivity.menufragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import uk.ac.reading.sis05kol.engine.R;
import main.game.core.etc.DifficultyLevel;
import main.menuanimators.VariableOpaqueButtonAnimator;
import main.menuactivity.menufragments.HandlersSet.SelectLevelFragmentHandlers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectLevelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectLevelFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static String loggerTag="SELECTLEVELFRAGMENT";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private SelectLevelFragmentHandlers handlers;

    private DifficultyLevel difficultyLevel=DifficultyLevel.HARD;

    public SelectLevelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment SelectLevelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance(SelectLevelFragmentHandlers handlers) {
        SelectLevelFragment fragment = new SelectLevelFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.handlers=handlers;
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
        View i =inflater.inflate(R.layout.fragment_select_level, container, false);
        // Inflate the layout for this fragment
        setAnimators(i);
        return i;
    }
    @SuppressLint("ClickableViewAccessibility")
    public void setAnimators(View i){

        ImageView backBtn = i.findViewById(R.id.backbtn);
        ImageView level1 = i.findViewById(R.id.level1);
        ImageView level2 = i.findViewById(R.id.level2);
        ImageView level3 = i.findViewById(R.id.level3);
        ImageView levelautogen = i.findViewById(R.id.autogeneratedlevel);

        level1.setOnTouchListener(new VariableOpaqueButtonAnimator(level1,handlers.getLevel1ButtonAction()));
        level2.setOnTouchListener(new VariableOpaqueButtonAnimator(level2,handlers.getLevel2ButtonAction()));
        level3.setOnTouchListener(new VariableOpaqueButtonAnimator(level3,handlers.getLevel3ButtonAction()));
        levelautogen.setOnTouchListener(new VariableOpaqueButtonAnimator(levelautogen,handlers.getLevelAutogenButtonAction()));
        backBtn.setOnTouchListener(new VariableOpaqueButtonAnimator(backBtn,handlers.getBackButtonAction()));
    }
}
