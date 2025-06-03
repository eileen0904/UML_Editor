package Buttons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Factory.AssociationLine;
import Mode.AssociationLineMode;

import java.awt.*;
import View.Canvas;
import View.Panel;

public class AssociationLineBtn extends ButtonAbstract implements ActionListener {
    public AssociationLineBtn() {
        super("icons/association.png", "association line");
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Canvas.getInstance().setMode(new AssociationLineMode(new AssociationLine()));
        Panel.getInstance().resetButtonColor();
        setBackground(Color.GRAY);
    }
}
