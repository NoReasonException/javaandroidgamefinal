package uk.ac.reading.sis05kol.engine.game.core.map;


import android.util.Log;
import android.util.Pair;

import java.util.HashMap;

import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;

/**
 * Map is considered only with tile coordinates
 */
public class Map {
    private HashMap<Position, Drawable> map=new HashMap<>();

    private String loggerTag="MAP";

    public Map(Drawable inportal, Drawable outPortal, Pair<Integer,Integer> tileCountXY) {
        map.put(new Position(0,0),inportal);
        map.put(new Position(tileCountXY.first-1,tileCountXY.second-1),outPortal);

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
        map.put(p,d);
        Log.i(loggerTag,".moveDrawable moved object "+d+" to "+p.toString());
        return d;
    }

    public HashMap<Position, Drawable> getMap() {
        return map;
    }
}
