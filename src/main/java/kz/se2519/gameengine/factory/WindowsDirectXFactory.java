package kz.se2519.gameengine.factory;

import kz.se2519.gameengine.family.WindowsFamily;
import kz.se2519.gameengine.product.*;
import kz.se2519.gameengine.product.windows.*;

public final class WindowsDirectXFactory implements SystemFactory<WindowsFamily> {
    @Override public RenderPipeline<WindowsFamily> createRenderPipeline() { return new WindowsDirectXRenderPipeline(); }
    @Override public AudioEngine<WindowsFamily> createAudioEngine() { return new WindowsDirectXAudioEngine(); }
    @Override public InputController<WindowsFamily> createInputController() { return new WindowsDirectXInputController(); }
}
