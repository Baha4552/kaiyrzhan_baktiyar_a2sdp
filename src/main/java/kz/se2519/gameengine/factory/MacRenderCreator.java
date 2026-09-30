package kz.se2519.gameengine.factory;

import kz.se2519.gameengine.family.MacOSFamily;
import kz.se2519.gameengine.product.RenderPipeline;
import kz.se2519.gameengine.product.macos.MacOSMetalRenderPipeline;

public final class MacRenderCreator extends RenderWorkflowCreator<MacOSFamily> {
    @Override protected RenderPipeline<MacOSFamily> factoryMethod() {
        return new MacOSMetalRenderPipeline();
    }
}
