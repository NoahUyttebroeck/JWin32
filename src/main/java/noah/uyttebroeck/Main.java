package noah.uyttebroeck;

import com.sun.jna.Pointer;
import noah.uyttebroeck.controls.button.Button;
import noah.uyttebroeck.controls.button.CheckBox;
import noah.uyttebroeck.controls.button.List;
import noah.uyttebroeck.controls.button.Radio;
import noah.uyttebroeck.controls.window.Window;
import noah.uyttebroeck.controls.window.WindowListenerImpl;

public class Main {
    public static void main(String[] args) {

        new Window("Hello World", 0, 0, 1024, 512) {
            @Override
            public void onInit() {
                new Button("Yes", 70, 23, 0, 0, this).setIsDefault(true);
                Button b = new Button("No", 70, 23, 70, 0, this);
                System.out.println(Pointer.nativeValue(b.getHandle().getPointer()));
                new CheckBox("Ola-la", 70, 23, 140, 0, this);
                new Radio("mmm", 70, 23, 210, 0, this);
                new List("xyz", 70, 23, 280, 0, this);
                addListener(new WindowListenerImpl());
            }
        };
    }
}
