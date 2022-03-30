package com.deltaclient.common;

import com.deltaclient.common.bridge.client.IMinecraftClientBridge;
import com.deltaclient.common.bridge.language.II18nBridge;
import com.deltaclient.common.bridge.session.ISessionBridge;
import com.deltaclient.common.bridge.session.ISessionFactory;
import com.deltaclient.common.bridge.util.IDrawableHelperBridge;
import com.deltaclient.common.command.CommandRegistry;
import com.deltaclient.common.command.impl.FeatureCommand;
import com.deltaclient.common.command.impl.LoginCommand;
import com.deltaclient.common.command.impl.arg.DraggableHUDFeatureArgumentProvider;
import com.deltaclient.common.config.FeatureConfig;
import com.deltaclient.common.feature.AbstractDraggableHUDFeature;
import com.deltaclient.common.feature.FeatureService;
import com.deltaclient.common.i18n.I18nService;
import com.deltaclient.common.msa.MSAAuthService;
import com.deltaclient.common.socket.WebSocketClient;
import com.deltaclient.common.util.ILWJGLDisplay;
import com.mojang.authlib.exceptions.AuthenticationException;
import net.arikia.dev.drpc.DiscordEventHandlers;
import net.arikia.dev.drpc.DiscordRichPresence;
import org.jetbrains.annotations.NotNull;

import static net.arikia.dev.drpc.DiscordRPC.*;

/**
 * All of this static shit is kinda getting on my nerves :z
 * TODO(For Lily): CLEAN!
 */
public final class Delta {
    private static final long START_TIMESTAMP = System.currentTimeMillis();

    public static IMinecraftClientBridge mc;

    public static ISessionFactory sessionFactory;

    public static ILWJGLDisplay lwjglDisplay;

    public static IDrawableHelperBridge drawableHelper;

    public static II18nBridge i18nBridge;

    public static boolean development = true; // TODO: Use a launch arg or something for this

    public static FeatureService featureService;

    private Delta() {
    }

    public static void onGameStart(@NotNull String version) {
        loginFromEnv();
        if (development) {
            CommandRegistry registry = new CommandRegistry();

            registry.bind(AbstractDraggableHUDFeature.class).toProvider(new DraggableHUDFeatureArgumentProvider());
            registry.register(new FeatureCommand(), "feature", "f", "feat");
            registry.register(new LoginCommand(), "login");
        }

        I18nService.INSTANCE.load();
        lwjglDisplay.setTitle(I18nService.INSTANCE.translate("client_name") + " " + version);

        // We only want features to load here, I think, could maybe load earlier, but it doesn't matter right now
        featureService = FeatureService.INSTANCE;

        FeatureConfig.INSTANCE.load();
        WebSocketClient socket = new WebSocketClient("ws://localhost:8080/auth");
        socket.connect();

        discordInitialize("944053798912024586", new DiscordEventHandlers(), true);
        discordRegister("944053798912024586", "");

        DiscordRichPresence presence = new DiscordRichPresence.Builder("Playing Minecraft " + version)
                .setStartTimestamps(START_TIMESTAMP)
                .setBigImage("client_logo", "Delta Client")
                .setSmallImage("company_logo", "Delta Studios LLC")
                .build();

        discordUpdatePresence(presence);
    }

    public static void onGameQuit() {
        FeatureConfig.INSTANCE.save();
    }

    public static void loginFromEnv() {
        if (System.getenv().containsKey("MC_CREDS")) {
            String[] creds = System.getenv("MC_CREDS").split(":");
            String type = creds[0];

            try {
                ISessionBridge session = null;
                if (type.equals("mojang")) {
                    session = sessionFactory.createMojangSession(creds[1], creds[2]);
                } else if (type.equals("msa")) {
                    String refresh = creds.length == 1 ? null : creds[1];
                    session = MSAAuthService.INSTANCE.doAuth(refresh, null);
                }

                mc.bridge$setSession(session);
            } catch (AuthenticationException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
