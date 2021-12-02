
package Form.Body;

import Form.Body.Event.PublicEvent;
import Model.GroupModel;
import Server.Client;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Item_Group extends javax.swing.JPanel {
    private GroupModel group;

    public GroupModel getGroup() {
        return group;
    }
    
    public Item_Group(GroupModel group) {
        this.group = group;
        initComponents();
        lbName.setText(group.getNameGroup());
        init();
    }
    public void updateNotiMsg(Boolean status){
        if(status){
            lbName.setVisible(false);
            lbName.setFont(new java.awt.Font("Time New Roman", 3, 14));
            lbName.setVisible(true);
        }
        else{
            lbName.setVisible(false);
            lbName.setFont(new java.awt.Font("Tahoma", 0, 14));
            lbName.setVisible(true);
        }
    }
    private void init(){
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(new Color(230,230,230));
            }
            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(242,242,242));
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                PublicEvent.getInstance().getEventChat().removeAllChatBody();
                PublicEvent.getInstance().getEventMain().SelectGroup(group);
                try {
                    Client.getInstance().send("loadmessage#~"+group.getIdGroup()+"^&"+Client.getInstance().User.getId());
                } catch (IOException ex) {
                    Logger.getLogger(Item_Group.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }  
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageAvatar1 = new component.ImageAvatar();
        lbName = new javax.swing.JLabel();

        setBackground(new java.awt.Color(242, 242, 242));

        imageAvatar1.setBorderSize(0);
        imageAvatar1.setImage(new javax.swing.ImageIcon(getClass().getResource("/Image/icons-group-50.png"))); // NOI18N

        lbName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbName.setText("Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbName, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(imageAvatar1, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(lbName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.ImageAvatar imageAvatar1;
    private javax.swing.JLabel lbName;
    // End of variables declaration//GEN-END:variables
}
