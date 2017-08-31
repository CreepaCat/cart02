package dao;

import bean.Order;
import connect.ConnectClose;
import connect.SQLConnector;

import java.sql.*;

public class OrderDAO {
    private Connection conn=null;
    private PreparedStatement psmt=null;
    private ResultSet rs=null;
    public void insert(Order order){
        try{
            String sql = "insert into order_ values (null,?)";
            conn = new SQLConnector().getConnect();
            psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            psmt.setInt(1, order.getUser().getId());

            psmt.execute();
            //获取自增键,赋予结果集
            rs = psmt.getGeneratedKeys();

            if(rs.next()){
                //获得第一列的自增键
                int id = rs.getInt(1);
                order.setId(id);
            }

        }
        catch (SQLException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            new ConnectClose(conn,psmt,rs);
        }
    }
}
