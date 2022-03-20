package com.deltaclient.client.v1_18.mixin;

import com.deltaclient.common.bridge.session.ISessionBridge;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.util.Session;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Session.class)
public abstract class SessionMixin implements ISessionBridge {
    @Shadow public abstract GameProfile getProfile();

    @Shadow @Final private String accessToken;

    @Shadow @Final private String username;

    @NotNull
    @Override
    public String bridge$getUsername() {
        return username;
    }

    @NotNull
    @Override
    public GameProfile bridge$getProfile() {
        return getProfile();
    }

    @NotNull
    @Override
    public String bridge$getAccessToken() {
        return accessToken;
    }
}
