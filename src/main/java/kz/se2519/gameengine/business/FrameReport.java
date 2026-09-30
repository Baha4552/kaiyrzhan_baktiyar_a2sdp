package kz.se2519.gameengine.business;

import kz.se2519.gameengine.product.*;

public record FrameReport(long frameId, String action, RenderResult render, AudioResult audio) {}
