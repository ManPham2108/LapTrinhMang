
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
    private int idgr;
    public ArrayList<GroupModel>getGroupUser(String id) throws Exception {
        ResultSet rs = this.connect.Select("`group`","IdUser='"+id+"'",null);
        ArrayList<GroupModel> listGroup = new ArrayList<GroupModel>();
        while (rs.next()) {
            listGroup.add(new GroupModel(
                    rs.getString("IdGroup"),
                    rs.getString("NameGroup")
            ));
        }
        return listGroup;
    }
//    public static void main(String[] args) throws Exception {
//        GroupDAL gr = new GroupDAL();
//        ArrayList<String> a = new  ArrayList<>();
//        a.add("006");
//        a.add("007");
//        gr.insert(new GroupModel("G102","xin",a));
//    }
    public ArrayList<String> allUserInGroup(String idgroup) throws Exception{
        ResultSet rs = this.connect.Select("`group`", "IdGroup='"+idgroup+"'");
        ArrayList<String> listuser = new ArrayList<String>();
        while (rs.next()){            
            listuser.add(rs.getString("IdUser"));
        }
        return listuser;
    }
    public void insert(GroupModel group) throws Exception{
        for(int i = 0;i<group.getListUserGroup().size();i++){
            HashMap<String, Object> hashgroup = new HashMap<>();
            hashgroup.put("IdGroup", group.getIdGroup());
            hashgroup.put("NameGroup", group.getNameGroup());
            hashgroup.put("IdUser", group.getListUserGroup().get(i)); 
            this.connect.insert("`group`", hashgroup);
        }
    }
}
