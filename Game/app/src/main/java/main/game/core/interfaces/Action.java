package main.game.core.interfaces;

import android.arch.core.util.Function;


/**
 * this abstract class represents an arbitary Action , drawables may generate Actions in order to interact with each other
 */
abstract public class Action {
    Function<Void,Void> onSuccessCallback;
    Function<Void,Void> onFailureCallback;

    /**
     * boilerplate for callback handling
     * @param result if true then the successCallback will be called , if not , the failureCallback will be called
     * @return propagates the @param result , useful for direct returns
     */
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
