package noah.uyttebroeck.linux;

import com.sun.jna.NativeLong;
import com.sun.jna.platform.unix.X11;

import static com.sun.jna.platform.unix.X11.*;

public class LinuxTest {

    public static void main(String[] args) {
        createLinuxWindow();
    }

    private static void createLinuxWindow() {

        final noah.uyttebroeck.unix.X11 X11INSTANCE = noah.uyttebroeck.unix.X11.INSTANCE;

        X11.Display display;
        X11.Window window;
        X11.XEvent event = new X11.XEvent();
        int screen;

        display = X11INSTANCE.XOpenDisplay(null);
        if (display == null) {
            System.err.println("Cannot open display");
            System.exit(1);
        }

        screen = X11INSTANCE.XDefaultScreen(display);

        window = X11INSTANCE.XCreateSimpleWindow(display, X11INSTANCE.XRootWindow(display, screen), 0, 0, 1024, 512, 1, X11INSTANCE.XBlackPixel(display, screen), X11INSTANCE.XWhitePixel(display, screen));
        X11INSTANCE.XStoreName(display, window, "Hello World");

        X11.Atom del_window = X11INSTANCE.XInternAtom(display, "WM_DELETE_WINDOW", false);
        X11INSTANCE.XSetWMProtocols(display, window, new X11.Atom[]{del_window}, 1);

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

}
