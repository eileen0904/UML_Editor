package Menu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Objects.Object;

public class Rename extends MenuItem implements ActionListener {
    
    public Rename(String name) {
        super(name);
        addActionListener(this);
        // JMenuItem changeNameItem = new JMenuItem("Rename Object");
        // changeNameItem.addActionListener(e -> menu.handleChangeName(canvas));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        handleRename();
    }

    private void handleRename() {
        Object selectedObject = canvas.getSelectedObject();
        if(selectedObject != null) {
            String newName = JOptionPane.showInputDialog("Enter new name:");
            if(newName != null && !newName.trim().isEmpty()) {
                selectedObject.setName(newName);
                canvas.repaint();
            }
        } 
        else {
            JOptionPane.showMessageDialog(null, "No object selected!");
        }
    }
}
