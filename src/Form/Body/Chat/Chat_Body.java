
package Form.Body.Chat;

import component.ScrollBar;
import java.awt.Adjustable;
import java.awt.Color;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;


public class Chat_Body extends javax.swing.JPanel {
    String text;
    public Chat_Body() {
        initComponents();
        init();
        addItemLeft("does not word wrap it at all showing all the text in one line only instead. It would be interesting to support word wrap on jTextPane1 resized too...","ManPham");
        addItemRight("does not word wrap it at all showing all the text in one line only instead. It would be interesting to support word wrap on jTextPane1 resized too...");
        addItemLeft("Allooooo","ManPham");
        addItemLeft("Allooooo","ManPham");
        addItemLeft("Allooooo","ManPham");
        addItemLeft("Allooooo","ManPham");
        addItemLeft("Allooooo","ManPham");

        
    }
    private void init(){
        body.setLayout(new MigLayout("fillx","","3[]3"));
        sp.setVerticalScrollBar(new ScrollBar());
        sp.getVerticalScrollBar().setBackground(Color.WHITE);
    }
    //them tin nhan bên trái
    public void addItemLeft(String text,String user){
        Chat_Left_With_Profile item = new Chat_Left_With_Profile();
        item.setText(text);
        item.setUserProfile(user);
        body.add(item,"wrap,w ::50%");
        //50% set chiều rộng
        body.repaint();
        body.revalidate();
        scrollToBottom();
    }
    //them tin nhan bên phải
    
    public void addItemRight(String text){   
        Chat_Right item = new Chat_Right();
        item.setText(text);
        body.add(item,"wrap, al right, w ::50%");
        //50% set chiều rộng
        body.repaint();
        body.revalidate();  
        scrollToBottom();
        
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