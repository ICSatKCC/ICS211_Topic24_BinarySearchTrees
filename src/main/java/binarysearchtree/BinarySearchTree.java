package binarysearchtree;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Generic class for a binary search tree.
 * 
 * @author Lisa Miller from William McDaniel Albritton
 * @param <T> the type of items held in tree.
 */
public class BinarySearchTree<T extends Comparable<T>> {

  /** Root node instance variable. */
   private BinaryNode<T> root = null;

  /** No parameter constructor. */
   public BinarySearchTree() {
      // root already initialized
   }

  /**
   * Adds an item to the tree.
   * Non-recursive wrapper method
   * 
   * @param item The object to be added
   */
   public void add(T item) {
   // calls a recursive, private method
   // cannot get direct access to the root outside the class
      root = add(root, item);
   }
  
  /**
   * Recusively adds an item to the tree.
   * 
   * @param node The root of the tree/subtree
   * @param item The object to be added
   * @return The current node
   */
   private BinaryNode<T> add(BinaryNode<T> node, T item) {
   // base case: empty tree or end of a leaf
      if (node == null) {
         return new BinaryNode<T>(item, null, null);
      }
      // base case: duplicate node, so throw exception
      else if (item.compareTo(node.getData()) == 0) {
         throw new TreeException("No duplicate items are allowed!");
      }
      // recursive case: if item is less than current node
      // then move to left child node
      else if (item.compareTo(node.getData()) < 0) {
      // set the node's left child to the
      // left subtree with item added
         node.setLeftChild(this.add(node.getLeftChild(), item));
         return node;
      }
      // recursive case: if item is greater than current node
      // then traverse to right child node
      else {
      // set the node's right child to the
      // right subtree with item added
         node.setRightChild(this.add(node.getRightChild(), item));
         return node;
      }
   }

  /**
   * called automatically by println/print method.
   * 
   * @return an inorder String of the tree
   */
   public String toString() {
      return this.inOrder(root);
   }

  /**
   * inOrder display of nodes, with newline between each node.
   * 
   * @param node The root of the tree/subtree
   * @return an inorder String of the tree
   */
   private String inOrder(BinaryNode<T> node) {
      String displayNodes = "";
      if (node != null) {
         displayNodes = displayNodes 
            + this.inOrder(node.getLeftChild());
         displayNodes = displayNodes + node.toString() + ", ";
         displayNodes = displayNodes 
            + this.inOrder(node.getRightChild());
      }
      return displayNodes;
   }

  /**
   * Pre-Order traversal of tree.
   * 
   * @return String representation of preOrder
   */
   public String preOrder() {
      return this.preOrder(root);
   }

  /**
   * Recursive preOrder traversal of tree.
   * 
   * @param node The root of the tree/subtree
   * @return a preOrder String of the tree
   */
   private String preOrder(BinaryNode<T> node) {
      String displayNodes = "";
      if (node != null) {
         displayNodes = displayNodes + node.toString() + ", ";
         displayNodes = displayNodes
            + this.preOrder(node.getLeftChild());
         displayNodes = displayNodes 
            + this.preOrder(node.getRightChild());
      }
      return displayNodes;
   }

  /**
   * Post-Order traversal of tree.
   * 
   * @return a postOrder String of the tree
   */
   public String postOrder() {
      return this.postOrder(root);
   }

  /**
   * Recursive postOrder traversal of tree.
   * To display nodes, with newline between each node
   * 
   * @param node The root of the tree/subtree
   * @return a post-Order String of the tree
   */
   private String postOrder(BinaryNode<T> node) {
      String displayNodes = "";
      if (node != null) {
         displayNodes = displayNodes + this.postOrder(node.getLeftChild());
         displayNodes = displayNodes + this.postOrder(node.getRightChild());
         displayNodes = displayNodes + node + ", ";
      }
      return displayNodes;
   }

  /**
   * gets an item from the tree with the same search key.
   * 
   * @param searchKey1 An object containing the search key
   * @return the data item in the tree with matching key.
   */
   public T get(T searchKey1) {
   // cannot get direct access to the root outside the class
      return this.get(root, searchKey1);
   }

  /**
   * Recursive method to get an item from the tree.
   * 
   * @param node The root of the tree/subtree
   * @param searchKey2 An object storing the key to get.
   * @return the data item in tree with matching key.
   * @throws TreeException if item not found
   */
   private T get(BinaryNode<T> node, T searchKey2) {
   // if not found, throw exception
      if (node == null) {
         throw new TreeException("Item not found!");
      } 
      else {
      // if the search key matches, return the item's address
         if (searchKey2.compareTo(node.getData()) == 0) {
            return node.getData();
         }
         // if the search key of the searchKey is less than the node,
         // then search the left subtree
         else if (searchKey2.compareTo(node.getData()) < 0) {
            return this.get(node.getLeftChild(), searchKey2);
         }
         // if the search key of the searchKey is greater than the node,
         // then search the right subtree
         else {
            return this.get(node.getRightChild(), searchKey2);
         }
      }
   }

  /**
   * Removes an item from the tree.
   * 
   * @param searchKey3 An object storing the key to remove.
   */
   public void remove(T searchKey3) {
      root = this.remove(root, searchKey3);
   }

  /**
   * Recursively removes an item from the tree.   
   * 
   * @param node The root of the tree/subtree
   * @param searchKey4 An object storing only the key to remove.
   * @return root of current subtree.
   * @throws TreeException if item not found in tree.
   */
   private BinaryNode<T> remove(BinaryNode<T> node, T searchKey4) {
   // if item not found, throw exception
      if (node == null) {
         throw new TreeException("Item not found!");
      }
      // if search key is less than node's search key,
      // continue to left subtree
      else if (searchKey4.compareTo(node.getData()) < 0) {
         node.setLeftChild(this.remove(node.getLeftChild(), searchKey4));
         return node;
      }
      // if search key is greater than node's search key,
      // continue to right subtree
      else if (searchKey4.compareTo(node.getData()) > 0) {
         node.setRightChild(this.remove(node.getRightChild(), searchKey4));
         return node;
      }
      // found node containing object with same search key,
      // so delete it
      else {
      // call private method remove
         node = this.remove(node);
         return node;
      }
   }

  /**
   * Helper method that takes a node out of tree.
   * 
   * @param node The node to remove
   * @return The node that replaces removed node or null.
   */
   private BinaryNode<T> remove(BinaryNode<T> node) {
   // if node is a leaf,return null
      if (node.getLeftChild() == null && node.getRightChild() == null) {
         return null;
      }
      // if node has a single right child node,
      // then return a reference to the right child node
      else if (node.getLeftChild() == null) {
         return node.getRightChild();
      }
      // if node has a single left child node,
      // then return a reference to the left child node
      else if (node.getRightChild() == null) {
         return node.getLeftChild();
      }
      // if the node has two child nodes
      else {
      // get next Smaller Item, which is Largest Item in Left Subtree
      // The next Smaller Item is stored at the rightmost node in the left
      // subtree.
         T largestItemInLeftSubtree = this.getItemWithLargestSearchKey(node
             .getLeftChild());
      // replace the node's item with this item
         node.setData(largestItemInLeftSubtree);
      // delete the rightmost node in the left subtree
         node.setLeftChild(this.removeNodeWithLargestSearchKey(node
             .getLeftChild()));
         return node;
      }
   }

  /**
   * Returns the item with the largest search key in the (sub)tree.
   * Helper method for removing interior nodes.
   * @param node The root of the tree/subtree
   * @return The data item with largest key
   */
   private T getItemWithLargestSearchKey(BinaryNode<T> node) {
   // if no right child, then this node contains the largest item
      if (node.getRightChild() == null) {
         return node.getData();
      }
      // if not, keep looking on the right
      else {
         return this.getItemWithLargestSearchKey(node.getRightChild());
      }
   }

  /**
   * Removes the node with the largest search key.
   * Helper method for removing interior nodes.
   * Remove the node formerly occupied by item with largest search key.
   * To be called after item is moved to new node location.
   * 
   * @param node The root of the tree/subtree
   * @return root of (sub)tree with node removed.
   */
   private BinaryNode<T> removeNodeWithLargestSearchKey(BinaryNode<T> node) {
   // if no right child, then this node contains the largest item
   // so replace it with its left child
      if (node.getRightChild() == null) {
         return node.getLeftChild();
      }
      // if not, keep looking on the right
      else {
         node.setRightChild(this.removeNodeWithLargestSearchKey(node
             .getRightChild()));
         return node;
      }
   }

   /** Breadth-first traversal to display the tree 
    * 
   */
   public void breadthFirstDisplay() {
      if (root == null) {
         return;
      }

      Queue<BinaryNode<T>> queue = new LinkedList<>();
      
 
      int largestLevel = 0;
      int spacing = 0;
      int level = 0;
      int totalNodes = 0;
      int totalStringLength = 0;

      String spaces = " ";
      ArrayList<ArrayList<T>> levels = new ArrayList<>();
      ArrayList<T> currentLevel = new ArrayList<>();

      queue.offer(root);
      currentLevel.add(root.getData());
      totalNodes++;
      totalStringLength += root.getData().toString().length();
      levels.add(currentLevel);
      // traverse to put all levels in their own arraylist
      while (!queue.isEmpty()) {
         level++;
         //store previous level
         ArrayList<T> previousLevel = currentLevel;
         currentLevel = new ArrayList<>((int) Math.pow(2, level));
         for (int i = 0; i < previousLevel.size(); i++) {
            if (previousLevel.get(i) == null) { // if parent is null, add two nulls to maintain structure
               currentLevel.add(null);
               currentLevel.add(null);
            }  else {
               BinaryNode<T> node = queue.poll();
               if (node.getLeftChild() != null) {
                  queue.offer(node.getLeftChild());
                  currentLevel.add(node.getLeftChild().getData());
                  totalNodes++;
                  totalStringLength += node.getLeftChild().getData().toString().length();
               } else {
                  currentLevel.add(null);
               }
               if (node.getRightChild() != null) {
                  queue.offer(node.getRightChild());
                  currentLevel.add(node.getRightChild().getData());
                  totalNodes++;
                  totalStringLength += node.getRightChild().getData().toString().length();
               } else {
                  currentLevel.add(null);
               }
            }
         }
         levels.add(currentLevel);
      }
      largestLevel =  (int) Math.pow(2, level-1) * (totalStringLength / totalNodes); //  
      spacing = (largestLevel + 1) / 2;
      System.out.println("Breadth-First Display of Tree:");
      // System.out.println("Largest level size for spacing: " + largestLevel);
      // System.out.println("Total nodes: " + totalNodes);  
      // System.out.println("Total string length: " + totalStringLength);
      // System.out.println("Initial Spacing: " + spacing);
      levels.remove(levels.size()-1)   ; //remove last level if all nulls
      for (ArrayList<T> lev : levels) {
        
         System.out.print(spaces.repeat(spacing));
         for (T item : lev) {
            if (item != null) {
               System.out.print(" " + item + spaces.repeat(spacing));
            } else { //print average spacing for null nodes
               System.out.print(spaces.repeat(spacing + 4));
            }
         }
         System.out.println("");
         System.out.print(spaces.repeat(spacing));
         for (T item : lev) {
            if (item != null) {
               System.out.print("/" + spaces.repeat(item.toString().length()) + "\\" + spaces.repeat(spacing));
            } else { // print average spacing for null nodes
               System.out.print(spaces.repeat(spacing + 4));
            }
         }
         System.out.println("\n");
         spacing = spacing /2;
      }
   }

  /**
   * Driver code to test class.
   * 
   * @param args are not used
   */
   public static void main(String[] args) {
   // using BinaryNode<String>
      BinarySearchTree<String> tree = new BinarySearchTree<String>();
      System.out.println("TEST add() method:");
      System.out.println("Adding ohua:");
      tree.add("ohua");
      System.out.println("Adding panuhunuhu:");
      tree.add("panuhunuhu");
      System.out.println("Adding kahaha:");
      tree.add("kahaha");
      System.out.println("Adding oama:");    
      tree.add("oama");
      System.out.println("Adding moilii:");  
      tree.add("moilii");
      System.out.println("Adding palamoi:");
      tree.add("palamoi");
      System.out.println("Adding anae:");
      tree.add("anae");
      System.out.println("Adding amaama:");
      tree.add("amaama");
      System.out.println("Adding moimana:");
      tree.add("moimana");
      System.out.println("Adding uhu:");
      tree.add("uhu");
      System.out.println("Adding wekea:");
      tree.add("wekea");
      System.out.println("Adding wekeula:");
      tree.add("wekeula");
      
      tree.breadthFirstDisplay();
      System.out.println();
      System.out.println("preorder traversal:\n" + tree.preOrder());
      System.out.println("inorder traversal:\n" + tree.toString());
      System.out.println("postorder traversal:\n" + tree.postOrder());
   
   // test get
      System.out.println("TEST get() method:");
      String fish = tree.get("kahaha");
      System.out.println("Got: " + fish);
      fish = tree.get("wekea");
      System.out.println("Got: " + fish);
      try {
         System.out.println("Trying to get item not in tree (ahi): ");
         fish = tree.get("ahi");
         System.out.println("Got: " + fish);
      } 
      catch (TreeException exception) {
         System.out.println(exception.toString());
      }
      fish = tree.get("uhu");
      System.out.println("Got: " + fish);
      tree.breadthFirstDisplay();
   // test remove
      System.out.println("\nTEST remove() method:");

      tree.remove("ohua");
      System.out.println("After removing ohua:");
      tree.breadthFirstDisplay();
      tree.remove("wekeula");
      System.out.println("After removing wekeula:");
      tree.breadthFirstDisplay();
      tree.remove("palamoi");
      System.out.println("After removing palamoi:");
      tree.breadthFirstDisplay();
      tree.remove("oama");
      System.out.println("After removing oama:");
      tree.breadthFirstDisplay();
   } // end of main
} // end of class

