/*-----------------------------------------------------------------------------
GWU - CS1112 Data Structures and Algorithms - Fall 2019

A product is an item available for purchase through the electronic market.  A
product consists primarily of an identifying name and an associated price.

authors: Aaron Coplan, James Taylor 
------------------------------------------------------------------------------*/public class Product {
    private final String name;
    private final double price;
  
    // Parameterized constructor requires both name and price to be provided.
    // We cannot create a product without both values
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
  
    // Accessor that returns the name of the product
    public String getName() {
        return name;
    }
  
    // Accessor that returns the price of the product
    public double getPrice() {
        return price;
    }
  
    // Comparison operation to determine whether two products are equivalent or 
    // whether a product precedes or follows another.  If two products have the
    // same name, the price is used for the comparison; otherwise, the name is 
    // used
    // @param other a product to compare to this product
    // @return returns 0 if the two products are equivalent, -1 if this product
    //         precedes the other, and 1 if this product follows the other
    public int compareTo(Product other) {
      // compare the names
      if( name.compareTo(other.name) == 0 ) {
          // names are equivalent, so check the price
          if ( price == other.price ) {
              // prices are equal, so products are equivalent
              return 0;
          } else if( price < other.price ) {
              // the price of this object is less than the other price, so this
              // product must precede the other
              return -1;
          } else { // price > other.price
              // the price of this object is greater than the other price, so 
              // this product must follow the other
            return 1;
          }
      }
      // if the names are not equivalent, just return the value of compareTo
      // for the names
      return name.compareTo(other.name);
    }
}
