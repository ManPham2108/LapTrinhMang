package Form.Body.Chat;

import Form.Body.Event.PublicEvent;
import Form.MainChat;
import Model.AccountModel;
import Model.FileModel;
import Model.GroupModel;
import Model.SendMessageModel;
import Server.Client;
import Sticker.Model_Sticker;
import Sticker.Sticker;
import com.google.gson.Gson;
import component.OptionButton;
import component.ScrollBar;
import component.WrapLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

public class Chat_PanelMore extends javax.swing.JPanel {

    private final long SIZE_FILE_1MB = 1024L * 1024L;
    private AccountModel aModel;
    private GroupModel group;
    public AccountModel getaModel() {
        return aModel;
    }

    public void setaModel(AccountModel aModel) {
        this.aModel = aModel;
    }

    public GroupModel getGroup() {
        return group;
    }

    public void setGroup(GroupModel group) {
        this.group = group;
    }
    
    public Chat_PanelMore() {
        initComponents();
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx"));
        panelHeader = new JPanel();
        panelHeader.setLayout(new BoxLayout(panelHeader, BoxLayout.LINE_AXIS));
        panelHeader.add(getButtonFile());
        panelHeader.add(getStickerStyle(Sticker.getInstance().getStyle1()));
        panelHeader.add(getStickerStyle(Sticker.getInstance().getStyle2()));
        add(panelHeader, "w 100%, h 30!, wrap");
        panelDetail = new JPanel();
        panelDetail.setLayout(new WrapLayout(WrapLayout.LEFT));
        JScrollPane ch = new JScrollPane(panelDetail);
        ch.setBorder(null);
        ch.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        ch.setVerticalScrollBar(new ScrollBar());
        add(ch, "w 100%, h 100%");
    }

    private JButton getButtonFile() {
        OptionButton cmd = new OptionButton();
        cmd.setIcon(new ImageIcon(getClass().getResource("/Image/link.png")));
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                JFileChooser ch = new JFileChooser();
                ch.setMultiSelectionEnabled(false);
                int option;
                boolean valid = false;
                while (!valid) {
                    option = ch.showOpenDialog(MainChat.getFrames()[0]);
                    if (option == JFileChooser.APPROVE_OPTION) {
                        File fileSecleted = ch.getSelectedFile();
                        if (fileSecleted.length() < SIZE_FILE_1MB) {
                            sendFile(fileSecleted);
                            valid = true;
                            break;
                        }
                    }
                    if (option == JFileChooser.CANCEL_OPTION) {
                        break;
                    }
                }
            }
        });
        return cmd;
    }
    
    public void sendFile(File fileSelected) {
        try {
            String message = "";
            if (fileSelected != null) {
                FileModel fileModel = new FileModel();
                fileModel.setData(readFile(fileSelected));
                fileModel.setExtFile(getExtensions(fileSelected.getAbsolutePath()));
                fileModel.setFileSize(fileSelected.length());
                fileModel.setNameFile(fileSelected.getName());
                message = "***file###" + convertArToString(fileModel);
                if (aModel != null) {
                    PublicEvent.getInstance().getEventChat().sendMessage(message);
                    SendMessageModel smm = new SendMessageModel(Client.getInstance().User.getId(), aModel.getId(), message);
                    Client.getInstance().send("ClientToClient#~" + convertArToString(smm));
                }
                if (group != null) {
                    PublicEvent.getInstance().getEventChat().sendMessage(message);
                    SendMessageModel smm = new SendMessageModel(Client.getInstance().User.getId(), group.getIdGroup(), message);
                    Client.getInstance().send("messagegroup#~" + convertArToString(smm) + "#~" + Client.getInstance().User.getFullName());
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(Chat_PanelMore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized byte[] readFile(File file) throws IOException {
        RandomAccessFile accFile = new RandomAccessFile(file, "r");
        long filepointer = accFile.getFilePointer();
        long filelength = file.length();
        if (filepointer != filelength) {
            byte[] data = new byte[(int) filelength];
            accFile.read(data);
            accFile.close();
            return data;
        } else {
            return null;
        }
    }
    
    private String getExtensions(String filePath) {
        return filePath.substring(filePath.lastIndexOf("."), filePath.length());
    }

    private JButton getStickerStyle(List<Model_Sticker> listSticker) {
        OptionButton cmd = new OptionButton();
        cmd.setIcon(Sticker.getInstance().getSticker(listSticker.get(0).getId()).toSize(55, 55).getIcon());
        cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                clearSelected();
                cmd.setSelected(true);
                panelDetail.removeAll();
                for (Model_Sticker d : listSticker) {
                    panelDetail.add(getButtonSticker(d));
                }
                panelDetail.repaint();
                panelDetail.revalidate();
            }
        });
        return cmd;
    }

    private JButton getButtonSticker(Model_Sticker sticker) {
        JButton c = new JButton(sticker.getIcon());
        c.setName(sticker.getId() + "");
        c.setBorder(new EmptyBorder(3, 3, 3, 3));
        c.setCursor(new Cursor(Cursor.HAND_CURSOR));
        c.setContentAreaFilled(false);
        c.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendSticker("***sticker###"+c.getName());
            }
        });
        return c;
    }

    public void sendSticker(String text) {
        try {
            if (aModel != null) {
                PublicEvent.getInstance().getEventChat().sendMessage(text);
                SendMessageModel smm = new SendMessageModel(Client.getInstance().User.getId(), aModel.getId(), text.replace("\r\n", "%20#1%"));
                Client.getInstance().send("ClientToClient#~" + convertArToString(smm));
            }
            if(group != null){
                PublicEvent.getInstance().getEventChat().sendMessage(text);
                 SendMessageModel smm = new SendMessageModel(Client.getInstance().User.getId(), group.getIdGroup(), text.replace("\r\n","%20#1%"));
                 Client.getInstance().send("messagegroup#~"+convertArToString(smm)+"#~"+Client.getInstance().User.getFullName());
            }
        } catch (Exception e) {
            
        } 
    }

    public String convertArToString(Object object){
        Gson gson = new Gson();
        return gson.toJson(object);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 598, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 147, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void clearSelected() {
        for (Component c : panelHeader.getComponents()) {
            if (c instanceof OptionButton) {
                ((OptionButton) c).setSelected(false);
            }
        }
    }
    private JPanel panelHeader;
    private JPanel panelDetail;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
