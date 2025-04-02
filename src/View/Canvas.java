package View;

import javax.swing.*;

import Objects.CompositeObject;
import Objects.Connection;
import Objects.Object;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Canvas extends JPanel {
    private static Canvas instance;
    private String mode = "select";
    public ArrayList<Object> objects = new ArrayList<>();
    private ArrayList<Connection> connections = new ArrayList<>();
    private Object selectedObject = null, target = null;
    private Point dragStartPoint = null, dragEndPoint = null;
    private Point currentMousePoint = null;
    private Point offset = null;

    public Canvas() {
        this.setBackground(Color.WHITE);

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                handleMousePressed(e);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                handleMouseReleased(e);
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                handleMouseDragged(e);
            }
        });
    }

    public static Canvas getInstance() {
        synchronized(Canvas.class) {
            if(instance == null) {
                instance = new Canvas();
            }
        }
        return instance;
    }

    
    private void handleMousePressed(MouseEvent e) {
        Point p = e.getPoint();
        for(Object obj : objects) {
            obj.setSelected(false);
        }
        if(mode.equals("select")) {
            if(isObjectSelected(p)) { // select object
                selectedObject = findObjectAt(p);
                selectedObject.setSelected(true);
                offset = new Point(selectedObject.getPosition().x - p.x, selectedObject.getPosition().y - p.y);
                repaint();
            }
            else { // drag select
                dragStartPoint = p;
                dragEndPoint = null;
            }
            repaint();
        } 
        else if(mode.equals("class") || mode.equals("use case")) {
            Object newObject = new Object(mode, p);
            objects.add(newObject);
            repaint();
        } 
        else if(mode.equals("association line") || mode.equals("composition line") || mode.equals("generalization line")) {
            selectedObject = findObjectAt(p);
            if(selectedObject != null) {
                dragStartPoint = selectedObject.getClosestPort(p);
            }
        }
    }

    private void handleMouseDragged(MouseEvent e) {
        if(mode.equals("select")) {
            if(dragStartPoint == null) { // drag object
                Point newPos = new Point(e.getPoint().x + offset.x, e.getPoint().y + offset.y);
                selectedObject.setPosition(newPos);
                repaint();
            }
            else { // drag select
                dragEndPoint = e.getPoint();
            }
            repaint();
        }
        else if(mode.equals("association line") || mode.equals("composition line") || mode.equals("generalization line")) {
            currentMousePoint = e.getPoint();
            repaint();
        }
    }

    private void handleMouseReleased(MouseEvent e) {
        if(mode.equals("select") && dragStartPoint != null && dragEndPoint != null) {
            Rectangle selectionRect = createRectangle(dragStartPoint, dragEndPoint);
            boolean anySelected = false;

            for(Object obj : objects) { // 遍歷所有物件，選擇完全包含在矩形範圍內的物件
                Rectangle objBounds = new Rectangle(obj.getPosition().x, obj.getPosition().y, obj.getWidth(), obj.getHeight());
                if(selectionRect.contains(objBounds)) {
                    obj.setSelected(true);
                    anySelected = true;
                } 
                else {
                    obj.setSelected(false);
                }
            }
            // 如果沒有物件被選中，取消所有選中狀態
            if(!anySelected) {
                for(Object obj : objects) {
                    obj.setSelected(false);
                }
            }
            dragStartPoint = null;
            dragEndPoint = null;
            repaint();
        }

        if(mode.equals("association line") || mode.equals("composition line") || mode.equals("generalization line")) {
            target = findObjectAt(e.getPoint());
            checkObjects(e.getPoint());
            if(selectedObject != null && target != null && selectedObject != target) {
                Point targetPoint = target.getClosestPort(e.getPoint());
                selectedObject.setConnectionPort(dragStartPoint, targetPoint, target);
                connections.add(new Connection(selectedObject, target, mode, dragStartPoint, targetPoint));
            }
            selectedObject = null;
            dragStartPoint = null;
            currentMousePoint = null;
            repaint();
        }
    }

    private Object findObjectAt(Point p) {
        // 從最上層物件開始檢查，找到包含點的物件
        for(int i = objects.size() - 1; i >= 0; i--) {
            Object obj = objects.get(i);
            if(obj.contains(p)) {
                return obj;
            }
        }
        return null;
    }

    private void checkObjects(Point targetP) {
        if(selectedObject != null && selectedObject instanceof CompositeObject) {
            selectedObject = ((CompositeObject) selectedObject).findObject(dragStartPoint);
        }
        if(target != null && target instanceof CompositeObject) {
            target = ((CompositeObject) target).findObject(target.getClosestPort(targetP));
        }
    }

    private boolean isObjectSelected(Point p) {
        for(Object obj : objects) {
            if(obj.contains(p)) {
                return true;
            }
        }
        return false;
    }

    public Object getSelectedObject() {
        return selectedObject;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    private Rectangle createRectangle(Point p1, Point p2) {
        int x = Math.min(p1.x, p2.x);
        int y = Math.min(p1.y, p2.y);
        int width = Math.abs(p1.x - p2.x);
        int height = Math.abs(p1.y - p2.y);
        return new Rectangle(x, y, width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(Object obj : objects) {
            obj.draw(g);
        }
        for(Connection line : connections) {
            line.draw(g);
        }
        if(dragStartPoint != null && dragEndPoint != null) { // select area
            g.setColor(Color.BLUE);
            Rectangle selectionRect = createRectangle(dragStartPoint, dragEndPoint);
            ((Graphics2D) g).draw(selectionRect);
        }
        if(dragStartPoint != null && currentMousePoint != null) { // dragging line
            g.setColor(Color.BLACK);
            g.drawLine(dragStartPoint.x, dragStartPoint.y, currentMousePoint.x, currentMousePoint.y);
        }
    }
}
