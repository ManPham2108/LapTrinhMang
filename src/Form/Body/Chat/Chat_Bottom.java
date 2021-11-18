package Form.Body.Chat;

import Form.Body.Event.EventMain;
import Form.Body.Event.PublicEvent;
import Form.Body.Item_People;
import Model.AccountModel;
import Model.SendMessageModel;
import Server.Client;
import com.google.gson.Gson;
import component.JIMSendTextPane;
import component.ScrollBar;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.plaf.basic.BasicComboBoxUI;
import net.miginfocom.swing.MigLayout;


public class Chat_Bottom extends javax.swing.JPanel {

    public AccountModel aModel;

    public AccountModel getaModel() {
        return aModel;
    }
    public void setaModel(AccountModel aModel) {
        this.aModel = aModel;
    }
    public Chat_Bottom() {
        initComponents();
        init();
        //getaModel().getId();
    }
    private void init(){
        setLayout(new MigLayout("fillx,filly","0[fill]0[]0[]2","2[fill]2"));
        JScrollPane scroll = new JScrollPane();
        scroll.setBorder(null);
        JIMSendTextPane txt = new JIMSendTextPane();
//        txt.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyTyped(KeyEvent ke) {
//                refresh();
//                if (ke.getKeyChar() == 10 && ke.isControlDown()) {
//                    //  user press controll + enter
//                    send(txt);
//                }
//            }
//        });
        txt.setHintText("nhap tin nhan");
        scroll.setViewportView(txt);
        ScrollBar sb = new ScrollBar();
        sb.setPreferredSize(new Dimension(2,10));
        scroll.setVerticalScrollBar(sb);
        add(sb);
        add(scroll,"w 100%");
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("filly","0[]0","0[bottom]0"));
        panel.setPreferredSize(new Dimension(30,28));
        panel.setBackground(Color.WHITE);
        JButton cmd = new JButton();
        cmd.setBorder(null);
        cmd.setContentAreaFilled(false);
        cmd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmd.setIcon(new ImageIcon(getClass().getResource("/Image/send.png")));
        cmd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                send(txt);
            }          
        });
        panel.add(cmd);
        add(panel);
    }
    public void send(JIMSendTextPane txt){
        String text = txt.getText().trim();
        if(!text.equals("")){
            PublicEvent.getInstance().getEventChat().sendMessage(text);
            SendMessageModel smm = new SendMessageModel(Client.getInstance().Amodel.getId(), aModel.getId(), text);
            try {
                Client.getInstance().send("ClientToClient#"+convertArToString(smm));
            } catch (IOException ex) {
                Logger.getLogger(Chat_Bottom.class.getName()).log(Level.SEVERE, null, ex);
            }
            txt.setText("");
            txt.grabFocus();
            refresh();
        }
        else{
            System.out.println("konhan");
            txt.grabFocus();
        }
    }
    public String convertArToString(Object object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }
    private void refresh(){
        revalidate();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(242, 242, 242));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
