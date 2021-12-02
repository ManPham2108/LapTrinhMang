/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Form.Body.Event;

import Model.AccountModel;
import Model.GroupModel;
import java.util.ArrayList;

/**
 *
 * @author man21
 */
public interface EventMenuLeft {
    public void addlistUser(ArrayList<AccountModel> listuser);
    public void updateStatusOnline(String Id);
    public void updateStatusOffline(String Id);
    public void listGroup(ArrayList<GroupModel> listgroup,String status);
    public void NotifiMsg(String iduser,String type,Boolean status);
    public void exitGroup(GroupModel gr);
}
