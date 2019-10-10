package order;

import item.Item;
import java.util.Map;

public interface Order {
    void addItem(Item item, int amount);
    Map<Item, Integer> getItems();
    /*
    * remove fixed amount of Items
    * @param item item to remove
    * @param amount amount of item to remove
    * @return {@code true} if the fixed amount of item was removed
    * */
    boolean removeItems(Item item, int amount);
    /*
    remove ALL items item
    @param item item to remove
    @return {@code true} if the item was removed
    * */
    boolean removeItems(Item item);
    double getItemsSum();
}
