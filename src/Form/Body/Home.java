
package Form.Body;

import Form.Body.Chat.Chat;
import Model.AccountModel;
import Model.GroupModel;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.miginfocom.swing.MigLayout;



public class Home extends javax.swing.JLayeredPane {

    /**
     * Creates new form Home
     */
    private Chat chat;
    public Home(){
        initComponents();
        init();
    }
    private void init(){
        //5[200!]0
        setLayout(new MigLayout("fillx,filly","0[200!]5[fill,100%]0","0[fill]0"));
        this.add(new Menu_left());
        try {
            chat = new Chat();
            this.add(chat);
            chat.setVisible(false);
        } catch (IOException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void remove(){
        chat.setVisible(false);
    }
    public void setUser(AccountModel am){
        chat.setUser(am);
        chat.setVisible(false);
        chat.setVisible(true);  
    }
    public void setSystem(){
        chat.setSystem();
        chat.setVisible(false);
        chat.setVisible(true);
    }
    public void setGroup(GroupModel gr){
        chat.setGroup(gr);
        chat.setVisible(false);
        chat.setVisible(true);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1007, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 543, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
