package kz.se2519.gameengine.factory;

import kz.se2519.gameengine.family.LinuxFamily;
import kz.se2519.gameengine.product.RenderPipeline;
import kz.se2519.gameengine.product.linux.LinuxVulkanRenderPipeline;

public final class LinuxRenderCreator extends RenderWorkflowCreator<LinuxFamily> {
    @Override protected RenderPipeline<LinuxFamily> factoryMethod() {
        return new LinuxVulkanRenderPipeline();
    }
}
