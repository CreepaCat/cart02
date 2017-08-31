package dao;

import bean.OrderItem;
import connect.ConnectClose;
import connect.SQLConnector;

import java.sql.*;

public class OrderItemDAO {
    private Connection conn=null;
    private PreparedStatement psmt=null;
    private ResultSet rs=null;
    public void insert(OrderItem oi){
        try{
            String sql = "insert into orderitem values(null,?,?,?)";
            conn = new SQLConnector().getConnect();
            psmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            psmt.setInt(1,oi.getProduct().getId());
            psmt.setInt(2,oi.getOrder().getId());
            psmt.setInt(3,oi.getNum());
            psmt.execute();
            /*
            rs = psmt.getGeneratedKeys();
            //System.out.println("结果集：/n"+rs);
            if(rs.next()){
                int id = rs.getInt(1);
                oi.setId(id);

            }*/
        }catch(SQLException e){
            e.printStackTrace();
        }finally {
            new ConnectClose(conn,psmt,rs);
        }
    }

}
