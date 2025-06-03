package Buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Mode.SelectMode;

import java.awt.*;
import View.Canvas;
import View.Panel;

public class SelectBtn extends ButtonAbstract implements ActionListener {
    public SelectBtn() {
        super("icons/select.png", "select");
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Canvas.getInstance().setMode(new SelectMode());
        Panel.getInstance().resetButtonColor();
        setBackground(Color.GRAY);
    }
}
