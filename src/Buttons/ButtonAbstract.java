package Buttons;

import javax.swing.*;
import java.awt.*;

public abstract class ButtonAbstract extends JButton{
    public ButtonAbstract(String iconPath, String tooltip) {
        int buttonIconSize = 60;

        try {
            ImageIcon icon = new ImageIcon(iconPath);

            int originalWidth = icon.getIconWidth();
            int originalHeight = icon.getIconHeight();

            double widthRatio = (double) buttonIconSize / originalWidth;
            double heightRatio = (double) buttonIconSize / originalHeight;
            double ratio = Math.min(widthRatio, heightRatio);

            int scaledWidth = (int) (originalWidth * ratio);
            int scaledHeight = (int) (originalHeight * ratio);

            Image scaledImage = icon.getImage().getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
            this.setIcon(new ImageIcon(scaledImage));
        } 
        catch (Exception e) {
            this.setText(tooltip);
        }

        this.setToolTipText(tooltip);
        this.setBackground(Color.WHITE);
        this.setBorderPainted(true);
        this.setFocusPainted(false);
    }
}
