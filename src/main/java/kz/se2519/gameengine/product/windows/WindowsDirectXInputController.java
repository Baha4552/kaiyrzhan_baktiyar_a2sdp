package kz.se2519.gameengine.product.windows;

import kz.se2519.gameengine.family.WindowsFamily;
import kz.se2519.gameengine.product.*;

public final class WindowsDirectXInputController implements InputController<WindowsFamily> {
    @Override public String family() { return "windows"; }
    @Override public InputState poll() { return new InputState(true, false, true, 640, 360); }
    @Override public InputAction map(InputState state) { return state.attack() ? new InputAction("ATTACK", 3) : new InputAction("MOVE", 1); }
}
