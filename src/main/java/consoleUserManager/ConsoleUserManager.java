package consoleUserManager;

import customer.Customer;
import customer.User;
import item.Item;
import order.Order;
import shop.Shop;
import util.Pair;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.Scanner;

public class ConsoleUserManager implements UserManager{
    private Shop shop;
    private Customer user;
    private Scanner consoleScaner;
    private static final String SHOW_ORDER_INFO = "show";
    private static final String MAKE_ORDER = "make";

    public ConsoleUserManager(Shop shop){
        this.shop = shop;
        consoleScaner = new Scanner(System.in);
    }

    public void start(){
        user = new User();
        getUserData();
        String line;
        do{
            createOrder();
            System.out.println("If you want to make another order, enter yes");
            line = consoleScaner.nextLine();
        } while(line.equals("yes"));
    }

    private void createOrder(){
        System.out.println("Enter the name of item from catalog and " +
                "amount of item that you need." + System.lineSeparator() +
                "If you want to see your order, enter "+ SHOW_ORDER_INFO +"," + System.lineSeparator() +
                "If you want to make an order, enter " + MAKE_ORDER);
        Order order = user.createOrder(shop);
        String line = consoleScaner.nextLine();
        while(!line.equals(MAKE_ORDER)){
            if(line.equals(SHOW_ORDER_INFO)){
                showOrderinfo(order);
            } else {
                addToOrder(line, order);
            }
            line = consoleScaner.nextLine();
        }
        user.makeOrder();
    }

    private void addToOrder(String line, Order order){
        String[] itemInfo = line.split(" ", 2);
        if(itemInfo.length < 2){
            System.out.println("Incorrect input, try again");
            return;
        }
        try{
            int amount = Integer.parseInt(itemInfo[1]);
            if(amount < 0){
                System.out.println("Incorrect amount of item, try again");
                return;
            }
            Pair<Item, Integer> items = shop.getItem(itemInfo[0], amount);
            if(items == null){
                System.out.println("Shop doesn't have " + itemInfo[0]);
                return;
            }
            order.addItem(items.getLeft(), items.getRight());
        } catch(NumberFormatException ex){
            System.out.println("Incorrect amount input, try again");
        }
    }

    private void showOrderinfo(Order order){
        System.out.print("Заказ №" + order.getId());
        for(String userInfo : user.getCustomerInfo()){
            System.out.print(" " + userInfo);
        }
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        System.out.println(" " + dateFormat.format(order.getDate()) + System.lineSeparator());
        System.out.printf("%-20s%-12s%-12s%-12s\n", "Название", "Цена", "Количество", "Сумма");
        for(Map.Entry<Item, Integer> itemAmount : order.getItems().entrySet()){
            double price = itemAmount.getKey().getPrice();
            int amount = itemAmount.getValue();
            System.out.printf("%-20s%-12.2f%-12d%-12.2f\n", itemAmount.getKey().getName(),
                    price, amount, price*amount);
        }
        System.out.println("Итого: " + order.getItemsSum());
    }

    private void getUserData(){
        System.out.println("Welcome to the MiniShop. " +
                "Please enter your name, surname and patronymic");
        String line = consoleScaner.nextLine();
        String[] userInfo = line.split(" ", 3);
        if(userInfo.length < 3){
            System.out.println("incorrect input, try again");
            getUserData();
            return;
        }
        ((User)user).setName(userInfo[0]);
        ((User)user).setSurname(userInfo[1]);
        ((User)user).setPatronymic(userInfo[2]);
    }
}
