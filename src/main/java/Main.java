import consoleUserManager.ConsoleUserManager;
import item.Item;
import item.Product;
import shop.MiniShop;

import java.io.*;

public class Main {
    private static final String catalogFilePath = "src" + File.separator +
            "resources"+ File.separator + "productsCatalog.txt";
    private static final int ITEM_AMOUNT = 10;

    public static void main(String[] args){
        MiniShop shop = new MiniShop();
        try{
            if (addItems(shop)){
                ConsoleUserManager userManager = new ConsoleUserManager(shop);
                userManager.start();
            }

        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    private static boolean addItems(MiniShop shop) throws IOException {
        File catalogFile = new File(catalogFilePath);
        if(!catalogFile.exists() || catalogFile.isDirectory()){
            System.out.println(catalogFile.getAbsolutePath());
            return false;
        }
        BufferedReader catalogReader = new BufferedReader
                (new FileReader(catalogFile));
        String line = catalogReader.readLine();
        while(line != null){
            String[] parced = line.split(" ", 2);
            Item item = createItem(parced);
            if(item == null){
                catalogReader.close();
                return false;
            }
            shop.addItems(item, ITEM_AMOUNT);
            line = catalogReader.readLine();
        }
        catalogReader.close();
        return true;
    }

    private static Item createItem(String[] parameters){
        Product item = new Product();
        if(parameters.length < 2){
            return null;
        }
        Double price;
        try{
            price =  Double.parseDouble(parameters[1]);
        } catch (NumberFormatException ex){
            return null;
        }
        item.setName(parameters[0]);
        item.setPrice(price);
        return item;
    }
}
