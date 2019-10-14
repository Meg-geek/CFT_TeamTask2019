package shop;

import customer.Customer;
import item.Item;
import order.Order;
import order.ShoppingBasket;
import util.Pair;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Entity
public class MiniShop implements Shop {
    @Id
    private int id;

    private List<Pair<Customer, Order>> customerOrderList;

    private Map<Item, Integer> itemAmountMap;

    public MiniShop(){
        itemAmountMap = new ConcurrentHashMap<>();
        customerOrderList = Collections.synchronizedList(new ArrayList<>());
    }
    public int getId(){
        return id;
    }

    @Override
    public Order createOrder(Customer customer) {
        Order order = new ShoppingBasket();
        customerOrderList.add(new Pair<>(customer, order));
        return order;
    }


    //@Override
    private List<String> getItemsNames() {
        List <String> names = new ArrayList<>();
        for(Map.Entry<Item, Integer> itemAmount: itemAmountMap.entrySet()){
            names.add(itemAmount.getKey().getName());
        }
        return names;
    }

    @Override
    public Pair<Item, Integer> getItem(String itemName, int amount){
        Pair<Item, Integer> itemIntPair = null;
        boolean itemEnded = false;
        for(Map.Entry<Item, Integer> itemAmount: itemAmountMap.entrySet()){
            if(itemAmount.getKey().getName().equalsIgnoreCase(itemName)){
                Item item = itemAmount.getKey();
                int oldValue = itemAmount.getValue();
                if(oldValue < amount){
                    itemAmount.setValue(0);
                    itemEnded = true;
                    itemIntPair = new Pair<>(item, oldValue);
                } else {
                    itemAmount.setValue(oldValue - amount);
                    itemIntPair = new Pair<>(item, amount);
                }
            }
        }
        if(itemIntPair == null){
            return null;
        }
        if(itemEnded){
            itemAmountMap.remove(itemIntPair.getLeft());
        }
        return itemIntPair;
    }

    @Override
    public void returnItem(Item item, int amount) {
        if(!itemAmountMap.containsKey(item)){
            itemAmountMap.put(item, amount);
        } else {
            itemAmountMap.replace(item, itemAmountMap.get(item) + amount);
        }
    }

    public void addItems(Item item, int amount){
        returnItem(item, amount);
    }
}
