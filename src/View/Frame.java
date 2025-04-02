package View;

import javax.swing.*;
import java.awt.*;
import Menu.MenuBar;

public class Frame extends JFrame{
    private static Frame instance;
    private final Panel panel;
    private final Canvas canvas;
    private final MenuBar menuBar;

    private Frame() {
        super("UML Editor");
        instance = this;
        panel = Panel.getInstance();
        canvas = Canvas.getInstance();
        menuBar = MenuBar.getInstance();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLayout(new BorderLayout());
        
        add(panel, BorderLayout.WEST);
        add(canvas, BorderLayout.CENTER);

        setJMenuBar(menuBar);

        setVisible(true);
        setResizable(true);
    }

    public static Frame getInstance() {
        synchronized(Frame.class) {
            if(instance == null) {
                instance = new Frame();
            }
        }
        return instance;
    }
}
