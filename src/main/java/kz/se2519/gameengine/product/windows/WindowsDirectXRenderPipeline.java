package kz.se2519.gameengine.product.windows;

import kz.se2519.gameengine.family.WindowsFamily;
import kz.se2519.gameengine.product.*;

public final class WindowsDirectXRenderPipeline implements RenderPipeline<WindowsFamily> {
    private int lastQuality = 3;
    @Override public String family() { return "windows"; }
    @Override public RenderResult render(FrameData frame, RenderContext context) {
        lastQuality = context.qualityLevel();
        return new RenderResult(family(), frame.frameId(), frame.objectCount() + context.qualityLevel() * 2,
                context.qualityLevel(), "DX12:" + frame.scene() + ":" + context.inputAction());
    }
    @Override public RenderMetrics metrics() { return new RenderMetrics(8.5, 120 + lastQuality * 5, 58); }
}
