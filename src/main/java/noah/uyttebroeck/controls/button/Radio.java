package noah.uyttebroeck.controls.button;

import com.sun.jna.platform.win32.WinDef;

import static com.sun.jna.platform.win32.WinUser.*;

public class Radio extends Button{
    public Radio(String text, int width, int height, int x, int y, WinDef.HWND parent) {
        super("BUTTON", text, WS_TABSTOP | WS_VISIBLE | WS_CHILD | BS_AUTORADIOBUTTON, x, y, width, height, parent);
    }
}
