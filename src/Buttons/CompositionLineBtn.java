package Buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Factory.CompositionLine;
import Mode.CompositionLineMode;

import java.awt.*;
import View.Canvas;
import View.Panel;

public class CompositionLineBtn extends ButtonAbstract implements ActionListener {
    public CompositionLineBtn() {
        super("icons/composition.png", "composition line");
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Canvas.getInstance().setMode(new CompositionLineMode(new CompositionLine()));
        Panel.getInstance().resetButtonColor();
        setBackground(Color.GRAY);
    }
}
