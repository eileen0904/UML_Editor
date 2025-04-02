package Objects;

import java.awt.*;

public class Connection {
    private Object source;
    private Object target;
    private Point startPoint, endPoint;
    private Port startPort, endPort;
    private String type;

    public Connection(Object source, Object target, String type, Point sourceP, Point targetP) {
        checkObjects(source, target, sourceP, targetP);
        this.type = type;
        this.startPort = source.PointToPort(sourceP);
        this.endPort = target.PointToPort(targetP);
    }

    private void checkObjects(Object source, Object target, Point sourceP, Point targetP) {
        if(source instanceof CompositeObject) {
            this.source = ((CompositeObject) source).findObject(sourceP);
        } 
        else {
            this.source = source;
        }
        if(target instanceof CompositeObject) {
            this.target = ((CompositeObject) target).findObject(targetP);
        } 
        else {
            this.target = target;
        }
    }

    public void draw(Graphics g) {
        startPoint = source.getAbsolutePort(startPort.getPort());
        endPoint = target.getAbsolutePort(endPort.getPort());

        g.drawLine(startPoint.x, startPoint.y, endPoint.x, endPoint.y);

        // 計算箭頭的角度
        double angle = Math.atan2(endPoint.y - startPoint.y, endPoint.x - startPoint.x);
        int arrowLength = 15;

        // 箭頭的兩個邊
        int[] arrowX = {(int) (endPoint.x - arrowLength * Math.cos(angle - Math.PI / 6)),
                        endPoint.x, (int) (endPoint.x - arrowLength * Math.cos(angle + Math.PI / 6))};

        int[] arrowY = {(int) (endPoint.y - arrowLength * Math.sin(angle - Math.PI / 6)),
                        endPoint.y, (int) (endPoint.y - arrowLength * Math.sin(angle + Math.PI / 6))};
        
        int[] arrowX2 = {(int) Math.round(endPoint.x - arrowLength * Math.cos(angle - Math.PI / 4)), 
                        endPoint.x, (int) Math.round(endPoint.x - arrowLength * Math.cos(angle + Math.PI / 4)),
                        (int) Math.round(endPoint.x - arrowLength * (Math.cos(angle - Math.PI / 4) + Math.cos(angle + Math.PI / 4))) };
        
        int[] arrowY2 = { (int) Math.round(endPoint.y - arrowLength * Math.sin(angle - Math.PI / 4)),
                        endPoint.y, (int) Math.round(endPoint.y - arrowLength * Math.sin(angle + Math.PI / 4)),
                        (int) Math.round(endPoint.y - arrowLength * (Math.sin(angle - Math.PI / 4) + Math.sin(angle + Math.PI / 4))) };

        if(type.equals("association line")) {
            g.drawLine(endPoint.x, endPoint.y, arrowX[0], arrowY[0]);
            g.drawLine(endPoint.x, endPoint.y, arrowX[2], arrowY[2]);
        } 
        else if(type.equals("composition line")) {
            g.setColor(Color.white);
            g.fillPolygon(new Polygon(arrowX2, arrowY2, 4));
            g.setColor(Color.BLACK);
            g.drawPolygon(new Polygon(arrowX2, arrowY2, 4));
        } 
        else if(type.equals("generalization line")) {
            // 畫出箭頭的三角形
            g.setColor(Color.white);
            g.fillPolygon(new Polygon(arrowX, arrowY, 3));
            g.setColor(Color.BLACK);
            g.drawPolygon(new Polygon(arrowX, arrowY, 3));
        }
    }

    public Object getSource() {
        return source;
    }

    public Object getTarget() {
        return target;
    }

    public void setStartPort(Port startPort) {
        this.startPort = startPort;
    }

    public void setEndPort(Port endPort) {
        this.endPort = endPort;
    }
}
