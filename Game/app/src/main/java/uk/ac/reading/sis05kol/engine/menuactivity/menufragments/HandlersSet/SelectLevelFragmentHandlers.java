package uk.ac.reading.sis05kol.engine.menuactivity.menufragments.HandlersSet;

import android.arch.core.util.Function;

public class SelectLevelFragmentHandlers {
    private Function<Void, Void> backButtonAction;
    private Function<Void, Void> level1ButtonAction;
    private Function<Void, Void> level2ButtonAction;
    private Function<Void, Void> level3ButtonAction;
    private Function<Void, Void> levelAutogenButtonAction;

    public SelectLevelFragmentHandlers(Function<Void, Void> backButtonAction, Function<Void, Void> level1ButtonAction, Function<Void, Void> level2ButtonAction, Function<Void, Void> level3ButtonAction, Function<Void, Void> levelAutogenButtonAction) {
        this.backButtonAction = backButtonAction;
        this.level1ButtonAction = level1ButtonAction;
        this.level2ButtonAction = level2ButtonAction;
        this.level3ButtonAction = level3ButtonAction;
        this.levelAutogenButtonAction = levelAutogenButtonAction;
    }

    public Function<Void, Void> getBackButtonAction() {
        return backButtonAction;
    }

    public Function<Void, Void> getLevel1ButtonAction() {
        return level1ButtonAction;
    }

    public Function<Void, Void> getLevel2ButtonAction() {
        return level2ButtonAction;
    }

    public Function<Void, Void> getLevel3ButtonAction() {
        return level3ButtonAction;
    }

    public Function<Void, Void> getLevelAutogenButtonAction() {
        return levelAutogenButtonAction;
    }
}
