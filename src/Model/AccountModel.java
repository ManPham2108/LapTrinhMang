/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Encrypt.DES_For_Client;
import java.math.BigInteger;
import java.sql.Date;

/**
 *
 * @author man21
 */
public class AccountModel {
    /**
     * default public key for client
     */
    private final BigInteger n = new BigInteger("5559198564321915535997584315413393092493348347255843419771222580227273559376281043338357980467043907042637716818326704144978286703037762308482220625386384388624680062949875597814032263102045209295610051823779969687947948509258575370491964212854716485165414726530861795479919698326099361821127536680927");
    private final BigInteger publickey = new BigInteger("65537");
    
    private String Id;
    private String Username;
    private String Password;
    private String FullName;
    private String Gender;
    private Date DateOfBirth;
    private final DES_For_Client DES_Client;

    public AccountModel(String Id, String Username, String Password, String FullName, String Gender, Date DateOfBirth) {        
        this.Id = Id;
        this.Username = Username;
        this.Password = Password;
        this.FullName = FullName;
        this.Gender = Gender;
        this.DateOfBirth = DateOfBirth;
        this.DES_Client = new DES_For_Client();
    }
    public AccountModel(){
        this.DES_Client = new DES_For_Client();
    }
    
    /**
     * Send string of this method for server
     * This is key of DES is encrypted by RSA
     * @return 
     */
    public String RSA_Encryption()
    {
        BigInteger keyOfDES = new BigInteger(DES_Client.getKeyDES());
        BigInteger Key_encypted = keyOfDES.modPow(publickey, n);
        return Key_encypted.toString();
    }

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
