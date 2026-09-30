package kz.se2519.gameengine.product.linux;

import kz.se2519.gameengine.family.LinuxFamily;
import kz.se2519.gameengine.product.*;

public final class LinuxVulkanRenderPipeline implements RenderPipeline<LinuxFamily> {
    private int lastQuality = 3;
    @Override public String family() { return "linux"; }
    @Override public RenderResult render(FrameData frame, RenderContext context) {
        lastQuality = context.qualityLevel();
        return new RenderResult(family(), frame.frameId(), frame.objectCount() + context.qualityLevel() * 3,
                context.qualityLevel(), "VK:" + frame.scene() + ":" + context.inputAction());
    }
    @Override public RenderMetrics metrics() { return new RenderMetrics(7.2, 100 + lastQuality * 6, 51); }
}
