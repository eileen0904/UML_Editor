package Objects;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import Objects.BasicObjects.ObjectAbstract;

public class CompositeObject extends ObjectAbstract {
    private ArrayList<ObjectAbstract> components;
    public HashMap<ObjectAbstract, Point> relativePositions = new HashMap<>();
    private ObjectAbstract selectedObject = null;

    public CompositeObject(ArrayList<ObjectAbstract> components) {
        super.setPosition(new Point(0, 0));
        super.setName("composite");
        super.setLabel(new Label("composite"));
        this.components = components;
        calculateBounds();
    }

    private void calculateBounds() { // components = selected objects
        if(components.isEmpty())
            return;

        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;

        for(ObjectAbstract obj : components) {
            Point pos = obj.getPosition();
            minX = Math.min(minX, pos.x);
            minY = Math.min(minY, pos.y);
            maxX = Math.max(maxX, pos.x + obj.getWidth());
            maxY = Math.max(maxY, pos.y + obj.getHeight());
        }

        super.setPosition(new Point(minX, minY));
        this.setWidth(maxX - minX);
        this.setHeight(maxY - minY);

        // 記錄每個子物件相對於 CompositeObject 的相對位置
        for(ObjectAbstract obj : components) {
            Point relativePosition = new Point(obj.getPosition().x - minX, obj.getPosition().y - minY);
            relativePositions.put(obj, relativePosition);
        }
    }

    @Override
    public void draw(Graphics g) {
        for(ObjectAbstract obj : components) {
            obj.draw(g);
        }
    }

    @Override
    public boolean contains(Point p) {
        for(ObjectAbstract obj : components) {
            if(obj.contains(p)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Point getClosestPort(Point target) {
        Point closest = null;
        double minDistance = Double.MAX_VALUE;

        for(ObjectAbstract obj : components) { // 遍歷所有子物件，找到最接近目標點的子物件的 Port
            Point closetPoint = obj.getClosestPort(target);
            double distance = closetPoint.distance(target);
            if(distance < minDistance) {
                minDistance = distance;
                closest = closetPoint;
                selectedObject = obj;
            }
        }
        return closest;
    }

    @Override
    public void setConnectionPort(Point source, Point target, ObjectAbstract targetObj) {
        for(ObjectAbstract obj : components) {
            for(Port port : obj.getPortList()) {
                Point absolutePoint = obj.getAbsolutePort(port.getPort());
                if(absolutePoint.equals(source)) {
                    selectedObject = obj;
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
    }

    public ObjectAbstract findObject(Point p) {
        for(ObjectAbstract obj : components) {
            for(Port port : obj.getPortList()) {
                Point absolutePoint = obj.getAbsolutePort(port.getPort());
                if(absolutePoint.equals(p)) {
                    return obj;
                }
            }
        }
        return null;
    }

    @Override
    public Port PointToPort(Point p) {
        for(ObjectAbstract obj : components) {
            for(Port port : obj.getPortList()) {
                Point absolutePoint = obj.getAbsolutePort(port.getPort());
                if(absolutePoint.equals(p)) 
                    return port;
            }
        }
        return null;
    }

    @Override
    public ArrayList<Port> getPortList() {
        return selectedObject.getPortList();
    }

    @Override
    public Point getAbsolutePort(Point relativePort) {
        return new Point(selectedObject.getPosition().x + relativePort.x, selectedObject.getPosition().y + relativePort.y);
    }

    @Override
    public void setPosition(Point newPosition) {
        super.setPosition(newPosition);

        // 更新子物件的絕對位置
        for(ObjectAbstract obj : components) {
            Point relativePosition = relativePositions.get(obj);
            Point newAbsolutePosition = new Point(relativePosition.x + newPosition.x, relativePosition.y + newPosition.y);
            obj.setPosition(newAbsolutePosition);
        }
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        for(ObjectAbstract obj : components) {
            obj.setSelected(selected);
        }
    }

    public ArrayList<ObjectAbstract> getComponents() {
        return components;
    }
}
