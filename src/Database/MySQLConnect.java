/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author PHUC
 */
public class MySQLConnect {

    String Host = "";
    String UserName = "";
    String Password = "";
    String Database = "";

    Connection conn = null;
    Statement st = null;
    ResultSet rs = null;

    public MySQLConnect(String Host, String UserName, String Password, String Database) {
        this.Host = Host;
        this.UserName = UserName;
        this.Password = Password;
        this.Database = Database;
    }

    protected void driverTest() throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (java.lang.ClassNotFoundException e) {
            throw new Exception("MySQL JDBC Driver not found");
        }
    }

    public Connection getConnect() throws Exception {
        if (this.conn == null) {
            driverTest();
        }
        String url = "jdbc:mysql://" + this.Host + "/" + this.Database;
        try {
            this.conn = (Connection) DriverManager.getConnection(url, this.UserName, this.Password);
        } catch (SQLException e) {
            throw new Exception("Không thể kết nối đến database " + url);
        }
        return this.conn;
    }

    protected Statement getStatement() throws SQLException, Exception {
        if (this.st == null ? true : this.st.isClosed()) {
            this.st = this.getConnect().createStatement();
        }
        return this.st;
    }

    public ResultSet executeQuery(String Query) throws Exception {
        try {
            this.rs = getStatement().executeQuery(Query);
        } catch (Exception e) {
            throw new Exception("ERROR");
        }
        return this.rs;
    }

    public int executeUpdate(String Query) throws Exception {
        int res = Integer.MIN_VALUE;
        try {
            res = getStatement().executeUpdate(Query);

        } catch (Exception e) {
            throw new Exception("ERROR");
        } finally {
            this.Close();
        }
        return res;
    }

    public void Close() throws SQLException {
        if (this.conn != null && !this.conn.isClosed()) {
            this.conn.close();
            this.conn = null;
        }
        if (this.st != null && !this.st.isClosed()) {
            this.st.close();
            this.st = null;
        }
        if (this.rs != null && !this.rs.isClosed()) {
            this.rs.close();
            this.rs = null;
        }
    }

    public static void main(String[] args) throws Exception {
        MySQLConnect m = new MySQLConnect("localhost:3306", "root", "admin1234", "quanlytourdulich");
        Connection c = m.getConnect();

//        ArrayList<TourDuLichModel> list = new ArrayList<>();
//        TourDuLichService tourService = new TourDuLichService();
//        
//        list = tourService.getTours();
//        System.out.println(list.size());
    }

}
