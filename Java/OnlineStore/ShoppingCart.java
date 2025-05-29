/**
 * Write a description of class ShoppingCart here.
 *
 * @author Jeffrey VanMeter
 * @version 0.5
 */
public class ShoppingCart
{
    double totalCost;
    
    void add(Product aProduct) {
        totalCost = totalCost + aProduct.price;
    }
    
    void remove(Product aProduct) {
        totalCost = totalCost - aProduct.price;
    }
    
    double getTotalCost() {
        return totalCost;
    }    
}