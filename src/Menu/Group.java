package Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import Objects.Object;
import Objects.CompositeObject;

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
        ArrayList<Object> selectedObjects = new ArrayList<>();

        for(Object obj : canvas.objects) {
            if(obj.isSelected()) {
                selectedObjects.add(obj);
            }
        }

        if(selectedObjects.size() > 1) {
            for(Object obj : selectedObjects) {
                obj.setSelected(false);
            }
            canvas.objects.removeAll(selectedObjects);

            CompositeObject composite = new CompositeObject(selectedObjects);
            canvas.objects.add(composite);

            canvas.repaint();
        }
    }

}
