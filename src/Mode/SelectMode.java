package Mode;

import java.awt.*;
import java.awt.event.MouseEvent;
import Objects.BasicObjects.ObjectAbstract;

public class SelectMode extends CanvasMode {
    private Point dragStart, dragEnd, offset;

    @Override
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        canvas.selectedObject = canvas.findObjectAt(p);
        //System.out.println("Select Mode");
        if(canvas.selectedObject != null) {
            //System.out.println("selectedObject = " + canvas.selectedObject.getClass().getName());
            canvas.selectedObject.setSelected(true);
            offset = new Point(canvas.selectedObject.getPosition().x - p.x, canvas.selectedObject.getPosition().y - p.y);
            canvas.repaint();
        } 
        else { // drag select
            dragStart = p;
            dragEnd = null;
        }
        canvas.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(dragStart == null) {
            Point newPos = new Point(e.getX() + offset.x, e.getY() + offset.y);
            canvas.selectedObject.setPosition(newPos);
            canvas.repaint();
        } 
        else {
            dragEnd = e.getPoint();
        }
        canvas.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(dragStart != null && dragEnd != null) {
            Rectangle selectionRect = createRectangle(dragStart, dragEnd);

            for(ObjectAbstract obj : canvas.getObjects()) { // 遍歷所有物件，選擇完全包含在矩形範圍內的物件
                Rectangle objBounds = new Rectangle(obj.getPosition().x, obj.getPosition().y, obj.getWidth(), obj.getHeight());
                obj.setSelected(selectionRect.contains(objBounds));
            }
        }
        dragStart = null;
        dragEnd = null;
        canvas.repaint();
    }

    @Override
    public void paint(Graphics g) {
        if(dragStart != null && dragEnd != null) {
            g.setColor(Color.BLUE);
            ((Graphics2D) g).draw(createRectangle(dragStart, dragEnd));
        }
    }

    private Rectangle createRectangle(Point p1, Point p2) {
        int x = Math.min(p1.x, p2.x);
        int y = Math.min(p1.y, p2.y);
        int width = Math.abs(p1.x - p2.x);
        int height = Math.abs(p1.y - p2.y);
        return new Rectangle(x, y, width, height);
    }
}
