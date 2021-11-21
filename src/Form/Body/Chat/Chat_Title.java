
package Form.Body.Chat;

import Model.AccountModel;
import java.awt.Color;


public class Chat_Title extends javax.swing.JPanel {
   
    private AccountModel aModel;

    public AccountModel getaModel() {
        return aModel;
    }
    
    public Chat_Title() {
        initComponents();
    }
    //set username
    public void setuser(AccountModel user){
        this.aModel = user;
        lbName.setText(aModel.getFullName());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layer = new javax.swing.JLayeredPane();
        lbName = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();
        imageAvatar1 = new component.ImageAvatar();
        imageAvatar2 = new component.ImageAvatar();

        setBackground(new java.awt.Color(242, 242, 242));

        layer.setPreferredSize(new java.awt.Dimension(217, 20));
        layer.setLayout(new java.awt.GridLayout(0, 1));

        lbName.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lbName.setForeground(new java.awt.Color(66, 66, 66));
        lbName.setText("Name");
        layer.add(lbName);

        lbStatus.setForeground(new java.awt.Color(35, 163, 59));
        layer.add(lbStatus);

        imageAvatar1.setBackground(new java.awt.Color(255, 255, 255));
        imageAvatar1.setBorderSize(0);
        imageAvatar1.setImage(new javax.swing.ImageIcon(getClass().getResource("/Image/leave-group.png"))); // NOI18N
        imageAvatar1.setInheritsPopupMenu(true);
        imageAvatar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imageAvatar1MouseClicked(evt);
            }
        });

        imageAvatar2.setBackground(new java.awt.Color(255, 255, 255));
        imageAvatar2.setBorderSize(0);
        imageAvatar2.setImage(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-block-50.png"))); // NOI18N
        imageAvatar2.setInheritsPopupMenu(true);
        imageAvatar2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                imageAvatar2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(layer, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(imageAvatar2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layer, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
            .addComponent(imageAvatar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(imageAvatar2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void imageAvatar1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageAvatar1MouseClicked
        System.out.println("aâ");
    }//GEN-LAST:event_imageAvatar1MouseClicked

    private void imageAvatar2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_imageAvatar2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_imageAvatar2MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.ImageAvatar imageAvatar1;
    private component.ImageAvatar imageAvatar2;
    private javax.swing.JLayeredPane layer;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbStatus;
    // End of variables declaration//GEN-END:variables
}
