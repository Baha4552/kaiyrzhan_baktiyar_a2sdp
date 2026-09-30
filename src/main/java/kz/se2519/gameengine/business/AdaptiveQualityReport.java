package kz.se2519.gameengine.business;

public record AdaptiveQualityReport(int selectedQuality, double frameTimeMs, int audioLatencyMs, String reason) {}
