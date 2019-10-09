package order;

import customer.Customer;
import item.Item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
public class ShoppingBasket implements Order {
    @Id
    private int id;

    @Column
    private Date date;

    @ManyToOne
    private Customer customer;

    private Map<Item, Integer> items;

    public ShoppingBasket(){
        items = new HashMap<>();
    }

    public void addItem(Item item) {
        items.merge(item, 1, Integer::sum);
    }

    public Map<Item, Integer> getItems() {
        return items;
    }

    public void removeItem(Item item) {

    }

    public double getItemsSum() {
        return 0;
    }
}
