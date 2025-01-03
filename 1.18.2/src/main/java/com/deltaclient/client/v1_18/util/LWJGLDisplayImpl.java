package com.deltaclient.client.v1_18.util;

import com.deltaclient.common.DeltaClient;
import com.deltaclient.common.util.ILWJGLDisplay;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

public final class LWJGLDisplayImpl implements ILWJGLDisplay {
    @Override
    public void setTitle(@NotNull String title) {
        GLFW.glfwSetWindowTitle(DeltaClient.INSTANCE.getMc().bridge$getWindowHandle(), title);
    }
}
