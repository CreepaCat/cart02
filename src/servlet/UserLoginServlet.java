package servlet;

import bean.User;
import dao.UserDAO;
//import dao.UserVerifyDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      User user = new User();
      UserDAO uv = new UserDAO();
      user.setName(request.getParameter("name"));
      user.setPassword(request.getParameter("password"));
      if(uv.verify(user)){
          //登录成功
          request.getSession().setAttribute("user",user);
          response.sendRedirect("/listProduct");
      }else {
          //登陆失败
          response.sendRedirect("/login");//servlet配置路径
      }
    }


}
