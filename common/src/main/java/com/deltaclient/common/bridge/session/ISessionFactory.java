package com.deltaclient.common.bridge.session;

import com.mojang.authlib.exceptions.AuthenticationException;
import org.jetbrains.annotations.NotNull;

public interface ISessionFactory {
    @NotNull
    ISessionBridge createMojangSession(@NotNull String email, @NotNull String password) throws AuthenticationException;
}
