package pl.majorczyk.mybookstore.filter;

import pl.majorczyk.mybookstore.model.Cart;
import pl.majorczyk.mybookstore.model.User;
import pl.majorczyk.mybookstore.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest)req;
        if(request.getUserPrincipal()!=null&&request.getSession().getAttribute("user")==null){
           saveUserInSession(request);
        }
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }
    private void saveUserInSession(HttpServletRequest request){
           String email=request.getUserPrincipal().getName();
           UserService userService=new UserService();
           User user= userService.getUserByEmail(email);
           List<Cart> cartList=new ArrayList<>();
           double sum=0;
           request.getSession(true).setAttribute("user",user);
           request.getSession(false).setAttribute("cartList",cartList);
           request.getSession(false).setAttribute("sum",sum);
    }

}
