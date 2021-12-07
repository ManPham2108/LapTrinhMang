package Form.Login;

import Form.Body.Event.PublicEvent;
import Server.Client;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;


public class P_Login extends javax.swing.JPanel {

    public P_Login() {
        initComponents();
    }
    public void loginfaile(){
        txtError.setText("Tên đăng nhập hoặc mật khẩu không đúng");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbTitle = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtPass = new javax.swing.JPasswordField();
        cmdLogin = new javax.swing.JButton();
        cmdRegister = new javax.swing.JButton();
        txtError = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(369, 496));

        lbTitle.setFont(new java.awt.Font("sansserif", 0, 30)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(87, 87, 87));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("ĐĂNG NHẬP");

        jLabel1.setText("TÀI KHOẢN");

        jLabel2.setText("MẬT KHẨU");

        cmdLogin.setText("ĐĂNG NHẬP");
        cmdLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdLoginActionPerformed(evt);
            }
        });
        cmdLogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cmdLoginKeyPressed(evt);
            }
        });

        cmdRegister.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        cmdRegister.setForeground(new java.awt.Color(15, 128, 206));
        cmdRegister.setText("ĐĂNG KÝ TÀI KHOẢN");
        cmdRegister.setContentAreaFilled(false);
        cmdRegister.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdRegisterActionPerformed(evt);
            }
        });

        txtError.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtError.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdRegister, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                    .addComponent(cmdLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtPass)
                    .addComponent(txtUser, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 325, Short.MAX_VALUE))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lbTitle)
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmdLogin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdRegister)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtError, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 222, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdRegisterActionPerformed
        PublicEvent.getInstance().getEventLogin().goRegister();
    }//GEN-LAST:event_cmdRegisterActionPerformed

    private void cmdLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdLoginActionPerformed
        int fail = 0;
        try {
            String user = txtUser.getText();
            String pass = txtPass.getText();
            if(isEmailAddress(user)==false){
                txtError.setText("Tên đăng nhập hoặc mật khẩu không đúng");
                fail++;
            }
            else{
                txtError.setText("");
            }
            if(!isPassword(pass).equals("Mật khẩu hợp lệ")){
                JOptionPane.showMessageDialog(null,isPassword(pass));
                fail++;
            }
            if(fail==0){
                Client.getInstance().send("login#~"+user+"#~"+pass);
            }
        } catch (Exception e) {
        }
    }//GEN-LAST:event_cmdLoginActionPerformed

    private void cmdLoginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cmdLoginKeyPressed
        int fail = 0;
        try {
                String user = txtUser.getText();
                String pass = txtPass.getText();
                if(isEmailAddress(user)==false){
                    txtError.setText("Tên đăng nhập hoặc mật khẩu không đúng");
                    fail++;
                }
                else{
                    txtError.setText("");
                }
                if(!isPassword(pass).equals("Mật khẩu hợp lệ")){
                    txtError.setText("Tên đăng nhập hoặc mật khẩu không đúng");
                    fail++;
                }
                if(fail==0){
                    Client.getInstance().send("login#~"+user+"<,"+pass);
                }
            
        } catch (Exception e) {
        }
        
    }//GEN-LAST:event_cmdLoginKeyPressed
    private boolean isEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdLogin;
    private javax.swing.JButton cmdRegister;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JLabel txtError;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
    // End of variables declaration//GEN-END:variables
}
