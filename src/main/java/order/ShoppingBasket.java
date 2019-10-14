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
import java.util.concurrent.atomic.AtomicInteger;

@Entity
public class ShoppingBasket implements Order {
    @Id
    private int id;

    private static AtomicInteger atomicId = new AtomicInteger(0);

    @Column
    private Date date;

    @ManyToOne
    private Customer customer;

    /*
    * Если отображать на базу данных, то я сделала бы
    * таблицу Order-Item со столбцами:
    * orderID, itemID, amount
    * где orderID, itemID - внешние ключи,
    * amount - количество товара данной категории
    * */
    private Map<Item, Integer> itemsMap;

    public ShoppingBasket(){
        itemsMap = new HashMap<>();
        date = new Date();
        id = atomicId.getAndIncrement();
    }

    @Override
    public boolean addItem(Item item, int amount) {
        return (itemsMap.merge(item, amount, Integer::sum) != null);
    }

    @Override
    public Map<Item, Integer> getItems() {
        return itemsMap;
    }

    @Override
    public boolean removeItems(Item item, int amount) {
        Integer curAmount = itemsMap.get(item);
        if(curAmount != null && curAmount > amount){
            return itemsMap.replace(item, curAmount, curAmount - amount);
        }
        if(curAmount != null){
            return removeItems(item);
        }
        return false;
    }

    @Override
    public boolean removeItems(Item item) {
        return (itemsMap.remove(item) != null);
    }

    @Override
    public double getItemsSum() {
        double sum = 0;
        for(Map.Entry<Item, Integer> itemAmount : itemsMap.entrySet()){
            sum+=itemAmount.getKey().getPrice()*itemAmount.getValue();
        }
        return sum;
    }

    @Override
    public int getId(){
        return id;
    }

    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    @Override
    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }
}
