package noah.uyttebroeck.windows.controls.button;

import noah.uyttebroeck.controls.Control;
import noah.uyttebroeck.controls.button.Button;

public class MyPushButton extends Button {

    private int counter;

    public MyPushButton(String text, int width, int height, int x, int y, Control parent) {
        super(text, width, height, x, y, parent);
    }

    @Override
    public void onClick() {
        setText(counter++ + "");
    }
}
