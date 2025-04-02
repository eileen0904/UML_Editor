package Menu;

public class EditMenu extends Menu {
    
    public EditMenu(String name) {
        super(name);
        add(new Rename("Rename Object"));
        add(new Group("Group"));
        add(new Ungroup("Ungroup"));
    }
}
