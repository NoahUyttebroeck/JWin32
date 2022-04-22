package noah.uyttebroeck;

import com.sun.jna.NativeLong;
import com.sun.jna.Platform;
import noah.uyttebroeck.controls.button.*;
import noah.uyttebroeck.controls.window.Window;
import noah.uyttebroeck.controls.window.WindowListenerImpl;
import com.sun.jna.platform.unix.X11;

import static com.sun.jna.platform.unix.X11.*;


public class Main {
    public static void main(String[] args) {
        if (Platform.isWindows()) {
            createWindowsWindow();
        } else {
            createLinuxWindow();
        }
    }

    private static void createLinuxWindow() {

        final noah.uyttebroeck.unix.X11 X11INSTANCE = noah.uyttebroeck.unix.X11.INSTANCE;

        X11.Display display;
        X11.Window window;
        XEvent event = new XEvent();
        int screen;

        display = X11INSTANCE.XOpenDisplay(null);
        if (display == null) {
            System.err.println("Cannot open display");
            System.exit(1);
        }

        screen = X11INSTANCE.XDefaultScreen(display);

        window = X11INSTANCE.XCreateSimpleWindow(display, X11INSTANCE.XRootWindow(display, screen), 0, 0, 1024, 512, 1, X11INSTANCE.XBlackPixel(display, screen), X11INSTANCE.XWhitePixel(display, screen));
        X11INSTANCE.XStoreName(display, window, "Hello World");

        Atom del_window = X11INSTANCE.XInternAtom(display, "WM_DELETE_WINDOW", false);
        X11INSTANCE.XSetWMProtocols(display, window, new Atom[]{del_window}, 1);

        X11INSTANCE.XSelectInput(display, window, new NativeLong(ExposureMask | KeyPressMask));

        X11INSTANCE.XMapWindow(display, window);

        boolean stop = false;
        while (!stop) {
            X11INSTANCE.XNextEvent(display, event);
            switch (event.type) {
                case KeyPress:
                case ClientMessage:
                    stop = true;
                    break;
                case Expose:
                    X11INSTANCE.XFillRectangle(display, window, X11INSTANCE.XDefaultGC(display, screen), 0,0, 50, 50);
                    break;
            }
        }

        X11INSTANCE.XDestroyWindow(display, window);
        X11INSTANCE.XCloseDisplay(display);
    }

    private static void createWindowsWindow() {

        new Window("Hello World", 0, 0, 1024, 512) {
            @Override
            public void onInit() {
                new PushButton("Yes", 70, 23, 0, 0, this).setIsDefault(true);
                PushButton b = new PushButton("No", 70, 23, 70, 0, this);
                new CheckBox("Ola-la", 70, 23, 140, 0, this);
                new Radio("mmm", 70, 23, 210, 0, this);
                new List("xyz", 70, 23, 280, 0, this);
                addListener(new WindowListenerImpl(b.getHandle()));
            }
        };
    }
}
