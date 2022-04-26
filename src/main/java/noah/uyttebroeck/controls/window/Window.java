package noah.uyttebroeck.controls.window;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.*;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.MSG;
import com.sun.jna.platform.win32.WinUser.WindowProc;
import noah.uyttebroeck.controls.button.Button;
import noah.uyttebroeck.windows.User32;
import noah.uyttebroeck.controls.Control;

import static noah.uyttebroeck.windows.User32.*;

public abstract class Window extends Control implements WindowProc{

    private WindowListener listener;

    public Window(String title, int x, int y, int width, int height) {
        super(title + "Class", title, WS_OVERLAPPEDWINDOW, x, y, width, height, null);
        init();
    }

    @Override
    protected void preConstruct() {
        WNDCLASSEX wc = new WNDCLASSEX();
        wc.lpfnWndProc = Window.this;
        wc.lpszClassName = CLASS_NAME;
        wc.hCursor = User32.INSTANCE.LoadCursor(null, IDC_ARROW);
        User32.INSTANCE.RegisterClassEx(wc);
    }

    private void init() {
        onInit();
        showWindow();

        //loop
        loop();

        destroyWindow();
    }

    public abstract void onInit();
    public abstract void onLoop();

    private void loop() {
        MSG msg = new MSG();
        while (User32.INSTANCE.GetMessage(msg, handle, 0, 0) > 0) {
            if (!User32.INSTANCE.IsDialogMessageA(handle, msg)) {
                User32.INSTANCE.TranslateMessage(msg);
                User32.INSTANCE.DispatchMessage(msg);
            }
            onLoop();
        }
    }

    private void showWindow() {
        User32.INSTANCE.ShowWindow(handle, 1);
    }

    private void destroyWindow() {
        User32.INSTANCE.UnregisterClass(CLASS_NAME, null);
        User32.INSTANCE.DestroyWindow(handle);
    }

    @Override
    public LRESULT callback(HWND hwnd, int uMsg, WPARAM wParam, LPARAM lParam) {

        boolean executeDefault = true;

        switch (uMsg) {

            case WM_CREATE:
                if (listener != null) {
                    executeDefault = listener.onInitialize();
                }
                break;

            case WM_DESTROY:
                if (listener != null) {
                    listener.onDestroy();
                }
                User32.INSTANCE.PostQuitMessage(0);
                break;

            case WM_PAINT:
                PAINTSTRUCT ps = new PAINTSTRUCT();
                HDC hdc = User32.INSTANCE.BeginPaint(hwnd, ps);

                User32.INSTANCE.FillRect(hdc, ps.rcPaint, new HBRUSH(new Pointer(COLOR_WINDOW+1)));

                User32.INSTANCE.EndPaint(hwnd, ps);
                break;

            case WM_SIZE:
                if (listener != null) {
                    switch (wParam.shortValue()) {
                        case 0:
                            //width(32-bit) and height(32-bit) are combined into one 64-bit number
                            DWORD dword = new DWORD(lParam.longValue());
                            executeDefault = listener.onResize(dword.getLow().intValue(), dword.getHigh().intValue());
                            break;
                        case 1:
                            executeDefault = listener.onMinimize();
                            break;
                        case 2:
                            executeDefault = listener.onMaximize();
                            break;
                    }
                }
                break;

            case WM_SYSKEYDOWN:
            case WM_KEYDOWN:
                if (listener != null) {
                    executeDefault = listener.onKeyDown(Win32VK.fromValue(wParam.intValue()));
                }
                break;

            case WM_SYSKEYUP:
            case WM_KEYUP:
                if (listener != null) {
                    System.out.println("eyy");
                    executeDefault = listener.onKeyUp(Win32VK.fromValue(wParam.intValue()));
                } else {
                    System.out.println("aiai");
                }
                break;

            case WM_SHOWWINDOW:
                if (listener != null) {
                    executeDefault = listener.onShow();
                }
                break;

            case WM_COMMAND:
                //get control from handle
                Control clickedControl = null;
                for (Control n : children) {
                    if (Pointer.nativeValue(n.getHandle().getPointer()) == Pointer.nativeValue(lParam.toPointer())) {
                        clickedControl = n;
                        break;
                    }
                }

                if (listener != null) {
                    executeDefault = listener.onControlClicked(clickedControl);
                }

                if (executeDefault || listener == null) {
                    if (clickedControl instanceof Button) {
                        ((Button) clickedControl).onClick();
                    }
                }
                break;
        }
        if (executeDefault) {
            return User32.INSTANCE.DefWindowProc(hwnd, uMsg, wParam, lParam);
        }
        return new LRESULT(0);
    }

    public final void addListener(WindowListener listener) {
        this.listener = listener;
    }
}