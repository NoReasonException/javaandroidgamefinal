package uk.ac.reading.sis05kol.engine.game.core.interfaces;

import android.arch.core.util.Function;

abstract public class Action {
    Function<Void,Void> onSuccessCallback;
    Function<Void,Void> onFailureCallback;

    public boolean informSubscribersAndCleanup(boolean result){
        if(result&&onSuccessCallback!=null){
            onSuccessCallback.apply(null);
        }else if((!result)&&onFailureCallback!=null) {
            onFailureCallback.apply(null);
        }
        return result;
    }

    public Action(Function<Void, Void> onSuccessCallback, Function<Void, Void> onFailureCallback) {
        this.onSuccessCallback = onSuccessCallback;
        this.onFailureCallback = onFailureCallback;
    }
}
