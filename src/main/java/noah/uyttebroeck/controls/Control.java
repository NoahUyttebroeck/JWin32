package noah.uyttebroeck.controls;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;
import noah.uyttebroeck.User32;

public abstract class Control {

    protected String text;
    protected int width;
    protected int height;
    protected int x;
    protected int y;
    protected HWND parent;
    protected final HWND handle;

    public Control(String ipClassName, String text, int dwStyle, int x, int y, int width, int height, HWND parent) {

        this.text = text;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.parent = parent;

        handle = User32.INSTANCE.CreateWindowEx(
                0,
                ipClassName,  // Predefined class; Unicode assumed
                text,      // Button text
                dwStyle,  // Styles
                x,         // x position
                y,         // y position
                width,        // Button width
                height,        // Button height
                parent,     // Parent window
                new WinDef.HMENU(new Pointer(7)),       // No menu. (can be used for id)
                null,
                null
        );
    }

    public HWND getHandle() {
        return handle;
    }

    public String getText() {
        return text;
    }
}
