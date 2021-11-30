
package Database;

import Model.GroupModel;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;


public final class GroupDAL {
    MyConnectUnit connect;
    public ArrayList<GroupModel> allGroup = new ArrayList<GroupModel>();
    public GroupDAL() throws Exception {
        this.connect = Database.DAL.getDAL();
    }
    private String idgrnew;
    public ArrayList<GroupModel>getGroupUser(String id) throws Exception {
        ResultSet rs = this.connect.Select("groupmember","IdUser='"+id+"'",null);
        ArrayList<String> listIdGroup = new ArrayList<String>();
        while (rs.next()) {
            listIdGroup.add(String.valueOf(rs.getInt("IdGroup")));
        }
        ArrayList<GroupModel> listGroup = new ArrayList<GroupModel>();
        for(String i : listIdGroup){
             ResultSet rss = this.connect.Select("`group`","Id='"+i+"'",null);
             while (rss.next()) {                
                listGroup.add(new GroupModel(
                     String.valueOf("G"+rss.getInt("Id")),
                    rss.getString("NameGroup")
             ));
            } 
        }
        return listGroup;
    }
    public static void main(String[] args) throws Exception {
        GroupDAL gr = new GroupDAL();
        gr.getGroupUser("1");
        ArrayList<String> a = new  ArrayList<>();
        a.add("006");
        a.add("007");
    }
    public ArrayList<String> allUserInGroup(String idgroup) throws Exception{
        ResultSet rs = this.connect.Select("groupmember", "IdGroup='"+idgroup.substring(1)+"'");
        ArrayList<String> listuser = new ArrayList<String>();
        while (rs.next()){            
            listuser.add(String.valueOf(rs.getInt("IdUser")));
        }
        return listuser;
    }
    public void insert(GroupModel group) throws Exception{
        HashMap<String, Object> hashgroup = new HashMap<>();
        hashgroup.put("NameGroup", group.getNameGroup());
        this.connect.insert("`group`", hashgroup);
        ResultSet rs = this.connect.Select("`group`","NameGroup='"+group.getNameGroup()+"'",null);
        String idgr = null;
        while(rs.next()){
            idgr = String.valueOf(rs.getInt("Id"));
        }
        idgrnew = idgr;
        HashMap<String, Object> listhashusergroup = new HashMap<>();
        for(int i = 0;i<group.getListUserGroup().size();i++){
            listhashusergroup.put("IdGroup", Integer.valueOf(idgr)); 
            listhashusergroup.put("IdUser", group.getListUserGroup().get(i)); 
            listhashusergroup.put("Block", "False");
            this.connect.insert("groupmember", listhashusergroup);
        }
    }
    public ArrayList<GroupModel> updateGrNew() throws Exception{
        ArrayList<GroupModel> grnew = new ArrayList<GroupModel>();
        ResultSet rs = this.connect.Select("`group`", "Id='"+idgrnew+"'");
        while (rs.next()) {            
            grnew.add(new GroupModel(
                     String.valueOf("G"+rs.getInt("Id")),
                    rs.getString("NameGroup")
             ));
        }
        return grnew;
    }
    public ArrayList<String> loadListGroupBlock(String iduser) throws Exception{
        ArrayList<String> listgroupblock = new ArrayList<>();
        ResultSet rs = this.connect.Select("groupmember", "Iduser='"+iduser+"' and Block='True'");
        while (rs.next()){            
            listgroupblock.add(String.valueOf("G"+rs.getInt("IdGroup")));
        }
        return listgroupblock;
    }
    public void updateBlockGroup(String idgroup,String iduser,String status) throws Exception{
        HashMap<String, Object> hash = new HashMap<>();
        hash.put("Block", status);
        this.connect.update("groupmember",hash,"IdGroup='"+idgroup.substring(1)+"' and IdUser='"+iduser+"'");
    }
    public void deleteUserInGr(String idgroup,String iduser) throws Exception{
        this.connect.delete("groupmember","IdGroup='"+idgroup.substring(1)+"' and IdUser='"+iduser+"'");
    }
}
