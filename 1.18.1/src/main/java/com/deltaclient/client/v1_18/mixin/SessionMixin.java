package com.deltaclient.client.v1_18.mixin;

import com.deltaclient.common.bridge.session.ISessionBridge;
import net.minecraft.client.util.Session;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Session.class)
public class SessionMixin implements ISessionBridge {
}
