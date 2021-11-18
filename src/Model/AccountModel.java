/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Date;

/**
 *
 * @author man21
 */
public class AccountModel {
    private String Id;
    private String Username;
    private String Password;
    private String FullName;
    private String Gender;
    private Date DateOfBirth;

    public AccountModel(String Id, String Username, String Password, String FullName, String Gender, Date DateOfBirth) {
        this.Id = Id;
        this.Username = Username;
        this.Password = Password;
        this.FullName = FullName;
        this.Gender = Gender;
        this.DateOfBirth = DateOfBirth;
    }
    public AccountModel(){};

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public void setDateOfBirth(Date DateOfBirth) {
        this.DateOfBirth = DateOfBirth;
    }
    
    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public String getGender() {
        return Gender;
    }

    public String getFullName() {
        return FullName;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public void setFullName(String FullName) {
        this.FullName = FullName;
    }

    public void setBirdOfDate(Date DateOfBirth) {
        this.DateOfBirth = DateOfBirth;
    }
    
}
