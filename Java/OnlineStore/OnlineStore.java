/**
 * Write a description of class nanoAmazon here.
 *
 * @author Jeffrey VanMeter
 * @version 0.5
 */
public class OnlineStore
{
    //properties
    Catalog theCatalog ;
    ShoppingCart theCart;

    /**
     * this method execute a simulation of a user that takes from the catalog
     * the three products and he adds to the shopping cart
     */
    void program() {

        Product first  = new Product("ISBN: 4567123", "BOOK", 15.99 );
        Product second = new Product("SN: 786543", "COMPUTER", 999.99 );
        Product third = new Product("LG SmartTV 2021", "TELEVISION", 129.00 );
        theCatalog = new Catalog(first,second , third );
        theCart = new ShoppingCart();
        theCart.add(theCatalog.harryPotterBook);
        theCart.add(theCatalog.macBookPro);
        theCart.add(theCatalog.lgSmartTV);
        System.out.println("The total cost of your shopping is " + theCart.getTotalCost());

    }

    public static void main(String [] args) {

        OnlineStore littleOne= new OnlineStore();
        littleOne.program();

    }
}
