package kz.se2519.gameengine.product.linux;

import kz.se2519.gameengine.family.LinuxFamily;
import kz.se2519.gameengine.product.*;

public final class LinuxVulkanInputController implements InputController<LinuxFamily> {
    @Override public String family() { return "linux"; }
    @Override public InputState poll() { return new InputState(false, true, false, 400, 300); }
    @Override public InputAction map(InputState state) { return state.jump() ? new InputAction("JUMP", 2) : new InputAction("MOVE", 1); }
}
