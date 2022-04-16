package noah.uyttebroeck.controls.button;

import static com.sun.jna.platform.win32.WinUser.*;
import static noah.uyttebroeck.User32.BS_SPLITBUTTON;

public class List extends Button{
    public List(String text, int width, int height, int x, int y, HWND parent) {
        super("BUTTON", text, WS_TABSTOP | WS_VISIBLE | WS_CHILD | BS_SPLITBUTTON, x, y, width, height, parent);
    }
}
