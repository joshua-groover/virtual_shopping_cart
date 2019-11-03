/*-----------------------------------------------------------------------------
GWU - CS1112 Data Structures and Algorithms - Fall 2019

This class is a multi-purpose list element container for shopping carts as it 
can act as an array element or a linked-list element 

It's chief purpose is to contain products and any other ancillary fields 
related to maintaining shopping cart data (like quantities)

Note that this class could be more generalized; however, it is specifically 
designed for the shopping cart application to make this problem a little easier.

authors: Joshua Groover
------------------------------------------------------------------------------*/
public class ListItem {

    // The product contained in this list item
    private final Product product;

    //quantity of product in cart
    public int quantity;

    //price of item in the cart
    public double discount;

    // The next field is only necessary for LinkedList based carts
    public ListItem next;

    // Parameterized constructor requires an instance of a Product be passed in
    // Note: You may modify theconstructor as needed
    public ListItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.discount = 0;
        this.next = null;
    }

    // Accessor to return the product contained by this list item    
    public Product getProduct() {
        return product;
    }

    //adds/deletes more items
    public void addQuantity(int numNew){
        quantity = quantity + numNew;
    }

    public void setDiscount(double tempDiscount){
        discount = tempDiscount;
    }


}
