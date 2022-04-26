package noah.uyttebroeck.controls.box;

import noah.uyttebroeck.controls.Control;

import static com.sun.jna.platform.win32.WinUser.WS_CHILD;
import static noah.uyttebroeck.windows.User32.*;

public class HList extends Control {

    public HList(int x, int y, int width, int height, Control parent) {
        super(WC_LISTVIEW, "eae", WS_CHILD | LVS_REPORT | LVS_EDITLABELS | WS_VISIBLE, x, y, width, height, parent);
    }
}
