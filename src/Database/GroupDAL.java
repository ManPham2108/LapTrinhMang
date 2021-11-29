
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
                     String.valueOf(rss.getInt("Id")),
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
        ResultSet rs = this.connect.Select("groupmember", "IdGroup='"+idgroup+"'");
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
        HashMap<String, Object> listhashusergroup = new HashMap<>();
        for(int i = 0;i<group.getListUserGroup().size();i++){
            listhashusergroup.put("IdGroup", idgr); 
            listhashusergroup.put("IdUser", group.getListUserGroup().get(i)); 
            this.connect.insert("groupmember", listhashusergroup);
        }
    }
}
