package com.deltaclient.client.v1_8.util;

import com.deltaclient.common.util.AbstractLWJGLUtil;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.Display;

public final class LWJGLUtilImpl extends AbstractLWJGLUtil {
    @Override
    public void setTitle(@NotNull String title) {
        Display.setTitle(title);
    }
}
