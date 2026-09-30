package kz.se2519.gameengine.factory;

import kz.se2519.gameengine.product.*;

public abstract class RenderWorkflowCreator<F extends FamilyTag> {
    protected abstract RenderPipeline<F> factoryMethod();

    /**
     * Real business logic: the Creator owns the workflow while subclasses
     * decide which concrete RenderPipeline participates in it.
     */
    public final RenderResult processWorkflow(FrameData frame, String action, int quality) {
        RenderPipeline<F> renderer = factoryMethod();
        RenderContext context = new RenderContext(action, quality);
        RenderResult result = renderer.render(frame, context);
        RenderMetrics metrics = renderer.metrics();
        if (metrics.frameTimeMs() > 10.0 && quality > 1) {
            result = renderer.render(frame, new RenderContext(action, quality - 1));
        }
        return result;
    }
}
