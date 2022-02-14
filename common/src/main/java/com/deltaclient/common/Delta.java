package com.deltaclient.common;

import com.deltaclient.common.bridge.game.IMinecraftClientBridge;
import com.deltaclient.common.i18n.I18nService;
import com.deltaclient.common.util.ILWJGLDisplay;
import org.jetbrains.annotations.NotNull;

public final class Delta {
    public static IMinecraftClientBridge mc;

    public static ILWJGLDisplay lwjglDisplay;

    private Delta() {
    }

    public static void onGameStart(@NotNull String version) {
        I18nService.INSTANCE.load();
        lwjglDisplay.setTitle(I18nService.INSTANCE.translate("client_name") + " " + version);
    }
}
