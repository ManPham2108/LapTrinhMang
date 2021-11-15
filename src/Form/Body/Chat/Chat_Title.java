
package Form.Body.Chat;

import java.awt.Color;


public class Chat_Title extends javax.swing.JPanel {

 
    public Chat_Title() {
        initComponents();
    }
    //set username
    public void setName(String username){
        lbName.setText(username);
    }
    public void statusOnline(){
        lbStatus.setText("Online");
        lbStatus.setForeground(new java.awt.Color(40,147,59));
        
    }
    public void setStatus(String text){
        lbStatus.setText(text);
        lbStatus.setForeground(new Color(160,160,160));
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layer = new javax.swing.JLayeredPane();
        lbName = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();

        setBackground(new java.awt.Color(242, 242, 242));

        layer.setPreferredSize(new java.awt.Dimension(217, 20));
        layer.setLayout(new java.awt.GridLayout(0, 1));

        lbName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbName.setForeground(new java.awt.Color(66, 66, 66));
        lbName.setText("Name");
        layer.add(lbName);

        lbStatus.setForeground(new java.awt.Color(35, 163, 59));
        lbStatus.setText("Online");
        layer.add(lbStatus);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(layer, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(416, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layer, javax.swing.GroupLayout.PREFERRED_SIZE, 31, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane layer;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbStatus;
    // End of variables declaration//GEN-END:variables
}
