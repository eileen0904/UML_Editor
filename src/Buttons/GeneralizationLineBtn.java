package Buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Factory.GeneralizationLine;
import Mode.GeneralizationLineMode;

import java.awt.*;
import View.Canvas;
import View.Panel;

public class GeneralizationLineBtn extends ButtonAbstract implements ActionListener {
    public GeneralizationLineBtn() {
        super("icons/generalization.png", "generalization line");
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Canvas.getInstance().setMode(new GeneralizationLineMode(new GeneralizationLine()));
        Panel.getInstance().resetButtonColor();
        setBackground(Color.GRAY);
    }
}
