package customer;

import item.Item;
import order.Order;
import shop.Shop;
import util.Pair;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User implements Customer {
    @Id
    private int id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String patronymic;

    private List<Pair<Order, Shop>> orderShopList;

    private Shop curShop;
    private Order curOrder;

    public User(){
        orderShopList = new ArrayList<>();
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getSurname(){
        return surname;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public String getPatronymic(){
        return patronymic;
    }

    public void setPatronymic(String patronymic){
        this.patronymic = patronymic;
    }

    @Override
    public List<String> getCustomerInfo() {
        List<String> info = new ArrayList<>();
        info.add(name);
        info.add(surname);
        info.add(patronymic);
        return info;
    }

    @Override
    public Order createOrder(Shop shop) {
        if(curOrder == null){
            curShop = shop;
            curOrder = curShop.createOrder(this);
            orderShopList.add(new Pair<>(curOrder, shop));
            return curOrder;
        }
        return null;
    }

    @Override
    public Order getCurOrder() {
        return curOrder;
    }

    @Override
    public boolean addItemInOrder(Item item, int amount) {
        if(curOrder != null){
            return curOrder.addItem(item, amount);
        }
        return false;
    }

    @Override
    public boolean removeItemInOrder(Item item, int amount) {
        if(curOrder != null){
            return curOrder.removeItems(item, amount);
        }
        return false;
    }

    @Override
    public boolean removeItemInOrder(Item item) {
        if(curOrder != null){
            return curOrder.removeItems(item);
        }
        return false;
    }

    @Override
    public Order makeOrder() {
        Order madeOrder = curOrder;
        curOrder = null;
        curShop = null;
        return madeOrder;
    }
}


