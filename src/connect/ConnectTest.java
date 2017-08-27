package connect;

//import java.sql.SQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectTest {
    public ConnectTest(){
        Connection conn = new SQLConnector().getConnect();

        String sql = "select * from product order by id desc";

        try{
            if(conn == null){ throw new SQLException("conn is null!");}
         PreparedStatement psmt =conn.prepareStatement(sql);
         ResultSet rs = psmt.executeQuery();
         while (rs.next()) {
             System.out.println(rs.getInt(1));
             System.out.println(rs.getString(2));
             System.out.println(rs.getFloat(3));
         }
         System.out.println("连接成功");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConnectTest ct = new ConnectTest();
    }
}
