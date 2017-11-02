package pl.majorczyk.mybookstore.service;

import pl.majorczyk.mybookstore.dao.FactoryDAO;
import pl.majorczyk.mybookstore.dao.OrderDAO;
import pl.majorczyk.mybookstore.model.Cart;
import pl.majorczyk.mybookstore.model.Order;
import pl.majorczyk.mybookstore.model.User;


import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class OrderService {
       public void addOrder(List<Cart> cartList, User user){
           Order order=new Order();
           order.setEmail(user.getEmail());
           order.setTitles(makeString(cartList,order));
           order.setOdate(new Timestamp(new Date().getTime()));

           FactoryDAO factory=FactoryDAO.getFactory(FactoryDAO.MY_SQL);
           OrderDAO orderDAO=factory.getOrderDAO();
           orderDAO.create(order);

       }

       private String makeString(List<Cart> cartList,Order order){
           String cartString=null;
           StringBuffer sb=new StringBuffer();
           double sum=0;
           for (Cart c:cartList){
                 sb.append(c.getTitle()+"+"+c.getAmount()+"+"+c.getPrice()+"/");
                 sum+=c.getPrice();
           }
           cartString=sb.toString();
           order.setTotal(sum);
           return cartString;
    }
    public List<Order> getAllOrders(){
           FactoryDAO factory=FactoryDAO.getFactory(FactoryDAO.MY_SQL);
           OrderDAO orderDAO=factory.getOrderDAO();
           List<Order> allOrders=orderDAO.readAllOrders();
           return allOrders.stream()
                   .sorted(Comparator.comparing(Order::getOdate))
                   .collect(Collectors.toList());
    }
    public List<Order>getActiveOrders(){
        FactoryDAO factory=FactoryDAO.getFactory(FactoryDAO.MY_SQL);
        OrderDAO orderDAO=factory.getOrderDAO();
        List<Order> activeOrdersList=orderDAO.readActiveOrders();
        return activeOrdersList.stream()
                .sorted(Comparator.comparing(Order::getOdate))
                .collect(Collectors.toList());
    }
   public void makeOrderCompleted(long id){
        FactoryDAO factory=FactoryDAO.getFactory(FactoryDAO.MY_SQL);
        OrderDAO orderDAO=factory.getOrderDAO();
        orderDAO.updateStatus(id);
   }
   public List<Order> getUserOrders(String email,boolean active){
       FactoryDAO factoryDAO=FactoryDAO.getFactory(FactoryDAO.MY_SQL);
       OrderDAO orderDAO=factoryDAO.getOrderDAO();
       List<Order> historyUserList=orderDAO.readUserOrders(email,active);
       return historyUserList.stream()
               .sorted(Comparator.comparing(Order::getOdate))
               .collect(Collectors.toList());
   }


}
