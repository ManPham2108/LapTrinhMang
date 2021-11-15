
package Form.Body.Chat;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.EmptyBorder;


public class Chat_item extends javax.swing.JLayeredPane {
    private JLabel label;

    public Chat_item() {
        initComponents();
        txt.setEditable(false);
        txt.setBackground(new Color(0,0,0,0));
        txt.setOpaque(false);
    }
    public void setUserProfile(String user){
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        layer.setBorder(new EmptyBorder(10,10,0,10));
        JLabel labeluser = new JLabel(user);
        labeluser.setBorder(null);
        labeluser.setFocusable(false);
        labeluser.setFont(new java.awt.Font("sansserif",1,13));
        labeluser.setForeground(new Color(30,121,213));
        txt.setBorder(javax.swing.BorderFactory.createEmptyBorder(5,10,5,10));
        layer.add(labeluser);
        add(layer,0);
    }  
    public void setText(String text){
        txt.setText(text);
    }
    public void seen(){
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));
        layer.setBorder(new EmptyBorder(0,5,17,5));
        label = new JLabel("Đã xem");
        //Màu chữ đã xem
        label.setForeground(new Color(110,110,110));
        label.setHorizontalTextPosition(JLabel.LEFT);
        layer.add(label);
        add(layer);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt = new component.JIMSendTextPane();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.PAGE_AXIS));

        txt.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        txt.setSelectionColor(new java.awt.Color(89, 173, 229));
        add(txt);
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.JIMSendTextPane txt;
    // End of variables declaration//GEN-END:variables
}
