package com.deltaclient.client.v1_18.mixin.texture;

import com.deltaclient.common.bridge.texture.ISpriteBridge;
import com.deltaclient.common.bridge.util.IIdentifierBridge;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Sprite.class)
public class SpriteMixin implements ISpriteBridge {
    @Shadow
    @Final
    private SpriteAtlasTexture atlas;

    @NotNull
    @Override
    public IIdentifierBridge bridge$getAtlas() {
        return (IIdentifierBridge) atlas.getId();
    }
}
