package kz.se2519.gameengine.business;

import kz.se2519.gameengine.factory.SystemFactory;
import kz.se2519.gameengine.product.*;

public final class GameSession<F extends FamilyTag> {
    private final RenderPipeline<F> renderer;
    private final AudioEngine<F> audio;
    private final InputController<F> input;

    public GameSession(SystemFactory<F> factory) {
        this.renderer = factory.createRenderPipeline();
        this.audio = factory.createAudioEngine();
        this.input = factory.createInputController();
    }

    /** Scenario 1: input -> action -> render -> audio synchronization. */
    public FrameReport runInteractiveFrame(long frameId, String scene, int objects, int quality) {
        InputState state = input.poll();
        InputAction action = input.map(state);
        FrameData frame = new FrameData(frameId, scene, objects);
        RenderResult render = renderer.render(frame, new RenderContext(action.name(), quality));
        AudioResult audioResult = audio.play(new AudioEvent(frameId, action.name(), action.magnitude()));
        AudioResult synchronizedAudio = audio.synchronize(frameId, render);
        return new FrameReport(frameId, action.name(), render,
                new AudioResult(audioResult.family(), audioResult.frameId(),
                        audioResult.cue(), audioResult.latencyMs(),
                        audioResult.syncToken() + "|" + synchronizedAudio.syncToken()));
    }

    /** Scenario 2: renderer metrics + audio latency produce an adaptive quality decision. */
    public AdaptiveQualityReport adaptQuality(long frameId, String scene, int objects, int requestedQuality) {
        InputAction action = input.map(input.poll());
        RenderResult render = renderer.render(
                new FrameData(frameId, scene, objects),
                new RenderContext(action.name(), requestedQuality));
        RenderMetrics metrics = renderer.metrics();
        AudioResult audioResult = audio.synchronize(frameId, render);
        int selected = requestedQuality;
        String reason = "balanced";
        if (metrics.frameTimeMs() > 10 || audioResult.latencyMs() > 8) {
            selected = Math.max(1, requestedQuality - 1);
            reason = "reduce-quality-for-frame-or-audio-budget";
        } else if (metrics.frameTimeMs() < 7 && audioResult.latencyMs() <= 5) {
            selected = Math.min(5, requestedQuality + 1);
            reason = "headroom-detected";
        }
        return new AdaptiveQualityReport(selected, metrics.frameTimeMs(),
                audioResult.latencyMs(), reason);
    }

    /** Scenario 3: input, rendering and audio produce one synchronized checkpoint. */
    public Checkpoint createCheckpoint(long frameId, String scene, int objects, int quality) {
        InputAction action = input.map(input.poll());
        RenderResult render = renderer.render(
                new FrameData(frameId, scene, objects),
                new RenderContext(action.name(), quality));
        AudioResult sync = audio.synchronize(frameId, render);
        return new Checkpoint(frameId, action.name(), render.signature(), sync.syncToken());
    }
}
