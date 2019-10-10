package shop;

import customer.Customer;
import item.Item;
import order.Order;

import java.util.List;
import java.util.Map;

public interface Shop {
    Order createOrder(Customer customer);
    List<Item> getItems();
    Item getItem(String itemName);
}
