/* This is the BinaryTree.java class which handles the creation and all the operations on a Binary Search Tree.
 * This class also contains the Private class Node which handles the creation and all the operations on a single Node
 * inside the Binary Search Tree.
 * Coded by Christopher Rosenfelt for CSI 213
 */
package binary_search_tree;

public class BinaryTree 
{
	// Fields - counter keeps track of how many Nodes are in the tree
	// while arrayCounter is used for the operations that require putting
	// the information from the Nodes in the tree into an Array for further usage
	private int counter, arrayCounter;
	private Node root;
	
	// Constructor for BinaryTree class
	public BinaryTree()
	{
		// Initialize field values
		this.counter = 0;
		this.arrayCounter = 0;
		this.root = null;
	}
	
	// Counter Getter
	public int getCounter()
	{
		return this.counter;
	}
	
	// Counter Setter
	private void setCounter(int counter)
	{
		this.counter = counter;
	}
	
	// Counter incrementer - increment by 1 to track each Node insertion
	private void incrementCounter()
	{
		this.counter++;
	}
		
	// Counter decrementer - decrement by 1 to track each Node deletion
	private void decrementCounter()
	{
		this.counter--;
	}
	
	// Array Counter Getter
	private int getArrayCounter()
	{
		return this.arrayCounter;
	}
		
	// Array Counter Setter
	private void setArrayCounter(int arrayCounter)
	{
		this.arrayCounter = arrayCounter;
	}
	
	// Array Counter incrementer
	private void incrementArrayCounter()
	{
		this.arrayCounter++;
	}
	
	// Root Getter
	private Node getRoot()
	{
		return this.root;
	}
	
	// Root Setter
	private void setRoot(Node root)
	{
		this.root = root;
	}
	
	// Method that determines and returns if the tree is currently empty
	private boolean isEmpty()
	{
		return getRoot() == null;
	}
	
	// Method that inserts a Node containing the user inputted String in the correct lexicographic
	// position in the Binary Search Tree. Calls on the insert method passing the String as well as the
	// boolean true to ensure that the insertion method balances the tree after each Node insertion if necessary
	public void insert(String name)
	{
		insert(name, true);
	}
	
	// Method that inserts a Node containing the user inputted String in the correct lexicographic
	// position in the Binary Search Tree, may check to balance the tree depending on whether true or false was
	// passed into its checkBalance boolean
	private void insert(String name, boolean checkBalance)
	{
		// Create the Node with the given String
		Node newNode = new Node(name);
		
		// Check to see if the Tree is currently empty
		if(isEmpty())
		{
			// Add the Node by setting the root to point to it and increment counter
			setRoot(newNode);
			incrementCounter();
		}
		// Tree is not empty so use the insert helper to place the Node in the correct spot
		else
		{
			// Passing the Root reference as the starting point for the Node insertion
			// as well as the reference to the newly create Node
			insert(getRoot(), newNode);
			
			// Balance the tree only if necessary - ie. checkBalance is true and
			// the current depth of the tree is greater than what it should be based on 
			// the total amount of Nodes in the tree
			if(checkBalance && depth() > howManyLevels())
			{
				// Call the balance method to balance the tree
				balance();
			}
		}
	}//END insert method
	
	// Recursive insert helper - recursively checks where the newNode should be inserted until it finds
	// the appropriate placement in the Binary Search Tree
	private void insert(Node current, Node newNode)
	{
		// Prevent the same name from being inserted as a new Node by doing nothing
		if(newNode.getName().equalsIgnoreCase(current.getName()))
		{
			return;
		}
		// The name is lexicographically less than the current Node's name
		else if(newNode.getName().compareToIgnoreCase(current.getName()) < 0)
		{
			// Spot is open, add name
			if(current.getLeftChild() == null)
			{
				current.setLeftChild(newNode);
				newNode.setParent(current);
				incrementCounter();
			}
			// Spot is occupied, keep searching through the Binary Tree recursively
			else
			{
				insert(current.getLeftChild(), newNode);
			}
		}
		// The name is lexicographically greater than the current Node's name
		else
		{
			// Spot is open, add name
			if(current.getRightChild() == null)
			{
				current.setRightChild(newNode);
				newNode.setParent(current);
				incrementCounter();
			}
			// Spot is occupied, keep searching through the Binary Tree recursively
			else
			{
				insert(current.getRightChild(), newNode);
			}
		}
	}// END recursive insert method
	
	// Method that searches the binary tree for the Node that contains the inputted String
	// used by delete method to find the appropriate Node to delete
	private Node search(String name)
	{
		// Check to see if the list is empty
		if(isEmpty())
		{
			// Return null which will be checked for by the method that called it
			return null;
		}
		// Tree is not empty so use the recursive search method starting at the root reference 
		// to find the correct Node containing the String name
		return search(getRoot(), name);
	}// END search
	
	// Recursive search helper method, recursively traverse the tree looking for the Node with
	// the passed in String name, returns the reference to that Node if found otherwise returns null
	private Node search(Node traverse, String name)
	{	
		// Haven't reach a dead-end yet
		if(traverse != null)
		{
			// Check to see if the current Node matches the String
			if(name.equalsIgnoreCase(traverse.getName()))
			{
				// return the location of the node
				return traverse;
			}
			// The name is lexicographically less than the current Node's name
			else if(name.compareToIgnoreCase(traverse.getName()) < 0)
			{
				// Keep searching through the left subtree
				return search(traverse.getLeftChild(), name); 
			}
			// The name is lexicographically greater than the current Node's name
			else
			{
				// Keep searching through the right subtree
				return search(traverse.getRightChild(), name);
			}
		}	
		// Node not found in the Binary Tree
		return null;
	} //END recursive search helper method
	
	// Method that deletes the Node containing the inputted String from the Binary Tree
	public boolean delete(String name)
	{
		// Find the location of the Node with the search method and assign it to current
		Node current = search(name);
		
		// Return False if the Node was not found
		if(current == null)
		{
			return false;
		}
		
		// Special case of deleting the root Node
		if(current == getRoot())
		{
			// Root has no children (aka is a leaf)
			if(getRoot().isLeaf())
			{
				// Deleting the Root (aka destroying the Binary Tree)
				setRoot(null);
				decrementCounter();
			}
			// Root only has a left child
			else if(getRoot().getRightChild() == null)
			{
				// Make the left child of the deleted Root the new Root
				setRoot(current.getLeftChild());
				current.getLeftChild().setParent(null);
				decrementCounter();
			}
			// Root only has a right child
			else if(getRoot().getLeftChild() == null)
			{
				// Make the right child of the deleted Root the new Root
				setRoot(current.getRightChild());
				current.getRightChild().setParent(null);
				decrementCounter();
			}
			// Root has two children
			else
			{
				// Find the minimum of the Right Subtree of the Node to delete to use as the replacement
				Node minimum = findMinimum(current.getRightChild());
				
				// Copy the String from the minimum Node to the original Node we were supposed to delete
				current.setName(minimum.getName());
				
				// Delete what used to be the minimum from the Tree
				// The minimum is the right child of its parent
				if(minimum.getParent().getRightChild() == minimum)
				{
					// Minimum by definition cannot have a left child, however:
					// The minimum has no children
					if(minimum.getRightChild() == null)
					{
						// Just delete the minimum
						minimum.getParent().setRightChild(null);
					}
					// The minimum has a right child
					else
					{
						// Delete the minimum but connect its child and its parent
						minimum.getParent().setRightChild(minimum.getRightChild());
						minimum.getRightChild().setParent(minimum.getParent());
					}
					
					// Node was deleted so adjust the counter accordingly
					decrementCounter();
				}
				// The minimum is the left child of its parent
				else
				{
					// Minimum by definition cannot have a left child, however:
					// The minimum has no children
					if(minimum.getRightChild() == null)
					{
						// Just delete the minimum
						minimum.getParent().setLeftChild(null);
					}
					// The minimum has a right child
					else
					{
						// Delete the minimum but connect its child and its parent
						minimum.getParent().setLeftChild(minimum.getRightChild());
						minimum.getRightChild().setParent(minimum.getParent());
					}
					
					// Node was deleted so adjust the counter accordingly
					decrementCounter();
				}
			}
		}// END special root case
		// Deleting any other Node besides the root
		else
		{
			// Node to delete has no children (aka is a leaf)
			if(current.isLeaf())
			{
				// Delete the Node by finding out what type of child it is
				// Node is a left child
				if(current.getParent().getLeftChild() == current)
				{
					current.getParent().setLeftChild(null);
				}
				// Node is a right child
				else
				{
					current.getParent().setRightChild(null);
				}
				// Node was deleted so adjust the counter accordingly	
				decrementCounter();
			}
			// Node to delete only has a left child
			else if(current.getRightChild() == null)
			{
				// Connect the Node to delete's parent and left child
				// Need to first find out what type of child the Node to delete is
				// Node is a left child
				if(current.getParent().getLeftChild() == current)
				{
					current.getLeftChild().setParent(current.getParent());
					current.getParent().setLeftChild(current.getLeftChild());
				}
				// Node is a right child
				else
				{
					current.getLeftChild().setParent(current.getParent());
					current.getParent().setRightChild(current.getLeftChild());
				}
					// Node was deleted so adjust the counter accordingly
					decrementCounter();
			}
			// Node to delete only has a right child
			else if(current.getLeftChild() == null)
			{
				// Connect the Node to delete's parent and right child
				// Need to first find out what type of child the Node to delete is
				// Node is a left child
				if(current.getParent().getLeftChild() == current)
				{
					current.getRightChild().setParent(current.getParent());
					current.getParent().setLeftChild(current.getRightChild());
				}
				// Node is a right child
				else
				{
					current.getRightChild().setParent(current.getParent());
					current.getParent().setRightChild(current.getRightChild());
				}
					// Node was deleted so adjust the counter accordingly
					decrementCounter();
			}
			// Node to delete has two children
			else
			{
				// Find the minimum of the Right Subtree of the Node to delete to use as the replacement
				Node minimum = findMinimum(current.getRightChild());
				
				// Copy the String from the minimum Node to the Node we were supposed to delete
				current.setName(minimum.getName());
				
				// Delete what used to be the minimum from the Tree
				// The minimum is the right child of its parent
				if(minimum.getParent().getRightChild() == minimum)
				{
					// Minimum by definition cannot have a left child, however:
					// The minimum has no children
					if(minimum.getRightChild() == null)
					{
						// Just delete the minimum
						minimum.getParent().setRightChild(null);
					}
					// The minimum has a right child
					else
					{
						// Delete the minimum but connect its child and its parent
						minimum.getParent().setRightChild(minimum.getRightChild());
						minimum.getRightChild().setParent(minimum.getParent());
					}
					
					// Node was deleted so adjust the counter accordingly
					decrementCounter();
				}
				// The minimum is the left child of its parent
				else
				{
					// Minimum by definition cannot have a left child, however:
					// The minimum has no children
					if(minimum.getRightChild() == null)
					{
						// Just delete the minimum
						minimum.getParent().setLeftChild(null);
					}
					// The minimum has a right child
					else
					{
						// Delete the minimum but connect its child and its parent
						minimum.getParent().setLeftChild(minimum.getRightChild());
						minimum.getRightChild().setParent(minimum.getParent());
					}
					
					// Node was deleted so adjust the counter accordingly
					decrementCounter();
				}
			}			
		}// END of deleting any case except for Root
		
		// Balance the tree only if necessary - the current depth of the tree is greater than what it should be based on 
		// the total amount of Nodes in the tree
		if(depth() > howManyLevels())
		{
			balance();
		}
		// A Node was deleted so return true
		return true;
	}//END delete method
	
	// Method that is used to find the Node reference to the "minimum" of the Right Subtree
	// of the Node that is to be deleted to use as the replacement. The Node current is initially passed in
	// as the right subtree of the Node to delete
	private Node findMinimum(Node current)
	{
		// Current Node is the minimum
		if(current.getLeftChild() == null)
		{
			return current;
		}
		// Current Node is not the minimum so keep traversing the left subtree of current Node
		else
		{
			return findMinimum(current.getLeftChild());
		}
	}
	
	// Method that prints the tree based on the traversal order that is passed in by returning a newline String per Node name
	// i.e. Inorder, Preorder, or Postorder - it uses a String array as a helper in Storing the String of the Nodes in the tree in 
	// the selected traversing order
	public String print(int option)
	{
		// The binary tree hasn't been created yet so no Nodes exist
		if(isEmpty())
		{
			return "The binary tree is empty!";
		}
		
		// Create the temporary String array to write the name of the Nodes in
		// and set the array counter to 0 for the proper insertion
		String[] temp = new String[getCounter()]; // getCounter() returns the number of Nodes currently in the Tree
		setArrayCounter(0);
		
		// Option is passed into the method to determine which traversing order to use
		switch(option)
		{
			case 0:
				inorder(temp, getRoot());
				break;
			case 1:
				preorder(temp, getRoot());
				break;
			case 2:
				postorder(temp,getRoot());
				break;
		}
		
		// Create the empty String that will be storing the names of each Node
		String content = new String();
		for(int i = 0; i < temp.length; i++)
		{
			content += temp[i] + "\n";
		}
		
		return content;
	}
	
	// Inorder recursive traversal while writing the name of each Node into the String array
	private void inorder(String[] content, Node current)
	{
		if(current != null)
		{
			// Left recursive traversal
			inorder(content, current.getLeftChild());
			
			// Write the name of the current Node into the String array
			content[getArrayCounter()] = current.getName();
			incrementArrayCounter();
			
			// Right recursive traversal
			inorder(content, current.getRightChild());
		}
	}//END inorder method
	
	// Preorder recursive traversal while writing the name of each Node into the String array
	private void preorder(String[] content, Node current)
	{
		if(current != null)
		{
			// Write the name of the current Node into the String array
			content[getArrayCounter()] = current.getName();
			incrementArrayCounter();
			
			// Left recursive traversal
			preorder(content, current.getLeftChild());
			// Right recursive traversal
			preorder(content, current.getRightChild());
		}
	}//END preorder method
	
	// Postorder recursive traversal while writing the name of each Node into the String array
	private void postorder(String[] content, Node current)
	{
		if(current != null)
		{
			// Left recursive traversal
			postorder(content, current.getLeftChild());
			// Right recursive traversal
			postorder(content, current.getRightChild());
			
			// Write the name of the current Node into the String array
			content[getArrayCounter()] = current.getName();
			incrementArrayCounter();
		}
	}//END postorder method
	
	// Method that figures out the depth of the Binary Tree and returns it as an integer
	private int depth()
	{
		// If tree is empty then there are 0 levels
		if(isEmpty())
		{
			return 0;
		}
		// Tree is not empty so use recursive depth method start at Root level 1 to figure out depth
		return depth(getRoot(), 1);
	}
	
	// Method that helps figure out the depth of the Binary Tree
	private int depth(Node current, int level)
	{
		// Current Node has two Children
		if(current.hasTwo())
		{
			// Traverse left subtree and obtain its level
			int tempLeft = (depth(current.getLeftChild(), level + 1));
			// Traverse right subtree and obtain its level
			int tempRight = (depth(current.getRightChild(), level + 1));
			// Return the maximum of the levels that were traversed by the left and right subtrees
			return Math.max(tempLeft, tempRight);
		}
		// Current Node has no children (aka is a leaf)
		else if(current.isLeaf())
		{
			return level;
		}
		// Current Node only has right child
		else if(current.getLeftChild() == null)
		{
			return depth(current.getRightChild(), level + 1);
		}
		// Current Node only has left child
		else
		{
			return depth(current.getLeftChild(), level + 1);
		}
	}//END depth method
	
	// Method that destroys the Binary Tree
	public void destroy()
	{
		setRoot(null);
		setCounter(0);
	}
	
	// Method that balances the tree by creating a new Tree and using the balance recursive helper method
	private void balance()
	{	
		BinaryTree balancedTree = new BinaryTree();
		
		// Temporary String array to store the node names of the old tree inorder
		String[] temp = new String[getCounter()];
		setArrayCounter(0);
				
		// use the inorder method to store the node names in the String array
		inorder(temp ,getRoot());
		
		// Call the balance method recursive helper to insert the Nodes into the new Tree in a "balanced" order
		balance(balancedTree, temp, 0, getCounter());
		
		// Make the root of the old tree point to the new tree
		setRoot(balancedTree.getRoot());
	}
	
	// Balance recursive helper method - recursively finds the median of each subtree of the oldtree at the median spot
	// using the find method to find the median Node each time and return its String name
	private void balance(BinaryTree tree, String[] temp, int low, int high)
	{
		
		if(low < high)
		{
			// The current median
			int median = (low + high) / 2;
			
			// Insert the median in the new Tree send false so that the insert method doesn't 
			// attempt to balance the tree since its being inserted into a balance position as is
			tree.insert(temp[median], false);
			// balance the left subtree of the current median
			balance(tree, temp, low, median);
			// balance the right subtree of the current median
			balance(tree, temp, median + 1, high);
		}
	}//END balance recursive helper method
	
	// Method that returns the minimum number of levels that would be needed to make a balanced tree with the
	// current number of nodes in the binary tree
	public int howManyLevels()
	{
		// Formula is the ceiling of the log base 2 of the current number of nodes + 1
		return (int) Math.ceil(Math.log(getCounter() + 1) / Math.log(2));
	}
	
	public void toDraw(String[] temp)
	{
		setArrayCounter(0);
		preorder(temp, getRoot());
	}
	
	// Private Node class
	private class Node
	{
		// Fields
		private String name;
		private Node leftChild;
		private Node rightChild;
		private Node parent;
		
		// Constructor
		private Node(String name)
		{
			this.name = name;
			this.leftChild = null;
			this.rightChild = null;
			this.parent = null;
		}
		
		// Name Getter
		private String getName()
		{
			return this.name;
		}
		
		// Name Setter
		private void setName(String name)
		{
			this.name = name;
		}
		
		// Left Child Getter
		private Node getLeftChild()
		{
			return this.leftChild;
		}
		
		// Left Child Setter
		private void setLeftChild(Node leftChild)
		{
			this.leftChild = leftChild;
		}
		
		// Right Child Getter
		private Node getRightChild()
		{
			return this.rightChild;
		}
		
		// Right Child Setter
		private void setRightChild(Node rightChild)
		{
			this.rightChild = rightChild;
		}
		
		// Parent Getter
		private Node getParent()
		{
			return this.parent;
		}
		
		// Parent Setter
		private void setParent(Node parent)
		{
			this.parent = parent;
		}
		
		// Method that determines and returns whether the Node is a leaf
		private boolean isLeaf()
		{
			return getLeftChild() == null && getRightChild() == null;
		}
		
		// Method that determines and returns whether the Node has two children
		private boolean hasTwo()
		{
			return getLeftChild() != null && getRightChild() != null;
		}
	} //END Node CLASS
}
