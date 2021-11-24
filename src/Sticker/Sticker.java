package Sticker;

import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;

public class Sticker {

    private static Sticker instance;

    public static Sticker getInstance() {
        if (instance == null) {
            instance = new Sticker();
        }
        return instance;
    }

    private Sticker() {
    }

    public List<Model_Sticker> getStyle1() {
        List<Model_Sticker> list = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            list.add(new Model_Sticker(i, new ImageIcon(getClass().getResource("/Sticker/Image/" + i + ".png"))).toSize(55, 55));
        }
        return list;
    }

    public List<Model_Sticker> getStyle2() {
        List<Model_Sticker> list = new ArrayList<>();
        for (int i = 13; i <= 19; i++) {
            list.add(new Model_Sticker(i, new ImageIcon(getClass().getResource("/Sticker/Image/" + i + ".png"))).toSize(55, 55));
        }
        return list;
    }

    public Model_Sticker getImoji(int id) {
        return new Model_Sticker(id, new ImageIcon(getClass().getResource("/Sticker/Image/" + id + ".png")));
    }
}
