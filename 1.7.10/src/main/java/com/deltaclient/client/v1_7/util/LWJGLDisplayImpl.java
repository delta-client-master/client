package com.deltaclient.client.v1_7.util;

import com.deltaclient.common.util.ILWJGLDisplay;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.opengl.Display;

public final class LWJGLDisplayImpl implements ILWJGLDisplay {
    @Override
    public void setTitle(@NotNull String title) {
        Display.setTitle(title);
    }
}
