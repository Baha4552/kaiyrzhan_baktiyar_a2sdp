package kz.se2519.gameengine.factory;

import kz.se2519.gameengine.family.AndroidFamily;
import kz.se2519.gameengine.product.*;
import kz.se2519.gameengine.product.android.*;

public final class AndroidOpenGLESFactory implements SystemFactory<AndroidFamily> {
    @Override public RenderPipeline<AndroidFamily> createRenderPipeline() { return new AndroidOpenGLESRenderPipeline(); }
    @Override public AudioEngine<AndroidFamily> createAudioEngine() { return new AndroidOpenGLESAudioEngine(); }
    @Override public InputController<AndroidFamily> createInputController() { return new AndroidOpenGLESInputController(); }
}
