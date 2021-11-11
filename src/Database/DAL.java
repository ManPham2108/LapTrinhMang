/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

/**
 *
 * @author PHUC
 */
public class DAL {
    public static MyConnectUnit getDAL(){
        return new MyConnectUnit("localhost:3306", "root", "admin1234", "quanlytourdulich");
    }
    
}
