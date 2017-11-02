package pl.majorczyk.mybookstore.service;

import pl.majorczyk.mybookstore.model.Cart;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CartService {
    public Cart getCartToOrder(String title, double price, int amount){
        Cart cart=new Cart();

        BigDecimal totalCost=BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP);
        BigDecimal itemPrice=new BigDecimal(price).setScale(2,RoundingMode.HALF_UP);
        BigDecimal itemCost=itemPrice.multiply(new BigDecimal(amount).setScale(2,RoundingMode.HALF_UP));
        totalCost=totalCost.add(itemCost);
        cart.setTitle(title);
        cart.setAmount(amount);
        cart.setPrice(totalCost.doubleValue());

        return cart;
    }
    public double getTotalSum(List<Cart> cartList){
        BigDecimal sum=BigDecimal.ZERO.setScale(2,RoundingMode.HALF_UP);
        for (Cart c : cartList) {
            BigDecimal itemCost=new BigDecimal(c.getPrice()).setScale(2,RoundingMode.HALF_UP);
            sum=sum.add(itemCost);
        }
        return sum.doubleValue();
    }
    public List<Cart> getListAfterRemove(List<Cart> cartList,Cart cart){
        for(int i=0;i<cartList.size();i++){
            if(cartList.get(i).getTitle().equals(cart.getTitle())&&
                    cartList.get(i).getAmount()==cart.getAmount()&&
                    cartList.get(i).getPrice()==cart.getPrice()){
                cartList.remove(i);
            }
        }
        return cartList;
    }
}
