package dao;

import bean.User;

import connect.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserVerifyDAO {

    private Connection conn = null;
    private PreparedStatement psmt = null;
    private ResultSet rs = null;
    private String sql = "select * from user where name = ? and password = ?";
    private boolean flag = false;
    public boolean verify(User user){
        String name = user.getName();
        String password = user.getPassword();
        try {
            conn = new SQLConnector().getConnect();
            psmt = conn.prepareStatement(sql);
            psmt.setString(1,name);
            psmt.setString(2,password);
            rs = psmt.executeQuery();
            while (rs.next()){
                flag = true;//验证成功;
            }
        }catch (Exception e){
             System.out.println("用户验证异常："+e.getMessage());
             e.printStackTrace();
        }finally {
            new ConnectClose(conn, psmt, rs);
        }
        return flag;
    }
}
