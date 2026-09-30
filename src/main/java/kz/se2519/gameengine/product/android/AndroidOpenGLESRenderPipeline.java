package kz.se2519.gameengine.product.android;

import kz.se2519.gameengine.family.AndroidFamily;
import kz.se2519.gameengine.product.*;

public final class AndroidOpenGLESRenderPipeline implements RenderPipeline<AndroidFamily> {
    private int lastQuality = 3;
    @Override public String family() { return "android"; }
    @Override public RenderResult render(FrameData frame, RenderContext context) {
        lastQuality = context.qualityLevel();
        return new RenderResult(family(), frame.frameId(), frame.objectCount() + context.qualityLevel() / 2,
                context.qualityLevel(), "GLES:" + frame.scene() + ":" + context.inputAction());
    }
    @Override public RenderMetrics metrics() { return new RenderMetrics(12.0, 70 + lastQuality * 3, 68); }
}
