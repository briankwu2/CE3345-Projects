// Project 2 - CS 3345.002
// By Brian Wu
// Uses code from Mark Allen Weiss's BinarySearchTree.java source code.
// Implements additional methods to the BST source code.



    // BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */

import java.util.*;

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        return findMin( root ).element;
    }
    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }


      /** The tree root. */
    private BinaryNode<AnyType> root;

    public BinaryNode<AnyType> getRoot() // Getter method
    {
        return root;
    }
//-----------------------------------------------------------------------------------------------------------------------
// Begin additional BST methods. i.e. Project 2.


// a) nodeCount
// Recursively traverses the tree and returns the count of nodes.
public int nodeCount ()
{
    if (root == null) // If no nodes are present
    {
        return 0;
    }
    return nodeCount(root); // To include the root node.

}

public int nodeCount (BinaryNode<AnyType> node) // Helper Function
{
    if (node == null)
    {
        return 0;
    }

    return 1 + nodeCount(node.left) + nodeCount(node.right); 
    // Recursively goes in post order traversal and returns 1 once checking two children to count node.
        
}

// b) isFull 
//         Returns true if the tree is full.  A full tree has every node 
//           as either a leaf or a parent with two children.

public boolean isFull ()
{
    if (root == null)
    {
        return false; // Base case for empty tree.
    }
    return isFull(root); // Calls the helper function
}

public boolean isFull(BinaryNode<AnyType> node) // Helper function
{
    if (node == null)
    {
        return true;
    }
    if (!isFull(node.left) || !isFull(node.right)) // Traverses in post-order, and if any node is not a "full" node, then returns false all the way back.
    {
        return false;
    }
    return isLeafOrParent(node); // Checks if a node is full after post-order traversal
 
}

public boolean isLeafOrParent (BinaryNode<AnyType> node)
{
    
    // Determines if parent of two children or a leaf node.
    if ((node.left == null && node.right == null) || (node.left != null && node.right != null))
    {
        return true;
    }
    else
    {
        return false;
    }

}

// c) compareStructure 
//         Compares the structure of current tree to another tree and returns
//           true if they match.

//             For example, these two trees have the same structure:
//                    5           10
//                   / \         /  \
//                  3   8       5   15
//                 / \         / \
//                1   4       2   7

public boolean compareStructure (BinarySearchTree<AnyType> tree2)
{

    // Otherwise compare the structures by recursively calling and seeing if their leaf nodes have the same structure.
    // Calls the helper function.
    return compareStructure(root, tree2.getRoot());
}

public boolean compareStructure (BinaryNode<AnyType> node1, BinaryNode<AnyType> node2)
{
    if (node1 == null && node2 == null) // Checks for both empty trees (nodes)
    {
        return true;
    }

    // Case if one tree is empty and the other is non-empty (nodes)
    if ((node1 == null && node2 != null) || (node1 != null && node2 == null))
    {
        return false;
    }

    return (compareStructure(node1.left, node2.left) && compareStructure(node1.right, node2.right)); // Recursively call

}

// d) equals
//     Compares the current tree to another tree and returns true
//     if they are identical.

// The same as compareStructure but making sure that the data is also equal.
public boolean equals (BinarySearchTree<AnyType> tree2)
{

    // Otherwise compare the structures by recursively calling and seeing if their leaf nodes have the same structure.
    // Calls the helper function.
    return compareStructure(root, tree2.getRoot());
}

public boolean equals (BinaryNode<AnyType> node1, BinaryNode<AnyType> node2)
{
    if (node1 == null && node2 == null) // Checks for both empty trees (nodes)
    {
        return true;
    }

    // Case if one tree is empty and the other is non-empty (nodes)
    if ((node1 == null && node2 != null) || (node1 != null && node2 == null))
    {
        return false;
    }

    return (equals(node1.left, node2.left) && equals(node1.right, node2.right) && (node1.element == node2.element)); // Recursively call

}


// e) copy
//         Creates and returns a new tree that is a copy of the original tree.

public BinarySearchTree<AnyType> copy()
{
    BinarySearchTree<AnyType> copyTree = new BinarySearchTree<>();
    copyTree.root = copy(root); // Calls the helper function to recursively copy each node and set left and right

    return copyTree;
    
}

public BinaryNode<AnyType> copy(BinaryNode<AnyType> origNode) // Traverses in pre-order.
{
    if (origNode == null) // Returns null if reaches a leaf
    {
        return null;
    }

    BinaryNode<AnyType> copyNode = new BinaryNode<AnyType>(origNode.element); // Creates a node with same data
    copyNode.left = copy(origNode.left); // Recursively traverses down left and creates node for every node in original tree
    copyNode.right = copy(origNode.right); // ^ above but for right subtrees.

    return copyNode; // Returns the root of the copied tree.
}

// f) mirror
//         Creates and returns a new tree that is a mirror image of the original tree.
//         For example, for the tree on the left, the tree on the right is returned:
    
//             100                 100
//            /   \               /   \
//           50   150    -->     150  50
//          /                           \
//         40                           40
//          \                           /
//          45                         45
// Essentially copy() but switch the right and left nodes.
public BinarySearchTree<AnyType> mirror()
{
    BinarySearchTree<AnyType> copyTree = new BinarySearchTree<>();
    copyTree.root = mirror(root); // Calls the helper function to recursively copy each node and set left and right

    return copyTree;
    
}

public BinaryNode<AnyType> mirror(BinaryNode<AnyType> origNode) // Traverses in pre-order.
{
    if (origNode == null) // Returns null if reaches a leaf
    {
        return null;
    }

    BinaryNode<AnyType> copyNode = new BinaryNode<AnyType>(origNode.element); // Creates a node with same data
    copyNode.right = copy(origNode.left); 
    copyNode.left = copy(origNode.right); 

    return copyNode; // Returns the root of the copied tree.
}


// g) isMirror 
//         Returns true if the tree is a mirror of the passed tree.
public boolean isMirror(BinarySearchTree<AnyType> tree)
{
    return isMirror(root, tree.root);
}

public boolean isMirror(BinaryNode<AnyType> currNode, BinaryNode<AnyType> compNode) // Traverses in pre-order.
{
    if (currNode == null && compNode == null) // Checks for both empty trees (nodes)
    {
        return true;
    }

    // Case if one tree is empty and the other is non-empty (nodes)
    if ((currNode == null && compNode != null) || (currNode != null && compNode == null))
    {
        return false;
    }

    return (equals(currNode.left, compNode.right) && equals(currNode.right, compNode.left) && (currNode.element == compNode.element)); // Recursively call

}

// h) rotateRight
//         Performs a single rotation on the node having the passed value.
//         If a RotateRight on 100 is performed:

//            100                  50
//           /   \                /   \
//          50   150    -->      40   100
//         /                      \     \
//        40                      45    150
//         \ 
//         45

// i) rotateLeft 
//         As above but left rotation.

// j) printLevels - performs a level-by-level printing of the tree.
public void printLevels ()
{
    if (root == null)
    {
        return;
    }

    Queue<BinaryNode<AnyType>> queue = new LinkedList<BinaryNode<AnyType>>();
    queue.add(root);

    while (!queue.isEmpty()) // While queue is not empty
    {
        BinaryNode<AnyType> tNode = queue.remove();
        System.out.println(tNode.element); // Dequeues the first node element.

        if (tNode.left != null) // Adds elements to queue
        {
            queue.add(tNode.left);
        }
        
        if (tNode.right != null) // Adds the elements to the queue
        {
            queue.add(tNode.right);
        }
        
    }

    
}


/*

Project #2

Due Dates:  Saturday, October 23 at 11:59pm 

Submit:    eLearning

Late Policy:  24-hour late period, then zero

Instructions: This is an individual assignment.  Answers should be your own work.



Introduction:

   In this project you will modify the author's BinarySearchTree code 
   to implement some new methods.



Description:

   Modify the author's BinarySearchTree code to implement the methods
   shown below.  

   Each method is 10 points.
        
   */
//   k) main - demonstrate in your main method that all of your new methods work.

// Main Program ----------------------------------------------------------------------------------------------------------
    public static void main( String [ ] args )
    {
        BinarySearchTree<Integer> BST1 = new BinarySearchTree<>( );
        BinarySearchTree<Integer> BST2 = new BinarySearchTree<>( );
        BinarySearchTree<Integer> BST3 = new BinarySearchTree<>( );

        for (int i = 0; i <= 5; i++)
        {
            BST1.insert(i);
            BST2.insert(i*2);
            BST2.insert(i*3);
        }
        BST3.insert(2);
        BST3.insert(1);
        BST3.insert(3); 

        // BST3 is a full and complete BST.

        // Test for a. nodeCount
        System.out.println("This is nodeCount for BST 1: " + BST1.nodeCount());
        System.out.println("This is nodeCount for BST 2: " + BST2.nodeCount());
        System.out.println("This is nodeCount for bST 3: " + BST3.nodeCount());

        // Test for b. isFull
        if (BST1.isFull())
            System.out.println("BST1 is full");
        else
            System.out.println("BST1 is not full");
        if (BST2.isFull())        
            System.out.println("BST2 is full");
        else
            System.out.println("BST2 is not full");
        if (BST3.isFull())
            System.out.println("BST3 is full");
        else
            System.out.println("BST3 is not full");

        // Test for c. compareStructure
        if (BST1.compareStructure(BST1)) // Compares BST1 to itself
            System.out.println("BST1 is equal in structure to BST1");
        else
            System.out.println("BST1 is not equal in structure to BST1");

        if (BST1.compareStructure(BST2)) // Compares BST1 to itself
            System.out.println("BST1 is equal in structure to BST2");
        else
            System.out.println("BST1 is not equal in structure to BST2");

        if (BST1.compareStructure(BST3)) // Compares BST1 to itself
            System.out.println("BST1 is equal in structure to BST3");
        else
            System.out.println("BST1 is not equal in structure to BST3");

        // Test for d. equals
        if (BST1.equals(BST1)) // Compares BST1 to itself
        System.out.println("BST1 is equal to BST1");
        else
            System.out.println("BST1 is not equal to BST1");

        if (BST1.equals(BST2)) // Compares BST1 to itself
            System.out.println("BST1 is equal to BST2");
        else
            System.out.println("BST1 is not equal to BST2");

        if (BST1.equals(BST3)) // Compares BST1 to itself
            System.out.println("BST1 is equal to BST3");
        else
            System.out.println("BST1 is not equal to BST3");


        // Tests e.copy
        BinarySearchTree<Integer> BST4 = BST1.copy(); // Copies BST1 into BST1.
        if (BST1.equals(BST4))
            System.out.println("BST4 is a copy of BST1");
        else
            System.out.println("BST4 is not a copy of BST1");   

        // Tests f.mirror
        BinarySearchTree<Integer> BST1_mirror = BST1.mirror();
        BST1_mirror.printTree(BST1_mirror.root);
        System.out.println();
        BST1.printTree(BST1.root);

        // Test g. isMirror
        if (BST1.isMirror(BST1_mirror))
            System.out.println("BST1 is a mirror of BST1_mirror");
        else
            System.out.println("BST1 is not a mirror of BST1_mirror");

        if (BST1.isMirror(BST2))
            System.out.println("BST1 is a mirror of BST2");
        else
            System.out.println("BST1 is not a mirror of BST2");

        // Test h, i => not implemented

        // Test k. printLevels
        System.out.println("This is BST1 Printed by Levels");
        BST1.printLevels();
        System.out.println("This is BST2 Printed by Levels");
        BST2.printLevels();
            
    }
}
 




