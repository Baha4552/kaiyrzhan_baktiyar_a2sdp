package kz.se2519.gameengine.product;

public record AudioResult(String family, long frameId, String cue, int latencyMs, String syncToken) {}
