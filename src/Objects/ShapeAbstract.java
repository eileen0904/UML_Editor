package Objects;

import java.awt.*;
import java.util.ArrayList;

public class ShapeAbstract {
    public void draw(Graphics g) {}

    /* Port */
    public void initializePorts() {
    }

    public ArrayList<Port> getPortList() {
        return new ArrayList<>();
    }

    public Point getAbsolutePort(Point relativePort) {
        return new Point(0, 0);
    }

    public Point getClosestPort(Point target) {
        return new Point(0, 0);
    }

    public Port PointToPort(Point p) {
        return null;
    }

    /* Boolean */
    public boolean contains(Point p) {
        return false;
    }

    public boolean isSelected() {
        return false;
    }

    /* Getter */
    public Point getPosition() {
        return new Point(0, 0);
    }

    public int getWidth() {
        return 0;
    }

    public int getHeight() {
        return 0;
    }

    public Label getLabel() {
        return null;
    }

    /* Setter */
    public void setSelected(boolean selected) {
    }

    public void setPosition(Point position) {
    }

    public void setWidth(int width) {
    }

    public void setHeight(int height) {
    }

    public void setName(String name) {
    }

    public void setLabel(Label label) {
    }
}
