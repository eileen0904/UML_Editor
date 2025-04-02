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

        String[] buttonNames = { "select", "association line", "generalization line", "composition line", "rect", "oval" };
        String[] iconPaths = {
                "icons/select.png",
                "icons/association.png",
                "icons/generalization.png",
                "icons/composition.png", 
                "icons/rect.png",
                "icons/oval.png"
        };

        int buttonIconSize = 60;

        for(int i = 0; i < buttonNames.length; i++) {
            JButton button = new JButton();

            try {
                ImageIcon icon = new ImageIcon(iconPaths[i]);

                int originalWidth = icon.getIconWidth();
                int originalHeight = icon.getIconHeight();

                double widthRatio = (double) buttonIconSize / originalWidth;
                double heightRatio = (double) buttonIconSize / originalHeight;
                double ratio = Math.min(widthRatio, heightRatio);

                int scaledWidth = (int) (originalWidth * ratio);
                int scaledHeight = (int) (originalHeight * ratio);

                Image scaledImage = icon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);

                button.setIcon(new ImageIcon(scaledImage));
            } 
            catch(Exception e) {
                button.setText(buttonNames[i]);
                e.printStackTrace();
            }

            // 設定工具提示以顯示按鈕名稱
            button.setToolTipText(buttonNames[i]);

            final String buttonName = buttonNames[i];
            button.addActionListener((ActionEvent e) -> {
                canvas.setMode(buttonName);
                button.setBackground(Color.GRAY);

                // 重設其他按鈕的顏色
                for(Component comp : getComponents()) {
                    if (comp instanceof JButton && comp != button) {
                        comp.setBackground(Color.WHITE);
                    }
                }
            });

            button.setBackground(Color.WHITE);
            button.setBorderPainted(true);
            button.setFocusPainted(false);
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
