package pl.majorczyk.mybookstore.controller;

import pl.majorczyk.mybookstore.model.Cart;
import pl.majorczyk.mybookstore.model.Data;
import pl.majorczyk.mybookstore.model.Order;
import pl.majorczyk.mybookstore.model.User;
import pl.majorczyk.mybookstore.service.OrderService;
import pl.majorczyk.mybookstore.util.DataIsolator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/my")
public class AccountServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cart> cartList = (List<Cart>) request.getSession(false).getAttribute("cartList");
        User user = (User) request.getSession(false).getAttribute("user");
        String action = request.getParameter("action");
        OrderService orderService = new OrderService();
        if (action != null) {
            if ("makeOrder".equals(action)) {
                orderService.addOrder(cartList, user);
                cartList.clear();
                double sum = 0;
                request.getSession(false).setAttribute("cartList", cartList);
                request.getSession(false).setAttribute("sum", sum);
                response.sendRedirect(getServletContext().getContextPath() + "/");
            }
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action=request.getParameter("action");
        User user = (User) request.getSession(false).getAttribute("user");
        OrderService orderService = new OrderService();
        DataIsolator isolator=new DataIsolator();
        List<Data> dataList=null;
        if(action!=null){
            if("history".equals(action)){
                List<Order> historyOrders=orderService.getUserOrders(user.getEmail(),false);
                dataList=isolator.getData(historyOrders);
                request.setAttribute("datas",dataList);
                request.setAttribute("historyOrders",historyOrders);
                request.getRequestDispatcher("WEB-INF/my.jsp").forward(request,response);
            }
            else if("currentOrders".equals(action)) {
                List<Order> activeOrders=orderService.getUserOrders(user.getEmail(),true);
                dataList=isolator.getData(activeOrders);
                request.setAttribute("datas",dataList);
                request.setAttribute("activeOrders",activeOrders);
                request.getRequestDispatcher("WEB-INF/my.jsp").forward(request,response);
            }
        }
        request.getRequestDispatcher("WEB-INF/my.jsp").forward(request,response);
    }
}
