package Form.Body.Chat;

import Form.MainChat;
import Sticker.Model_Sticker;
import Sticker.Sticker;
import component.OptionButton;
import component.ScrollBar;
import component.WrapLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

public class Chat_PanelMore extends javax.swing.JPanel {

    public Chat_PanelMore() {
        initComponents();
        init();
    }
    
    private void init(){
        setLayout(new MigLayout("fillx"));
        panelHeader = new JPanel();
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.LINE_AXIS));
        panelHeader.add(getButtonFile());
        panelHeader.add(getStickerStyle1());
        panelHeader.add(getStickerStyle2());
        add(panelHeader, "w 100%, h 30!, wrap");
        panelDetail = new JPanel();
        panelDetail.setLayout(new WrapLayout(WrapLayout.LEFT));
        JScrollPane ch = new JScrollPane(panelDetail);
        ch.setBorder(null);
        ch.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ch.setVerticalScrollBar(new ScrollBar());
        add(ch, "w 100%, h 100%");
    }
    
    private JButton getButtonFile() {
        OptionButton cmd = new OptionButton();
        cmd.setIcon(new ImageIcon(getClass().getResource("/Image/link.png")));
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser ch = new JFileChooser();
                ch.showOpenDialog(MainChat.getFrames()[0]);
                //  Update next
            }
        });
        return cmd;
    }
      
    private JButton getStickerStyle1() {
        OptionButton cmd = new OptionButton();
        cmd.setIcon(Sticker.getInstance().getImoji(1).toSize(55, 55).getIcon());
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                clearSelected();
                cmd.setSelected(true);
                panelDetail.removeAll();
                for (Model_Sticker d : Sticker.getInstance().getStyle1()) {
                    JButton c = new JButton(d.getIcon());
                    c.setName(d.getId() + "");
                    c.setBorder(new EmptyBorder(3, 3, 3, 3));
                    c.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    c.setContentAreaFilled(false);
                    panelDetail.add(c);
                }
                panelDetail.repaint();
                panelDetail.revalidate();
            }
        });
        return cmd;
    }
    
    private JButton getStickerStyle2() {
        OptionButton cmd = new OptionButton();
        cmd.setIcon(Sticker.getInstance().getImoji(13).toSize(55, 55).getIcon());
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                clearSelected();
                cmd.setSelected(true);
                panelDetail.removeAll();
                for (Model_Sticker d : Sticker.getInstance().getStyle2()) {
                    JButton c = new JButton(d.getIcon());
                    c.setName(d.getId() + "");
                    c.setBorder(new EmptyBorder(3, 3, 3, 3));
                    c.setCursor(new Cursor(Cursor.HAND_CURSOR));
                    c.setContentAreaFilled(false);
                    panelDetail.add(c);
                }
                panelDetail.repaint();
                panelDetail.revalidate();
            }
        });
        return cmd;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 147, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void clearSelected(){
        for(Component c : panelHeader.getComponents()){
            if(c instanceof OptionButton){
                ((OptionButton) c).setSelected(false);
            }
        }
    }
    private JPanel panelHeader;
    private JPanel panelDetail;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
