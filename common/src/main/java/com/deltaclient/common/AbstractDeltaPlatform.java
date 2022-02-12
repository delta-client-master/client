package com.deltaclient.common;

import com.deltaclient.common.util.AbstractLWJGLUtil;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractDeltaPlatform {
    public abstract @NotNull AbstractLWJGLUtil getLWJGLUtil();
}