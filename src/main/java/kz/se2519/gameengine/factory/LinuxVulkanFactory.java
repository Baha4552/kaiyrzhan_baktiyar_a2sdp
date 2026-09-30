package kz.se2519.gameengine.factory;

import kz.se2519.gameengine.family.LinuxFamily;
import kz.se2519.gameengine.product.*;
import kz.se2519.gameengine.product.linux.*;

public final class LinuxVulkanFactory implements SystemFactory<LinuxFamily> {
    @Override public RenderPipeline<LinuxFamily> createRenderPipeline() { return new LinuxVulkanRenderPipeline(); }
    @Override public AudioEngine<LinuxFamily> createAudioEngine() { return new LinuxVulkanAudioEngine(); }
    @Override public InputController<LinuxFamily> createInputController() { return new LinuxVulkanInputController(); }
}
