package Sticker;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Model_Sticker {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public Model_Sticker(int id, Icon icon) {
        this.id = id;
        this.icon = icon;
    }

    public Model_Sticker() {
    }

    private int id;
    private Icon icon;

    public Model_Sticker toSize(int x, int y) {
        return new Model_Sticker(id, new ImageIcon(((ImageIcon) icon).getImage().getScaledInstance(x, y, Image.SCALE_SMOOTH)));
    }
}
