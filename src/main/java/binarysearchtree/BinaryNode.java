package binarysearchtree;
/**
 * Generic class for a binary node.
 * 
 * @author Lisa Miller from W Albritton
 * @param <E> The datatype held in Nodes.
 */
public class BinaryNode<E> {
	// data fields
  /** Data held in Node. */
   private E data;
   /** Link to left child Node. */
   private BinaryNode<E> left;
   /** Link to right child Node. */
   private BinaryNode<E> right;

	/**
	 * Constructor.
	 * 
	 * @param d The address of the object that is stored by the node
	 * @param l The address of the left child
	 * @param r The address of the right child
	 */
   public BinaryNode(E d, BinaryNode<E> l,
   		BinaryNode<E> r) {
      data = d;
      left = l;
      right = r;
   }

	/**
	 * Automatically called by println() or print() method.
	 * 
	 * @return the item's string
	 */
   public String toString() {
      String display = data.toString();
      return display;
   }

	/**
	 * Accessor method.
	 * 
	 * @return the item's address
	 */
   public E getData() {
      return data;
   }

	/**
	 * Mutator method.
	 * 
	 * @param d
	 *            is the item's address
	 */
   public void setData(E d) {
      data = d;
   }

	/**
	 * Accessor method.
	 * 
	 * @return the left child's address
	 */
   public BinaryNode<E> getLeftChild() {
      return left;
   }

	/**
	 * Mutator method.
	 * 
	 * @param l
	 *            is the left child's address
	 */
   public void setLeftChild(BinaryNode<E> l) {
      left = l;
   }

	/**
	 * Accessor method.
	 * 
	 * @return the right child's address
	 */
   public BinaryNode<E> getRightChild() {
      return right;
   }

	/**
	 * Mutator method.
	 * 
	 * @param r
	 *            is the right child's address
	 */
   public void setRightChild(BinaryNode<E> r) {
      right = r;
   }

	/**
	 * Driver code to test class.
	 * 
	 * @param args
	 *            are not used
	 */
   public static void main(String[] args) {
   	// using BinaryNode<String>
      System.out.println("test 3 BinaryNode<String> nodes:");
      BinaryNode<String> node1 = new BinaryNode<String>("A", null, null);
      BinaryNode<String> node2 = new BinaryNode<String>("B", null, null);
      BinaryNode<String> node3 = new BinaryNode<String>("C", node1, node2);
      System.out.println("root = " + node3.toString());
      System.out.println("left child = " + node3.getLeftChild().toString());
      System.out.println("right child = " + node3.getRightChild().toString());
   	//change left child's data to "X"
      BinaryNode<String> nodeX = node3.getLeftChild();
      nodeX.setData("X");
      System.out.println(nodeX.toString());
   
   	// using BinaryNode<Integer>
      System.out.println("\ntest 3 BinaryNode<Integer> nodes:");
      BinaryNode<Integer> nodeA = new BinaryNode<Integer>(10, null, null);
      BinaryNode<Integer> nodeB = new BinaryNode<Integer>(30, null, null);
      BinaryNode<Integer> nodeC = new BinaryNode<Integer>(20, nodeA, nodeB);
      System.out.println("root = " + nodeC);
      System.out.println("left child = " + nodeC.getLeftChild());
      System.out.println("right child = " + nodeC.getRightChild());
   
   	// test accessor and mutator methods
      System.out.println("\ntest accessor and mutator methods:");
      System.out.println("(set right child to left child's data + 100)");
      nodeB.setData(nodeA.getData() + 100);
      System.out.println("right child = " + nodeC.getRightChild());
      System.out.println("(switch left and right child nodes)");
      nodeC.setLeftChild(nodeB);
      nodeC.setRightChild(nodeA);
      System.out.println("left child = " + nodeC.getLeftChild());
      System.out.println("right child = " + nodeC.getRightChild());
   } //end of main
} // end of class

/*
PROGRAM OUTPUT:

test 3 BinaryNode<String> nodes:
root = C
left child = A
right child = B
X

test 3 BinaryNode<Integer> nodes:
root = 20
left child = 10
right child = 30

test accessor and mutator methods:
(set right child to left child's data + 100)
right child = 110
(switch left and right child nodes)
left child = 110
right child = 10

*/
