package noah.uyttebroeck.controls;

import com.sun.jna.platform.win32.*;
import com.sun.jna.platform.win32.WinDef.HWND;
import noah.uyttebroeck.User32;

import java.util.ArrayList;

public abstract class Control {

    protected String text;
    protected int width;
    protected int height;
    protected int x;
    protected int y;
    protected Control parent;
    protected final HWND handle;
    protected final String CLASS_NAME;

    protected ArrayList<Control> children;

    public Control(String ipClassName, String text, int dwStyle, int x, int y, int width, int height, Control parent) {

        CLASS_NAME = ipClassName;
        this.text = text;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.parent = parent;

        children = new ArrayList<>();

        if (parent != null) {
            parent.children.add(this);
        }

        preConstruct();

        handle = User32.INSTANCE.CreateWindowEx(
                0,
                ipClassName,  // Predefined class; Unicode assumed
                text,      // Button text
                dwStyle,  // Styles
                x,         // x position
                y,         // y position
                width,        // Button width
                height,        // Button height
                parent != null ? parent.getHandle() : null,     // Parent window
                null,       // No menu. (can be used for id)
                null,
                null
        );

        if (handle == null) {
            throw new IllegalStateException("Failed to create window " + Kernel32.INSTANCE.GetLastError());
        }
    }

    protected void preConstruct() {

    }

    public HWND getHandle() {
        return handle;
    }

    public String getText() {
        return text;
    }
}
