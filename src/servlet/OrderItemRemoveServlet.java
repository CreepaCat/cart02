package servlet;

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
/*
*已出订单项
 */
public class OrderItemRemoveServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException,IOException {
        int pid =Integer.parseInt( request.getParameter("pid"));
        Product p = new ProductDAO().getProduct(pid);
        List<OrderItem> ois = (List<OrderItem>) request.getSession().getAttribute("ois");
        List<OrderItem> oi4Remove = new ArrayList<>();
        if(null!=ois){
            for (OrderItem oi : ois) {
                if (oi.getProduct().getId() == pid){
                    oi4Remove.add(oi);
                }
            }
            ois.removeAll(oi4Remove);
            response.sendRedirect("/listOrderItem");
        }else{
            String error ="订单项为空";
            request.getSession().setAttribute("error",error);
            response.sendRedirect("/error.jsp");
        }
    }
}
