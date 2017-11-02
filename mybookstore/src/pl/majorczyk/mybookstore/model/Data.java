package pl.majorczyk.mybookstore.model;

public class Data {
    private String title;
    private String amount;
    private String price;
    public Data(){ }
    public Data(String title,String amount,String price){
        this.title =title;
        this.amount=amount;
        this.price=price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAmount() {
        return amount;
    }

    public String getPrice() {
        return price;
    }
}
