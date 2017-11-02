package pl.majorczyk.mybookstore.controller;



import pl.majorczyk.mybookstore.model.Cart;
import pl.majorczyk.mybookstore.service.CartService;
import pl.majorczyk.mybookstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cart> cartList = (List<Cart>) request.getSession(false).getAttribute("cartList");
        String action=request.getParameter("action");
        String title = request.getParameter("title");
        String id=request.getParameter("id");
        double price = Double.valueOf(request.getParameter("price"));
        int amount = Integer.valueOf(request.getParameter("amount"));
        CartService cartService=new CartService();
        Cart cart=cartService.getCartToOrder(title,price,amount);

        if(action!=null) {
            if ("remove".equals(action)) {
                Cart rCart = new Cart(cart);
                rCart.setPrice(cart.getPrice() / cart.getAmount());
                cartList = cartService.getListAfterRemove(cartList, rCart);
                setAttributes(request, cartList, cartService);
                response.sendRedirect(getServletContext().getContextPath() + "/cart");
            } else {
                cartList.add(cart);
                setAttributes(request, cartList, cartService);
                response.sendRedirect(getServletContext().getContextPath() + "/book?id="+id);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           request.getRequestDispatcher("WEB-INF/cart.jsp").forward(request,response);
    }

    private void setAttributes(HttpServletRequest request,List<Cart> cartList,CartService cartService){
        double sum=cartService.getTotalSum(cartList);

        request.getSession(false).setAttribute("sum", sum);
        request.getSession(false).setAttribute("cartList", cartList);
    }
}
