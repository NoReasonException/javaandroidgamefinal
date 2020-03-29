package uk.ac.reading.sis05kol.engine.game.core.map;


import android.graphics.Bitmap;
import android.util.Log;

import java.util.HashMap;

import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;

public class Map {
    private HashMap<Position, Drawable> map;
    private String loggerTag="MAP";

    public Map() {
        this.map = new HashMap<>();
        Log.i(loggerTag,"Initialization Complete");
    }

    //TODO draw checks
    public Drawable getDrawableAtPosition(Position p ){
        Drawable d =map.get(p);
        Log.i(loggerTag,".getDrawableAtPosition "+p.toString()+" found object "+d);
        return d;
    }
    public Drawable setDrawableAtPosition(Position p, Drawable d ){
        Drawable prev = map.put(p,d);
        Log.i(loggerTag,".setDrawableAtPosition "+p.toString()+" overwrite object "+prev+" with "+d);
        return prev;

    }
    public Drawable moveDrawable(Position p , Drawable d){
        map.remove(d.getPosition());
        map.put(p,d);
        d.setPosition(p);
        Log.i(loggerTag,".moveDrawable moved object "+d+" to "+p.toString());
        return d;
    }

}
