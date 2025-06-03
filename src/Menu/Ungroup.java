package Menu;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Objects.CompositeObject;
import Objects.BasicObjects.ObjectAbstract;

public class Ungroup extends MenuItem implements ActionListener {
    public Ungroup(String name) {
        super(name);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        handleUngroup();
    }

    private void handleUngroup() {
        //System.out.println("UnGroup");
        //System.out.println("selectedObject = " + canvas.selectedObject);
        if(canvas.selectedObject instanceof CompositeObject) {
            //System.out.println("ungroup");
            CompositeObject composite = (CompositeObject) canvas.selectedObject;

            // 解構 CompositeObject 的最外層
            for(ObjectAbstract obj : composite.getComponents()) {
                Point relativePosition = composite.relativePositions.get(obj);
                Point absolutePosition = new Point(
                        composite.getPosition().x + relativePosition.x,
                        composite.getPosition().y + relativePosition.y);

                obj.setPosition(absolutePosition);
                obj.setSelected(false);
                canvas.getObjects().add(obj);
            }

            canvas.getObjects().remove(composite);
            composite.getComponents().clear();
            composite.relativePositions.clear();

            // 嘗試自動選中內層的 CompositeObject
            for(ObjectAbstract obj : canvas.getObjects()) {
                if(obj instanceof CompositeObject) {
                    obj.setSelected(true);
                    canvas.repaint();
                    return;
                }
            }
            canvas.repaint();
        }
    }
}
