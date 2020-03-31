package uk.ac.reading.sis05kol.engine.game.core.map;


import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;

/**
 * Map is considered only with tile coordinates
 */
public class Map {
    private final HashMap<Position, Drawable> map=new HashMap<>();

    private String loggerTag="MAP";

    public Map(Drawable inportal, Drawable outPortal, Pair<Integer,Integer> tileCountXY) {
        map.put(new Position(0,0),inportal);
        map.put(new Position(tileCountXY.first-1,tileCountXY.second-1),outPortal);

    }

    //TODO draw checks
    public Drawable getDrawableAtPosition(Position p ){
        synchronized (map){
            Drawable d =map.get(p);
            Log.i(loggerTag,".getDrawableAtPosition "+p.toString()+" found object "+d);
            return d;
        }
    }
    public Drawable setDrawableAtPosition(Position p, Drawable d ){
        synchronized (map){
            Drawable prev = map.put(p,d);
            Log.i(loggerTag,".setDrawableAtPosition "+p.toString()+" overwrite object "+prev+" with "+d);
            return prev;
        }

    }
    public Drawable moveDrawable(Position p , Drawable d){
        synchronized (map){
            map.put(p,d);
            Log.i(loggerTag,".moveDrawable moved object "+d+" to "+p.toString());
            return d;
        }
    }
    //why this overhead? in order to assure that the map object is always syncronised! we send a copy of keySet and
    //we not expose the real keySet()
    public List<Position> getDrawableObjects(){
        return new ArrayList<>(map.keySet());
    }
}
