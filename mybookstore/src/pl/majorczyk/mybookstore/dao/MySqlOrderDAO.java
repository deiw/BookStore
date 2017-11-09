package pl.majorczyk.mybookstore.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import pl.majorczyk.mybookstore.model.Order;
import pl.majorczyk.mybookstore.model.User;
import pl.majorczyk.mybookstore.util.ConnectionProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MySqlOrderDAO implements OrderDAO {

    private static final String CREATE="INSERT INTO orders(email,titles,total,odate) " +
            "VALUES(:email,:titles,:total,:odate);";
    private static final String READ_ALL_ORDERS="SELECT * FROM user JOIN orders ON orders.email=user.email;";



    private NamedParameterJdbcTemplate template;

    public MySqlOrderDAO(){
        template=new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }

    @Override
    public Order create(Order order) {
        Order resultOrder=new Order(order);
        KeyHolder keyHolder=new GeneratedKeyHolder();
        SqlParameterSource parameterSource=new BeanPropertySqlParameterSource(order);
        int rowAffected=template.update(CREATE,parameterSource,keyHolder);
        if(rowAffected>0){
            resultOrder.setId((long)keyHolder.getKey());
        }
        return resultOrder;
    }

    @Override
    public List<Order> readAllOrders() {
        List<Order> ordersList=template.query(READ_ALL_ORDERS,new OrderRowMapper());
        return ordersList;
    }

    @Override
    public List<Order> readActiveOrders() {
        final String activeOrdersSQL="SELECT * FROM user JOIN orders ON orders.email=user.email WHERE active=true;";
        List<Order> activeOrdersList=template.query(activeOrdersSQL,new OrderRowMapper());
        return activeOrdersList;
    }


    @Override
    public List<Order> readUserOrders(String email,boolean active) {
        final String userOrdersSQL="SELECT * FROM orders WHERE email=:email AND active=:active;";
        Map<String,Object> map=new HashMap<>();
        map.put("email",email);
        map.put("active",active);
        SqlParameterSource parameterSource=new MapSqlParameterSource(map);
        List<Order> userActiveOrders=template.query(userOrdersSQL,parameterSource, new BeanOrderRowMapper());
        return userActiveOrders;
    }

    @Override
    public void updateStatus(long id) {
        final String changeToDoneSQL="UPDATE orders SET active=false WHERE order_id=:id";
        SqlParameterSource parameterSource=new MapSqlParameterSource("id",id);
        template.update(changeToDoneSQL,parameterSource);
    }
    private class BeanOrderRowMapper implements RowMapper<Order>{
        @Override
        public Order mapRow(ResultSet resultSet, int i) throws SQLException {
            Order order=new Order();
            order.setId(resultSet.getLong("order_id"));
            order.setEmail(resultSet.getString("email"));
            order.setTitles(resultSet.getString("titles"));
            order.setTotal(resultSet.getDouble("total"));
            order.setActive(resultSet.getBoolean("active"));
            order.setOdate(resultSet.getTimestamp("odate"));

            return order;
        }
    }

    private class OrderRowMapper implements RowMapper<Order>{
        @Override
        public Order mapRow(ResultSet resultSet, int i) throws SQLException {
            Order order=new Order();
            order.setId(resultSet.getLong("order_id"));
            order.setEmail(resultSet.getString("email"));
            order.setTitles(resultSet.getString("titles"));
            order.setTotal(resultSet.getDouble("total"));
            order.setActive(resultSet.getBoolean("active"));
            order.setOdate(resultSet.getTimestamp("odate"));
            User user=new User();
            user.setId(resultSet.getLong("user_id"));
            user.setFirstname(resultSet.getString("firstname"));
            user.setLastname(resultSet.getString("lastname"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
            user.setStreet(resultSet.getString("street"));
            user.setCity(resultSet.getString("city"));
            order.setUser(user);
            return order;
        }
    }
}
