package customer;

import com.sun.tools.javac.util.Pair;
import item.Item;
import order.Order;
import shop.Shop;

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
        if(curShop == null){
            curShop = shop;
            Order order = curShop.createOrder(this);
            orderShopList.add(new Pair<>(order, shop));
            return order;
        }
        return null;
    }

    @Override
    public Order getCurOrder() {
        if(curShop == null){
            return null;
        }
        if(orderShopList.size() > 0){
            return orderShopList.get(orderShopList.size() - 1).fst;
        }
        return null;
    }

    @Override
    public void addItemInOrder(Item item, int amount) {

    }

    @Override
    public void removeItemInOrder(Item item, int amount) {

    }

    @Override
    public void removeItemInOrder(Item item) {

    }

    @Override
    public Order makeOrder() {

    }
}
