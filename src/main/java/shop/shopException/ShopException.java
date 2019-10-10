package shop.shopException;

public class ShopException extends Exception {
    public ShopException(){
        super();
    }

    public ShopException(String message){
        super(message);
    }

    public ShopException(String message, Throwable cause){
        super(message, cause);
    }

    public ShopException(Throwable cause){
        super(cause);
    }
}
