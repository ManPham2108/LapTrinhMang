package Database;
import Encrypt.HashPassword;
import Model.AccountModel;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


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
        HashPassword hash = new HashPassword();
        String passhash = hash.generateHash(pass);
        ArrayList<AccountModel> listAcc = this.getAllAccount("Username='" + user + "'"+" AND "+"Password='"+passhash+"'", null);
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
        HashPassword hash = new HashPassword();
        String passhash = hash.generateHash(user.getPassword());
        try {
            HashMap<String, Object> hashUserAccount = new HashMap<>();
            hashUserAccount.put("Id", user.getId());
            hashUserAccount.put("Username", user.getUsername());
            hashUserAccount.put("Password", passhash);
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
}
