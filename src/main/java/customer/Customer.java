package customer;

import item.Item;
import order.Order;
import shop.Shop;

import java.util.List;

public interface Customer {
    List<String> getCustomerInfo();

    Order createOrder(Shop shop);
    Order getCurOrder();
    boolean addItemInOrder(Item item, int amount);
    //remove fixed amount
    boolean removeItemInOrder(Item item, int amount);
    //remove all item products
    boolean removeItemInOrder(Item item);
    Order makeOrder();
}
