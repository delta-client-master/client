package com.deltaclient.common;

import com.deltaclient.common.util.AbstractLWJGLUtil;
import org.jetbrains.annotations.NotNull;

// TODO: Event system using Consumers
// TODO: Basic mod system
// TODO: Properties system for mods
// TODO: Most mods are just text elements so we can probably do some easy abstraction for those
// Note: Most mods should be able to be written in this common module because we shouldn't have to rewrite a FPS mod 6 times, abstraction city is what we're after
// TODO: WebSocket communication
// TODO: Cosmetics base
// TODO: Friends
// TODO: Possibly Voice Chat
// TODO: Emote base

public abstract class AbstractDeltaPlatform {
    public abstract @NotNull AbstractLWJGLUtil getLWJGLUtil();
}