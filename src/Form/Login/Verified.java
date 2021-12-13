package Form.Login;

import Form.Body.Event.EventAuthenOtp;
import Form.Body.Event.PublicEvent;
import Model.AccountModel;
import Server.Client;
import com.google.gson.Gson;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Verified extends javax.swing.JFrame {
    private AccountModel userregist;
    private int minute = 10;
    private int second = 0;
    private Timer timer;

    public AccountModel getUserregist() {
        return userregist;
    }

    public void setUserregist(AccountModel userregist) {
        this.userregist = userregist;
    }
    
    public Verified(){
        initComponents();
        setLocationRelativeTo(null);
        init();
        timer = new Timer(1000, new ActionListener() {// 1 mili giây sẽ gọi hàm này 1 lần 1000mili = 1s
            @Override
            public void actionPerformed(ActionEvent e) {
                if(second == -1){
                    minute--;
                    second = 59;
                }
                timeOut.setText(minute+":"+second--);
                if(second == 0 && minute == 0){
                    JOptionPane.showMessageDialog(rootPane, "Hết hạn mã OTP");
                    try {
                        Client.getInstance().send("authenotp#~timeout#~");
                    } catch (IOException ex) {
                        Logger.getLogger(Verified.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    dispose();
                }
            }
        });
        timer.start();
    }
    private void init(){
        lberror.setVisible(false);
        PublicEvent.getInstance().addEventAuthenOtp(new EventAuthenOtp() {
            @Override
            public void authenSucess() {
                Gson gson = new Gson();
                String user = gson.toJson(getUserregist());
                try {
                    PublicEvent.getInstance().getEventLogin().goLogin();
                    Client.getInstance().send("register#~"+user);
                    timer.stop();
                } catch (IOException ex) {
                    Logger.getLogger(P_Register.class.getName()).log(Level.SEVERE, null, ex);
                }
                dispose();
            }
            @Override
            public void authenFaile() {
                lberror.setVisible(true);
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnCloseVerified = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtOTP = new javax.swing.JTextField();
        cmdVerifiedOTP = new javax.swing.JButton();
        lbVerified = new javax.swing.JLabel();
        lberror = new javax.swing.JLabel();
        timeOut = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(153, 0, 153));
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnCloseVerified.setBackground(new java.awt.Color(255, 255, 255));
        btnCloseVerified.setForeground(new java.awt.Color(255, 0, 0));
        btnCloseVerified.setText("X");
        btnCloseVerified.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCloseVerifiedActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtOTP.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        cmdVerifiedOTP.setText("XÁC NHẬN");
        cmdVerifiedOTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdVerifiedOTPActionPerformed(evt);
            }
        });

        lbVerified.setFont(new java.awt.Font("sansserif", 0, 22)); // NOI18N
        lbVerified.setForeground(new java.awt.Color(87, 87, 87));
        lbVerified.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbVerified.setText("MÃ XÁC NHẬN");

        lberror.setForeground(new java.awt.Color(255, 0, 51));
        lberror.setText("MÃ XÁC NHẬN CHƯA ĐÚNG");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbVerified, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                    .addComponent(cmdVerifiedOTP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtOTP)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(lberror)))
                .addContainerGap(90, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(lbVerified)
                .addGap(38, 38, 38)
                .addComponent(txtOTP, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lberror)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmdVerifiedOTP, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4))
        );

        timeOut.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        timeOut.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeOut.setText("10:00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCloseVerified)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addComponent(timeOut, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(178, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(86, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(btnCloseVerified)
                .addGap(28, 28, 28)
                .addComponent(timeOut, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(129, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                    .addContainerGap(18, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(18, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCloseVerifiedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCloseVerifiedActionPerformed
        dispose();
    }//GEN-LAST:event_btnCloseVerifiedActionPerformed
    
    private void cmdVerifiedOTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdVerifiedOTPActionPerformed
        String otp = txtOTP.getText();
        if(isOTP(otp)==true){
            lberror.setVisible(false);
            try {
                Client.getInstance().send("authenotp#~check#~"+otp);
            } catch (IOException ex) {
                Logger.getLogger(Verified.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        else{
            lberror.setVisible(true);
        }
    }//GEN-LAST:event_cmdVerifiedOTPActionPerformed
    private boolean isOTP(String str) {
        Pattern p1 = Pattern.compile("([a-z])");
        Matcher m1 = p1.matcher(str);
        Pattern p2 = Pattern.compile("([A-Z])");
        Matcher m2 = p2.matcher(str);
        if(m1.find()==true){
            return false;
        }
        if(m2.find() == true){
            return false;
        }
        Pattern p3 = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m3 = p3.matcher(str);
        if(m3.find() == true){
            return false;
        }
        if(str.equals("")){
            return false;
        }
        return true;
    }    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Verified.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Verified.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Verified.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Verified.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Verified().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCloseVerified;
    private javax.swing.JButton cmdVerifiedOTP;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbVerified;
    private javax.swing.JLabel lberror;
    private javax.swing.JLabel timeOut;
    private javax.swing.JTextField txtOTP;
    // End of variables declaration//GEN-END:variables
}
