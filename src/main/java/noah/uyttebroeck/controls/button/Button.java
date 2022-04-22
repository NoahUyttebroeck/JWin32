package noah.uyttebroeck.controls.button;

import com.sun.jna.Pointer;
import noah.uyttebroeck.windows.GDI32;
import noah.uyttebroeck.windows.User32;
import noah.uyttebroeck.controls.Control;

import static com.sun.jna.platform.win32.WinUser.*;
import static noah.uyttebroeck.windows.User32.*;
import static noah.uyttebroeck.windows.User32.VARIABLE_PITCH;

public abstract class Button extends Control {

    private HFONT font;

    public Button(String text, int width, int height, int x, int y, Control parent) {
        this("BUTTON", text, WS_TABSTOP | WS_VISIBLE | WS_CHILD | BS_PUSHBUTTON, x, y, width, height, parent);
    }

    protected Button(String ipClassName, String text, int dwStyle, int x, int y, int width, int height, Control parent) {
        super(ipClassName, text, dwStyle, x, y, width, height, parent);
        font = GDI32.INSTANCE.CreateFontA(14,0,0,0,FW_DONTCARE,false,false,false,DEFAULT_CHARSET,OUT_OUTLINE_PRECIS,
                CLIP_DEFAULT_PRECIS,CLEARTYPE_QUALITY, VARIABLE_PITCH,"Segoe UI");
        INSTANCE.SendMessage(getHandle(), WM_SETFONT, new WPARAM(Pointer.nativeValue(font.getPointer())), new LPARAM(1));
    }

    public void setIsDefault(boolean isDefault) {
        long currentStyle = INSTANCE.GetWindowLong(getHandle(), GWL_STYLE);
        if (isDefault) {
            //remove normal
            currentStyle = currentStyle & ~BS_PUSHBUTTON;
            //add default
            INSTANCE.SetWindowLong(getHandle(), GWL_STYLE, (int) (currentStyle | BS_DEFPUSHBUTTON));
            return;
        }
        //remove default
        currentStyle = currentStyle & ~BS_DEFPUSHBUTTON;
        //add normal
        INSTANCE.SetWindowLong(getHandle(), GWL_STYLE, (int) (currentStyle | BS_PUSHBUTTON));
    }


    public void setText(String text) {
        User32.INSTANCE.SetWindowText(handle, text);
        this.text = text;
    }

    public void onClick(){}
}
