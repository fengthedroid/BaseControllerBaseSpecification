package panda;

/**
 * Created by Feng on 11-Jan-15.
 */
public class Test {
    public static void main(String[] args) throws NoSuchFieldException {
        System.out.println( Customer.class.getDeclaredField("address").getType().getDeclaredField("street"));
    }

}
