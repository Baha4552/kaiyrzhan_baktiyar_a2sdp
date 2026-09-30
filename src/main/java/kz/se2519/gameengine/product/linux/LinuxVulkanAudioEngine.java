package kz.se2519.gameengine.product.linux;

import kz.se2519.gameengine.family.LinuxFamily;
import kz.se2519.gameengine.product.*;

public final class LinuxVulkanAudioEngine implements AudioEngine<LinuxFamily> {
    @Override public String family() { return "linux"; }
    @Override public AudioResult play(AudioEvent event) { return new AudioResult(family(), event.frameId(), event.cue(), 4, "ALSA:" + event.frameId()); }
    @Override public AudioResult synchronize(long frameId, RenderResult renderResult) { return new AudioResult(family(), frameId, "sync", 3, "VK-SYNC:" + renderResult.signature()); }
}
