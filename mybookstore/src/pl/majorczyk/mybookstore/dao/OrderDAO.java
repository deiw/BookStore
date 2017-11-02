package pl.majorczyk.mybookstore.dao;


import pl.majorczyk.mybookstore.model.Order;

import java.util.List;

public interface OrderDAO {
        Order create(Order order);
        List<Order> readAllOrders();
        List<Order> readActiveOrders();
        List<Order> readUserOrders(String email,boolean active);
        void updateStatus(long id);

}
