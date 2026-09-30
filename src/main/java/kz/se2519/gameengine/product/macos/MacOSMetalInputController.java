package kz.se2519.gameengine.product.macos;

import kz.se2519.gameengine.family.MacOSFamily;
import kz.se2519.gameengine.product.*;

public final class MacOSMetalInputController implements InputController<MacOSFamily> {
    @Override public String family() { return "mac"; }
    @Override public InputState poll() { return new InputState(true, true, false, 720, 420); }
    @Override public InputAction map(InputState state) { return state.jump() ? new InputAction("JUMP", 2) : new InputAction("MOVE", 1); }
}
