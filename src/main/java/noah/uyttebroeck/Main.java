package noah.uyttebroeck;

import noah.uyttebroeck.controls.button.Button;
import noah.uyttebroeck.controls.button.CheckBox;
import noah.uyttebroeck.controls.button.List;
import noah.uyttebroeck.controls.button.Radio;
import noah.uyttebroeck.window.Window;
import noah.uyttebroeck.window.WindowListenerImpl;

public class Main {
    public static void main(String[] args) {

        Window window = new Window("Goeiendag", 0, 0, 1024, 512) {
            @Override
            public void onInit() {
                new Button("Ja", 70, 23, 0, 0, hWnd).setIsDefault(true);
                Button b = new Button("Nee", 70, 23, 70, 0, hWnd);
                System.out.println(b.getHandle());
                new CheckBox("Olala", 70, 23, 140, 0, hWnd);
                new Radio("hmmm", 70, 23, 210, 0, hWnd);
                new List("aeae", 70, 23, 280, 0, hWnd);
                addListener(new WindowListenerImpl());
            }
        };
    }
}
