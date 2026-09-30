package kz.se2519.gameengine.product;

public interface RenderPipeline<F extends FamilyTag> {
    String family();
    RenderResult render(FrameData frame, RenderContext context);
    RenderMetrics metrics();
}
