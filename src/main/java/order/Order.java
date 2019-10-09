package order;

import item.Item;
import java.util.Map;

public interface Order {
    void addItem(Item item);
    Map<Item, Integer> getItems();
    //remove fixed amount of Items
    void removeItems(Item item, int amount);
    //remove ALL items
    void removeItems(Item item);
    double getItemsSum();
}
