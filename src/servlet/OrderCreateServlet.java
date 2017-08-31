package servlet;

import bean.Order;
import bean.OrderItem;
import bean.User;
import dao.OrderDAO;
import dao.OrderItemDAO;
import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderCreateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();
        //if(userDAO.verify())
        User u = (User)req.getSession().getAttribute("user");
        if(null == u){
            resp.sendRedirect("login.jsp");
            return;
        }
        Order o = new Order();
        o.setUser(u);
        System.out.println("o: /n"+o);
        new OrderDAO().insert(o);
        List<OrderItem> ois = (List<OrderItem>)req.getSession().getAttribute("ois");

        for (OrderItem oi:ois){
            oi.setOrder(o);
            System.out.println("oi: /n"+oi);
            new OrderItemDAO().insert(oi);
        }
        //清空自增键
        ois.clear();
        resp.setContentType("text/html;charset=utf-8");
        resp.getWriter().println("订单创建成功");


    }


}
