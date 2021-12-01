package Form.Body.Chat;

import Sticker.Sticker;
import component.ScrollBar;
import java.awt.Adjustable;
import java.awt.Color;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.JScrollBar;
import net.miginfocom.swing.MigLayout;

public class Chat_Body extends javax.swing.JPanel {

    public Chat_Body() {
        initComponents();
        init();
    }

    private void init() {
        body.setLayout(new MigLayout("fillx", "", "5[bottom]5"));
        sp.setVerticalScrollBar(new ScrollBar());
        sp.getVerticalScrollBar().setBackground(Color.WHITE);
    }

    //them tin nhan bên trái
    public void addItemLeft(String text,String user) {
        //Chat_Left itemLeft = new Chat_Left();
        Chat_Left_With_Profile itemLeft = new Chat_Left_With_Profile();
        String[] message = text.split("\\###");
        if (message.length == 2 && message[0].equals("***sticker")) {
            itemLeft.setSticker(Sticker.getInstance().getSticker(Integer.valueOf(message[1])).toSize(60, 60).getIcon());
        } else {
            itemLeft.setText(text);
        }
        itemLeft.setUserProfile(user);
        body.add(itemLeft, "wrap, w 100::80%");
        //50% set chiều rộng
        repaint();
        revalidate();
        scrollToBottom();
    }

    //them tin nhan bên phải
    public void addItemRight(String text) {
        Chat_Right itemRight = new Chat_Right();
        String[] message = text.split("\\###");
        if (message.length == 2 && message[0].equals("***sticker")) {
            itemRight.setSticker(Sticker.getInstance().getSticker(Integer.valueOf(message[1])).toSize(60, 60).getIcon());
        }
        else{
            itemRight.setText(text);
        }
        body.add(itemRight, "wrap, al right,w ::50%");
        //50% set chiều rộng
        repaint();
        revalidate();
        scrollToBottom();
    }

    public void removeall() {
        body.removeAll();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        body = new javax.swing.JPanel();

        sp.setBackground(new java.awt.Color(255, 255, 255));
        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        body.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 826, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 555, Short.MAX_VALUE)
        );

        sp.setViewportView(body);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
    }// </editor-fold>//GEN-END:initComponents
    private void scrollToBottom() {
        JScrollBar verticalBar = sp.getVerticalScrollBar();
        AdjustmentListener downScroller = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                Adjustable adjustable = e.getAdjustable();
                adjustable.setValue(adjustable.getMaximum());
                verticalBar.removeAdjustmentListener(this);
            }
        };
        verticalBar.addAdjustmentListener(downScroller);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
