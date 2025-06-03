package View;

import javax.swing.*;

import Mode.CanvasMode;
import Objects.BasicObjects.ObjectAbstract;
import Objects.Line.LineAbstract;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Canvas extends JPanel implements MouseListener, MouseMotionListener {
    private static Canvas instance;
    private CanvasMode currentMode;
    private ArrayList<ObjectAbstract> objects;
    private ArrayList<LineAbstract> lines;
    public ObjectAbstract selectedObject = null;

    public Canvas() {
        this.setBackground(Color.WHITE);
        this.setFocusable(true);

        objects = new ArrayList<>();
        lines = new ArrayList<>();

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    // Singleton
    public static Canvas getInstance() {
        synchronized(Canvas.class) {
            if(instance == null) {
                instance = new Canvas();
            }
        }
        return instance;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(currentMode != null) {
            clearSelection();
            currentMode.mousePressed(e);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(currentMode != null)
            currentMode.mouseDragged(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(currentMode != null)
            currentMode.mouseReleased(e);
    }

    public ArrayList<ObjectAbstract> getObjects() {
        return objects;
    }

    public ArrayList<LineAbstract> getLines() {
        return lines;
    }

    public void setMode(CanvasMode mode) {
        this.currentMode = mode;
    }

    public void addObject(ObjectAbstract obj) {
        objects.add(obj);
        System.out.println();
    }

    public void addLine(LineAbstract line) {
        lines.add(line);
    }

    public void clearSelection() {
        for(ObjectAbstract obj : objects) {
            obj.setSelected(false);
        }
    }

    public ObjectAbstract findObjectAt(Point p) {
        // 從最上層物件開始檢查，找到包含點的物件
        for(int i = objects.size() - 1; i >= 0; i--) {
            ObjectAbstract obj = objects.get(i);
            if(obj.contains(p)) {
                return obj;
            }
        }
        return null;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (ObjectAbstract obj : objects) {
            obj.draw(g);
        }
        for (LineAbstract line : lines) {
            line.draw(g);
        }

        // 呼叫當前 mode 的繪圖方法（例如畫正在拖曳的線）
        if (currentMode != null) {
            currentMode.paint(g);
        }
    }
}
