package uk.ac.reading.sis05kol.engine.game.core.interfaces.actions;

import uk.ac.reading.sis05kol.engine.game.core.map.Map;

public class IdleAction extends Action {
    @Override
    public boolean performAction(Map map) {
        return true;
    }
}
