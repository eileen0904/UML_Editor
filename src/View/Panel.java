package View;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;

public class Panel extends JPanel {
    private static Panel instance;
    private final Canvas canvas;

    public Panel() {
        this.setLayout(new GridLayout(6, 1));
        canvas = Canvas.getInstance();

        String[] buttons = { "select", "association line", "generalization line", "composition line", "class", "use case" };
        for(String buttonName : buttons) {
            JButton button = new JButton(buttonName);
            button.addActionListener((ActionEvent e) -> {
                canvas.setMode(buttonName);
                button.setBackground(Color.GRAY);
            });
            button.setBackground(Color.white);
            this.add(button);
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
