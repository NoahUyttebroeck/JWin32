package noah.uyttebroeck.controls.text;

import com.sun.jna.Pointer;
import noah.uyttebroeck.controls.Control;
import noah.uyttebroeck.windows.GDI32;

import static com.sun.jna.platform.win32.WinUser.*;
import static com.sun.jna.platform.win32.WinUser.WS_CHILD;
import static noah.uyttebroeck.windows.User32.*;

public class Text extends Control {

    private HFONT font;

    public Text(String text, int x, int y, int width, int height, Control parent) {
        super("STATIC", text, SS_CENTERIMAGE | WS_VISIBLE | WS_CHILD, x, y, width, height, parent);
        font = GDI32.INSTANCE.CreateFontA(14,0,0,0,FW_DONTCARE,false,false,false,DEFAULT_CHARSET,OUT_OUTLINE_PRECIS,
                CLIP_DEFAULT_PRECIS,CLEARTYPE_QUALITY, VARIABLE_PITCH,"Segoe UI");
        INSTANCE.SendMessage(getHandle(), WM_SETFONT, new WPARAM(Pointer.nativeValue(font.getPointer())), new LPARAM(1));

    }
}
