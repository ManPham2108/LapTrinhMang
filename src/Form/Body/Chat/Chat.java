/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.Body.Chat;

import Form.Body.Event.EventChat;
import Form.Body.Event.PublicEvent;
import Model.AccountModel;
import Model.GroupModel;
import Server.Client;
import java.io.IOException;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author man21
 */
public class Chat extends javax.swing.JPanel {

    /**
     * Creates new form Menu_left
     */
    private Chat_Title chatTitle;
    private Chat_Body chatBody;
    private Chat_Bottom chatBottom; 
    public Chat() throws IOException {
        initComponents();
        init();
    }
    private void init() throws IOException{
        setLayout(new MigLayout("fillx", "0[fill]0", "0[]0[100%, fill]0[shrink 0]0"));
        chatTitle = new Chat_Title();
        chatBody = new Chat_Body();
        chatBottom = new Chat_Bottom();
        //thêm tin nhắn vào bên phải
        PublicEvent.getInstance().addEventChat(new EventChat(){
            @Override
            public void sendMessage(String text) {
                    chatBody.addItemRight(text);
            }
            @Override
            public void reciveMessage(String text) {          
                String[] message = text.split("#-~");
                System.out.println(text);
                String userid = message[0];
                String msg = message[1].replace("%20","\r\n");
                if(chatTitle.getaModel() == null && userid.equals("system")){
                    chatBody.addItemLeft(msg);
                }
                else{
                    if(chatTitle.getaModel() != null && chatTitle.getaModel().getId().equals(userid)){
                        System.out.println("useridaaaaa "+userid);
                        chatBody.addItemLeft(msg);
                    }
                    if(chatTitle.getGroup() != null && chatTitle.getGroup().getIdGroup().equals(userid)){
                        chatBody.addItemLeft(msg);
                    }
                }
            }
            @Override
            public void removeAllChatBody(){
                chatBody.removeItemRight();
            }

            @Override
            public void loadMessage(String text) {
                String[] a = text.split("#-~");
                String userid = a[0];
                String message = a[1].replace("%20","\r\n");
                if(chatTitle.getaModel()!=null && chatTitle.getaModel().getId().equals(userid)){
                    chatBody.addItemLeft(message);
                }
                if(chatTitle.getaModel()!=null && Client.getInstance().User.getId().equals(userid)){
                   chatBody.addItemRight(message);
                }
                if(chatTitle.getaModel()==null && Client.getInstance().User.getId().equals(userid)){
                    chatBody.addItemRight(message);
                }
                if(chatTitle.getaModel()==null && !Client.getInstance().User.getId().equals(userid)){
                    chatBody.addItemLeft(message);
                }
            }
        });
        add(chatTitle,"wrap");
        add(chatBody,"wrap");
        add(chatBottom,"h ::30%");
    }
    public void setUser(AccountModel am){
        chatTitle.setuser(am);
        chatTitle.loadblockuser();
        chatBottom.setaModel(am);
        chatBottom.setGroup(null);
    }
    public void setSystem(){
        chatTitle.setSystem();
        chatTitle.hideblock();
        chatBottom.setVisible(false);
    }
    public void setGroup(GroupModel gr){
        chatTitle.setGroup(gr);
        chatTitle.loadexitgr();
        chatBottom.setGroup(gr);
        chatBottom.setaModel(null);
    }
    //@//SuppressWarnings("unchecked");
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 727, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 672, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
