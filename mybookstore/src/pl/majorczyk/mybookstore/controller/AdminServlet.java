package pl.majorczyk.mybookstore.controller;

import pl.majorczyk.mybookstore.model.Book;
import pl.majorczyk.mybookstore.model.Data;
import pl.majorczyk.mybookstore.model.Order;
import pl.majorczyk.mybookstore.model.User;
import pl.majorczyk.mybookstore.service.BookService;
import pl.majorczyk.mybookstore.service.OrderService;
import pl.majorczyk.mybookstore.service.UserService;
import pl.majorczyk.mybookstore.util.DataIsolator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        OrderService orderService = new OrderService();
        long orderId = Long.valueOf(request.getParameter("order"));
        orderService.makeOrderCompleted(orderId);
        response.sendRedirect("/admin");

    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");

        if("activeOrders".equals(action)) {
            OrderService orderService = new OrderService();
            DataIsolator isolator=new DataIsolator();
            List<Order> activeOrders = orderService.getActiveOrders();
            List<Data> datas = isolator.getData(activeOrders);
            request.setAttribute("datas", datas);
            request.setAttribute("activeOrders", activeOrders);
        }
        else if("booksList".equals(action)){
            BookService bookService=new BookService();
            List<Book> booksList=bookService.getAllBooks();
            request.setAttribute("booksList",booksList);
        }
        else if("usersList".equals(action)){
            UserService userService=new UserService();
            List<User> usersList=userService.getAllUsers();
            request.setAttribute("usersList",usersList);
        }
        request.getRequestDispatcher("WEB-INF/admin.jsp").forward(request,response);
    }
}
