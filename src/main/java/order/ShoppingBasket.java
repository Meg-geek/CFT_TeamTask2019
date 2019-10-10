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
    }

    @Override
    public void addItem(Item item) {
        itemsMap.merge(item, 1, Integer::sum);
    }

    @Override
    public Map<Item, Integer> getItems() {
        return itemsMap;
    }

    @Override
    public void removeItems(Item item, int amount) {
        Integer curAmount = itemsMap.get(item);
        if(curAmount != null && curAmount > amount){
            itemsMap.replace(item, curAmount, curAmount - amount);
        }
        if(curAmount != null && curAmount == amount){
            removeItems(item);
        }
    }

    @Override
    public void removeItems(Item item) {
        itemsMap.remove(item);
    }

    @Override
    public double getItemsSum() {
        double sum = 0;
        for(Map.Entry<Item, Integer> itemAmount : itemsMap.entrySet()){
            sum+=itemAmount.getKey().getPrice()*itemAmount.getValue();
        }
        return 0;
    }

    public int getId(){
        return id;
    }

    public Customer getCustomer(){
        return customer;
    }

    public void setCustomer(Customer customer){
        this.customer = customer;
    }

    public Date getDate(){
        return date;
    }

    public void setDate(Date date){
        this.date = date;
    }
}
