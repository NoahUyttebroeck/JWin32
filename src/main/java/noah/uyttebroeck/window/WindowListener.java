package noah.uyttebroeck.window;

import com.sun.jna.platform.win32.Win32VK;

public interface WindowListener {

    default boolean onInitialize() {
        return true;
    }

    default boolean onDestroy() {
        return true;
    }

    default boolean onKeyUp(Win32VK key) {
        return true;
    }

    default boolean onKeyDown(Win32VK key) {
        return true;
    }

    default boolean onResize(int width, int height) {
        return true;
    }

    default boolean onMaximize() {
        return true;
    }

    default boolean onMinimize() {
        return true;
    }

    default boolean onShow() {
        return true;
    }

    default boolean onLeftMouseButtonDown() {
        return true;
    }

    default boolean onLeftMouseButtonUp() {
        return true;
    }

    default boolean onRightMouseButtonDown() {
        return true;
    }

    default boolean onRightMouseButtonUp() {
        return true;
    }

    default boolean onMiddleMouseButtonDown() {
        return true;
    }

    default boolean onMiddleMouseButtonUp() {
        return true;
    }

}
