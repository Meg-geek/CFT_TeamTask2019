package customer;

import item.Item;
import order.Order;
import shop.Shop;

import java.util.List;

public interface Customer {
    List<String> getCustomerInfo();

    Order createOrder(Shop shop);
    Order getCurOrder();
    void addItemInOrder(Item item, int amount);
    //remove fixed amount
    void removeItemInOrder(Item item, int amount);
    //remove all item products
    void removeItemInOrder(Item item);
    Order makeOrder();
}
