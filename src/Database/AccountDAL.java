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

    public AccountDAL() {
        this.connect = Database.DAL.getDAL();
        try {
            allAccount = getAllAccount(null, null);
        } catch (Exception ex) {
            Logger.getLogger(AccountDAL.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<AccountModel> getAllAccount(String condition, String OrderBy) throws Exception {
        ResultSet rs = this.connect.Select("account", condition, OrderBy);
        ArrayList<AccountModel> listAccount = new ArrayList<>();
        while (rs.next()) {
            listAccount.add(new AccountModel(
                    rs.getInt("Id"),
                    rs.getString("Username"),
                    rs.getString("Password"),
                    rs.getString("FullName"),
                    rs.getString("Gender"),
                    rs.getDate("DateOfBirth")
            ));
        }
        return listAccount;
    }
    public static void main(String[] args) {
        AccountDAL ac = new AccountDAL();
        for(AccountModel a : ac.allAccount){
            System.out.println(a.getFullName());
        }
    }
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
