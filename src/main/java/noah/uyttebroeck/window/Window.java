package noah.uyttebroeck.window;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.*;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinDef.LPARAM;
import com.sun.jna.platform.win32.WinDef.LRESULT;
import com.sun.jna.platform.win32.WinDef.WPARAM;
import com.sun.jna.platform.win32.WinUser.MSG;
import com.sun.jna.platform.win32.WinUser.WNDCLASSEX;
import com.sun.jna.platform.win32.WinUser.WindowProc;
import noah.uyttebroeck.User32;
import noah.uyttebroeck.controls.Control;

import java.util.ArrayList;

import static noah.uyttebroeck.User32.*;

public abstract class Window extends Thread implements WindowProc{

    private final String CLASS_NAME;
    private final User32 USER32INST = User32.INSTANCE;
    protected HWND hWnd;

    private WindowListener listener;
    private ArrayList<Control> children; //TODO

    public Window(String title, int x, int y, int width, int height) {

        // define new window class
        CLASS_NAME = title + "Class";

        createWindow(title, x, y, width, height);

        init();
    }

    private void init() {
        onInit();
        showWindow();

        //loop
        loop();

        destroyWindow();
    }

    public abstract void onInit();

    private void loop() {
        MSG msg = new MSG();
        while (USER32INST.GetMessage(msg, hWnd, 0, 0) > 0) {
            if (!USER32INST.IsDialogMessageA(hWnd, msg)) {
                USER32INST.TranslateMessage(msg);
                USER32INST.DispatchMessage(msg);
            }
        }
    }

    private void createWindow(String title, int x, int y, int width, int height) {

        // register window class
        WNDCLASSEX wc = new WNDCLASSEX();
        wc.lpfnWndProc = Window.this;
        wc.lpszClassName = CLASS_NAME;
        wc.hCursor = INSTANCE.LoadCursor(null, IDC_ARROW);
        USER32INST.RegisterClassEx(wc);

        // create new window
        hWnd = USER32INST.CreateWindowEx(
                        WS_EX_APPWINDOW,
                        CLASS_NAME,
                        title,
                        WS_OVERLAPPEDWINDOW,
                        x, y, width, height,
                        null,
                        null,
                        null,
                        null
                );

        if (hWnd == null) {
            System.err.println("An error occurred when trying to create a window");
            return;
        }

        System.out.println("window sucessfully created! window hwnd: "
                + hWnd.getPointer().toString());
    }

    private void showWindow() {
        USER32INST.ShowWindow(hWnd, 1);
    }

    private void destroyWindow() {
        USER32INST.UnregisterClass(CLASS_NAME, null);
        USER32INST.DestroyWindow(hWnd);
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
                HDC hdc = USER32INST.BeginPaint(hwnd, ps);

                USER32INST.FillRect(hdc, ps.rcPaint, new HBRUSH(new Pointer(COLOR_WINDOW+1)));

                USER32INST.EndPaint(hwnd, ps);
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
                System.out.println(wParam + " (In Control.java)");
                break;
        }
        if (executeDefault) {
            return USER32INST.DefWindowProc(hwnd, uMsg, wParam, lParam);
        }
        return new LRESULT(0);
    }

    public final int getLastError() {
        int rc = Kernel32.INSTANCE.GetLastError();

        if (rc != 0)
            System.out.println("error: " + rc);

        return rc;
    }

    public void addListener(WindowListener listener) {
        this.listener = listener;
    }
}