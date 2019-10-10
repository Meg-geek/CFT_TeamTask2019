package shop;

import customer.Customer;
import item.Item;
import order.Order;
import shop.shopException.ShopException;
import util.Pair;

public interface Shop {
    Order createOrder(Customer customer);
    //List<String> getItemsNames();
    Pair<Item, Integer> getItem(String itemName, int amount) throws ShopException;
    void returnItem(Item item, int amount);
}
