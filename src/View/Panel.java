package View;

import javax.swing.*;

import Buttons.*;

import java.awt.*;
import java.util.ArrayList;

public class Panel extends JPanel {
    private static Panel instance;
    private static ArrayList<ButtonAbstract> buttons = new ArrayList<>();

    public Panel() {
        this.setLayout(new GridLayout(6, 1));

        buttons.add(new SelectBtn());
        buttons.add(new AssociationLineBtn());
        buttons.add(new GeneralizationLineBtn());
        buttons.add(new CompositionLineBtn());
        buttons.add(new RectBtn());
        buttons.add(new OvalBtn());

        for(ButtonAbstract button : buttons) {
            this.add(button);
        }
    }

    public void resetButtonColor() {
        for(ButtonAbstract button : buttons) {
            button.setBackground(Color.WHITE);
        }
    }

    public static Panel getInstance() {
        synchronized (Panel.class) {
            if(instance == null) {
                instance = new Panel();
            }
        }
        return instance;
    }
}
