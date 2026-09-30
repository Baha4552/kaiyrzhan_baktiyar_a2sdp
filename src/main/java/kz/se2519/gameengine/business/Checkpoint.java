package kz.se2519.gameengine.business;

public record Checkpoint(long frameId, String inputAction, String renderSignature, String audioSyncToken) {}
