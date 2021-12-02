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
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import net.miginfocom.swing.MigLayout;


public class Chat_Bottom extends javax.swing.JPanel {
    private AccountModel aModel;
    private GroupModel group;
    private MigLayout mig;
    private Chat_PanelMore panelMore;
    private JPanel panel = new JPanel();
    private JIMSendTextPane txt;
    private JButton cmd;
    private JButton cmdMore;
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
        panelMore.setGroup(group);
    }
    
    public Chat_Bottom() {
        initComponents();
        init();
    }
    public void removeall(){
        init();
    }
    private void init(){
        removeAll();
        mig = new MigLayout("fillx,filly","0[fill]0[]0[]2","2[fill]2[]0");
        setLayout(mig);
        JScrollPane scroll = new JScrollPane();
        scroll.setBorder(null);
        txt = new JIMSendTextPane();
        //remove keybling enter in txt
        txt.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER,0),"none");
        txt.addKeyListener(new KeyListener(){
            @Override
            public void keyPressed(KeyEvent e) {
                refresh();
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                   send(txt);
               }
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }

            @Override
            public void keyTyped(KeyEvent e) {
                
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
        cmd = new JButton();
        cmd.setBorder(null);
        cmd.setContentAreaFilled(false);
        cmd.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cmd.setIcon(new ImageIcon(getClass().getResource("/Image/send.png")));
        cmd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                send(txt);
            }
        });
        cmdMore = new JButton();
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
    public void setBlock(String type){
        removeAll();
        txt.setVisible(false);
        cmdMore.setVisible(false);
        cmd.setVisible(false);
        Label lberror = new Label();
        lberror.setForeground(Color.red);
        JButton btnerror = new JButton("Bỏ chặn");
        btnerror.setBorder(null);
        btnerror.setContentAreaFilled(false);
        btnerror.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnerror.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try { 
                    
                    if(group!=null){
                        Client.getInstance().send("blockuser#~unblockgroup^&"+group.getIdGroup()+"^&"+Client.getInstance().User.getId());
                        PublicEvent.getInstance().getEventChat().loadBlock("updateremoveblockgroup;"+group.getIdGroup());
                    }
                    if(aModel!=null){
                        Client.getInstance().send("blockuser#~unblock^&"+Client.getInstance().User.getId()+"^&"+aModel.getId());
                        PublicEvent.getInstance().getEventChat().loadBlock("updateremoveblock;"+aModel.getId());
                    }      
                } catch (IOException ex) {
                }
                btnerror.setVisible(false);
                removeAll();
                init();  
            }
        });
        if(type.equals("userblock")){
            panel.removeAll();
            lberror.setText("Bạn đã chặn");
            System.err.println("vo luôn");
            panel.add(lberror);
            panel.add(btnerror);
        }
        if(type.equals("userblocked")){
            panel.removeAll();
            lberror.setText("Bạn đã bị chặn");
            panel.add(lberror);
        }
        add(panel,"wrap");
    }
    public void send(JIMSendTextPane txt){
        try {
            String text = txt.getText().trim();
            if(!text.equals("")){
                PublicEvent.getInstance().getEventChat().sendMessage(text);
                if(aModel!=null){
                    SendMessageModel  smm = new SendMessageModel(Client.getInstance().User.getId(), aModel.getId(), text.replace("\r\n","%20#1%"));
                    Client.getInstance().send("ClientToClient#~"+convertArToString(smm));
                }
                if(group!=null){
                   SendMessageModel smm = new SendMessageModel(Client.getInstance().User.getId(), group.getIdGroup(), text.replace("\r\n","%20#1%"));
                   Client.getInstance().send("messagegroup#~"+convertArToString(smm)+"#~"+Client.getInstance().User.getFullName());
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
