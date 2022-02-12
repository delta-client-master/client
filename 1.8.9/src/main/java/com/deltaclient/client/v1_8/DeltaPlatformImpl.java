package com.deltaclient.client.v1_8;

import com.deltaclient.client.v1_8.util.LWJGLUtilImpl;
import com.deltaclient.common.AbstractDeltaPlatform;
import com.deltaclient.common.util.AbstractLWJGLUtil;
import org.jetbrains.annotations.NotNull;

public final class DeltaPlatformImpl extends AbstractDeltaPlatform {
    public static final DeltaPlatformImpl INSTANCE = new DeltaPlatformImpl();

    private final LWJGLUtilImpl lwjglUtil = new LWJGLUtilImpl();

    @Override
    public @NotNull AbstractLWJGLUtil getLWJGLUtil() {
        return lwjglUtil;
    }
}
