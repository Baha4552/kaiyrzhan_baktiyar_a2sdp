package kz.se2519.gameengine;

import kz.se2519.gameengine.factory.*;
import kz.se2519.gameengine.product.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FactoryFamiliesTest {
    @Test void windowsCreatesAllProducts() { assertFamily(new WindowsDirectXFactory(), "windows"); }
    @Test void linuxCreatesAllProducts() { assertFamily(new LinuxVulkanFactory(), "linux"); }
    @Test void macCreatesAllProducts() { assertFamily(new MacOSMetalFactory(), "mac"); }
    private <F extends FamilyTag> void assertFamily(SystemFactory<F> f, String id) {
        assertEquals(id, f.createRenderPipeline().family());
        assertEquals(id, f.createAudioEngine().family());
        assertEquals(id, f.createInputController().family());
    }
}
