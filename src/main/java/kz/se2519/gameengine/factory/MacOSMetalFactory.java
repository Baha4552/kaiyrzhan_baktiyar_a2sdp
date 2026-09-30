package kz.se2519.gameengine.factory;

import kz.se2519.gameengine.family.MacOSFamily;
import kz.se2519.gameengine.product.*;
import kz.se2519.gameengine.product.macos.*;

public final class MacOSMetalFactory implements SystemFactory<MacOSFamily> {
    @Override public RenderPipeline<MacOSFamily> createRenderPipeline() { return new MacOSMetalRenderPipeline(); }
    @Override public AudioEngine<MacOSFamily> createAudioEngine() { return new MacOSMetalAudioEngine(); }
    @Override public InputController<MacOSFamily> createInputController() { return new MacOSMetalInputController(); }
}
