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

    public Object(String type, Point position) {
        this.type = type;
        this.position = position;
        if(type.equals("class")) {
            width = 100;
            height = 150;
            name = "Class";
        }
        else if(type.equals("use case")) {
            width = 150;
            height = 80;
            name = "Use Case";
        }
        else { // composite object
            name = "Composite";
        }
        initializePorts();
    }

    public void draw(Graphics g) {
        if(type.equals("class")) {
            g.drawRect(position.x, position.y, width, height);
            //g.drawLine(position.x, position.y + 50, position.x + width, position.y + 50);
            //g.drawLine(position.x, position.y + 100, position.x + width, position.y + 100);
            g.drawString(name, position.x + width / 3, position.y + height / 2);
        } 
        else if(type.equals("use case")) {
            g.drawOval(position.x, position.y, width, height);
            g.drawString(name, position.x + width / 3, position.y + height / 2);
        }

        if(selected) { // draw ports
            g.setColor(Color.BLACK);
            for(Port port : portList) {
                Point absolutePoint = getAbsolutePort(port.getPort());
                g.fillRect(absolutePoint.x - 3, absolutePoint.y - 3, 8, 8);
            }
        }
    }

    private void initializePorts() {
        portList.add(new Port(new Point(width / 2, 0), "N")); // 中上
        portList.add(new Port(new Point(width / 2, height), "S")); // 中下
        portList.add(new Port(new Point(0, height / 2), "W")); // 中左
        portList.add(new Port(new Point(width, height / 2), "E")); // 中右
        
        if(type.equals("class")) {
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

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
