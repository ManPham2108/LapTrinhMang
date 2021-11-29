
package Form.Body.Chat;

import Model.AccountModel;
import Model.GroupModel;


public class Chat_Title extends javax.swing.JPanel {
   
    private AccountModel aModel;
    private GroupModel group;
    public AccountModel getaModel() {
        return aModel;
    }

    public GroupModel getGroup() {
        return group;
    }
    
    public Chat_Title() {
        initComponents();
    }
    public void loadblockuser(){
        lbblockmsg.setVisible(true);
        lbexitgr.setVisible(false);
    }
    public void loadexitgr(){
        lbblockmsg.setVisible(true);
        lbexitgr.setVisible(true);
    }
    public void hideblock(){
        lbblockmsg.setVisible(false);
        lbexitgr.setVisible(false);
    }
    //set username
    public void setuser(AccountModel user){
        this.aModel = user;
        lbName.setText(aModel.getFullName());
        group = null;
    }
    public void setSystem(){
        lbName.setText("System");
        aModel = null;
        group = null;
    }
    public void setGroup(GroupModel gr){
        this.group = gr;
        lbName.setText(group.getNameGroup());
        aModel = null;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layer = new javax.swing.JLayeredPane();
        lbName = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();
        lbexitgr = new component.ImageAvatar();
        lbblockmsg = new component.ImageAvatar();

        setBackground(new java.awt.Color(242, 242, 242));

        layer.setPreferredSize(new java.awt.Dimension(217, 20));

        lbName.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lbName.setForeground(new java.awt.Color(66, 66, 66));
        lbName.setText("Name");

        lbStatus.setForeground(new java.awt.Color(35, 163, 59));

        layer.setLayer(lbName, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layer.setLayer(lbStatus, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layerLayout = new javax.swing.GroupLayout(layer);
        layer.setLayout(layerLayout);
        layerLayout.setHorizontalGroup(
            layerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layerLayout.createSequentialGroup()
                .addGroup(layerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(178, 178, 178))
        );
        layerLayout.setVerticalGroup(
            layerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layerLayout.createSequentialGroup()
                .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 9, Short.MAX_VALUE))
        );

        lbexitgr.setBackground(new java.awt.Color(255, 255, 255));
        lbexitgr.setBorderSize(0);
        lbexitgr.setImage(new javax.swing.ImageIcon(getClass().getResource("/Image/leave-group.png"))); // NOI18N
        lbexitgr.setInheritsPopupMenu(true);
        lbexitgr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbexitgrMouseClicked(evt);
            }
        });

        lbblockmsg.setBackground(new java.awt.Color(255, 255, 255));
        lbblockmsg.setBorderSize(0);
        lbblockmsg.setImage(new javax.swing.ImageIcon(getClass().getResource("/Image/icons8-block-50.png"))); // NOI18N
        lbblockmsg.setInheritsPopupMenu(true);
        lbblockmsg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbblockmsgMouseClicked(evt);
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
                .addComponent(lbexitgr, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbblockmsg, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(layer, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
            .addComponent(lbexitgr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbblockmsg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lbexitgrMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbexitgrMouseClicked
        System.out.println("aâ");
    }//GEN-LAST:event_lbexitgrMouseClicked

    private void lbblockmsgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbblockmsgMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_lbblockmsgMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane layer;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbStatus;
    private component.ImageAvatar lbblockmsg;
    private component.ImageAvatar lbexitgr;
    // End of variables declaration//GEN-END:variables
}
