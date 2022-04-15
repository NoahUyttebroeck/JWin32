package noah.uyttebroeck.controls.button;

import static com.sun.jna.platform.win32.WinUser.*;

public class CheckBox extends Button{

    public CheckBox(String text, int width, int height, int x, int y, HWND parent) {
        super("BUTTON", text, WS_TABSTOP | WS_VISIBLE | WS_CHILD | BS_AUTOCHECKBOX, x, y, width, height, parent);
    }
}
