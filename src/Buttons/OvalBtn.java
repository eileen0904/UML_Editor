package Buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Factory.OvalObject;
import Mode.OvalMode;

import java.awt.*;
import View.Canvas;
import View.Panel;

public class OvalBtn extends ButtonAbstract implements ActionListener {
    public OvalBtn() {
        super("icons/oval.png", "oval");
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Canvas.getInstance().setMode(new OvalMode(new OvalObject()));
        Panel.getInstance().resetButtonColor();
        setBackground(Color.GRAY);
    }
}
