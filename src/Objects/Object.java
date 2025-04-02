package Objects;

import java.awt.*;
import java.util.ArrayList;

public class Object {
    private String type;
    private Point position;
    private int width = 0, height = 0, depth = 0;
    private boolean selected = false;
    private String name = "";
    private ArrayList<Port> portList = new ArrayList<>();
    private Label label;

    public Object(String type, Point position) {
        this.type = type;
        this.position = position;
        if(type.equals("rect")) {
            width = 100;
            height = 150;
            name = "Rect";
        }
        else if(type.equals("oval")) {
            width = 150;
            height = 80;
            name = "oval";
        }
        else { // composite object
            name = "Composite";
        }
        label = new Label(name);
        initializePorts();
    }

    public void draw(Graphics g) {
        if(type.equals("rect")) {
            g.drawRect(position.x, position.y, width, height);
        } 
        else if(type.equals("oval")) {
            g.drawOval(position.x, position.y, width, height);
        }

        if(selected) { // draw ports
            g.setColor(Color.BLACK);
            for(Port port : portList) {
                Point absolutePoint = getAbsolutePort(port.getPort());
                g.fillRect(absolutePoint.x - 3, absolutePoint.y - 3, 8, 8);
            }
        }

        if(label.isEnabled()) { // draw label
            label.draw(g, position, width, height);
        }
    }

    private void initializePorts() {
        portList.add(new Port(new Point(width / 2, 0), "N")); // 中上
        portList.add(new Port(new Point(width / 2, height), "S")); // 中下
        portList.add(new Port(new Point(0, height / 2), "W")); // 中左
        portList.add(new Port(new Point(width, height / 2), "E")); // 中右
        
        if(type.equals("rect")) {
            portList.add(new Port(new Point(0, 0), "LN")); // 左上
            portList.add(new Port(new Point(width, 0), "RN")); // 右上
            portList.add(new Port(new Point(0, height), "LS")); // 左下
            portList.add(new Port(new Point(width, height), "RS")); // 右下
        }
    }

    public Point getAbsolutePort(Point relativePort) {
        return new Point(position.x + relativePort.x, position.y + relativePort.y);
    }

    public ArrayList<Port> getPortList() {
        return portList;
    }

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

    public void setConnectionPort(Point source, Point target, Object targetObj) {
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

    public Port PointToPort(Point p) {
        for(Port port : portList) {
            Point absolutePoint = getAbsolutePort(port.getPort());
            if(absolutePoint.equals(p)) {
                return port;
            }
        }
        return null;
    }

    public boolean contains(Point p) {
        return p.x >= position.x && p.x <= position.x + width && p.y >= position.y && p.y <= position.y + height;
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
        return label;
    }
}
