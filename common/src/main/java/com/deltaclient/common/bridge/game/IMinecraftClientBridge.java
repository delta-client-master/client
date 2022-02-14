package com.deltaclient.common.bridge.game;

import com.deltaclient.common.bridge.lang.ILanguageManagerBridge;
import com.deltaclient.common.bridge.player.IClientPlayerEntityBridge;
import com.deltaclient.common.bridge.session.ISessionBridge;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface IMinecraftClientBridge {
    @Nullable
    IClientPlayerEntityBridge getClientPlayer();

    @NotNull
    ILanguageManagerBridge getLanguageManager();

    @Nullable
    ISessionBridge getSession();

    void setSession(@Nullable ISessionBridge sessionBridge);

    long getWindowHandle();
}
