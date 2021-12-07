
package Form.Body.Chat;

import Form.Body.Event.PublicEvent;
import Model.AccountModel;
import Model.GroupModel;
import Server.Client;
import java.io.IOException;
import javax.swing.JOptionPane;

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
                    .addComponent(lbStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
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
        try {
            Object[] options = {"Có","Không"};
            int result = JOptionPane.showOptionDialog(null, "Bạn có muốn rời nhóm không", "Xác nhận", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
            if(result == JOptionPane.YES_OPTION){
                if(group!=null){
                    Client.getInstance().send("exitgroup#~"+group.getIdGroup()+"#~"+Client.getInstance().User.getId());
                    GroupModel gm = new GroupModel(group.getIdGroup(),group.getNameGroup());
                    PublicEvent.getInstance().getEventMenuLeft().exitGroup(gm);
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_lbexitgrMouseClicked

    private void lbblockmsgMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbblockmsgMouseClicked
        // TODO add your handling code here:
        try{
            Object[] options = {"Có chặn","Không"};
            int result = JOptionPane.showOptionDialog(null, "Bạn có muốn chặn không", "Xác nhận", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
            if(result == JOptionPane.YES_OPTION){
                if(aModel!=null){
                    PublicEvent.getInstance().getEventChat().loadBlock("load;"+aModel.getId());
                    Client.getInstance().send("blockuser#~block^&"+Client.getInstance().User.getId()+"^&"+aModel.getId());
                }
                if(group!=null){
                    PublicEvent.getInstance().getEventChat().loadBlock("loadblockgroup;"+group.getIdGroup());
                    Client.getInstance().send("blockuser#~blockgroup^&"+group.getIdGroup()+"^&"+Client.getInstance().User.getId());
                }
            } 
        }catch (IOException ex){
            System.out.println(ex);
        }
    }//GEN-LAST:event_lbblockmsgMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane layer;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbStatus;
    private component.ImageAvatar lbblockmsg;
    private component.ImageAvatar lbexitgr;
    // End of variables declaration//GEN-END:variables
}
