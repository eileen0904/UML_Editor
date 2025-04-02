package Menu;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Objects.Object;
import Objects.CompositeObject;

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
        Object selectedObject = canvas.getSelectedObject();

        if(selectedObject instanceof CompositeObject) {
            CompositeObject composite = (CompositeObject) selectedObject;

            // 解構 CompositeObject 的最外層
            for(Object obj : composite.getComponents()) {
                Point relativePosition = composite.relativePositions.get(obj);
                Point absolutePosition = new Point(
                        composite.getPosition().x + relativePosition.x,
                        composite.getPosition().y + relativePosition.y);

                obj.setPosition(absolutePosition);
                obj.setSelected(false);
                canvas.objects.add(obj);
            }

            canvas.objects.remove(composite);
            composite.getComponents().clear();
            composite.relativePositions.clear();

            // 嘗試自動選中內層的 CompositeObject
            for(Object obj : canvas.objects) {
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
