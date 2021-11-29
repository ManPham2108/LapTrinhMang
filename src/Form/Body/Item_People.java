
package Form.Body;

import Form.Body.Chat.Chat_Title;
import Form.Body.Event.PublicEvent;
import Model.AccountModel;
import Server.Client;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Item_People extends javax.swing.JPanel {
    public AccountModel user;

    public AccountModel getUser() {
        return user;
    }
    
    public Item_People(AccountModel user) {
        this.user = user;
        initComponents();
        lbName.setText(user.getFullName());
        if(user.isStatus()){
            lbStatus.setVisible(true);
        }
        else{
            lbStatus.setVisible(false);
        }
        init();
    }
    public void updateStatus(){
        if(user.isStatus()){
            lbStatus.setVisible(true);
        }
        else{
            lbStatus.setVisible(false);
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
                PublicEvent.getInstance().getEventMain().SelectUser(user);
                PublicEvent.getInstance().getEventChat().removeAllChatBody();
                Chat_Title chattitle = new Chat_Title();
                chattitle.loadblockuser();
                try {
                    Client.getInstance().send("loadmessage#~"+Client.getInstance().User.getId()+"^&"+user.getId());
                } catch (IOException ex) {
                    Logger.getLogger(Item_People.class.getName()).log(Level.SEVERE, null, ex);
                }
            }  
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        imageAvatar1 = new component.ImageAvatar();
        lbName = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();

        setBackground(new java.awt.Color(242, 242, 242));

        imageAvatar1.setBorderSize(0);
        imageAvatar1.setImage(new javax.swing.ImageIcon(getClass().getResource("/Image/male-user.png"))); // NOI18N

        lbName.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lbName.setText("Name");

        lbStatus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbStatus.setForeground(new java.awt.Color(0, 153, 0));
        lbStatus.setText("Online");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbName, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                    .addComponent(lbStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lbName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(0, 0, 0)
                        .addComponent(lbStatus)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private component.ImageAvatar imageAvatar1;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbStatus;
    // End of variables declaration//GEN-END:variables
}
