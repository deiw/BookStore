package pl.majorczyk.mybookstore.model;

public class Cart {
    private String title;
    private int amount;
    private double price;
    public Cart(){}
    public Cart(String tittle,int amount,double price){
        this.title =tittle;
        this.amount=amount;
        this.price=price;
    }
    public Cart(Cart cart){
        this.title =cart.title;
        this.amount=cart.amount;
        this.price=cart.price;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
