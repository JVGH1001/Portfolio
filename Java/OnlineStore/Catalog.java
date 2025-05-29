/**
 *
 * @author Jeffrey VanMeter
 * @version 0.5
 */
public class Catalog
{
    //properties
    Product harryPotterBook ;
    Product macBookPro;
    Product lgSmartTV;
  
    
    /**
     * This constructor will initialize our catalog of three products
     */
    Catalog(Product aBook, Product aComputer, Product aTV) {
        harryPotterBook = aBook;
        macBookPro = aComputer;
        lgSmartTV = aTV;
    }
}