package kz.se2519.gameengine.product;

public record RenderResult(String family, long frameId, int drawCalls, int qualityLevel, String signature) {}
