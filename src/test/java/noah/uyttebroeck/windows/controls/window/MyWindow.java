package noah.uyttebroeck.windows.controls.window;

import noah.uyttebroeck.controls.box.HList;
import noah.uyttebroeck.controls.window.Window;
import noah.uyttebroeck.windows.controls.button.MyPushButton;
import noah.uyttebroeck.windows.controls.text.MyText;

public class MyWindow extends Window {


    public MyWindow() {
        super("Hello World", 0, 0, 1024, 512);
    }

    @Override
    public void onInit() {
        new MyPushButton("Hello", 72, 23, 0,0, this);
        new MyText("Dit is een zeer lange text", 72, 0, 120, 23, this);

        HList hList = new HList(192, 100, 150, 23, this);
        new MyPushButton("o", 72, 23, 0,0, hList);
        new MyPushButton("p", 72, 23, 72,0, hList);
    }

    @Override
    public void onLoop() {

    }
}
