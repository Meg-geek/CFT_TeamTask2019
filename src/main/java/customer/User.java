package customer;

import order.Order;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class User implements Customer {
    @Id
    private int id;

    @OneToMany
    private List<Order> orders;


}
