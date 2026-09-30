package kz.se2519.gameengine.product.macos;

import kz.se2519.gameengine.family.MacOSFamily;
import kz.se2519.gameengine.product.*;

public final class MacOSMetalAudioEngine implements AudioEngine<MacOSFamily> {
    @Override public String family() { return "mac"; }
    @Override public AudioResult play(AudioEvent event) { return new AudioResult(family(), event.frameId(), event.cue(), 3, "COREAUDIO:" + event.frameId()); }
    @Override public AudioResult synchronize(long frameId, RenderResult renderResult) { return new AudioResult(family(), frameId, "sync", 2, "MTL-SYNC:" + renderResult.signature()); }
}
