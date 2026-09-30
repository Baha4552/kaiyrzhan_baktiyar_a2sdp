package kz.se2519.gameengine;

import kz.se2519.gameengine.business.GameSession;
import kz.se2519.gameengine.config.FactoryProvider;
import kz.se2519.gameengine.factory.SystemFactory;
import kz.se2519.gameengine.product.FamilyTag;

import java.util.Map;

public final class Main {
    private Main() {}

    public static void main(String[] args) {
        SystemFactory<?> factory = FactoryProvider.fromConfig(args, Map.copyOf(System.getenv()));
        run(factory);
    }

    private static <F extends FamilyTag> void run(SystemFactory<F> factory) {
        GameSession<F> session = new GameSession<>(factory);
        System.out.println(session.runInteractiveFrame(1, "MainMenu", 80, 3));
        System.out.println(session.adaptQuality(2, "Battlefield", 250, 3));
        System.out.println(session.createCheckpoint(3, "BossRoom", 320, 3));
    }
}
