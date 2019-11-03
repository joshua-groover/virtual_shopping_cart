/*-----------------------------------------------------------------------------
GWU - CS1112 Data Structures and Algorithms - Fall 2019

InventoryHelper generates one or more products that can be used with the 
shopping cart 

authors: Aaron Coplan, James Taylor 
------------------------------------------------------------------------------*/
public class InventoryHelper {

  // Creates a single sample product and returns that product
  public static Product getSingleProduct() {
    return new Product("Sunglasses", 49.99);
  }

  // Creates an array of products and returns the array
  // Note: you should add a number of products here to create a robust test set
  public static Product[] getMultipleProducts() {
    Product[] products = new Product[3];
    products[0] = new Product("Hat", 5.49);
    products[1] = new Product("Watch", 150.00);
    products[2] = new Product("Earrings", 19.99);
    return products;
  }
}
