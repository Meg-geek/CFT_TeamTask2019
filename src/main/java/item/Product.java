package item;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product implements Item {
    @Id
    private int id;

    @Column
    private String name;

    @Column
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double newPrice){
        price = newPrice;
    }

    public void setName(String newName){
        name = newName;
    }

    public String getName() {
        return name;
    }
}
