
package Database;

public class DAL {
    public static MyConnectUnit getDAL(){
        return new MyConnectUnit("localhost:3306", "root", "", "laptrinhmang");
    }
    
}
