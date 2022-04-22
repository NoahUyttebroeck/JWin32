package noah.uyttebroeck.controls.window;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Win32VK;
import com.sun.jna.platform.win32.WinDef;
import noah.uyttebroeck.controls.Control;
import noah.uyttebroeck.controls.button.Button;
import noah.uyttebroeck.controls.button.PushButton;

public class WindowListenerImpl implements WindowListener {

    private int counter;
    private WinDef.HWND handle;

    public WindowListenerImpl(WinDef.HWND handle) {
        this.handle = handle;
        counter = 0;
    }

    @Override
    public boolean onKeyUp(Win32VK key) {
        System.out.println(key.name() + " aaeae");
        return true;
    }

    @Override
    public boolean onInitialize() {
        return WindowListener.super.onInitialize();
    }

    @Override
    public boolean onDestroy() {
        return WindowListener.super.onDestroy();
    }

    @Override
    public boolean onKeyDown(Win32VK key) {
        return WindowListener.super.onKeyDown(key);
    }

    @Override
    public boolean onResize(int width, int height) {
        return WindowListener.super.onResize(width, height);
    }

    @Override
    public boolean onMaximize() {
        return WindowListener.super.onMaximize();
    }

    @Override
    public boolean onMinimize() {
        return WindowListener.super.onMinimize();
    }

    @Override
    public boolean onShow() {
        return WindowListener.super.onShow();
    }

    @Override
    public boolean onLeftMouseButtonDown() {
        return WindowListener.super.onLeftMouseButtonDown();
    }

    @Override
    public boolean onLeftMouseButtonUp() {
        return WindowListener.super.onLeftMouseButtonUp();
    }

    @Override
    public boolean onRightMouseButtonDown() {
        return WindowListener.super.onRightMouseButtonDown();
    }

    @Override
    public boolean onRightMouseButtonUp() {
        return WindowListener.super.onRightMouseButtonUp();
    }

    @Override
    public boolean onMiddleMouseButtonDown() {
        return WindowListener.super.onMiddleMouseButtonDown();
    }

    @Override
    public boolean onMiddleMouseButtonUp() {
        return WindowListener.super.onMiddleMouseButtonUp();
    }

    @Override
    public boolean onControlClicked(Control clickedControl) {
        if (clickedControl instanceof PushButton) {
            Button button = (Button) clickedControl;
            if (Pointer.nativeValue(button.getHandle().getPointer()) == Pointer.nativeValue(handle.getPointer())) {
                counter++;
                System.out.println(counter);
                button.setText(counter + "");
            }
        }
        return true;
    }
}
