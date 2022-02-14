package com.deltaclient.common.bridge.game;

import com.deltaclient.common.bridge.lang.ILanguageManagerBridge;
import com.deltaclient.common.bridge.player.IClientPlayerEntityBridge;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IMinecraftClientBridge {
    @Nullable
    IClientPlayerEntityBridge getClientPlayer();

    @NotNull
    ILanguageManagerBridge getLanguageManager();

    long getWindowHandle();
}
