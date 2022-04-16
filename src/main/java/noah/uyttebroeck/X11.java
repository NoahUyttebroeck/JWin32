package noah.uyttebroeck;

import com.sun.jna.Native;

public interface X11 extends com.sun.jna.platform.unix.X11 {
    X11 INSTANCE = Native.load("X11", X11.class);

    GC XDefaultGC(Display display, int screen_number);

    int XBlackPixel(Display display, int screen_number);

    int XWhitePixel(Display display, int screen_number);

    void XStoreName(Display display, Window window, String window_name);
}
