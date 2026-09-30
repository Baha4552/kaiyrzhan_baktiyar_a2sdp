package kz.se2519.gameengine;

import kz.se2519.gameengine.business.GameSession;
import kz.se2519.gameengine.factory.AndroidOpenGLESFactory;
import kz.se2519.gameengine.family.AndroidFamily;
import kz.se2519.gameengine.product.*;
import kz.se2519.gameengine.product.android.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AndroidFamilyTest {
    @Test void androidFactoryCreatesConcreteGlesProducts() {
        var f = new AndroidOpenGLESFactory();
        assertTrue(f.createRenderPipeline() instanceof AndroidOpenGLESRenderPipeline);
        assertTrue(f.createAudioEngine() instanceof AndroidOpenGLESAudioEngine);
        assertTrue(f.createInputController() instanceof AndroidOpenGLESInputController);
    }
    @Test void androidBusinessScenarioWorksWithoutBusinessLayerChanges() {
        GameSession<AndroidFamily> s = new GameSession<>(new AndroidOpenGLESFactory());
        var r = s.runInteractiveFrame(5,"TouchLevel",60,3);
        assertEquals("android", r.render().family());
        assertTrue(r.audio().syncToken().contains("GLES-SYNC:"));
    }
    @Test void androidInputProducesTouchOrMoveAction() {
        var input = new AndroidOpenGLESInputController();
        assertEquals("MOVE", input.map(input.poll()).name());
    }
}
