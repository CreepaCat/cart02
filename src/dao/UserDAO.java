package dao;

import bean.User;
import connect.ConnectClose;
import connect.SQLConnector;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection conn = null;
    private PreparedStatement psmt = null;
    private ResultSet rs = null;
    private boolean flag = false;
    private String list_sql = "select * from user";
    private String verify_sql = "select * from user where name = ? and password = ?";

    public static void main(String[] args) {
        //for test
        System.out.println(new UserDAO().ListUser().size());
    }
    public boolean verify(User user){
        String name = user.getName();
        String password = user.getPassword();
        try {
            conn = new SQLConnector().getConnect();
            psmt = conn.prepareStatement(verify_sql);
            psmt.setString(1,name);
            psmt.setString(2,password);
            rs = psmt.executeQuery();
            if (rs.next()){
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


    public List<User> ListUser() {
        List<User> users = new ArrayList<User>();
        try{
            conn = new SQLConnector().getConnect();
            psmt = conn.prepareStatement(list_sql);
            rs = psmt.executeQuery();
            while(rs.next()){
                User u = new User();
                u.setId(rs.getInt(1));
                u.setName(rs.getString(2));
                u.setPassword(rs.getString(3));
                users.add(u);
            }
        }catch(Exception e){
            System.out.println("数据库操作异常："+e.getMessage());
            e.printStackTrace();
        }finally {
            new ConnectClose(conn,psmt,rs);
        }
        return users;
    }

}
