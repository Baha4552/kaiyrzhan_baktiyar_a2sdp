package kz.se2519.gameengine.factory;

import kz.se2519.gameengine.family.WindowsFamily;
import kz.se2519.gameengine.product.RenderPipeline;
import kz.se2519.gameengine.product.windows.WindowsDirectXRenderPipeline;

public final class WindowsRenderCreator extends RenderWorkflowCreator<WindowsFamily> {
    @Override protected RenderPipeline<WindowsFamily> factoryMethod() {
        return new WindowsDirectXRenderPipeline();
    }
}
