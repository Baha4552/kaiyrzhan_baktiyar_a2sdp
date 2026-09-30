package kz.se2519.gameengine.product.macos;

import kz.se2519.gameengine.family.MacOSFamily;
import kz.se2519.gameengine.product.*;

public final class MacOSMetalRenderPipeline implements RenderPipeline<MacOSFamily> {
    private int lastQuality = 3;
    @Override public String family() { return "mac"; }
    @Override public RenderResult render(FrameData frame, RenderContext context) {
        lastQuality = context.qualityLevel();
        return new RenderResult(family(), frame.frameId(), frame.objectCount() + context.qualityLevel(),
                context.qualityLevel(), "METAL:" + frame.scene() + ":" + context.inputAction());
    }
    @Override public RenderMetrics metrics() { return new RenderMetrics(6.4, 90 + lastQuality * 4, 45); }
}
