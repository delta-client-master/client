package com.deltaclient.common;

import com.deltaclient.common.bridge.game.IMinecraftClientBridge;
import com.deltaclient.common.bridge.session.ISessionBridge;
import com.deltaclient.common.bridge.session.ISessionFactory;
import com.deltaclient.common.feature.FeatureService;
import com.deltaclient.common.i18n.I18nService;
import com.deltaclient.common.util.ILWJGLDisplay;
import com.mojang.authlib.exceptions.AuthenticationException;
import org.jetbrains.annotations.NotNull;

public final class Delta {
    public static IMinecraftClientBridge mc;

    public static ISessionFactory sessionFactory;

    public static ILWJGLDisplay lwjglDisplay;

    private Delta() {
    }

    public static void onGameStart(@NotNull String version) {
        I18nService.INSTANCE.load();
        lwjglDisplay.setTitle(I18nService.INSTANCE.translate("client_name") + " " + version);

        if (System.getenv().containsKey("MC_CREDS")) {
            String[] creds = System.getenv("MC_CREDS").split(":");
            String email = creds[0];
            String pass = creds[1];

            try {
                ISessionBridge session = sessionFactory.createMojangSession(email, pass);
                mc.bridge$setSession(session);
            } catch (AuthenticationException e) {
                throw new RuntimeException(e);
            }
        }

        // hack to load it
        FeatureService featureService = FeatureService.INSTANCE;
    }
}
