package com.deltaclient.common.util;

import java.util.function.Supplier;

public final class BasicLazy<T> {
    private final Supplier<T> supplier;
    private T stored;

    public BasicLazy(Supplier<T> supplier) {
        this.supplier = supplier;
    }

    public T get() {
        if (stored == null) {
            stored = supplier.get();
        }

        return stored;
    }
}
