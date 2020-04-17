package main.game.core.object.drawables.portals;

import android.content.Context;

import main.game.core.info.LevelInfo;
import main.game.core.map.Position;
import main.game.core.object.animator.DrawableAnimator;
import main.menuanimators.elements.Element;

public class BluePortal extends Portal {
    public BluePortal(Context context, Position absolutePosition, LevelInfo levelInfo) {
        super(context,absolutePosition,levelInfo,new DrawableAnimator(Element.BLUEPORTAL,context,levelInfo));
    }
}
