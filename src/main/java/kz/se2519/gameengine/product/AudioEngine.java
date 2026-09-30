package kz.se2519.gameengine.product;

public interface AudioEngine<F extends FamilyTag> {
    String family();
    AudioResult play(AudioEvent event);
    AudioResult synchronize(long frameId, RenderResult renderResult);
}
