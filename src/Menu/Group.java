package Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Objects.CompositeObject;
import Objects.BasicObjects.ObjectAbstract;

public class Group extends MenuItem implements ActionListener { 
    public Group(String name) {
        super(name);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        handleGroup();
    }

    private void handleGroup() {
        ArrayList<ObjectAbstract> selectedObjects = new ArrayList<>();
        for(ObjectAbstract obj : canvas.getObjects()) {
            if(obj.isSelected()) {
                selectedObjects.add(obj);
            }
        }

        if(selectedObjects.size() > 1) {
            for(ObjectAbstract obj : selectedObjects) {
                obj.setSelected(false);
            }
            canvas.getObjects().removeAll(selectedObjects);

            ObjectAbstract composite = new CompositeObject(selectedObjects);
            canvas.getObjects().add(composite);

            canvas.repaint();
        }
    }

}
