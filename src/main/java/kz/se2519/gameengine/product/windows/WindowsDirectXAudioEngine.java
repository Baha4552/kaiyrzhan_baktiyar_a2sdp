package kz.se2519.gameengine.product.windows;

import kz.se2519.gameengine.family.WindowsFamily;
import kz.se2519.gameengine.product.*;

public final class WindowsDirectXAudioEngine implements AudioEngine<WindowsFamily> {
    @Override public String family() { return "windows"; }
    @Override public AudioResult play(AudioEvent event) { return new AudioResult(family(), event.frameId(), event.cue(), 7, "WASAPI:" + event.frameId()); }
    @Override public AudioResult synchronize(long frameId, RenderResult renderResult) { return new AudioResult(family(), frameId, "sync", 5, "DX-SYNC:" + renderResult.signature()); }
}
