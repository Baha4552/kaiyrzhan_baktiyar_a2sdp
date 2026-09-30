package kz.se2519.gameengine.factory;

import kz.se2519.gameengine.family.AndroidFamily;
import kz.se2519.gameengine.product.RenderPipeline;
import kz.se2519.gameengine.product.android.AndroidOpenGLESRenderPipeline;

public final class AndroidRenderCreator extends RenderWorkflowCreator<AndroidFamily> {
    @Override protected RenderPipeline<AndroidFamily> factoryMethod() {
        return new AndroidOpenGLESRenderPipeline();
    }
}
