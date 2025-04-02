package Objects;

import java.awt.*;

public class Label {
    private String name;
    private String shape = "Rectangle";
    private String color = "white";
    private int fontSize = 12;
    private boolean enabled = false;

    public Label(String name) {
        this.name = name;
    }

    public void draw(Graphics g, Point position, int width, int height) {
        if(!enabled) 
            return;

        Color labelColor = getColorFromString(this.color);
        Color originalColor = g.getColor();

        if(shape.equals("Rectangle")) {
            g.drawRect(position.x + 10, position.y + height / 3, width - 20, height / 4 + 10);
            g.setColor(labelColor);
            g.fillRect(position.x + 10, position.y + height / 3, width - 20, height / 4 + 10);
        } 
        else if(shape.equals("Oval")) {
            g.drawOval(position.x + 10, position.y + height / 3 - 10, width - 20, height / 4 + 20);
            g.setColor(labelColor);
            g.fillOval(position.x + 10, position.y + height / 3 - 10, width - 20, height / 4 + 20);
        }

        g.setColor(Color.BLACK);

        Font originalFont = g.getFont();
        Font labelFont = new Font(originalFont.getName(), Font.PLAIN, fontSize);
        g.setFont(labelFont);

        FontMetrics fm = g.getFontMetrics();
        int textX = position.x + width / 2 - fm.stringWidth(name) / 2;
        int textY = position.y + height / 2 + fm.getAscent() / 4;
        g.drawString(name, textX, textY);

        g.setFont(originalFont);
        g.setColor(originalColor);
    }

    public void enable() {
        this.enabled = true;
    }

    public void disable() {
        this.enabled = false;
    }

    public boolean isEnabled() {
        return enabled;
    }

    private Color getColorFromString(String colorName) {
        switch (colorName.toLowerCase()) {
            case "yellow":
                return Color.YELLOW;
            case "red":
                return Color.RED;
            case "blue":
                return Color.BLUE;
            case "green":
                return Color.GREEN;
            case "white":
            default:
                return Color.WHITE;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }
}
