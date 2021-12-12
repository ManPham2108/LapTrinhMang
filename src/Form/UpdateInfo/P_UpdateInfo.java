package Form.UpdateInfo;

import Form.Body.Event.PublicEvent;
import Model.AccountModel;
import Server.Client;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class P_UpdateInfo extends javax.swing.JPanel {
    private AccountModel user;
    public P_UpdateInfo() {
        initComponents();
        init();
        jDateBirth.getDateEditor().setEnabled(false);
        lbErrorNS.setVisible(false);
        lbErrorFullName.setVisible(false);
    }
    private void init(){
        this.user = Client.getInstance().User;
        txtFullName.setText(user.getFullName());
        if(user.getGender().equals("Nam")){
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
        lbErrorFullName = new javax.swing.JLabel();
        jDateBirth = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        errorNgaySinh = new javax.swing.JLabel();
        lbErrorNS = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        lbTitle.setFont(new java.awt.Font("sansserif", 0, 22)); // NOI18N
        lbTitle.setForeground(new java.awt.Color(87, 87, 87));
        lbTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTitle.setText("THÔNG TIN CÁ NHÂN");

        cmdUpdateInfo.setText("Cập nhật");
        cmdUpdateInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdUpdateInfoActionPerformed(evt);
            }
        });

        cmdBackChangePassword.setFont(new java.awt.Font("sansserif", 0, 11)); // NOI18N
        cmdBackChangePassword.setForeground(new java.awt.Color(15, 128, 206));
        cmdBackChangePassword.setText("THAY ĐỔI MẬT KHẨU");
        cmdBackChangePassword.setContentAreaFilled(false);
        cmdBackChangePassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdBackChangePassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdBackChangePasswordActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("NGÀY SINH");

        buttonGroup1.add(btnMale);
        btnMale.setSelected(true);
        btnMale.setText("Nam");

        buttonGroup1.add(btnFemale);
        btnFemale.setText("Nữ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("HỌ VÀ TÊN");

        lbErrorFullName.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbErrorFullName.setForeground(new java.awt.Color(255, 0, 0));
        lbErrorFullName.setText("Họ và tên không được chứa số và kí tự đặt biệt");

        jDateBirth.setToolTipText("");
        jDateBirth.setDateFormatString("yyyy/MM/dd");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("GIỚI TÍNH");

        errorNgaySinh.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        errorNgaySinh.setForeground(new java.awt.Color(255, 0, 0));
        errorNgaySinh.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        lbErrorNS.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbErrorNS.setForeground(new java.awt.Color(255, 0, 0));
        lbErrorNS.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbErrorNS.setText("Ngày sinh không hợp lệ (>=12)");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmdBackChangePassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmdUpdateInfo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnMale, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnFemale))
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateBirth, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lbErrorFullName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtFullName, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(errorNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbErrorNS)))
                        .addContainerGap())))
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
                .addGap(10, 10, 10)
                .addComponent(lbErrorFullName, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateBirth, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnMale)
                            .addComponent(btnFemale))))
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errorNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbErrorNS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdUpdateInfo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cmdBackChangePassword)
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cmdBackChangePasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdBackChangePasswordActionPerformed
        PublicEvent.getInstance().getEventUpdateInfo().goChangePassword();
    }//GEN-LAST:event_cmdBackChangePasswordActionPerformed

    private void cmdUpdateInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdUpdateInfoActionPerformed
        try {
            String gender;
            Gson gson = new Gson();
            String fullname = txtFullName.getText();
            if(btnFemale.isSelected()){
                gender = "Nữ";
            }
            else{
                gender = "Nam";
            }
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String DateOfBirth=sdf.format(jDateBirth.getDate());
            String dateuser = sdf.format(user.getDateOfBirth());//
            if(fullname.equals(user.getFullName()) && gender.equals(user.getGender()) && DateOfBirth.equals(dateuser)){
                JOptionPane.showMessageDialog(null, "Không có thông tin nào được thay đổi","Thông báo",JOptionPane.INFORMATION_MESSAGE);
            }
            else{
                int fail = 0;
                if(isFullname(txtFullName.getText())==false){
                    lbErrorFullName.setVisible(true);
                    fail++;
                }
                else{
                    lbErrorFullName.setVisible(false);
                }
                if((tinhngay(DateOfBirth) / 365) < 13){
                    lbErrorNS.setVisible(true);
                    fail++;
                }
                else{
                    lbErrorNS.setVisible(false);
                }
                if(fail==0){
                    AccountModel updateuser = new AccountModel();
                    updateuser.setId(user.getId());
                    updateuser.setFullName(txtFullName.getText());
                    updateuser.setGender(gender);
                    updateuser.setBirdOfDate(Date.valueOf(DateOfBirth));
                    String user = gson.toJson(updateuser);
                    Client.getInstance().send("updateuser#~infor#~"+user);
                    PublicEvent.getInstance().getEventUpdateInfo().updateInfo();
                    PublicEvent.getInstance().getEventMain().logout();
                    Client.getInstance().send("logout#~"+updateuser.getId());
                }
            }
        } catch (ParseException ex) {
            Logger.getLogger(P_UpdateInfo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(P_UpdateInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cmdUpdateInfoActionPerformed
    private boolean isFullname(String str) {
        Pattern p = Pattern.compile("([0-9])");
        Matcher m = p.matcher(str);
        if(m.find() == true){
            return false;
        }
        Pattern p3 = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher m3 = p3.matcher(str);
        if(m3.find() == true){
            return false;
        }
        return true;        
    }
    private long tinhngay(String ngaySinh) throws ParseException{
        java.util.Date now = new java.util.Date();
        String ngay = ngaySinh.substring(0,10);
        java.util.Date NgaySinh = new SimpleDateFormat("yyyy-MM-dd").parse(ngay);
        Calendar calendarNow=Calendar.getInstance();
        Calendar calendarNgaySinh=Calendar.getInstance();
        calendarNow.setTime(now);
        calendarNgaySinh.setTime(NgaySinh);
        return -(calendarNgaySinh.getTime().getTime()-calendarNow.getTime().getTime())/(24*3600*1000);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton btnFemale;
    private javax.swing.JRadioButton btnMale;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cmdBackChangePassword;
    private javax.swing.JButton cmdUpdateInfo;
    private javax.swing.JLabel errorNgaySinh;
    private com.toedter.calendar.JDateChooser jDateBirth;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel lbErrorFullName;
    private javax.swing.JLabel lbErrorNS;
    private javax.swing.JLabel lbTitle;
    private javax.swing.JTextField txtFullName;
    // End of variables declaration//GEN-END:variables
}
