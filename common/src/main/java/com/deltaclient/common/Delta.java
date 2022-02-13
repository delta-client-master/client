package com.deltaclient.common;

import com.deltaclient.common.bridge.game.IMinecraftClientBridge;
import com.deltaclient.common.util.ILWJGLDisplay;
import org.jetbrains.annotations.NotNull;

public final class Delta {
    public static IMinecraftClientBridge mc;

    public static ILWJGLDisplay lwjglDisplay;

    private Delta() {
    }

    public static void onGameStart(@NotNull String version) {
        lwjglDisplay.setTitle("Delta " + version);
    }
}
