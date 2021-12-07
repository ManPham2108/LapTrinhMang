
package Database;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;

public class MyConnectUnit {
    MySQLConnect connect;

    public MyConnectUnit(String Host, String UserName, String Password, String Database) {
        this.connect = new MySQLConnect(Host, UserName, Password, Database);
    }
    
    //SELECT
    public ResultSet Select(String Table, String Condition, String OrderBy) throws Exception{
        StringBuilder query=new StringBuilder("SELECT * FROM "+Table);
        this.AddCondition(query, Condition);
        this.AddOrderBy(query, OrderBy);
        query.append(";");
        return this.connect.executeQuery(query.toString());
    }
    public ResultSet Select(String Table, String Condition) throws Exception{
        return this.Select(Table, Condition, null);
    }
    public ResultSet Select(String Table) throws Exception{
        return this.Select(Table, null);
    }
    //
    public void AddCondition(StringBuilder query, String Condition){
        if(Condition!=null){
            query.append(" WHERE "+Condition);
        }
    }
    public void AddOrderBy(StringBuilder query,String OderBy){
        if(OderBy!=null){
            query.append(" ORDER BY "+OderBy);
        }
    }
    //INSERT
    public boolean insert(String Table,HashMap<String,Object> ColumnValues) throws Exception{
        StringBuilder query=new StringBuilder("INSERT INTO "+Table);
        StringBuilder valueInsert=new StringBuilder();
        query.append("(");
        for(String key:ColumnValues.keySet()){
            query.append(key+",");
            valueInsert.append("'"+ColumnValues.get(key).toString()+"',");
        }
        query.delete(query.length()-1, query.length());
        valueInsert.delete(valueInsert.length()-1, valueInsert.length());
        query.append(") VALUES("+valueInsert.toString()+");");
        return this.connect.executeUpdate(query.toString())>0;
    }
    //UPDATE
    public boolean update(String Table,HashMap<String,Object> ColumnValues,String Condition) throws Exception{
        StringBuilder query=new StringBuilder("UPDATE "+Table+" SET ");
        for(String key:ColumnValues.keySet()){
            query.append(key+"='"+ColumnValues.get(key).toString()+"',");
        }
        query.delete(query.length()-1, query.length());
        this.AddCondition(query, Condition);
        query.append(";");
        return this.connect.executeUpdate(query.toString())>0;
    }
    //DELETE
    public boolean delete(String Table,String Condition) throws Exception{
        StringBuilder query=new StringBuilder("DELETE FROM "+Table);
        this.AddCondition(query, Condition);
        query.append(";");
        return this.connect.executeUpdate(query.toString())>0;
    }
    
    public static int getColumnCount(ResultSet rs) throws SQLException{
        return rs.getMetaData().getColumnCount();
    }
    public static String[] getColumnName(ResultSet rs) throws SQLException{
        ResultSetMetaData rsMetaData=(ResultSetMetaData) rs.getMetaData();
        int ColumnCount=rsMetaData.getColumnCount();
        String[] list=new String[ColumnCount];
        for(int i=0;i<list.length;i++){
            list[i]=rsMetaData.getColumnName(i);
        }
        return list;
    }
    public void Close() throws SQLException{
        this.connect.Close();
    }
}
