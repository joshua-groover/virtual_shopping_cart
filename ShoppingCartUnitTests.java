import javax.lang.model.util.ElementScanner6;
import java.lang.Math;

/*-----------------------------------------------------------------------------
GWU - CS1112 Data Structures and Algorithms - Fall 2019

Shopping cart unit tests runs a series of tests to validate that ShoppingCart,
List, and ListItem are functioning correctly.

Tests 1-6 validate that the List class is fully functional by adding items,
removing items, and verifying the items in the cart at a given time.

Tests 7 and 8 verify that the ShoppingCart functions subtotal, discount, and 
checkout are functioning properly. This is done by hand calculating the
expected subtotal and checkout prices and comparing it to the prices found in
the arraycart/llcart lists.

author: Joshua Groover
------------------------------------------------------------------------------*/
public class ShoppingCartUnitTests {

    public static void main(String[] args) {
        // Basic creation of shopping carts and inventory
        // TODO: you can change this as necessary.  This is just an illustration
        // of how to create a shopping cart of both types and a list of inventory
        ShoppingCart arraycart = new ShoppingCart(List.Type.ARRAY);
        ShoppingCart llcart = new ShoppingCart(List.Type.LINKEDLIST);
        Product[] inventory = InventoryHelper.getMultipleProducts();

        //start with the empty carts, test their length/is empty
        test1(arraycart, llcart, 1);

        //add items into the cart, test that the length/is empty
        int numItems = inventory.length;
        int quantity = 2;
        for (int product=0; product<numItems; product++){
            arraycart.add(inventory[product], quantity);
            llcart.add(inventory[product], quantity);
        }
        test2(arraycart, llcart, numItems, 2);

        //test if the right items are in the shopping cart
        test3(arraycart, llcart, inventory, 3);

        //remove one item from the cart, verify the length and items
        int newNumItems = numItems - 1;
        Product prodToRemove = inventory[newNumItems];
        arraycart.remove(prodToRemove);
        llcart.remove(prodToRemove);
        Product[] newInventory = new Product[newNumItems];
        for (int item=0; item<newNumItems; item++){
            newInventory[item] = inventory[item];
        }
        test4(arraycart, llcart, newInventory);

        //remove all the items, check that the cart is empty
        arraycart.clear();
        llcart.clear();
        test5(arraycart, llcart);

        //add items back into cart, check if the list is empty, length, and contents
        Product[] inventory2 = InventoryHelper.getMultipleProducts();
        int numItems2 = inventory2.length;
        int quantity2 = 3;
        for (int product=0; product<numItems2; product++){
            arraycart.add(inventory2[product], quantity2);
            llcart.add(inventory2[product], quantity2);
        }
        test6(arraycart, llcart, inventory2);

        //test discount and subtotal with subtotal f(x)
        //note discount if 30% off
        double discount = .3;
        arraycart.applyDiscount(inventory2[1], discount);
        llcart.applyDiscount(inventory2[1], discount);
        test7(arraycart, llcart, discount);


        //test discount and subtotal with subtotal f(x)
        //note discount if 30% off
        //sales tax is .1 (ie, the tax is 10% of the sale)
        double salestax = .1;
        arraycart.applyDiscount(inventory2[1], discount);
        llcart.applyDiscount(inventory2[1], discount);
        test8(arraycart, llcart, discount, salestax);

    }

    static void test1(ShoppingCart arraycart, ShoppingCart llcart, int testNum){
        //test if array cart is empty
        if (!arraycart.isEmpty()){
            System.out.println("Test " + testNum + " array cart is empty: Failed");
        }	
        else{
            System.out.println("Test " + testNum + " array cart is empty: Passed");
        }
        //test is linked list cart is empty
        if (!llcart.isEmpty()){
            System.out.println("Test " + testNum + " ll cart is empty: Failed");
        } 
        else{
            System.out.println("Test " + testNum + " ll cart is empty: Passed");
        } 
        //test the length of array cart
        if (arraycart.length() != 0){
            System.out.println("Test " + testNum + " array cart length: Failed");
        } 
        else{
            System.out.println("Test " + testNum + " array cart length: Passed");
        } 
        //test the length of the linked list cart
        if (llcart.length() != 0){
            System.out.println("Test " + testNum + " ll cart length: Failed");
        }
        else{
            System.out.println("Test " + testNum + " ll cart length: Passed");
        }
    }

    static void test2(ShoppingCart arraycart, ShoppingCart llcart, int numItems, int testNum){
        //test if array cart is empty
        if (arraycart.isEmpty()){
            System.out.println("Test " + testNum + " array cart is empty has products: Failed");
        } 
        else{
            System.out.println("Test " + testNum + " array cart is empty has products: Passed");
        } 
        //test is linked list cart is empty
        if (llcart.isEmpty()){
            System.out.println("Test " + testNum + " ll cart is empty has produts: Failed");
        }
        else{
            System.out.println("Test " + testNum + " ll cart is empty has products: Passed");
        }
        //test the length of array cart
        if (arraycart.length() != numItems){
            System.out.println("Test " + testNum + " array cart length has correct num products: Failed");
        }
        else{
            System.out.println("Test " + testNum + " array cart length has correct num products: Passed");
        }
        //test the length of the linked list cart
        if (llcart.length() != numItems){
            System.out.println("Test " + testNum + " ll cart length has correct num products: Failed");
        }
        else{
            System.out.println("Test " + testNum + " ll cart length has correct num products: Passed");
        }
    }

    static void test3(ShoppingCart arraycart, ShoppingCart llcart, Product[] inventory, int testNum){
        //get iterations and declare flag
        int numIterations = inventory.length;
        boolean errorArrayCart = false;
        boolean errorLlCart = false;

        //iterate through arraycart/inventory and check for name difference
        for (int item=0; item<numIterations; item++){
            if (!arraycart.get(item).getName().equals(inventory[item].getName()))
                errorArrayCart = true;
        }
        
        //iterate through llcart/inventory and check for name difference
        for (int item=0; item<numIterations; item++){
            if (!llcart.get(item).getName().equals(inventory[item].getName()))
                errorLlCart = true;
        }

        //if arraycart flagged, difference between lists and test failed
        if (errorArrayCart)
            System.out.println("Test " + testNum + " array cart items in list correctly: Failed");
        else 
            System.out.println("Test " + testNum + " array cart items in list correctly: Passed");

        //if llcart flagged, difference between lists and test failed
        if (errorLlCart)
            System.out.println("Test " + testNum + " ll cart items in list correctly: Failed");
        else 
            System.out.println("Test " + testNum + " ll cart items in list correctly: Passed");

    }

    static void test4(ShoppingCart arraycart, ShoppingCart llcart, Product[] newInventory){
        //repeat test2 to verify the length/if the lists are empty
        test2(arraycart, llcart, newInventory.length, 4);

        //repeat test3 to verify the new items have been added and are in the cart
        test3(arraycart, llcart, newInventory, 4);
    }

    static void test5(ShoppingCart arraycart, ShoppingCart llcart){
        //repeat test1 to verify cart is empty
        test1(arraycart, llcart, 5);
    }

    static void test6(ShoppingCart arraycart, ShoppingCart llcart, Product[] inventory2){
        //repeat test2 and test3 to verify the correctness of the carts
        int numItems2 = inventory2.length;
        test2(arraycart, llcart, numItems2, 6);
        test3(arraycart, llcart, inventory2, 6);
    }

    static void test7(ShoppingCart arraycart, ShoppingCart llcart, double discount){
        //calculate what the subtotal should be given known info
        double subtotal = 3*(((1-discount)*150) + 5.49 + 19.99);
        int testNum = 7;

        //check if the array cart's subtotal is within the calculated subtotal by a cent
        //if not, fail the subtotoal function
        if (arraycart.subtotal() >= (subtotal+.01) || arraycart.subtotal() <= (subtotal-.01))
            System.out.println("Test " + testNum + " array cart subtotal and discount: Failed");
        else 
            System.out.println("Test " + testNum + " array cart subtotal and discount: Passed");

        //check if the ll cart's subtotal is within the calculated subtotal by a cent
        //if not, fail the subtotoal function
        if (llcart.subtotal() >= (subtotal+.01) || llcart.subtotal() <= (subtotal-.01))
            System.out.println("Test " + testNum + " ll cart subtotal and discount: Failed");
        else 
            System.out.println("Test " + testNum + " ll cart subtotal and discount: Passed");

    }

    static void test8(ShoppingCart arraycart, ShoppingCart llcart, double discount, double salestax){
        //calculate the subtotal including the discount for item 2
        double subtotal = 3*(((1-discount)*150) + 5.49 + 19.99);

        //calculate the total with salestax
        double total = subtotal * (1+salestax);
        int testNum = 8;

        //if array cart's total not within +/- 1 cent of the calculated subtotoal, fail it
        if (arraycart.checkout(salestax) >= (total+.01) || arraycart.checkout(salestax) <= (total-.01))
            System.out.println("Test " + testNum + " array cart total at checkout: Failed");
        else 
            System.out.println("Test " + testNum + " array cart total at checkout: Passed");

        //if ll cart's total not within +/- 1 cent of the calculated subtotoal, fail it
        if (llcart.checkout(salestax) >= (total+.01) || llcart.checkout(salestax) <= (total-.01))
            System.out.println("Test " + testNum + " ll cart total at checkout: Failed");
        else 
            System.out.println("Test " + testNum + " array cart total at checkout: Passed");

    }
}



