package Objects.BasicObjects;

import java.awt.*;
import java.util.ArrayList;

import Objects.Label;
import Objects.Port;
import Objects.ShapeAbstract;

public class ObjectAbstract extends ShapeAbstract{
    private Point position;
    private int width = 0, height = 0;
    private boolean selected = false;
    private String name = "";
    protected ArrayList<Port> portList = new ArrayList<>();
    private Label label;
    
    public void draw(Graphics g) {
    }

    public void initializePorts() {
    }

    public void createObject(Point position) {
        this.position = position;
    }

    @Override
    public ArrayList<Port> getPortList() {
        return portList;
    }

    @Override
    public Point getAbsolutePort(Point relativePort) {
        return new Point(position.x + relativePort.x, position.y + relativePort.y);
    }
    
    @Override
    public Point getClosestPort(Point target) {
        Point closest = null;
        double minDistance = Double.MAX_VALUE;

        for(Port port : portList) {
            Point absolutePoint = getAbsolutePort(port.getPort());
            double distance = absolutePoint.distance(target);
            if(distance < minDistance) {
                minDistance = distance;
                closest = absolutePoint;
            }
        }
        return closest;
    }
    
    public void setConnectionPort(Point source, Point target, ObjectAbstract targetObj) {
        for(Port port : portList) {
            Point absolutePoint = this.getAbsolutePort(port.getPort());
            if(absolutePoint.equals(source)) {
                for(Port targetPort : targetObj.getPortList()) {
                    if(targetObj.getAbsolutePort(targetPort.getPort()).equals(target)) {
                        port.addTargetPort(targetPort);
                        targetPort.addTargetPort(port);
                        return;
                    }
                }
            }
        }
    }

    @Override
    public Port PointToPort(Point p) {
        for(Port port : portList) {
            Point absolutePoint = getAbsolutePort(port.getPort());
            if(absolutePoint.equals(p)) {
                return port;
            }
        }
        return null;
    }

    @Override
    public boolean contains(Point p) {
        return p.x >= position.x && p.x <= position.x + width && p.y >= position.y && p.y <= position.y + height;
    }
    
    @Override
    public boolean isSelected() {
        return this.selected;
    }

    public Point getPosition() {
        return this.position;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public Label getLabel() {
        return this.label;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLabel(Label label) {
        this.label = label;
    }
}
