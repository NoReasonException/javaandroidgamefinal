package main.menuactivity.menufragments.HandlersSet;

import android.arch.core.util.Function;

public class MainMenuFragmentHandlers {

    private Function<Void,Void> playButtonCallback;
    private Function<Void,Void>exitButtonCallback;

    public MainMenuFragmentHandlers(Function<Void, Void> playButtonCallback, Function<Void, Void> exitButtonCallback) {
        this.playButtonCallback = playButtonCallback;
        this.exitButtonCallback = exitButtonCallback;
    }

    public Function<Void, Void> getPlayButtonCallback() {
        return playButtonCallback;
    }

    public Function<Void, Void> getExitButtonCallback() {
        return exitButtonCallback;
    }
}
