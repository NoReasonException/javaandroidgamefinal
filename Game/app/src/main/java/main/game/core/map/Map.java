package main.game.core.map;


import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import main.game.core.object.Drawable;

/**
 * Map is considered only with tile coordinates, detects collisions
 */
public class Map {
        private final HashMap<Position, Drawable> map=new HashMap<>();

    private String loggerTag="MAP";

    public Map(Drawable inPortal, Drawable outPortal, Pair<Integer,Integer> tileCountXY) {

        map.put(new Position(0,0), inPortal);
        map.put(new Position(tileCountXY.first-1,tileCountXY.second-1),outPortal);

    }

    public Drawable getDrawableAtPosition(Position p ){
        synchronized (map){
            return map.get(p);
        }
    }

    /**
     * removes a drawable at given tile position
     * @param p the position
     * @param d the drawable
     * @return true at success
     */
    public boolean removeDrawable(Position p,Drawable d) {
        synchronized (map) {
            if (map.get(p) == d) {
                map.remove(p);
                return true;
            } else {
                Log.w(loggerTag, "invalid call of .removeDrawable by object " + d + " to " + p.toString() + " another object exists there");
            }
            return false;
        }
    }

    /**
     * returns true if exists any object at position given
     * @param p the position
     * @return true if exists
     */
    public boolean existsObjectAtPosition(Position p){
        synchronized (map){
            return map.containsKey(p);
        }
    }

    /**
     * draw a drawable
     * @param p the tile position
     * @param d the drawable
     * @return
     */
    public boolean setDrawableAtPosition(Position p, Drawable d ){
        synchronized (map){
            if(existsObjectAtPosition(p)){
                Log.w(loggerTag,"invalid call of .setDrawableAtPosition by object "+d+" to "+p.toString()+" another object exists there");
                return false;
            }
            map.put(p,d);
        }
        return true;

    }

    /**
     * move drawable
     * @param old the old tile position
     * @param newp the new tile position
     * @param d the drawable
     * @return true on success
     */
    public boolean moveDrawable(Position old,Position newp , Drawable d){
        synchronized (map){
            if(map.get(old)!=null &&
                    (map.get(old)!=d)||(map.get(newp)!=null&&map.get(newp)!=d)){
                Log.w(loggerTag,"invalid call of .moveDrawable by object "+d+" to "+newp.toString()+" another object exists there");
                return false;
            }else {
                map.remove(old);
                map.put(newp,d);
            }

            Log.i(loggerTag,".moveDrawable moved object "+d+" to "+newp.toString());
            return true;
        }
    }
    //why this overhead? in order to assure that the map object is always syncronised! we send a copy of keySet and
    //we not expose the real keySet()
    public List<Position> getDrawableObjects(){
        synchronized (map){
            return new ArrayList<>(map.keySet());
        }

    }
}
