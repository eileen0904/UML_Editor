package Menu;

import javax.swing.*;

import View.Canvas;
import View.Frame;

public class MenuItem extends JMenuItem {
    protected Canvas canvas;
    protected Frame frame;

    public MenuItem(String name) {
        super(name);
        canvas = Canvas.getInstance();
        frame = Frame.getInstance();
    }
}
