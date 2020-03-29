package uk.ac.reading.sis05kol.engine.gameactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import uk.ac.reading.sis05kol.engine.R;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }
}
