package order;

import item.Item;
import java.util.Map;

public interface Order {
    void addItem(Item item);
    Map<Item, Integer> getItems();
    void removeItem(Item item);
    double getItemsSum();
}
