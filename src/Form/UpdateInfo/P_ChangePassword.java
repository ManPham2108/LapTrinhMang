package Form.UpdateInfo;

import Form.Body.Event.PublicEvent;
import Server.Client;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class P_ChangePassword extends javax.swing.JPanel {
    private String pass;
    private String passconf;
    public P_ChangePassword() {
        initComponents();
        lbErrConfirmPass.setVisible(false);
        lbErrNewPass.setVisible(false);
        lbErrOldPass.setVisible(false);
    }
    public void updatePass(String status){
        if(status.equals("true")){
            if(pass.equals(passconf)){
                try {
                    Client.getInstance().send("updateuser#~updatepass#~"+Client.getInstance().User.getId()+"#~"+pass);
                    PublicEvent.getInstance().getEventMain().logout();
                    Client.getInstance().send("logout#~"+Client.getInstance().User.getId());
                } catch (IOException ex) {
                    Logger.getLogger(P_ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                lbErrConfirmPass.setVisible(true);
            }
        }
        else{
            lbErrOldPass.setVisible(true);
        }    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lbTitle = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNewPass = new javax.swing.JPasswordField();
        cmdChangePassword = new javax.swing.JButton();
        cmdBackInfo = new javax.swing.JButton();
        txtConfirmPass = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtOldPass = new javax.swing.JPasswordField();
        lbErrOldPass = new javax.swing.JLabel();
        lbErrConfirmPass = new javax.swing.JLabel();
        lbErrNewPass = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        lbTitle.setFont(new java.awt.Font("sansserif", 0, 22)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(87, 87, 87));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("?????I M???T KH???U");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("M???T KH???U M???I");

        cmdChangePassword.setText("C???P NH???T");
        cmdChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdChangePasswordActionPerformed(evt);
            }
        });

        cmdBackInfo.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        cmdBackInfo.setForeground(new java.awt.Color(15, 128, 206));
        cmdBackInfo.setText("QUAY L???I TH??NG TIN C?? NH??N");
        cmdBackInfo.setContentAreaFilled(false);
        cmdBackInfo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdBackInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBackInfoActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("X??C NH???N M???T KH???U");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("M???T KH???U C??");

        lbErrOldPass.setForeground(new java.awt.Color(255, 0, 51));
        lbErrOldPass.setText("M???t kh???u kh??ng ????ng");

        lbErrConfirmPass.setForeground(new java.awt.Color(255, 0, 51));
        lbErrConfirmPass.setText("Kh??ng kh???p v???i m???t kh???u m???i");

        lbErrNewPass.setForeground(new java.awt.Color(255, 0, 51));
        lbErrNewPass.setText("Tr??ng v???i m???t kh???u c??");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdBackInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdChangePassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNewPass, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtConfirmPass, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtOldPass, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbErrOldPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbErrConfirmPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbErrNewPass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtOldPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(lbErrOldPass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNewPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(lbErrNewPass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addComponent(lbErrConfirmPass)
                .addGap(30, 30, 30)
                .addComponent(cmdChangePassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdBackInfo)
                .addGap(9, 9, 9))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdBackInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBackInfoActionPerformed
        PublicEvent.getInstance().getEventUpdateInfo().goUpdateInfo();
    }//GEN-LAST:event_cmdBackInfoActionPerformed

    private void cmdChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdChangePasswordActionPerformed
        pass = txtNewPass.getText();
        passconf = txtConfirmPass.getText();
        String passold =txtOldPass.getText();
        int fail = 0;
        if(!isPassword(passold).equals("M???t kh???u h???p l???")){
            JOptionPane.showMessageDialog(null,isPassword(pass));
            fail++;
        }
        if(!isPassword(pass).equals("M???t kh???u h???p l???")){
            JOptionPane.showMessageDialog(null,isPassword(pass));
            fail++;
        }
        if(!isPassword(passconf).equals("M???t kh???u h???p l???")){
            JOptionPane.showMessageDialog(null,isPassword(pass));
            fail++;
        }
        if(fail==0){
            if(passold.equals(pass)){
                lbErrNewPass.setVisible(true);
            }
            else{
                if(pass.equals(passconf)){
                    try {
                        Client.getInstance().send("updateuser#~checkpassold#~"+Client.getInstance().User.getId()+"#~"+passold);
                    } catch (IOException ex) {
                        Logger.getLogger(P_ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else{
                    lbErrConfirmPass.setVisible(true);
                }
            }
        }
    }//GEN-LAST:event_cmdChangePasswordActionPerformed
    private String isPassword(String password){
        String result = "";
        if(password.length() < 8){
            result += "M???t kh???u ph???i c?? ??t nh???t 8 k?? t???\n";
        }
        Pattern p = Pattern.compile("([0-9])");
        Matcher m = p.matcher(password);
        if(m.find() == false){
            result += "M???t kh???u ph???i c?? ??t nh???t 1 s???\n";
        }
        Pattern p2 = Pattern.compile("([A-Z])");
        Matcher m2 = p2.matcher(password);
        if(m2.find() == false){
            result += "M???t kh???u ph???i c?? ??t nh???t 1 ch??? hoa\n";
        }
        Pattern p3 = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m3 = p3.matcher(password);
        if(m3.find() == false){
            result += "M???t kh???u ph???i c?? ??t nh???t 1 k?? t??? ?????t bi???t\n";
        }
        if(password.contains("'")){
            result += "M???t kh???u kh??ng ???????c ch???a '\n";
        }
        if(password.contains("--")){
            result += "M???t kh???u kh??ng ???????c ch???a --\n";
        }
        if(password.contains("#")){
            result += "M???t kh???u kh??ng ???????c ch???a #";
        }
        if(password.contains("~")){
            result += "M???t kh???u kh??ng ???????c ch???a ~";
        }
        if(password.contains(" ")){
            result += "M???t kh???u kh??ng ???????c ch???a kho???ng ch???n";
        }
        if(password.contains("=")){
            result += "M???t kh???u kh??ng ???????c ch???a d???u b???ng";
        }
        if(result.length() != 0){
            return result;
        }
        else{
            return "M???t kh???u h???p l???";    
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cmdBackInfo;
    private javax.swing.JButton cmdChangePassword;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lbErrConfirmPass;
    private javax.swing.JLabel lbErrNewPass;
    private javax.swing.JLabel lbErrOldPass;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JPasswordField txtConfirmPass;
    private javax.swing.JPasswordField txtNewPass;
    private javax.swing.JPasswordField txtOldPass;
    // End of variables declaration//GEN-END:variables
}
