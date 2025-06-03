package Mode;

import java.awt.event.MouseEvent;
import Factory.ObjectInterface;
import Objects.BasicObjects.ObjectAbstract;
import View.Canvas;

public class RectMode extends CanvasMode {
    private ObjectInterface factory;

    public RectMode(ObjectInterface factory) {
        this.factory = factory;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ObjectAbstract obj = factory.createObject(e.getPoint());
        Canvas.getInstance().addObject(obj);
        Canvas.getInstance().repaint();
    }
}
