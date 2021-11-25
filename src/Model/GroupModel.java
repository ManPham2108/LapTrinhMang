/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author man21
 */
public class GroupModel {
    private String IdGroup;
    private String NameGroup;
    private ArrayList<String> listUserId;
    public GroupModel(){};
    public GroupModel(String IdGroup, String NameGroup){
            this.IdGroup = IdGroup;
            this.NameGroup = NameGroup;
        }
    public GroupModel(String IdGroup, String NameGroup, ArrayList<String> listUserId) {
        this.IdGroup = IdGroup;
        this.NameGroup = NameGroup;
        this.listUserId = listUserId;
    }
    
    public String getIdGroup() {
        return IdGroup;
    }

    public String getNameGroup() {
        return NameGroup;
    }

    public ArrayList<String> getListUserGroup() {
        return listUserId;
    }

    public void setIdGroup(String IdGroup) {
        this.IdGroup = IdGroup;
    }

    public void setNameGroup(String NameGroup) {
        this.NameGroup = NameGroup;
    }

    public void setListUserGroup(ArrayList<String> listUserId) {
        this.listUserId = listUserId;
    }

}
