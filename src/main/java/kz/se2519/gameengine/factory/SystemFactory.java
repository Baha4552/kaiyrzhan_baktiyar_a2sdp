package kz.se2519.gameengine.factory;

import kz.se2519.gameengine.product.*;

public interface SystemFactory<F extends FamilyTag> {
    RenderPipeline<F> createRenderPipeline();
    AudioEngine<F> createAudioEngine();
    InputController<F> createInputController();
}
