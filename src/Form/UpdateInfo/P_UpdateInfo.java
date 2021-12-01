package Form.UpdateInfo;

import Form.Body.Event.PublicEvent;
import Model.AccountModel;
import Server.Client;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class P_UpdateInfo extends javax.swing.JPanel {
    private AccountModel user;
    public P_UpdateInfo() {
        initComponents();
        init();
    }
    private void init(){
        this.user = Client.getInstance().User;
        txtFullName.setText(user.getFullName());
        if(user.getGender().equals("Male")){
            btnMale.setSelected(true);
        }
        else{
            btnFemale.setSelected(true);
        }
        jDateBirth.setDate(user.getDateOfBirth());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        lbTitle = new javax.swing.JLabel();
        cmdUpdateInfo = new javax.swing.JButton();
        cmdBackChangePassword = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtFullName = new javax.swing.JTextField();
        btnMale = new javax.swing.JRadioButton();
        btnFemale = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jDateBirth = new com.toedter.calendar.JDateChooser();
        lbErrDate = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        lbTitle.setFont(new java.awt.Font("sansserif", 0, 22)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(87, 87, 87));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("Change Information");

        cmdUpdateInfo.setText("Change");
        cmdUpdateInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdUpdateInfoActionPerformed(evt);
            }
        });

        cmdBackChangePassword.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        cmdBackChangePassword.setForeground(new java.awt.Color(15, 128, 206));
        cmdBackChangePassword.setText("Back Change Password");
        cmdBackChangePassword.setContentAreaFilled(false);
        cmdBackChangePassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdBackChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBackChangePasswordActionPerformed(evt);
            }
        });

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Date of Birth");

        buttonGroup1.add(btnMale);
        btnMale.setSelected(true);
        btnMale.setText("Male");

        buttonGroup1.add(btnFemale);
        btnFemale.setText("Female");

        jLabel5.setText("Full Name");

        jLabel6.setText("Gender");

        jDateBirth.setToolTipText("");
        jDateBirth.setDateFormatString("yyyy/MM/dd");

        lbErrDate.setForeground(new java.awt.Color(255, 0, 51));
        lbErrDate.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cmdBackChangePassword, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmdUpdateInfo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFullName)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnMale, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnFemale)
                                .addGap(0, 32, Short.MAX_VALUE)))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbErrDate, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lbTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
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
                .addGap(2, 2, 2)
                .addComponent(lbErrDate, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmdUpdateInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdBackChangePassword)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdBackChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBackChangePasswordActionPerformed
        PublicEvent.getInstance().getEventUpdateInfo().goChangePassword();
    }//GEN-LAST:event_cmdBackChangePasswordActionPerformed

    private void cmdUpdateInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUpdateInfoActionPerformed
        String gender;
        Gson gson = new Gson();
        if(btnFemale.isSelected()){
            gender = "Female";
        }
        else{
            gender = "Male";
        }
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String DateOfBirth=sdf.format(jDateBirth.getDate());
        AccountModel updateuser = new AccountModel();
        updateuser.setId(user.getId());
        updateuser.setFullName(txtFullName.getText());
        updateuser.setGender(gender);
        updateuser.setBirdOfDate(Date.valueOf(DateOfBirth));
        String user = gson.toJson(updateuser);
        try {
            Client.getInstance().send("updateuser#~"+user);
            //JOptionPane.showMessageDialog(null, "Đã cập nhật thành công");
            //Client.getInstance().User = updateuser;
            PublicEvent.getInstance().getEventUpdateInfo().updateInfo();
            PublicEvent.getInstance().getEventMain().logout();
            Client.getInstance().send("logout#~"+updateuser.getId());
        } catch (IOException ex) {
            
        }
        
        
        
    }//GEN-LAST:event_cmdUpdateInfoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton btnFemale;
    private javax.swing.JRadioButton btnMale;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cmdBackChangePassword;
    private javax.swing.JButton cmdUpdateInfo;
    private com.toedter.calendar.JDateChooser jDateBirth;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel lbErrDate;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JTextField txtFullName;
    // End of variables declaration//GEN-END:variables
}
