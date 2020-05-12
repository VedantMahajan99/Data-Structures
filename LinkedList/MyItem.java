/*

# File Name: MyItem.java
# Author: Vedant Mahajan
# Modification History
# This code was written on 18th Feburary.
# Procedures:
# MyItem

*/




import java.util.LinkedList;
import java.util.List;

public class MyItem implements IDedObject {

    private int itemID;

    private int itemPrice;

    private List<Integer> itemDescription;

    // Constructor that initializes the variables declared
    
    public MyItem(int ID, int price, List<Integer> items) {
        
        itemID = ID;
        itemPrice = price;
        itemDescription = new LinkedList<Integer>(items);
    }

    
    @Override // overrides getid method of interface
    
    public int getID() {
        
        return itemID;
    }

    @Override // overrides printid method of interface
    
    public String printID()
    {
        String stringID = Integer.toString(itemID);

        String string_item_price = Integer.toString(itemPrice);

        String new_desc = stringID + " " + string_item_price;

        for(int i=0 ; i<itemDescription.size() ; i++)
        {
            new_desc = new_desc + " " + itemDescription.get(i);
        }

        return new_desc;
    }



}
