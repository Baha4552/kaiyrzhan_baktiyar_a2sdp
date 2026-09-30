package kz.se2519.gameengine;

import kz.se2519.gameengine.business.*;
import kz.se2519.gameengine.config.*;
import kz.se2519.gameengine.factory.*;
import kz.se2519.gameengine.product.*;
import kz.se2519.gameengine.family.*;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class BehaviorAndRuntimeTest {
    @Test void runtimeSelectsLinuxFromCli() {
        var f = FactoryProvider.fromConfig(new String[]{"--family=linux"}, Map.of());
        assertEquals("linux", f.createRenderPipeline().family());
    }
    @Test void cliOverridesEnvironment() {
        var f = FactoryProvider.fromConfig(new String[]{"--family=mac"}, Map.of("GAME_FAMILY","linux"));
        assertEquals("mac", f.createRenderPipeline().family());
    }
    @Test void runtimeSelectsAndroidFromEnvironment() {
        var f = FactoryProvider.fromConfig(new String[0], Map.of("GAME_FAMILY","android"));
        assertEquals("android", f.createRenderPipeline().family());
    }
    @Test void interactiveScenarioCombinesInputRenderAndAudio() {
        var s = new GameSession<>(new WindowsDirectXFactory());
        var r = s.runInteractiveFrame(7,"Battle",100,3);
        assertEquals("ATTACK", r.action());
        assertEquals(7, r.render().frameId());
        assertTrue(r.audio().syncToken().contains("DX-SYNC:"));
    }
    @Test void adaptiveScenarioChangesQualityWhenAndroidIsOverBudget() {
        var s = new GameSession<>(new AndroidOpenGLESFactory());
        var r = s.adaptQuality(8,"Battle",200,3);
        assertEquals(2, r.selectedQuality());
        assertTrue(r.reason().contains("reduce-quality"));
    }
    @Test void checkpointScenarioCombinesAllThreeProducts() {
        var s = new GameSession<>(new LinuxVulkanFactory());
        var c = s.createCheckpoint(9,"Boss",300,4);
        assertEquals(9,c.frameId());
        assertTrue(c.renderSignature().startsWith("VK:"));
        assertTrue(c.audioSyncToken().startsWith("VK-SYNC:"));
    }
    @Test void invalidFamilyIsRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> FactoryProvider.fromConfig(new String[]{"--family=playstation"}, Map.of()));
    }
    @Test void invalidEnvironmentIsRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> FactoryProvider.fromConfig(new String[0], Map.of("GAME_FAMILY","unknown")));
    }
    @Test void compatibilityIsEncodedByOneFactoryType() {
        SystemFactory<WindowsFamily> factory = new WindowsDirectXFactory();
        GameSession<WindowsFamily> session = new GameSession<>(factory);
        assertNotNull(session.runInteractiveFrame(1,"Menu",20,2));
    }
    @Test void macBusinessWorkflowUsesMacProducts() {
        var s = new GameSession<>(new MacOSMetalFactory());
        assertEquals("mac", s.createCheckpoint(2,"Menu",20,2).renderSignature().split(":")[0].equals("METAL") ? "mac" : "wrong");
    }
    @Test void factoryMethodCreatorExecutesRealWorkflow() {
        var creator = new WindowsRenderCreator();
        var r = creator.processWorkflow(new FrameData(3,"Arena",50),"ATTACK",3);
        assertTrue(r.signature().startsWith("DX12:"));
        assertEquals(3,r.frameId());
    }
    @Test void clientCanBeUsedOnlyThroughAbstractions() {
        SystemFactory<LinuxFamily> factory = new LinuxVulkanFactory();
        GameSession<LinuxFamily> client = new GameSession<>(factory);
        assertEquals("linux", client.runInteractiveFrame(4,"Menu",10,2).render().family());
    }
}
