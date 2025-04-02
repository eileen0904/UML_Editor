package Menu;

public class EditMenu extends Menu {
    public EditMenu(String name) {
        super(name);
        add(new CustomLabel("Label"));
        add(new Group("Group"));
        add(new Ungroup("Ungroup"));
    }
}
