package kz.se2519.gameengine.product.android;

import kz.se2519.gameengine.family.AndroidFamily;
import kz.se2519.gameengine.product.*;

public final class AndroidOpenGLESAudioEngine implements AudioEngine<AndroidFamily> {
    @Override public String family() { return "android"; }
    @Override public AudioResult play(AudioEvent event) { return new AudioResult(family(), event.frameId(), event.cue(), 11, "AAUDIO:" + event.frameId()); }
    @Override public AudioResult synchronize(long frameId, RenderResult renderResult) { return new AudioResult(family(), frameId, "sync", 8, "GLES-SYNC:" + renderResult.signature()); }
}
