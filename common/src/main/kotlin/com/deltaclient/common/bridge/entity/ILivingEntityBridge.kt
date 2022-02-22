package com.deltaclient.common.bridge.entity

import com.deltaclient.common.bridge.effect.IStatusEffectInstanceBridge

@Suppress("INAPPLICABLE_JVM_NAME")
interface ILivingEntityBridge {
    @JvmName("bridge\$getStatusEffects")
    fun getStatusEffects(): Collection<IStatusEffectInstanceBridge>
}