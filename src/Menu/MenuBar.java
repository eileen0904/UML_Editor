package Menu;

import javax.swing.*;

public class MenuBar extends JMenuBar {
    private static MenuBar instance;

    private MenuBar() {
        add(new EditMenu("Edit"));
    }

    public static MenuBar getInstance() {
        synchronized(MenuBar.class) {
            if(instance == null) {
                instance = new MenuBar();
            }
        }
        return instance;
    }
}
