/*-----------------------------------------------------------------------------
GWU - CS1112 Data Structures and Algorithms - Fall 2019

This class is designed to handle lists and the items within them. There are two 
ways this is implemented: array and linked lists.

Compared to conventional array lists, this class gives additional functionality
such as add, remove, isEmpty, length, clear, and isIn. 

The class also has additional functions to interface with the ShoppingCart file
(such as set/getDiscount and getQuantity of items)

authors: Joshua Groover
------------------------------------------------------------------------------*/

public class List {

    public enum Type {
        ARRAY,
        LINKEDLIST
    }
 
    // Type of list drives how the list his managed internally
    private final Type type;
    
    // For a linked-list based list, the head pointer
    private ListItem head;
    private ListItem tail;
    // For an array-based list, the array itself
    private ListItem[] array;
    // The counter to track the number of elements in the list
    private int count;
 
    // Parameterized constructor.  The list type must be provided at 
    // construction because it is used as a switch to determine how the 
    // functions must behave 
    public List(Type type) {
        this.type = type;
        count = 0;
  
        if(type == Type.ARRAY) {
            array = new ListItem[2];
        } 
        else if(type == Type.LINKEDLIST) {
            head = null;
            tail = null;
        }
    }
    
    public void add(Product product, int quantity) {
        //create new product list item
        ListItem newProd = new ListItem(product, quantity);

        if(type == Type.ARRAY) {
            //if item in list, find it and change quantity
            if (isIn(product)){
                for (int i=0; i<count; i++){
                    if (product.getName().equals(array[i].getProduct().getName())){
                        array[i].addQuantity(quantity);
                        break;
                    }
                }
            }
            //if array len less than count add newProd into count pos
            else if (array.length != count){
                array[count] = newProd;
            }
            //if array full make a new one, move data to it and add new element
            else{
                ListItem[] newArray = new ListItem[count*2];
                for (int item=0; item<array.length; item++){
                    newArray[item] = array[item];
                }
                array = newArray;
                newArray[count] = newProd;
            }
        } 
        else if(type == Type.LINKEDLIST) {
            //if in list, change quantity
            if (isIn(product)){
                ListItem prod = head;
                for (int i=0; i<count; i++){
                    if (product.getName().equals(prod.getProduct().getName())){
                        prod.addQuantity(quantity);
                        break;
                    }
                    prod = prod.next;
                }
            }
            //if no head make head the new prod
            else if (head == null){
                head = newProd;
                tail = newProd;
                head.next = null;
                tail.next = null;
            }
            //if head, go through list to find current item then add new item as next element
            else{
                ListItem lastItem = head;
                while (lastItem.next != null){
                    lastItem = lastItem.next;
                }
                lastItem.next = newProd;
                tail = lastItem.next;
            }
        }
        //increment the number of elements in the list
        count++;
    }
  
    public boolean remove(Product product) {
        if(type == Type.ARRAY) {
            //create new list for products and number of prods to remove found
            ListItem[] newArray = new ListItem[array.length];
            int prodNumFound = 0;

            //if found, dont add and increase number found. If not found, add prod
            // to the position item-numfound in new list
            for (int item=0; item<count; item++){
                if (array[item].getProduct().getName().equals(product.getName())){
                    prodNumFound++;
                }
                else{
                    newArray[item-prodNumFound] = array[item];
                }
            }

            //set the array equal to the newArray we created and decrease count
            array = newArray;
            count = count-prodNumFound;

            //return true if removed anything, false if didnt
            if (prodNumFound > 0)
                return true;
        } 
        else if(type == Type.LINKEDLIST) {
            //declare things, curItem is going to point next at head
            ListItem curItem = new ListItem(product, 1);
            curItem.next = head;
            int prodNumFound = 0;

            //run while the curItem.next is not equal to null
            while (curItem.next != null){
                //if the next item is the head and is product, change head to next item
                if (curItem.next == head && head.getProduct().getName().equals(product.getName())){
                    head = head.next;
                    prodNumFound++;
                }
                //if the item is head and not the product, continue
                else if(curItem.next == head && !head.getProduct().getName().equals(product.getName())){
                    curItem = curItem.next;
                }
                //if next item is product, set the cur next item to the item after prod
                else if (curItem.next.getProduct().getName().equals(product.getName())){
                    curItem.next = curItem.next.next;
                    prodNumFound++;
                }
                //increase the current item
                else{
                    curItem = curItem.next;
                }
            }

            //tail is equal to the final item in the list
            tail = curItem;

            //adjust the count based on number of products removed
            count = count-prodNumFound;
            
            //if the number of products found is >0, then some item was removed
            if (prodNumFound > 0)
                return true;
        }
        //default (if nothing is removed)
        return false;
    }
    
    public void clear() {
        //declare a new array list if array type
        if(type == Type.ARRAY) {
            array = new ListItem[2];
            count = 0;
        } 
        //set the head equal to null to reset the list
        else if(type == Type.LINKEDLIST) {
            head = null;
            tail = null;
            count = 0;
        }
    }
    
    public boolean isEmpty() {
        //empty if count=0
        if(count == 0)
            return true;
        else
            return false;
    }
    
    public int length() {
        //count is the length of the list
        return count;
    }
    
    public Product get(int i) {
        //if out of range, return null
        if(i >= count){
            return null;
        }
        //return the product at position i
        else if(type == Type.ARRAY) {

            ListItem prod = array[i];
            return prod.getProduct();
        } 
        //iterate through until at the correct pos, return product
        else if(type == Type.LINKEDLIST) {
            ListItem prod = head;
            for (int item=0; item<i; item++){
                prod = prod.next;
            }
            return prod.getProduct();
        }
        //if out of range, return null
        return null;
    }

    public boolean isIn(Product product){
        if (type == Type.ARRAY){
            //checks if the product name is inside the array list
            for (int i=0; i<count; i++){
                if (product.getName().equals(array[i].getProduct().getName())){
                    return true;
                }
            }
        }
        else if (type == Type.LINKEDLIST){
            //checks if the product name is inside the linked list
            ListItem prod = head;
            for (int i=0; i<count; i++){
                if (product.getName().equals(prod.getProduct().getName())){
                    return true;
                }
                prod = prod.next;
            }
        }
        return false;
    }

    public void setDiscount(Product product, double discount){
        if (type == Type.ARRAY){
            //sets the discount equal to the input for a specific product
            for (int i=0; i<count; i++){
                if (product.getName().equals(array[i].getProduct().getName())){
                    array[i].setDiscount(discount);
                    break;
                }
            }
        }
        else if (type == Type.LINKEDLIST){
            //sets the discount equal to the input for a specific product
            ListItem prod = head;
            for (int i=0; i<count; i++){
                if (product.getName().equals(prod.getProduct().getName())){
                    prod.setDiscount(discount);
                    break;
                }
                prod = prod.next;
            }
        }
    }

    public double getDiscount(int i){
        if (type == Type.ARRAY){
            //gets the discount for the ith element of the arraylist
            return array[i].discount;
        }
        else if (type == Type.LINKEDLIST){
            //gets the discount for the ith element of the linked list
            ListItem prod = head;
            for (int item=0; item<i; item++){
                prod = prod.next;
            }
            return prod.discount;
        }

        return 0;
    }

    public double getQuantity(int i){
        if (type == Type.ARRAY){
            //gets the product quantity at position i in array list
            return array[i].quantity;
        }
        else if (type == Type.LINKEDLIST){
            //gets the product quantity at position i in linked list
            ListItem prod = head;
            for (int item=0; item<i; item++){
                prod = prod.next;
            }
            return prod.quantity;
        }

        return 0;
    }
   
    public String toString() {
        //it doesnt work
        String s = "";
        if(type == Type.ARRAY) {
            s = s + "Type: " + type + ", ";
            System.out.println(s);
            s = s + "Length: " + count + ", ";
            s = s + "Elements: ";
            for (int i=0; i<count; i++){
                s = s + array[i].getProduct().getName() + ", ";
            }
        } 
        else if(type == Type.LINKEDLIST) {
            s += "Type: " + type + ", ";
            s += "Length: " + count + ", ";
            s += "Elements: ";
            ListItem prod = head;
            for (int i=0; i<count; i++){
                s += prod.getProduct().getName() + ", ";
                prod = prod.next;
            }
        }
        return s; 
    }
}
