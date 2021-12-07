package Form.Body.Chat;

import Model.FileModel;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.EmptyBorder;

public class Chat_item extends javax.swing.JLayeredPane {


    public Chat_item() {
        initComponents();
        txt.setEditable(false);
        txt.setBackground(new Color(0, 0, 0, 0));
        txt.setOpaque(false);
    }

    public void setUserProfile(String user) {
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        layer.setBorder(new EmptyBorder(10, 10, 0, 10));//top,left,bottom,right 10, 10, 0, 10
        JButton cmd = new JButton(user);
        cmd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmd.setBorder(null);
        cmd.setContentAreaFilled(false);
        cmd.setFocusable(false);
        cmd.setForeground(new Color(30, 121, 213));
        cmd.setFont(new java.awt.Font("sansserif", 1, 11));
        txt.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));//5, 10, 5, 10
        layer.add(cmd);
        add(layer, 0);
    }

    public void setText(String text) {
        txt.setText(text);
    }
    public void seen() {
        txt.setText("Đã xem");
        txt.setBorder(new EmptyBorder(0,0,0,0));
        txt.setFont(new java.awt.Font("sansserif", 0, 11));
        setBackground(null);
    }

    public void setSticker(boolean right, Icon icon) {
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(right ? FlowLayout.LEFT : FlowLayout.LEFT));//,0,0
        layer.setBorder(new EmptyBorder(0, 5, 0, 5));
        JLabel label = new JLabel();
        label.setPreferredSize(new Dimension(60, 60));
        label.setIcon(icon);
        layer.add(label);
        add(layer);
        setBackground(null);
    }
    
    public void setFile(boolean right, FileModel fileModel, boolean isImage) {
        JLayeredPane layer = new JLayeredPane();
        layer.setLayout(new FlowLayout(right ? FlowLayout.RIGHT : FlowLayout.LEFT));
        layer.setBorder(new EmptyBorder(0, 5, 0, 5));
        if(isImage){
            Chat_Image chatImage = new Chat_Image(right);
            chatImage.addImage(fileModel);
            layer.add(chatImage);
        }
        else{   
            Chat_File chatFile = new Chat_File();
            chatFile.setFile(fileModel);
            layer.add(chatFile);
        }
        add(layer);
    }
    
    public void hideText() {
        txt.setVisible(false);
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
        if (getBackground() != null) {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        }
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.JIMSendTextPane txt;
    // End of variables declaration//GEN-END:variables
}
