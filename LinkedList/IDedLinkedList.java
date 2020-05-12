/*

# File Name: IDedLinkedList.java
# Author: Vedant Mahajan
# Modification History
# This code was written on 18th Feburary.
# Procedures:
# Node

*/



public class IDedLinkedList  <Anytype extends IDedObject> {
    
    
  //****************************************************** NODE CLASS ********************************************************************

    class Node <Type> {

    private Type data;

    private Node <Type> next;

    public Node(Type data, Node <Type> next)
    {
       this.data = data;
       this.next = next;
    }
  
 }
    
  //****************************************************************************************************************************************

    private Node <Anytype> head;

    // default constructor
    //  Constructs an empty list

    IDedLinkedList()
    {
        head = null;
    }
    
    // Constructor 2
    
    public IDedLinkedList(Node<Anytype> Head) {

        head = Head;
    }

    // checks if list is empty

    void makeEmpty()
    {
       head = null;
    }

    // finds the id in list and returns its data

    Anytype findID(int ID)
    {
       Node <Anytype> temp = head;

       while(temp != null)
       {
           if(temp.data.getID() == ID)
           {
               return temp.data;
           }

           temp = temp.next ;
       }

       return null;
    }
    
    // inserts a node at the front of the list

    boolean insertAtFront(Anytype x)
    {
        Node<Anytype> temp = head;

        boolean isExist = false;

        if( head != null)
        {
            while(temp != null)
            {
                    if(temp.data.getID() == (x.getID()))
                    {
                       isExist = true;
                    }

                    temp = temp.next;
            }

             if(isExist == true)
             {
                 System.out.println("The element " + x + " already exists.");

                 return  false;
             }
             else
             {
                Node <Anytype> inserted_node = head;

                inserted_node = new Node<>(x,head);

                head = inserted_node;

                return  true;
             }
             
        }
        
        else
        {
             
            Node <Anytype> inserted_node ;

            inserted_node = new Node<>(x,head);

            head = inserted_node;

            return true;
        
        }
             

        
    }

    // deletes a node from the front of the list
    
    Anytype deleteFromFront()
    {
        if(head == null)
        {
            return null;
        }
        else
        {

            Node <Anytype> temp = head;
            
            Node <Anytype> node_to_be_deleted = head;
            
            node_to_be_deleted.data = head.data;

            temp = temp.next;

            head = temp;

            return node_to_be_deleted.data;
        }
    }

    // deletes a node containing the given id
    
    Anytype delete(int ID)
    {
        Node<Anytype> before = null;

        Node<Anytype> current = head;
        
        if(findID(ID) == null)
        {
            return null;
        }

        if(head == null)
        {
            return  null;
        }

        else if(head.next == null && head.data.getID() == ID )
        {
            head = null;
            return current.data;
        }
        
        else if(head.data.getID() == ID)
        {
            head = current.next;
            return current.data;
            
        }

        else
        {

            while( current != null )
                {
                    before = current;
                    current = current.next;

                    if(current.data.getID() == ID)
                    {
                        before.next = current.next;

                        return current.data;

                    }
                 
                    
                    
                }
            
            return null;
        }


    }
    
    // prints sum of all IDs
    
    int printTotal()
    {
        if(head == null)
        {
            return -1;
        }

        else
        {
            Node<Anytype> temp = head;

            int sum = 0;

            while(temp != null)
            {
                sum = sum + temp.data.getID();
                
                temp = temp.next;
            }

            return sum;
        }
    }

   


}
