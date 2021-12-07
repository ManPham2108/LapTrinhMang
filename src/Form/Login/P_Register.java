package Form.Login;

import Form.Body.Event.PublicEvent;
import Model.AccountModel;
import Server.Client;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class P_Register extends javax.swing.JPanel {
    private Verified ver; 
    public P_Register() {
        initComponents();
    }
    public void checkUserName(String status) {
        if(status.equals("true")){
            ver.setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(null, "Tài khoản đã có người đăng ký");
        }
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lbTitle = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        cmdRegister = new javax.swing.JButton();
        cmdBackLogin = new javax.swing.JButton();
        txtConfirmPass = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtFullName = new javax.swing.JTextField();
        btnMale = new javax.swing.JRadioButton();
        btnFemale = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jDateBirth = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        errorFullName = new javax.swing.JLabel();
        errorUserName = new javax.swing.JLabel();
        errorPassword = new javax.swing.JLabel();
        errorConfirmPassword = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        lbTitle.setFont(new java.awt.Font("sansserif", 0, 30)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(87, 87, 87));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("ĐĂNG KÍ TÀI KHOẢN");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("TÀI KHOẢN");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("MẬT KHẨU");

        cmdRegister.setText("ĐĂNG KÍ");
        cmdRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRegisterActionPerformed(evt);
            }
        });

        cmdBackLogin.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        cmdBackLogin.setForeground(new java.awt.Color(15, 128, 206));
        cmdBackLogin.setText("QUAY LẠI ĐĂNG NHẬP");
        cmdBackLogin.setContentAreaFilled(false);
        cmdBackLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdBackLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBackLoginActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("XÁC NHẬN MẬT KHẨU");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("NGÀY SINH");

        buttonGroup1.add(btnMale);
        btnMale.setSelected(true);
        btnMale.setText("Nam");

        buttonGroup1.add(btnFemale);
        btnFemale.setText("Nữ");
        btnFemale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFemaleActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("HỌ VÀ TÊN");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("GIỚI TÍNH");

        jDateBirth.setToolTipText("");
        jDateBirth.setDateFormatString("yyyy/MM/dd");

        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel7.setText("Sai ngày sinh");

        errorFullName.setForeground(new java.awt.Color(255, 0, 0));

        errorUserName.setForeground(new java.awt.Color(255, 0, 0));

        errorPassword.setForeground(new java.awt.Color(255, 0, 0));

        errorConfirmPassword.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdBackLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdRegister, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPass, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtUsername)
                    .addComponent(txtConfirmPass, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtFullName)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnMale, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnFemale)
                                .addGap(0, 52, Short.MAX_VALUE)))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateBirth, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(errorConfirmPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(errorFullName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(errorUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(errorPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(errorFullName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFullName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel4))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnMale)
                            .addComponent(btnFemale)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(1, 1, 1)
                .addComponent(jLabel7)
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(errorUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(errorPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(errorConfirmPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtConfirmPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmdRegister)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdBackLogin)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdBackLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBackLoginActionPerformed
        PublicEvent.getInstance().getEventLogin().goLogin();
    }//GEN-LAST:event_cmdBackLoginActionPerformed

    private void cmdRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRegisterActionPerformed
        int fail = 0;
        if(isFullname(txtFullName.getText())==false){
            errorFullName.setText("Họ và tên không được chứ số và kí tự đặt biệt");
            fail++;
        }
        else{
            errorFullName.setText("");
        }
        if(isEmailAddress(txtUsername.getText())==false){
            errorUserName.setText("Email không  hợp lệ");
            fail++;
        }
        else{
            errorUserName.setText("");
        }
        if(!isPassword(txtPass.getText()).equals("Mật khẩu hợp lệ")){
            JOptionPane.showMessageDialog(null,isPassword(txtPass.getText()));
            fail++;
        }
        if(fail == 0){
            try {
                String gender;
                if(btnFemale.isSelected()){
                    gender = "Nữ";
                }
                else{
                    gender = "Nam";
                }
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                String DateOfBirth=sdf.format(jDateBirth.getDate());
                ver = new Verified();
                AccountModel userRegister = new AccountModel(null, txtUsername.getText(), txtPass.getText(), txtFullName.getText(), gender,Date.valueOf(DateOfBirth));
                ver.setUserregist(userRegister);
                try {
                    Client.getInstance().send("OTP#~"+userRegister.getUsername());
                } catch (IOException ex) {
                    Logger.getLogger(P_Register.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(P_Register.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        else{
            JOptionPane.showMessageDialog(null,"Đăng ký thất bại");
        }
        
    }//GEN-LAST:event_cmdRegisterActionPerformed
    private boolean isEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }
    private boolean isFullname(String str) {
        String expression = "^[a-zA-Z\\s]+"; 
        return str.matches(expression);        
    }
    private String isPassword(String password){
        String result = "";
        if(password.length() < 8){
            result += "Mật khẩu phải có ít nhất 8 kí tự\n";
        }
        Pattern p = Pattern.compile("([0-9])");
        Matcher m = p.matcher(password);
        if(m.find() == false){
            result += "Mật khẩu phải có ít nhất 1 số\n";
        }
        Pattern p2 = Pattern.compile("([A-Z])");
        Matcher m2 = p2.matcher(password);
        if(m2.find() == false){
            result += "Mật khẩu phải có ít nhất 1 chữ hoa\n";
        }
        Pattern p3 = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m3 = p3.matcher(password);
        if(m3.find() == false){
            result += "Mật khẩu phải có ít nhất 1 kí tự đặt biệt\n";
        }
        if(password.contains("'")){
            result += "Mật khẩu không được chứa '\n";
        }
        if(password.contains("--")){
            result += "Mật khẩu không được chứa --\n";
        }
        if(password.contains("#")){
            result += "Mật khẩu không được chứa #";
        }
        if(password.contains("~")){
            result += "Mật khẩu không được chứa #";
        }
        if(password.contains(" ")){
            result += "Mật khẩu không được chứa khoảng chắn";
        }
        if(password.contains("=")){
            result += "Mật khẩu không được chứa dấu bằng";
        }
        if(result.length() != 0){
            return result;
        }
        else{
            return "Mật khẩu hợp lệ";    
        }
    }
    private void btnFemaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFemaleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnFemaleActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton btnFemale;
    private javax.swing.JRadioButton btnMale;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cmdBackLogin;
    private javax.swing.JButton cmdRegister;
    private javax.swing.JLabel errorConfirmPassword;
    private javax.swing.JLabel errorFullName;
    private javax.swing.JLabel errorPassword;
    private javax.swing.JLabel errorUserName;
    private com.toedter.calendar.JDateChooser jDateBirth;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JPasswordField txtConfirmPass;
    private javax.swing.JTextField txtFullName;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
