package com.deltaclient.common.util;

import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public final class BasicLazy<T> {
    private final Supplier<T> supplier;
    private T stored;

    public BasicLazy(@NotNull Supplier<T> supplier) {
        this.supplier = supplier;
    }

    @NotNull
    public T get() {
        if (stored == null) {
            stored = supplier.get();
        }

        return stored;
    }
}
