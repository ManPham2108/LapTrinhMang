package Form.Body.Chat;

import Form.Body.Event.EventChat;
import Form.Body.Event.PublicEvent;
import Model.AccountModel;
import Model.GroupModel;
import Server.Client;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;

public class Chat extends javax.swing.JPanel {
    private Chat_Title chatTitle;
    private Chat_Body chatBody;
    private Chat_Bottom chatBottom;
    private ArrayList<String> listblock = new ArrayList<>();
    private ArrayList<String> listblocked = new ArrayList<>();
    private ArrayList<String> listblockgroup = new ArrayList<>();
    private HashSet<String> listuserseen = new HashSet<>();
    private Gson g = new Gson();
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
                chatBody.removeseen();
                chatBody.setCheck(0);
                chatBody.addItemRight(text);
                if(chatTitle.getaModel() != null){
                    listuserseen.remove(chatTitle.getaModel().getId());
                }  
            }

            @Override
            public void seenMessage(String userid) {
                if(chatTitle.getaModel() != null && chatTitle.getaModel().getId().equals(userid) && chatBody.getCheck()==0){
                    chatBody.removeseen();
                    chatBody.addseen();
                    listuserseen.add(userid);
                }
                if(chatTitle.getaModel() != null && !chatTitle.getaModel().getId().equals(userid)){
                    listuserseen.add(userid);
                }
            }
            @Override
            public void reciveMessage(String text) {          
                String[] message = text.split("#-~");
                String userid = message[0];
                String msg = message[1].replace("%20#1%","\r\n");
                String username = null;
                if(chatTitle.getaModel()== null || !chatTitle.getaModel().getId().equals(userid) || userid.equals("system")){
                    PublicEvent.getInstance().getEventMenuLeft().NotifiMsg(userid,"individual",true);
                }
                if(chatTitle.getGroup() == null || !chatTitle.getGroup().getIdGroup().equals(userid) || userid.equals("system")){
                    PublicEvent.getInstance().getEventMenuLeft().NotifiMsg(userid,"group",true);
                }
                if(message.length==3){
                    username = message[2];
                }
                if(chatTitle.getaModel() == null && userid.equals("system")){
                    JOptionPane.showMessageDialog(null, msg,"Thông báo từ hệ thống",JOptionPane.INFORMATION_MESSAGE);
                    chatBody.addItemLeft(msg,userid);
                }
                else{
                    if(chatTitle.getaModel() != null && chatTitle.getaModel().getId().equals(userid)){
                        chatBody.removeseen();
                        chatBody.addItemLeft(msg,chatTitle.getaModel().getFullName());
                        chatBody.check();
                        try {
                            Client.getInstance().send("seenmsg#~"+userid+"#~"+Client.getInstance().User.getId());
                        } catch (IOException ex) {
                            Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if(chatTitle.getGroup() != null && chatTitle.getGroup().getIdGroup().equals(userid) && !listblockgroup.contains(userid)){
                        chatBody.addItemLeft(msg,username);
                    }
                }
            }
            @Override
            public void removeAllChatBody(){
                chatBody.removeall();
                chatBottom.removeall();
            }
            @Override
            public void loadMessage(String text) {
                String[] a = text.split("#-~");
                String userid = a[0];
                String message = a[1].replace("%20#1%","\r\n");
                String username = null;
                if(a.length==3){
                    username = a[2];
                }
                if(chatTitle.getaModel()!=null && chatTitle.getaModel().getId().equals(userid)){
                    chatBody.addItemLeft(message,chatTitle.getaModel().getFullName());
                    chatBody.check();
                    chatBody.removeseen();
                    PublicEvent.getInstance().getEventMenuLeft().NotifiMsg(userid,"individual",false);
                }
                if(chatTitle.getaModel()!=null && Client.getInstance().User.getId().equals(userid)){
                   chatBody.addItemRight(message);
                   chatBody.setCheck(0);
                   for(String s : listuserseen){
                       if(s.equals(chatTitle.getaModel().getId())){
                           chatBody.addseen();
                           break;
                       }
                   }
                }
                if(chatTitle.getaModel()==null && Client.getInstance().User.getId().equals(userid)){
                    chatBody.addItemRight(message);
                }
                if(chatTitle.getaModel()==null && !Client.getInstance().User.getId().equals(userid)){
                    chatBody.addItemLeft(message,username);
                    PublicEvent.getInstance().getEventMenuLeft().NotifiMsg(chatTitle.getGroup().getIdGroup(),"group",false);
                }
            }

            @Override
            public void loadListBlock(String text) {
                StringTokenizer tmp = new StringTokenizer(text,"^&");
                String type = tmp.nextToken();
                String list = tmp.nextToken();
                if(type.equals("userblock")){
                    listblock = g.fromJson(list,new TypeToken<ArrayList<String>>() {}.getType());
                }
                if(type.equals("userblocked")){
                    listblocked = g.fromJson(list,new TypeToken<ArrayList<String>>() {}.getType());
                }
                if(type.equals("blockgroup")){
                    listblockgroup = g.fromJson(list,new TypeToken<ArrayList<String>>() {}.getType());
                }
            }
            public void loadBlock(String text) {
                String[] ab = text.split(";");
                String type = ab[0];
                switch(type){
                    case "load":
                        chatBottom.setBlock("userblock");
                        chatTitle.hideblock();
                        listblock.add(ab[1]);
                        break;
                    case "updateremoveblock":
                        listblock.remove(ab[1]);
                        break;
                    case "loadblockgroup":
                        chatBottom.setBlock("userblock");
                        chatTitle.hideblock();
                        listblockgroup.add(ab[1]);
                        break;
                    case "updateremoveblockgroup":
                        listblockgroup.remove(ab[1]);
                        break;
                } 
            }

            @Override
            public void updateUserBlock(String text) {
                StringTokenizer st = new StringTokenizer(text,"^&");
                String type = st.nextToken();
                String id = st.nextToken();
                switch(type){
                    case "updateuserunblock":
                        if(chatTitle.getGroup()==null && chatTitle.getaModel() != null &&chatTitle.getaModel().getId().equals(id)){
                            chatBottom.setVisible(false);
                            chatBottom.removeall();
                            chatBottom.setVisible(true);
                        }
                        listblocked.remove(id);
                        break;
                    case "updateuserblock":
                        if(chatTitle.getGroup()==null && chatTitle.getaModel() != null && chatTitle.getaModel().getId().equals(id)){
                            chatBottom.setBlock("userblocked");
                            chatTitle.hideblock();
                        }
                        listblocked.add(id);
                        break;    
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
        chatBottom.setVisible(true);
        chatBottom.setaModel(am);
        chatBottom.setGroup(null);
        if(listblock.contains(am.getId())){
            chatBottom.setBlock("userblock");
            chatTitle.hideblock();
        }
        if(listblocked.contains(am.getId())){
            chatBottom.setBlock("userblocked");
            chatTitle.hideblock();
        }
    }
    public void setSystem(){
        chatTitle.setSystem();
        chatTitle.hideblock();
        chatBottom.setVisible(false);
    }
    public void setGroup(GroupModel gr){
        chatTitle.setGroup(gr);
        chatTitle.loadexitgr();
        chatBottom.setVisible(true);
        chatBottom.setGroup(gr);
        chatBottom.setaModel(null);
        if(listblockgroup.contains(gr.getIdGroup())){
            chatBottom.setBlock("userblock");
            chatTitle.hideblock();
        }
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
