package noah.uyttebroeck.controls.button;

import noah.uyttebroeck.controls.Control;

import static com.sun.jna.platform.win32.WinUser.*;

public abstract class CheckBox extends Button{

    public CheckBox(String text, int width, int height, int x, int y, Control parent) {
        super("BUTTON", text, WS_TABSTOP | WS_VISIBLE | WS_CHILD | BS_AUTOCHECKBOX, x, y, width, height, parent);
    }
}
