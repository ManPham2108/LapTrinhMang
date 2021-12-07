
package Form.Body.Event;

import Model.AccountModel;
import Model.GroupModel;
import java.util.ArrayList;

public interface EventMenuLeft {
    public void addlistUser(ArrayList<AccountModel> listuser);
    public void updateStatusOnline(String Id);
    public void updateStatusOffline(String Id);
    public void listGroup(ArrayList<GroupModel> listgroup,String status);
    public void NotifiMsg(String iduser,String type,Boolean status);
    public void exitGroup(GroupModel gr);
}
