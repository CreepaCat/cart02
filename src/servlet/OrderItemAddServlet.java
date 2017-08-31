package servlet;

//import com.sun.org.apache.xpath.internal.operations.String;

import bean.OrderItem;
import bean.Product;
import dao.ProductDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import java.sql.SQLException;

public class OrderItemAddServlet extends HttpServlet{
    public void service(HttpServletRequest request, HttpServletResponse response)
                 throws ServletException,IOException {
        int num = Integer.parseInt(request.getParameter("num"));
        int pid =Integer.parseInt(request.getParameter("pid"));
        //没DAO出product,错误原因已解决，ProductDAO.getProduct()出错
        Product product = new ProductDAO().getProduct(pid);
        OrderItem oi = new OrderItem();
       // oi.setId(pid);
        //oi.setId();
        oi.setNum(num);
        oi.setProduct(product);

        //获取会话中的ois值,赋于ois;
        List<OrderItem> ois =(List<OrderItem>)request.getSession().getAttribute("ois");
        //检查是否为空,若是则创建并加入session中
        if(null == ois){
            ois = new ArrayList<OrderItem>();
            request.getSession().setAttribute("ois",ois);
           // ois.add()
        }
        //遍历
        boolean found = false;
        for (OrderItem o : ois){
           // System.out.println("测试：");
           // System.out.println("o,oi,o.getProduct(),oi.getProduct()"+o+" "+oi+" "+o.getProduct()+" "+oi.getProduct());
            if(o.getProduct().getId() == oi.getProduct().getId()){
                o.setNum(o.getNum() + oi.getNum());
                found = true;
                break;
            }
        }

        if(!found) ois.add(oi);
        response.sendRedirect("/listOrderItem");
    }

}
