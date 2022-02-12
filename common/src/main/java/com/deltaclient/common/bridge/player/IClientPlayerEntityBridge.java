package com.deltaclient.common.bridge.player;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public interface IClientPlayerEntityBridge {
    @NotNull String getUsername();

    @NotNull UUID getUUID();
}
