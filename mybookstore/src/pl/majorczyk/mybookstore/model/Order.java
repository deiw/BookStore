package pl.majorczyk.mybookstore.model;

import java.sql.Timestamp;

public class Order {
    private long id;
    private String email;
    private String titles;
    private double total;
    private boolean active;
    private Timestamp odate;
    private User user;

    public Order() {
    }
    public Order(User user){
        this.user=user;
    }
    public Order(Order order){
        this.id=order.id;
        this.email=order.email;
        this.titles =order.titles;
        this.total=order.total;
        this.active=order.active;
        this.odate=order.odate;
        this.user=order.user;
    }

    public Timestamp getOdate() {
        return odate;
    }

    public void setOdate(Timestamp odate) {
        this.odate = odate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isActive() {
        return active;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getTitles() {
        return titles;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
