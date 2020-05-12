/*

# File Name: LazyBinarySearchTree.java
# Author: Vedant Mahajan
# Modification History
# This code was written on 12th March.

*/
public class LazyBinarySearchTree {

    // class variables
    private TreeNode root;
    private int total_nodes = 0;
    private int deleted_nodes = 0;

    // default constructor
    public LazyBinarySearchTree() {
        root = null;
    }

    // parameterized constructor
    public LazyBinarySearchTree(TreeNode node) {
        root = node;
    }

//**************************************************************************************************************************
    // public function to insert a variable in the BST
    public boolean insert(int element) throws IllegalArgumentException {
        // throws an exception is element is not with in given range

        if (element < 1 || element > 99) {
            throw new IllegalArgumentException("IllegalArgumentException raised");
        }

        TreeNode insert = new TreeNode(element);

        if (root == null) {
            root = insert;
            total_nodes++;
            return true;
        }

        TreeNode parent = null, temp = root;

        while (temp != null) {
            parent = temp;

            if (temp.key == element && temp.deleted == true) {
                temp.deleted = false;
                deleted_nodes++;
                return true;
            }
            if (temp.key < element) {
                temp = temp.rightChild;

            } else if (temp.key > element) {
                temp = temp.leftChild;
            }
        }

        if (element < parent.key) {
            parent.leftChild = insert;
            total_nodes++;
            return true;

        } else if (element > parent.key) {
            parent.rightChild = insert;
            total_nodes++;
            return true;
        }

        return false;
    }
//**************************************************************************************************************************

    // public function for lazy deletion of element from BST
    public boolean delete(int element) throws IllegalArgumentException {
        // throws an exception is element is not with in given range

        if (element < 1 || element > 99) {
            throw new IllegalArgumentException("IllegalArgumentException raised");
        }

        // check if tree is null
        if (root == null) {
            return false;
        }

        TreeNode temp = root;

        while (temp != null) {
            if (temp.key == element) {

                if (temp.deleted == false) {
                    temp.deleted = true;
                    deleted_nodes++;
                    return true;
                } else {
                    return false;
                }

            } else if (temp.key < element) {
                temp = temp.rightChild;
            } else if (temp.key > element) {
                temp = temp.leftChild;
            }
        }

        return false;

    }

//**************************************************************************************************************************
    //return whether the given element both exists in the tree and is non-deleted.
    public boolean contains(int element) throws IllegalArgumentException {
        // throws an exception is element is not with in given range

        if (element < 1 || element > 99) {
            throw new IllegalArgumentException("IllegalArgumentException raised");
        }

        // check if tree is null
        if (root == null) {
            return false;
        }

        TreeNode temp = root;

        while (temp != null) {
            if (temp.key == element) {

                if (temp.deleted == false) {
                    return true;
                } else {
                    return false;
                }

            } else if (temp.key < element) {
                temp = temp.rightChild;
            } else if (temp.key > element) {
                temp = temp.leftChild;
            }
        }

        return false;

    }

    //**************************************************************************************************************************
    // return the value of the minimum non-deleted element, or -1 if none exists.
    public int findMin() {
        if (root == null) {
            return -1;
        }

        TreeNode temp = FindMin(root);

        return (temp.deleted == false) ? temp.key : -1;
    }

    private TreeNode FindMin(TreeNode x) {
        if (x == null) {
            return null;
        }

        TreeNode temporary = FindMin(x.leftChild); // search leftchild

        if (temporary != null && temporary.deleted == false) {
            return temporary; // return undeleted left child
        }

        if (x.deleted == false) {
            return x; // return undeleted parent
        }

        return FindMin(x.rightChild); // search right
    }

    //**************************************************************************************************************************
    // return the value of the maximum non-deleted element, or -1 if none exists.
    public int findMax() {
        if (root == null) {
            return -1;
        }

        TreeNode temp = FindMax(root);

        return (temp.deleted == false) ? temp.key : -1;
    }

    private TreeNode FindMax(TreeNode x) {
        if (x == null) {
            return null;
        }

        TreeNode temp = FindMax(x.rightChild);

        if (temp != null && temp.deleted == false) {
            return temp;
        }

        if (x.deleted == false) {
            return x;
        }

        return FindMax(x.leftChild);
    }

//**************************************************************************************************************************
    // Performs an pre-order traversal of the tree and print the value of each element, including elements marked as deleted.
    String printPreorder(TreeNode node) {

        if (node == null) {
            return "";
        }

        String s1 = "", s2 = "", s = "";

        /* first print key of node */
        if (node.deleted == true) {
            s = s + " *" + node.key + " ";
        } else {

            s = s + node.key + " ";
        }

        /* then recur on leftChild subtree */
        s1 = printPreorder(node.leftChild);

        /* now recur on rightChild subtree */
        s2 = printPreorder(node.rightChild);

        return s + s1 + s2;
    }

    @Override

    public String toString() {

        String ans = printPreorder(root);

        return ans;
    }

//**************************************************************************************************************************
    // returns the height of the tree, including “deleted” elements.
    public int height() {
        return height(root);
    }

    private int height(TreeNode node) {

        if (node == null) {
            return -1;
        }

        int leftHeight = height(node.leftChild);
        int rightHeight = height(node.rightChild);

        if (leftHeight > rightHeight) {
            return leftHeight + 1;
        } else {
            return rightHeight + 1;
        }
    }
//**************************************************************************************************************************

    //  returns the count of elements in the tree, including “deleted” ones.
    public int size() {
        return total_nodes;
    }

//**************************************************************************************************************************
    // TreeNode class
    private class TreeNode {

        int key;
        boolean deleted;
        TreeNode leftChild;
        TreeNode rightChild;

        // constructor
        public TreeNode(int element) {

            key = element;
            deleted = false;
            leftChild = null;
            rightChild = null;

        }
    }
//**************************************************************************************************************************

}
