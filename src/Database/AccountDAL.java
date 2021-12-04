package Database;
import Encrypt.HashPassword;
import Model.AccountModel;
import java.sql.Date;
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
                    String.valueOf(rs.getInt("Id")),
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
                    String.valueOf(rs.getInt("Id")),
                    rs.getString("FullName"),
                    rs.getString("Gender"),
                    rs.getDate("DateOfBirth")
            ));
        }
        return listAccountinfor;
    }
    public static void main(String[] args) throws Exception {
        AccountDAL ac = new AccountDAL();
        ac.getUser("a", "a");
        

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
        HashPassword hash = new HashPassword();
        String passhash = hash.generateHash(user.getPassword());
        try {
            HashMap<String, Object> hashUserAccount = new HashMap<>();
            hashUserAccount.put("Username", user.getUsername());
            hashUserAccount.put("Password", passhash);
            hashUserAccount.put("Block", "False");
            this.connect.insert("account", hashUserAccount);
            user.setId(getAllAccount("Username='"+user.getUsername()+"'", null).get(0).getId());
            HashMap<String, Object> hashUserAccountInfor = new HashMap<>();
            hashUserAccountInfor.put("Id", Integer.valueOf(user.getId()));
            hashUserAccountInfor.put("Fullname", user.getFullName());
            hashUserAccountInfor.put("Gender", user.getGender());
            hashUserAccountInfor.put("DateOfBirth", user.getDateOfBirth());
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
            hashUseriInfor.put("Fullname", user.getFullName());
            hashUseriInfor.put("Gender", user.getGender());
            hashUseriInfor.put("DateOfBirth", user.getDateOfBirth());
            this.connect.update("accountinfor", hashUseriInfor, "Id='"+user.getId()+"'");
        } catch (Exception ex) {
            
        }
    }
    public boolean checkPassOld(String iduser,String passold) throws Exception{
        HashPassword hash = new HashPassword();
        String passhash = hash.generateHash(passold);
        if(getAllAccount("Id='"+iduser+"' and Password='"+passhash+"'", null).size()>0){
            return true;
        }
        else{
            return false;
        }
    }
    public void updatePass(String iduser,String pass){
        HashPassword hash = new HashPassword();
        String passhash = hash.generateHash(pass);
        try {
            HashMap<String, Object> hashUserPass = new HashMap<>();
            hashUserPass.put("Password",passhash );
            this.connect.update("account", hashUserPass, "Id='"+iduser+"'");
        } catch (Exception ex) {
            
        }
    }
    public ArrayList<String> listUserblock(String iduser) throws Exception{
        ArrayList<String> list = new ArrayList<>();
        ResultSet rs = this.connect.Select("blockuser","Id='"+iduser+"'", null);
        while (rs.next()) {            
            list.add(String.valueOf(rs.getInt("IdUserBlock")));
        }
        return list;
    }
    public ArrayList<String> listUserBlocked(String iduser) throws Exception{
        ArrayList<String> list = new ArrayList<>();
        ResultSet rs = this.connect.Select("blockuser","IdUserBlock='"+iduser+"'", null);
        while (rs.next()) {            
            list.add(String.valueOf(rs.getInt("Id")));
        }
        return list;
    }
    public void insertBlockUser(String iduser, String idUserblock) throws Exception{
        HashMap<String,Object> Block = new HashMap<>();
        Block.put("Id", Integer.valueOf(iduser));
        Block.put("IdUserBlock", Integer.valueOf(idUserblock));
        this.connect.insert("blockuser", Block);
    }
    public void deleteBlockUser(String iduser, String idUserblock) throws Exception{
        this.connect.delete("blockuser","Id='"+iduser+"' and IdUserBlock='"+idUserblock+"'");
    }
}
