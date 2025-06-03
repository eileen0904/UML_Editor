package Mode;

import java.awt.*;
import java.awt.event.MouseEvent;

import Factory.LineInterface;
import Objects.CompositeObject;
import Objects.BasicObjects.ObjectAbstract;
import Objects.Line.LineAbstract;
import View.Canvas;

public class GeneralizationLineMode extends CanvasMode {
    private LineInterface factory;
    private ObjectAbstract target = null;
    private Point dragStartPoint = null, currentMousePoint = null;

    public GeneralizationLineMode(LineInterface factory) {
        this.factory = factory;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        canvas.selectedObject = findObjectAt(e.getPoint());
        if(canvas.selectedObject != null) {
            dragStartPoint = canvas.selectedObject.getClosestPort(e.getPoint());
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        currentMousePoint = e.getPoint();
        Canvas.getInstance().repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        ObjectAbstract target = findObjectAt(e.getPoint());
        checkObjects(e.getPoint());
        if(canvas.selectedObject != null && target != null && canvas.selectedObject != target) {
            Point targetPoint = target.getClosestPort(e.getPoint());
            LineAbstract newLine = factory.addLine(canvas.selectedObject, target, dragStartPoint, targetPoint);
            canvas.selectedObject.setConnectionPort(dragStartPoint, targetPoint, target);
            Canvas.getInstance().addLine(newLine);
        }
        canvas.selectedObject = null;
        dragStartPoint = null;
        currentMousePoint = null;
        Canvas.getInstance().repaint();
    }

    @Override
    public void paint(Graphics g) {
        if(dragStartPoint != null && currentMousePoint != null) { // dragging line
            g.setColor(Color.BLACK);
            g.drawLine(dragStartPoint.x, dragStartPoint.y, currentMousePoint.x, currentMousePoint.y);
        }
    }

    private ObjectAbstract findObjectAt(Point p) {
        for(int i = Canvas.getInstance().getObjects().size() - 1; i >= 0; i--) {
            ObjectAbstract obj = Canvas.getInstance().getObjects().get(i);
            if(obj.contains(p))
                return obj;
        }
        return null;
    }

    private void checkObjects(Point targetP) {
        if(canvas.selectedObject != null && canvas.selectedObject instanceof CompositeObject) {
            canvas.selectedObject = ((CompositeObject) canvas.selectedObject).findObject(dragStartPoint);
        }
        if(target != null && target instanceof CompositeObject) {
            target = ((CompositeObject) target).findObject(target.getClosestPort(targetP));
        }
    }
}
