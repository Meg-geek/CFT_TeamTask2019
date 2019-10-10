package shop;

import customer.Customer;
import item.Item;
import order.Order;
import util.Pair;

public interface Shop {
    Order createOrder(Customer customer);
    //List<String> getItemsNames();
    Pair<Item, Integer> getItem(String itemName, int amount);
    void returnItem(Item item, int amount);
}
