package Form.Body.Chat;

import Form.Body.Event.PublicEvent;
import Model.AccountModel;
import Model.GroupModel;
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
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;


public class Chat_Bottom extends javax.swing.JPanel {
    private AccountModel aModel;
    private GroupModel group;
    private MigLayout mig;
    private Chat_PanelMore panelMore;
    public AccountModel getaModel() {
        return aModel;
    }
    public void setaModel(AccountModel aModel) {
        this.aModel = aModel;
        panelMore.setaModel(aModel);
    }

    public GroupModel getGroup() {
        return group;
    }

    public void setGroup(GroupModel group) {
        this.group = group;
    }
    
    public Chat_Bottom() {
        initComponents();
        init();
    }
    private void init(){
        mig = new MigLayout("fillx,filly","0[fill]0[]0[]2","2[fill]2[]0");
        setLayout(mig);
        JScrollPane scroll = new JScrollPane();
        scroll.setBorder(null);
        JIMSendTextPane txt = new JIMSendTextPane();
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                refresh();
                if (ke.getKeyChar() == 10 && ke.isControlDown()) {
                    //  user press controll + enter
                    send(txt);
                }
            }
        });
        txt.setHintText("Nhập tin nhắn");
        scroll.setViewportView(txt);
        ScrollBar sb = new ScrollBar();
        sb.setPreferredSize(new Dimension(2,10));
        scroll.setVerticalScrollBar(sb);
        add(sb);
        add(scroll,"w 100%");
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("filly","0[]3[]0","0[bottom]0"));
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
        JButton cmdMore = new JButton();
        cmdMore.setBorder(null);
        cmdMore.setContentAreaFilled(false);
        cmdMore.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmdMore.setIcon(new ImageIcon(getClass().getResource("/Image/more_disable.png")));
        cmdMore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if (panelMore.isVisible()) {
                    cmdMore.setIcon(new ImageIcon(getClass().getResource("/Image/more_disable.png")));
                    panelMore.setVisible(false);
                    mig.setComponentConstraints(panelMore, "dock south,h 0!");
                    revalidate();
                } else {
                    cmdMore.setIcon(new ImageIcon(getClass().getResource("/Image/more.png")));
                    panelMore.setVisible(true);
                    mig.setComponentConstraints(panelMore, "dock south,h 170!");
                    revalidate();
                }
            }
        });
        panel.add(cmdMore);
        panel.add(cmd);
        add(panel, "wrap");
        panelMore = new Chat_PanelMore();
        panelMore.setVisible(false);
        add(panelMore, "dock south,h 0!");
    }
    public void send(JIMSendTextPane txt){
        try {
            String text = txt.getText().trim();
            if(!text.equals("")){
                PublicEvent.getInstance().getEventChat().sendMessage(text);
                if(aModel!=null){
                    SendMessageModel  smm = new SendMessageModel(Client.getInstance().User.getId(), aModel.getId(), text.replace("\r\n","%20"));
                    Client.getInstance().send("ClientToClient#~"+convertArToString(smm));
                }
                if(group!=null){
                   SendMessageModel smm = new SendMessageModel(Client.getInstance().User.getId(), group.getIdGroup(), text.replace("\r\n","%20"));
                   Client.getInstance().send("messagegroup#~"+convertArToString(smm));
                }
                txt.setText("");
                txt.grabFocus();
                refresh();
            }
            else{
                txt.grabFocus();
            }
        }catch(IOException e){
            System.out.println(e);
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
