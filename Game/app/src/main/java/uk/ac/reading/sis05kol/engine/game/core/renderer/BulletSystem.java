package uk.ac.reading.sis05kol.engine.game.core.renderer;

import android.arch.core.util.Function;

import java.util.ArrayList;
import java.util.List;

import uk.ac.reading.sis05kol.engine.game.core.object.Drawable;

public class BulletSystem {

    private final ArrayList<Drawable> bullets;
    private static BulletSystem instance;
    private String loggerTag="BULLETSYSTEM";

    public static BulletSystem getInstance(){
        return instance==null?(instance=new BulletSystem()):instance;
    }
    private BulletSystem() {
        this.bullets = new ArrayList<>();
    }

    public void subscribeBullet(Drawable d) {
        synchronized (bullets) {
            this.bullets.add(d);
        }
    }
    public void removeBullet(Drawable d) {
        synchronized (bullets) {
            this.bullets.remove(d);
        }
    }
    public void syncWithBulletSystem(Function<ArrayList<Drawable>,Void> function){
        synchronized (bullets){
            function.apply(bullets);
        }
    }

}
