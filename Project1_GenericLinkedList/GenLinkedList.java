import java.util.List;
import java.util.NoSuchElementException;
import java.util.ArrayList;
// Project1 for CS 3345.002
// Author: Brian Wu
// Objective: Create a working generic singly linked list


public class GenLinkedList<G> {

     // Main to test all methods
     public static void main(String[] args) 
     {
          GenLinkedList<Integer> list = new GenLinkedList<>();
          
          // addFront
          list.addFront(10);
          System.out.println("This tests addFront: " + list.headNode.getValue()); // Prints 10

          // addEnd
          list.addEnd(100);
          System.out.println("This tests addEnd: " + list.headNode.getNextNode().getValue()); // Prints 100

          // removeFront
          System.out.println("This tests removeFront: " + list.removeFront()); // Prints out 10

          // removeEnd
          System.out.println("This tests removeEnd: " + list.removeEnd()); // Prints out 100

          for (int i = 0; i < 6; i++)
          {
               list.addFront(i*10);
          }

          // Set
          list.set(3, 500);

          System.out.print("This tests set: ");
          Node<Integer> testNode = list.getHeadNode();
          for (int i = 0; i < list.getSize(); i++) // Prints out the whole list.
          {
               System.out.print(testNode.getValue() + " ");
               testNode = testNode.getNextNode();
          }

          //Get
          System.out.println();
          System.out.println("This tests get: " + list.get(3));


          //Shift
          list.shift(2);
          System.out.print("This tests shift: ");

          testNode = list.getHeadNode();
          for (int i = 0; i < list.getSize(); i++) // Prints out the whole list.
          {
               System.out.print(testNode.getValue() + " ");
               testNode = testNode.getNextNode();
          }

          list.shift(-3);
          System.out.print("This tests shift: ");

          testNode = list.getHeadNode();
          for (int i = 0; i < list.getSize(); i++) // Prints out the whole list.
          {
               System.out.print(testNode.getValue() + " ");
               testNode = testNode.getNextNode();
          }
          System.out.println();

          // removeMatching
          list.removeMatching(500);
          System.out.print("This tests removeMatching: ");
          testNode = list.getHeadNode();
          for (int i = 0; i < list.getSize(); i++) // Prints out the whole list.
          {
               System.out.print(testNode.getValue() + " ");
               testNode = testNode.getNextNode();
          }
          System.out.println();

          // erase
          System.out.print("This tests erase: ");
          list.erase(1, 2);

          testNode = list.getHeadNode();
          for (int i = 0; i < list.getSize(); i++) // Prints out the whole list.
          {
               System.out.print(testNode.getValue() + " ");
               testNode = testNode.getNextNode();
          }
          System.out.println();
          // insertList

          ArrayList<Integer> otherList = new ArrayList<>();
          otherList.add(1);
          otherList.add(1);
          otherList.add(1);

          System.out.print("This tests insertList: ");
          testNode = list.getHeadNode();
          for (int i = 0; i < list.getSize(); i++) // Prints out the whole list.
          {
               System.out.print(testNode.getValue() + " ");
               testNode = testNode.getNextNode();
          }
          System.out.println();









     }

     //Pointer to headNode.
     private Node<G> headNode;
     private Node<G> tailNode;
     private int size = 0;

     // Default Constructor
     GenLinkedList()
     {
          headNode = null;
          tailNode = null;
     }

     // Overloaded Constructor
    
     public Node<G> getHeadNode()
     {
          return headNode;
     }

     public Node<G> getTailNode()
     {
          return tailNode;
     }
     
     public int getSize()
     {
          return size;
     }

     // a.  addFront
     // receives an item to add as a parameter, and adds to the front of the list.
     public void addFront(G frontValue)
     {
          // Assigns the headNode if there is no Nodes in the list.
          if (headNode == null)
          {
               headNode = new Node<G>(frontValue, null); // Creates new Node containing a generic value, and has no pointer to any node.
               tailNode = headNode; // Since there is only one node, assign the headNode to the tailNode as well.
          }
          else
          {
               headNode = new Node<G>(frontValue, headNode);
               // Performs the creation of a new Node, and assigns the pointer to the current headNode and then
               // performs the left side by assigning the new headNode to be the newly created node.
          }
          size++; // Increments the size counter.

     }

     // b.  addEnd
     // receives an item to add as a parameter, and adds to the end of the list
     public void addEnd (G endValue)
     {
          if (headNode == null)
          {
               headNode = new Node<G>(endValue, null); // Creates new Node containing a generic value, and has no pointer to any node.
               tailNode = headNode; // Since there is only one node, assign the headNode to the tailNode as well.
          }
          else
          {
               tailNode.setNextNode(new Node<G>(endValue,null)); // Create a new node containing the endValue.
               tailNode = tailNode.getNextNode(); // Sets the current tailNode to the new node.
          }
          size++; // Increments the size counter.
     }

     // c.  removeFront
     // removes a node from the front of the list.
     public G removeFront ()
     {
          G data;
          // If the list is empty, throw an exception
          if (headNode == null)
          {
               throw new NoSuchElementException();
          }

          // In the case that there is only one node.
          if (headNode == tailNode)
          {
               data = headNode.getValue(); // Stores the value of the one node into a temporary variable
               headNode.setNextNode(null); // Sets both tail and head nodes to null.
               tailNode.setNextNode(null);
          }
          else // More than one node
          {
               data = headNode.getValue(); // Stores the value of the head node into a temp variable
               headNode = headNode.getNextNode(); // Sets the headNode to the old Node's next node.
          }

          size--; // Decrements the size counter.
          return data; // Returns the data of the removed node.
          

     }

     // d.  removeEnd
     // removes a node from the end of the list.
     public G removeEnd ()
     {
          G data;
          // If the list is empty, throw an exception
          if (headNode == null)
          {
               throw new NoSuchElementException();
          }

          // In the case that there is only one node.
          if (headNode == tailNode)
          {
               data = headNode.getValue(); // Stores the value of the one node into a temporary variable
               headNode.setNextNode(null); // Sets both tail and head nodes to null.
               tailNode.setNextNode(null);
          }
          else // More than one node
          {
               data = tailNode.getValue();

               // Must iterate to the last node of the list in order to reach the second to last node.
               Node<G> currNode = headNode;
               while (currNode.getNextNode() != tailNode)
               {
                    currNode = currNode.getNextNode(); // Finds the second to last node.
               }
               currNode.setNextNode(null);
               tailNode = currNode;
          }
          
          size--; // Decrements the size counter

          return data; // Returns the data of the removed node.

     }

     // e.  set
     // receives a position and item as parameters, sets the element at this
     // position, provided it is within the current size

     public void set(int position, G value)
     {

          // Handles an invalid position error.
          if (position > size - 1 )
          {
               throw new NoSuchElementException();
          }

          int count = 0;
          Node<G> currNode = headNode;
          if (position == 0)
          {
               this.addFront(value); // If position is 0, then add to front.
          }
          else
          {
               while (count < position - 1) // Traverses to the node just before the old node at the position.
               {
                    currNode = currNode.getNextNode();
                    count++;
               }
               // Creates the new node and assigns its next pointer as the one it's replacing
               Node<G> newNode = new Node<G>(value, currNode.getNextNode().getNextNode()); 
               currNode.setNextNode(newNode); // Sets the one before the position nodes next node pointer to the newNode.
          }
     }

     // f.  get
     // receives a position as a parameter, returns the item at this position,
     // provided it is within the current size

     public G get(int position)
     {

          // Handles an invalid position error.
          if (position > size - 1 )
          {
               throw new NoSuchElementException();
          }

          int count = 0;
          Node<G> currNode = headNode;

          while (count < position) // Traverses to the node at given position.
          {
               currNode = currNode.getNextNode();
               count++;
          }

          return currNode.getValue();
     }

     // g.  swap
     // receives two index positions as parameters, and swaps the nodes at
     // these positions (not just the values inside the nodes), provided 
     // both positions are within the current size


     public void swap(int ind1, int ind2)
     {

          if (ind1 == ind2) // Case in where they are the same
          {
               return;
          }

          if ((ind1 > size - 1) || (ind1 < 0) || ind2 > size - 1 || ind2 < 0)
          {
               throw new NoSuchElementException();
          }

          // Search for x and y nodes

          Node<G> xNode, yNode, pXNode, pYNode = null;

          Node<G> tempNode = null;



     }

     // h.  shift
     //      receives an integer as a parameter, and shifts the list forward or
     //      backward this number of nodes, provided it is within the current size
     //           1,2,3,4,5    shifted +2    3,4,5,1,2
     //           1,2,3,4,5    shifted -1    5,1,2,3,4
     public void shift(int shiftAmount)
     {
          if (shiftAmount > 0)
          {
               // Shifts the list right by shiftAmount #.
               for (int i = 0; i < shiftAmount; i++)
               {
                    G tempData;
                    tempData = this.removeEnd();
                    this.addFront(tempData);
               }

          }
          else if (shiftAmount < 0)
          {
                // Shifts the list left by shiftAmount #.
                for (int i = 0; i < (shiftAmount * -1); i++)
                {
                     G tempData;
                     tempData = this.removeFront();
                     this.addEnd(tempData);
                }

          }
          else
          {
               throw new NoSuchElementException();
          }
     }

     // i.  removeMatching
     // receives a value of the generic type as a parameter and removes all
     // occurrences of this value from the list.

     public void removeMatching (G matchValue)
     {
          Node<G> currNode = headNode;
          Node<G> prevNode = null;
          
          while (currNode.getNextNode() != null)
          {
               if (currNode.getValue() == matchValue) // Looks for a matching value
               {
                    if(currNode == headNode) // If the currNode is the head.
                    {
                         currNode = currNode.getNextNode();
                         removeFront(); // Removes the first node.
                    }
                    else if (currNode == tailNode)
                    {
                         removeEnd();
                    }
                    else
                    {
                         prevNode.setNextNode(currNode.getNextNode()); // "Removes" the currnode from the list.
                         prevNode = currNode;
                         currNode = currNode.getNextNode(); // Increments to the next node.
                    }
               }
               else // Otherwise will increment
               {
                    prevNode = currNode; // Saves the current position.
                    currNode = currNode.getNextNode(); // Increments to the next node
               }
          }

     }

     // j.  erase 
     //      receives an index position and number of elements as parameters, and
     //      removes elements beginning at the index position for the number of 
     //      elements specified, provided the index position is within the size
     //      and together with the number of elements does not exceed the size

     public void erase (int index, int numElements)
     {
          // Exception checker
          if ((index > size - 1) || (numElements > size - index))
          {
               throw new NoSuchElementException();
          }

          Node<G> prevNode = headNode;
          
          // Search for the index node
          for (int i = 0; i < index - 1; i++)
               prevNode = prevNode.getNextNode();
          
          Node<G> endNode = prevNode;

          // Delete the amount of numElements
          for (int i = 0; i < numElements; i++)
          {
               endNode = endNode.getNextNode();
          }

          if (endNode.getNextNode() == null) // If the amount of numElements deletes the rest of the list.
          {
               endNode.setNextNode(null);
          }
          else // Sets the prev pointer to the ending node.
          {
               prevNode.setNextNode(endNode);
          }

     }
    
     // k.  insertList
     //      receives a generic List (a Java List) and an index position as parameters, 
     //      and copies each value of the passed list into the current list starting
     //      at the index position, provided the index position does not exceed the size.
     //      For example, if list has a,b,c and another list having 1,2,3 is inserted at
     //      position 2, the list becomes a,b,1,2,3,c,d,e

     public void insertList (List<G> list, int index)
     {
          if (index > size - 1)
          {
               throw new NoSuchElementException();
          }

          Node<G> currNode = headNode;
          // Search for the index node
          for (int i = 0; i < index; i++)
               currNode = currNode.getNextNode();
          
          Node<G> nextNode = currNode.getNextNode(); // Sets the node connecting after the inserted list
          Node<G> firstListNode = null;
          Node<G> listNode = null;
          Node<G> tempNode = null;

          for (int i = 0; i < list.size(); i++)
          {
               if (i == 0)
               {
                    firstListNode = new Node<G>(list.get(i), null);
                    listNode = firstListNode;
               }
               else
               {
                    tempNode = new Node<G>(list.get(i), null);
                    listNode.setNextNode(tempNode);
                    listNode = tempNode;
               }
          }

          currNode.setNextNode(firstListNode);
          listNode.setNextNode(nextNode);

     }

}


class Node<G> {

     // Value of node, and pointer to next node
     private G value;
     private Node<G> nextNode;

     Node() // Default Constructor
     {
          value = null;
          nextNode = null;
     }
     // Constructor
     Node(G value, Node<G> nextNode)
     {
          this.value = value;
          this.nextNode = nextNode;
     }

     // Setters
     public void setNextNode(Node<G> nextNode)
     {
          this.nextNode = nextNode;
     }

     // Getters
     public Node<G> getNextNode()
     {
          return nextNode;
     }

     public G getValue ()
     {
          return value;
     }
}