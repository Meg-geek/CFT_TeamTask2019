package shop;

import customer.Customer;
import order.Order;

public interface Shop {
    Order createOrder(Customer customer);
}
