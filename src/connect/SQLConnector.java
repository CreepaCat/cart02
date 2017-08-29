package connect;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnector {
    Connection conn = null ;
    public  SQLConnector() {
        try {
            //加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获得数据库连接对象
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/cart?characterEncoding=UTF-8",
                    "root", "123");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public Connection getConnect(){
        return conn;
    }
}
