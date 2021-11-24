package Database;
import Model.AccountModel;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author letoan
 */
public final class AccountDAL {
    MyConnectUnit connect;
    public ArrayList<AccountModel> allAccount = new ArrayList<AccountModel>();
    public ArrayList<AccountModel> allAccountInfor = new ArrayList<AccountModel>();

    public AccountDAL() {
        this.connect = Database.DAL.getDAL();
        try {
            allAccount = getAllAccount(null, null);
            allAccountInfor = getAllAccountInfor(null, null);
        } catch (Exception ex) {
            Logger.getLogger(AccountDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<AccountModel> getAllAccount(String condition, String OrderBy) throws Exception {
        ResultSet rs = this.connect.Select("account", condition, OrderBy);
        ArrayList<AccountModel> listAccount = new ArrayList<>();
        while (rs.next()) {
            listAccount.add(new AccountModel(
                    rs.getString("Id"),
                    rs.getString("Username"),
                    rs.getString("Password"),
                    rs.getString("Block")
            ));
        }
        return listAccount;
    }
    public ArrayList<AccountModel> getAllAccountInfor(String condition, String OrderBy) throws Exception {
        ResultSet rs = this.connect.Select("accountinfor", condition, OrderBy);
        ArrayList<AccountModel> listAccountinfor = new ArrayList<>();
        while (rs.next()) {
            listAccountinfor.add(new AccountModel(
                    rs.getString("Id"),
                    rs.getString("FullName"),
                    rs.getString("Gender"),
                    rs.getDate("DateOfBirth")
            ));
        }
        return listAccountinfor;
    }
    public static void main(String[] args) throws Exception {
        AccountDAL ac = new AccountDAL();
        AccountModel am = ac.getUser("a@gmail.com", "123456789");

    }
    public AccountModel getUser(String user,String pass) throws Exception{
        ArrayList<AccountModel> listAcc = this.getAllAccount("Username='" + user + "'"+" AND "+"Password='"+pass+"'", null);
        if(listAcc.size()>0){
            ArrayList<AccountModel> listAccInofr = this.getAllAccountInfor("Id='"+listAcc.get(0).getId()+"'", null);
            listAccInofr.get(0).setBlock(listAcc.get(0).getBlock());
            return listAccInofr.get(0);
        }
        return null;
    }
    public void Insert(AccountModel user) throws Exception{
        allAccount=getAllAccount(null, null);
        user.setId("00"+(allAccount.size()+1));
        try {
            HashMap<String, Object> hashUserAccount = new HashMap<>();
            hashUserAccount.put("Id", user.getId());
            hashUserAccount.put("Username", user.getUsername());
            hashUserAccount.put("Password", user.getPassword());
            hashUserAccount.put("Block", "False");
            HashMap<String, Object> hashUserAccountInfor = new HashMap<>();
            hashUserAccountInfor.put("Id", user.getId());
            hashUserAccountInfor.put("Fullname", user.getFullName());
            hashUserAccountInfor.put("Gender", user.getGender());
            hashUserAccountInfor.put("DateOfBirth", user.getDateOfBirth());
            this.connect.insert("account", hashUserAccount);
            this.connect.insert("accountinfor", hashUserAccountInfor);
        } catch (Exception ex) {
            
        }
    }
    public void UpdateBlock(String userid,String status) throws Exception{
        HashMap<String, Object> hashAccount = new HashMap<>();
        hashAccount.put("Block", status);
        this.connect.update("account",hashAccount,"Id='"+userid+"'");
    }
    public void UpdateInfor(AccountModel user){
        try {
            HashMap<String, Object> hashUseriInfor = new HashMap<>();
            hashUseriInfor.put("Id", user.getId());
            hashUseriInfor.put("Fullname", user.getFullName());
            hashUseriInfor.put("Gender", user.getGender());
            hashUseriInfor.put("DateOfBirth", user.getDateOfBirth());
            System.out.println(user.getId());
            this.connect.update("accountinfor", hashUseriInfor, "Id='"+user.getId()+"'");
        } catch (Exception ex) {
            
        }
    }
//    public void sendEmail(String tenKH,String MaCode,String mailKH) throws AddressException, MessagingException {
//        Properties mailServerProperties;
//        Session getMailSession;
//        MimeMessage mailMessage;
//        // Step1: setup Mail Server
//        mailServerProperties = System.getProperties();
//        mailServerProperties.put("mail.smtp.port", "587");
//        mailServerProperties.put("mail.smtp.auth", "true");
//        mailServerProperties.put("mail.smtp.starttls.enable", "true");
//        // Step2: get Mail Session
//        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
//        mailMessage = new MimeMessage(getMailSession);
//
//        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mailKH)); //Thay abc bằng địa chỉ người nhận
//
//        // Bạn có thể chọn CC, BCC
//        mailMessage.setSubject("Mã otp để xác thực đăng kí tài khoản chat");
//        String emailBody = "<p>Chào anh/chị <b>"+tenKH+"</b></p>"
//                         + "<p>Công ty sắp tới sẽ có chương trình khuyến mãi mua vé máy bay</p>"
//                         + "<p>Công ty xin tặng anh/chị <b>"+tenKH+"</b> mã code của chương trình khuyến mãi</p>"
//                         + "<table style=\"border-spacing: 0px\" border='1'>"
//                            + "<tr>"
//                                + "<th style=\"padding: 10px\">Chương trình khuyến mãi</th>"
//                                + "<th style=\"padding: 10px\">Mã code</th>"
//                                + "<th style=\"padding: 10px\">Phần trăm khuyến mãi</th>"
//                                + "<th style=\"padding: 10px\">Ngày bắt đầu</th>"
//                                + "<th style=\"padding: 10px\">Ngày kết thúc</th>"
//                            + "</tr>"
//                            + "<tr style=\"color: red; text-align: center\">"
//                                + "<td>"+ctkm.getTenCTKM()+"</td>"
//                                + "<td>"+MaCode+"</td>"
//                                + "<td>"+Integer.toString(ctkm.getPhanTramKM())+"%</td>"
//                                + "<td>"+ctkm.getNgayBatDau()+"</td>"
//                                + "<td>"+ctkm.getNgayKetThuc()+"</td>"
//                            + "</tr>"
//                         + "</table>"
//                         + "<p>Cám ơn anh/chị <b>"+tenKH+"</b> đã sử dụng dịch vụ mua vé máy bay bên công ty</p>";
//        mailMessage.setContent(emailBody,"text/html; charset=UTF-8");
//        // Step3: Send mail
//        Transport transport = getMailSession.getTransport("smtp");
//        // Thay your_gmail thành gmail của bạn, thay your_password thành mật khẩu gmail của bạn
//        transport.connect("smtp.gmail.com", "bvemaybay@gmail.com", "vemaybay123"); 
//        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
//        transport.close();
//    }
//    public NhanVienModel getKhachHang(String maNV) throws Exception {
//        ArrayList<NhanVienModel> listNhanVien = this.getAllNhanVien("MaNV='" + maNV + "'", null);
//        if (listNhanVien.size() > 0) {
//            return listNhanVien.get(0);
//        }
//        return null;
//    }
//    
//    public void Update(NhanVienModel nv) throws Exception {
//        HashMap<String, Object> map = new HashMap<>();
//        map.put("MaNV", nv.getMaNhanVien());
//        map.put("HoTenNV", nv.getTenNhanVien());
//        map.put("NgaySinh", nv.getNgaySinh());
//        map.put("GioiTinh", nv.getGioiTinh());
//        map.put("SĐT", nv.getSDT());
//        map.put("DiaChi", nv.getDiaChi());
//        map.put("Luong", nv.getLuong());
//        map.put("TrangThai", nv.getTrangThai());
//
//        this.connect.update("nhanvien", map, "MaNV='" + nv.getMaNhanVien()+ "'");
//    }

}
