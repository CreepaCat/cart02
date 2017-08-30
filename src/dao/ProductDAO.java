package dao;

import bean.Product;
import connect.ConnectClose;
import connect.SQLConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection conn=null;
    private PreparedStatement psmt=null;
    private ResultSet rs=null;
    public static void main(String[] args) {

        System.out.println(new ProductDAO().ListProduct().size());

    }
    public Product getProduct(int id){
        Product result = new Product();
        try{
            String sql = "select * from product where id = ?";
            conn = new SQLConnector().getConnect();
            psmt = conn.prepareStatement(sql);
            psmt.setInt(1, id);
            rs = psmt.executeQuery();
            if(rs.next()){
                result.setId(id);
                result.setName(rs.getString(2));
                result.setPrice(rs.getFloat(3));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            new ConnectClose(conn, psmt, rs);
        }
        return result;

    }
    public List<Product> ListProduct(){
        List<Product> products = new ArrayList<Product>();

        //ConnectClose close = new ConnectClose();

        String sql = "select * from product order by id desc";
        try {
            conn = new SQLConnector().getConnect();
            psmt = conn.prepareStatement(sql);
             rs = psmt.executeQuery();
            while (rs.next()){
                Product prod = new Product();
                prod.setId(rs.getInt(1));
                prod.setName(rs.getString(2));
                prod.setPrice(rs.getFloat(3));
                products.add(prod);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            new ConnectClose(conn, psmt, rs);
        }
        return products;
    }

}
