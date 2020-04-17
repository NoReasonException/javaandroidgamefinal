package main.game.core.renderer;

import android.arch.core.util.Function;

import java.util.ArrayList;

import main.game.core.object.Drawable;

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

    /**
     * Enrolls a bullet
     * @param d the drawable bullet
     */
    public void subscribeBullet(Drawable d) {
        synchronized (bullets) {
            this.bullets.add(d);
        }
    }

    /**
     * removes a bullet
     * @param d the bullet to delete
     */
    public void removeBullet(Drawable d) {
        synchronized (bullets) {
            this.bullets.remove(d);
        }
    }

    /**
     * set callback to avoid ConcurrentModificationExceptions
     * @param function the callback
     */
    public void syncWithBulletSystem(Function<ArrayList<Drawable>,Void> function){
        synchronized (bullets){
            function.apply(bullets);
        }
    }


}
