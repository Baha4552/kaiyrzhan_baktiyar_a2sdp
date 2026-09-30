package kz.se2519.gameengine.product.android;

import kz.se2519.gameengine.family.AndroidFamily;
import kz.se2519.gameengine.product.*;

public final class AndroidOpenGLESInputController implements InputController<AndroidFamily> {
    @Override public String family() { return "android"; }
    @Override public InputState poll() { return new InputState(true, false, false, 360, 640); }
    @Override public InputAction map(InputState state) { return state.move() ? new InputAction("MOVE", 1) : new InputAction("IDLE", 0); }
}
