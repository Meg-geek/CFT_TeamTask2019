package order;

import item.Item;

import java.util.List;

public interface Order {
    void addItem(Item item);
    List<Item>  getItems();
    void deleteItem(Item item);
    double getItemsSum();
}
