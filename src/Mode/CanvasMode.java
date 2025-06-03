package Mode;

import java.awt.*;
import java.awt.event.MouseEvent;
import View.Canvas;

public abstract class CanvasMode {
    protected Canvas canvas = Canvas.getInstance();

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void paint(Graphics g) {
    }
}
