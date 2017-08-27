package connect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectClose {
    public void ConnectClose(Connection conn, PreparedStatement psmt, ResultSet rs){
        try{
            if(rs != null){
                rs.close();
                rs = null;
            }else if(psmt != null){
                psmt.close();
               psmt = null;
            }else if(conn != null){
                conn.close();
                conn = null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
