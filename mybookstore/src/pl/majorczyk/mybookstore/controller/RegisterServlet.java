package pl.majorczyk.mybookstore.controller;

import org.springframework.dao.DuplicateKeyException;
import pl.majorczyk.mybookstore.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String firstname=request.getParameter("firstname");
        String lastname=request.getParameter("lastname");
        String password=request.getParameter("password");
        String email=request.getParameter("email");
        String street=request.getParameter("street");
        String city=request.getParameter("city");

        try {
            UserService userService=new UserService();
            userService.addUser(firstname,lastname,password,email,street,city);
            response.sendRedirect(getServletContext().getContextPath()+"/");
        }catch (DuplicateKeyException e){
            request.setAttribute("message","User already exists!");
            request.getRequestDispatcher("WEB-INF/register.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.getRequestDispatcher("WEB-INF/register.jsp").forward(request,response);
    }
}
