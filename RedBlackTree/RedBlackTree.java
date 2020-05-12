
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vedantmahajan
 * my code 1
 */
public class RedBlackTree<E extends Comparable<E>> {
    
    static boolean RED = false;
    static boolean BLACK = true;
    
    // creating a new node unrelated to tree yet
    private Node<E> nil = new Node<E>();
    
    Node<E> root = nil;
    
    static class Node<E extends Comparable<E>>
    {
        // data in the nodes
        public E element;
        
        // colour of the node
        boolean color;
        
        // elements of a Node class
        Node<E> parent;
        Node<E> leftChild;
        Node<E> rightChild;
        
        // default constructor
	Node() 
        {
            color = true;
            parent = null;
            leftChild = null;
            rightChild = null;
	}
        
        // parameterized constructor
	Node(E element) 
        {
            this();
            this.element = element;
	}
        
    }
    
    // default constructor
    public RedBlackTree() 
    {
	root.leftChild = nil;
	root.rightChild = nil;
	root.parent = nil;
    }
    
    // public function to insert in RedBlackTree
    public boolean insert(E element) throws NullPointerException
    {
        // throws an exception if element to be entered is null

        if (element == null) 
        {
            throw new NullPointerException("The element entered is null !");
        }
        
        else
        {
            return insert(new Node<E>(element));
        }
    }
    
    // @param: z, the node to be inserted into the Tree
    // Inserts z into the appropriate position in the RedBlackTree 
    // calculates total number of left and right nodes of a node
    
    private boolean insert(Node<E> z) 
    {
        
        // Create a reference to root & initialize a node to nil
	Node<E> y = nil;
	Node<E> x = root;

        // traverse through the tree to find apt position to insert z
        while(isNil(x) == false)
        {
            y = x;   
            
            // if z.element is < than the current element, go left
            
            if(z.element.compareTo(x.element) < 0)
            {
                x = x.leftChild;
            }
            
            // if z.element is > than the current element, go right
            
            else if(z.element.compareTo(x.element) > 0)
            {
                x = x.rightChild;
                
            }
            
            // if element is a duplicate
            
            else
            {
                return false;
            }
        }
        
        // y will hold z's parent
	z.parent = y;
        
        // Depending on the value of y.element, put z as the left or
	// right child of y
        
	if (isNil(y) == true) 
        {
            root = z;
	}
        else if (z.element.compareTo(y.element) < 0) 
        {
            y.leftChild = z;
	} 
        else 
        {
            y.rightChild = z;
	}
        
        // Initialize z's children to nil and z's color to red
	z.leftChild = nil;
	z.rightChild = nil;
	z.color = RED;

        // Call insertFixup(z)
	rebalancing(z);

        return true;
        
    }
    
    private void rebalancing(Node<E> z) 
    {
        Node<E> tmp = nil;
        
        // if the parent of inserted node is red then fix up is required
        while(z.parent.color == RED)
        {
            // If z's parent is the the left child of it's parent.
            if (z.parent == z.parent.parent.leftChild)
            {
                // check z's uncle
                tmp = z.parent.parent.rightChild;
                
                // if uncle's colour is red then recolour
                if(tmp.color == RED)
                { 
                    //tmp was red so make it black
                    tmp.color = BLACK; 
                    
                    // it entered the while loop because z's parent was red so make it black
                    z.parent.color = BLACK;
                    
                    // if z's parent was red then it's grandparent had to be black so make it red
                    z.parent.parent.color = RED;
                    
                    // will check the properties until root it reaches root
                    z = z.parent.parent;
                }
                
                // if uncle's colour is black or is null and newly inserted node is a rightchild
                else if( z == z.parent.rightChild)
                {
                    z = z.parent;
                    leftRotate(z);
                    
                }
                
                // if uncle's colour is black or is null and newly inserted node is a leftchild
                else if( z == z.parent.leftChild)
                {
                    z.parent.color = BLACK;
                    z.parent.parent.color = RED;
                    rightRotate(z.parent.parent);
                    
                }
                
            }
            
            // If z's parent is the right child of it's parent.
            else if(z.parent == z.parent.parent.rightChild)
            {
                // check z's uncle
                tmp = z.parent.parent.leftChild;
                
                // if uncle's colour is red then recolour
		if (tmp.color == RED) 
                {
                    // it entered the while loop because z's parent was red so make it black
                    z.parent.color = BLACK;
                    
                    //tmp was red so make it black
                    tmp.color = BLACK;
                    
                    // if z's parent was red then it's grandparent had to be black so make it red
                    z.parent.parent.color = RED;
                    
                    // will check the properties until root it reaches root
                    z = z.parent.parent;
		}
                
                // if uncle's colour is black or is null and newly inserted node is a leftchild
                else if( z == z.parent.leftChild)
                {
                    z = z.parent;
                    rightRotate(z);
                }
                
                // if uncle's colour is black or is null and newly inserted node is a rightchild
                else if( z == z.parent.rightChild)
                {
                    z.parent.color = BLACK;
                    z.parent.parent.color = RED;
                    leftRotate(z.parent.parent);
                    
                }
            }
        }
        
        // root always needs to be black
        root.color = BLACK;
    }
    
    private void leftRotate(Node<E> x)
    {
        
        Node<E> y;
        
        y = x.rightChild;
        x.rightChild = y.leftChild;
        
        y.parent = x.parent;
        
        // x's parent is nul
	if (isNil(x.parent))
        {
            root = y;
        }
        
        // x is the left child of it's parent
	else if (x.parent.leftChild == x)
        {
            x.parent.leftChild = y;
        }
        
        // x is the right child of it's parent
	else if (x.parent.rightChild == x)
        {
            x.parent.rightChild = y;
        }
        
        y.leftChild = x;
        x.parent = y;
        
    }
    
    private void rightRotate(Node<E> y) 
    {
        Node<E> x;
        
        x = y.leftChild;
        y.leftChild = x.rightChild;
        
        x.parent = y.parent;
        
        // y.parent is nil
	if (isNil(y.parent))
        {
            root = x;
        }
			
	// y is a right child of it's parent.
	else if (y.parent.rightChild == y)
        {
            y.parent.rightChild = x;
        }		

	// y is a left child of it's parent.
        else if(y.parent.leftChild == y)
        {
            y.parent.leftChild = x;
        }
        
        x.rightChild = y;
	y.parent = x;
    }
    
    // function to check if an object is present in the tree
    public boolean contains(Comparable<E> z)
    {
        
        E element = (E) z;
        
        return contains(element, root);

    }


    private boolean contains(E element , Node root) 
    {

	// Initialize a pointer to the root to traverse the tree
	Node<E> current = root;

	// While we haven't reached the end of the tree
	while (!isNil(current)) 
        {

            // If we have found a node with a element equal to element
            if (current.element.equals(element))
            {
                // return that node and exit search
		return true;
            }
		
            // go left or right based on value of current and element
            else if (current.element.compareTo(element) < 0)
            {
                current = current.rightChild;
            }
	
            // go left or right based on value of current and element
            else
            {
                current = current.leftChild;
            }
				
	}

		// we have not found a node whose element is "element"
		return false;

	}
    
    // public toString function
    public String toString() 
    {
	return toString(root);
    }
    
    // function to print nodes of RedBlackTree in PreOrderTraversal i.e. root , left , right
    private String toString(Node<E> node) 
    {
        if (node == nil) 
        {
            return "";
	}   
        
        String str1 = "";
	String str2 = "";
	String str = "";
        
        if (node.color == BLACK) 
        {
            str = str + node.element + " ";
	} 
        else 
        {
            str = str + "*" + node.element + " ";
	}
        
        str1 = toString(node.leftChild);
        
	str2 = toString(node.rightChild);
        
        return str + str1 + str2;
    }
    
    // check if node is empty
    private boolean isNil(Node<E> node) 
    {
	if(node == nil)
        {
            return true;
        }
        else
        {
            return false;
        }

    }
    
}
