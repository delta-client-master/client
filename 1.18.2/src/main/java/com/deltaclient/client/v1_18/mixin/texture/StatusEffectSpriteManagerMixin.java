package com.deltaclient.client.v1_18.mixin.texture;

import com.deltaclient.common.bridge.effect.IStatusEffectBridge;
import com.deltaclient.common.bridge.texture.ISpriteBridge;
import com.deltaclient.common.bridge.texture.IStatusEffectSpriteManagerBridge;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.StatusEffectSpriteManager;
import net.minecraft.entity.effect.StatusEffect;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(StatusEffectSpriteManager.class)
public abstract class StatusEffectSpriteManagerMixin implements IStatusEffectSpriteManagerBridge {
    @Shadow
    public abstract Sprite getSprite(StatusEffect effect);

    @Override
    public @NotNull ISpriteBridge bridge$getSprite(@NotNull IStatusEffectBridge effect) {
        return (ISpriteBridge) getSprite((StatusEffect) effect);
    }
}
