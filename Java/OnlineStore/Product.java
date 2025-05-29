/**
 * 
 *
 * @author Jeffrey VanMeter
 * @version 0.5
 */
public class Product
{
    //attributes of the object
    //properties of the object
    //fields of the object
    String identifier;
    String type;
    double price;
    
    /** 
     * constructor for the object
     * and this construction method initialize the properties with the
     * tree arguments
     */
    Product(String anId, String aType, double aPrice) {
         identifier = anId;
         type  = aType;
         price = aPrice;
        
    }
}