package kz.se2519.gameengine.anti;

import kz.se2519.gameengine.product.*;
import kz.se2519.gameengine.product.windows.*;
import kz.se2519.gameengine.product.linux.*;
import kz.se2519.gameengine.product.macos.*;

public final class LegacyGameClient {
    /*
     * PART A — intentional anti-pattern.
     *
     * Problem 1: the client depends directly on every concrete platform class.
     * Problem 2: adding another family requires editing this conditional.
     * Problem 3: creation logic is duplicated and there is no type-level guarantee
     * that the renderer/audio/input objects belong to one family.
     */
    public static String directRun(String platform) {
        RenderPipeline<?> renderer;
        AudioEngine<?> audio;
        InputController<?> input;

        if ("windows".equals(platform)) {
            renderer = new WindowsDirectXRenderPipeline();
            audio = new WindowsDirectXAudioEngine();
            input = new WindowsDirectXInputController();
        } else if ("linux".equals(platform)) {
            renderer = new LinuxVulkanRenderPipeline();
            audio = new LinuxVulkanAudioEngine();
            input = new LinuxVulkanInputController();
        } else if ("mac".equals(platform)) {
            renderer = new MacOSMetalRenderPipeline();
            audio = new MacOSMetalAudioEngine();
            input = new MacOSMetalInputController();
        } else {
            throw new IllegalArgumentException("Unknown platform: " + platform);
        }

        InputAction action = ((InputController) input).map(((InputController) input).poll());
        RenderResult frame = ((RenderPipeline) renderer).render(
                new FrameData(1, "LegacyScene", 50),
                new RenderContext(action.name(), 2));
        return ((AudioEngine) audio).synchronize(1, frame).syncToken();
    }
}
