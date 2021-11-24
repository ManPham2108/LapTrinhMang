/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.Body.Chat;

import Form.Body.Event.EventChat;
import Form.Body.Event.PublicEvent;
import Model.AccountModel;
import Model.SendMessageModel;
import Server.Client;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
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
                StringTokenizer st = new StringTokenizer(text,"^&");
                System.out.println(text);
                String userid = st.nextToken();
                if(chatTitle.getaModel() == null && userid.equals("system")){
                    chatBody.addItemLeft(st.nextToken());
                }
                else{
                    if(chatTitle.getaModel().getId().equals(userid)){
                        System.out.println("useridaaaaa "+userid);
                        chatBody.addItemLeft(st.nextToken());
                    } 
                }
            }

            @Override
            public void removeAllChatBody(){
                chatBody.removeItemRight();
            }

            @Override
            public void loadMessage(String text) {
                StringTokenizer st = new StringTokenizer(text,"^&");
                String userid = st.nextToken();
                String message = st.nextToken();
                if(chatTitle.getaModel().getId().equals(userid)){
                    chatBody.addItemLeft(message);
                }
                if(Client.getInstance().User.getId().equals(userid)){
                   chatBody.addItemRight(message);
                }
            }
        });
        add(chatTitle,"wrap");
        add(chatBody,"wrap");
        add(chatBottom,"h ::30%");
    }
    public void setUser(AccountModel am){
        chatTitle.setuser(am);
        chatBottom.setaModel(am);     
    }
    public void setSystem(){
        chatTitle.setSystem();
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
