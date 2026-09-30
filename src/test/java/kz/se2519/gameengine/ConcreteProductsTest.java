package kz.se2519.gameengine;

import kz.se2519.gameengine.factory.*;
import kz.se2519.gameengine.product.*;
import kz.se2519.gameengine.product.windows.*;
import kz.se2519.gameengine.product.linux.*;
import kz.se2519.gameengine.product.macos.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ConcreteProductsTest {
    @Test void windowsRendererUsesDirectXSignature() {
        var r = new WindowsDirectXRenderPipeline().render(new FrameData(1,"A",10), new RenderContext("MOVE",3));
        assertTrue(r.signature().startsWith("DX12:"));
    }
    @Test void linuxRendererUsesVulkanSignature() {
        var r = new LinuxVulkanRenderPipeline().render(new FrameData(1,"A",10), new RenderContext("JUMP",3));
        assertTrue(r.signature().startsWith("VK:"));
    }
    @Test void macRendererUsesMetalSignature() {
        var r = new MacOSMetalRenderPipeline().render(new FrameData(1,"A",10), new RenderContext("JUMP",3));
        assertTrue(r.signature().startsWith("METAL:"));
    }
    @Test void windowsAudioUsesWasapi() {
        assertTrue(new WindowsDirectXAudioEngine().play(new AudioEvent(1,"ATTACK",3)).syncToken().startsWith("WASAPI:"));
    }
}
