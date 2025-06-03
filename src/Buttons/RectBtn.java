package Buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Factory.RectObject;
import Mode.RectMode;

import java.awt.*;
import View.Canvas;
import View.Panel;

public class RectBtn extends ButtonAbstract implements ActionListener {
    public RectBtn() {
        super("icons/rect.png", "rect");
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Canvas.getInstance().setMode(new RectMode(new RectObject()));
        Panel.getInstance().resetButtonColor();
        setBackground(Color.GRAY);
    }
}
