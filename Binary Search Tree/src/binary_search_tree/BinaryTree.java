package binary_search_tree;

public class BinaryTree 
{
	// Fields
	private int counter, arrayCounter;
	private Node root;
	
	// Constructor
	public BinaryTree()
	{
		this.counter = 0;
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
	
	// Counter incrementer
	private void incrementCounter()
	{
		this.counter = getCounter() + 1;
	}
		
	// Counter decrementer
	private void decrementCounter()
	{
		this.counter = getCounter() - 1;
	}
	
	// Array Counter Getter
	public int getArrayCounter()
	{
		return this.arrayCounter;
	}
		
	// Array Counter Setter
	private void setArrayCounter(int arrayCounter)
	{
		this.arrayCounter = arrayCounter;
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
	
	// Is Tree empty?
	private boolean isEmpty()
	{
		return this.root == null;
	}
	
	// Method that inserts a Node containing the user inputted String in the correct lexicographic
	// position in the Binary Search Tree
	public void insert(String name)
	{
		insert(name, true);
	}
	
	// Method that inserts a Node containing the user inputted String in the correct lexicographic
	// position in the Binary Search Tree
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
			insert(getRoot(), newNode);
			
			// Rebalance the tree only if necessary
			if(checkBalance && depth() > howManyLevels())
			{
				balance();
			}
		}
	}//END insert method
	
	// Recursive insert helper
	private void insert(Node current, Node newNode)
	{
		// Prevent the same name from being inserted as a new Node
		if(newNode.getName().equalsIgnoreCase(current.getName()))
		{
			return;
		}
		// The name is lexicographically less than the current Node's name
		else if(newNode.getName().compareToIgnoreCase(current.getName()) < 0)
		{
			// Spot is open, then add name
			if(current.getLeftChild() == null)
			{
				current.setLeftChild(newNode);
				newNode.setParent(current);
				incrementCounter();
			}
			// Keep searching through the Binary Tree
			else
			{
				insert(current.getLeftChild(), newNode);
			}
		}
		// The name is lexicographically greater than the current Node's name
		else
		{
			// Spot is open, then add name
			if(current.getRightChild() == null)
			{
				current.setRightChild(newNode);
				newNode.setParent(current);
				incrementCounter();
			}
			// Keep searching through the Binary Tree
			else
			{
				insert(current.getRightChild(), newNode);
			}
		}
	}
	
	// Method that searches the binary tree for the Node that contains the inputted String
	public Node search(String name)
	{
		// Check to see if the list is empty
		if(isEmpty())
		{
			///////////////////////////////////////////
			System.out.println("The list is empty!");
			return null;
		}
		// Use the recursive search method to find the Node containing the String
		return search(getRoot(), name);
	}// END search
	
	// Recursive search helper method
	private Node search(Node traverse, String name)
	{	
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
				// Keep searching through the tree
				return search(traverse.getLeftChild(), name); 
			}
			// The name is lexicographically less than the current Node's name
			else
			{
				// Keep searching through the tree
				return search(traverse.getRightChild(), name);
			}
		}	
		// Node not found in the Binary Tree
		return null;
	} //END recursive search helper method
	
	// Method that deletes the Node containing the inputted String from the Binary Tree
	public boolean delete(String name)
	{
		// Find the location of the Node with the search method
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
				
				// Copy the String from the minimum Node to the Node we are deleting
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
			else if(getRoot().getLeftChild() == null)
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
				
				// Copy the String from the minimum Node to the Node we are deleting
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
		
		// Rebalance the tree only if necessary
		if(depth() > howManyLevels())
		{
			balance();
		}
		// A Node was deleted so return true
		return true;
	}//END delete method
	
	// Method that is used to find the Node reference to the "minimum" of the Right Subtree
	// of the Node that is to be deleted to use as the replacement
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
	// i.e. Inorder, Preorder, or Postorder
	public String print(int option)
	{
		// The binary tree hasn't been created yet
		if(isEmpty())
		{
			return "The binary tree is empty!";
		}
		
		// Create the temporary String array to write the name of the Nodes in
		// and set the global array counter to 0 for the proper insertion
		String[] temp = new String[getCounter()];
		setArrayCounter(0);
		
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
	
	// Inorder traversal recursively while writing the name of each Node into the file
	private void inorder(String[] content, Node current)
	{
		if(current != null)
		{
			inorder(content, current.getLeftChild());
			
			// Write the name of each Node into the String array
			content[getArrayCounter()] = current.getName();
			setArrayCounter(getArrayCounter() + 1);
			
			inorder(content, current.getRightChild());
		}
	}
	
	// Preorder traversal recursively while writing the name of each Node into the file
	private void preorder(String[] content, Node current)
	{
		if(current != null)
		{
			// Write the name of each Node into the String array
			content[getArrayCounter()] = current.getName();
			setArrayCounter(getArrayCounter() + 1);
			
			preorder(content, current.getLeftChild());
			preorder(content, current.getRightChild());
		}
	}
	
	// Postorder traversal recursively while writing the name of each Node into the file
	private void postorder(String[] content, Node current)
	{
		if(current != null)
		{
			preorder(content, current.getLeftChild());
			preorder(content, current.getRightChild());
			
			// Write the name of each Node into the String array
			content[getArrayCounter()] = current.getName();
			setArrayCounter(getArrayCounter() + 1);
		}
	}
	
	// Method that figures out the depth of the Binary Tree and returns it as an integer
	public int depth()
	{
		if(isEmpty())
		{
			return 0;
		}
		
		return depth(getRoot(), 1);
	}
	
	// Method that helps figure out the depth of the Binary Tree
	public int depth(Node current, int level)
	{
		// Current Node has two Children
		if(current.hasTwo())
		{
			int tempLeft = (depth(current.getLeftChild(), level + 1));
			int tempRight = (depth(current.getRightChild(), level + 1));
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
	}
	// Method that destroys the Binary Tree
	public void destroy()
	{
		setRoot(null);
		setCounter(0);
	}
	
	// Method that balances the tree uses a new Tree and the find position method
	private void balance()
	{	
		BinaryTree balancedTree = new BinaryTree();
		
		balance(balancedTree, 1, getCounter());
		
		// Add the last node of the tree that is skipped by the helper
		balancedTree.insert(find(getCounter()), false);
		
		// Make the root of the old tree point to the new tree
		setRoot(balancedTree.getRoot());
	}
	
	// Balance method recursive helper
	private void balance(BinaryTree tree, int low, int high)
	{
		if(low < high)
		{
			int median = (low + high) / 2;
			tree.insert(find(median), false);
			balance(tree, low, median);
			balance(tree, median + 1, high);
		}
	}
	// Method that finds the Node at the specified position in the Binary tree inorder and returns the String
	private String find(int position)
	{
		String[] temp = new String[getCounter()];
		setArrayCounter(0);
		
		inorder(temp ,getRoot());
		
		return temp[position - 1];
	}
	
	// Method that returns the minimum number of levels that would be needed to make a balanced tree with the
	// current number of nodes in the binary tree
	public int howManyLevels()
	{
		return (int) Math.ceil(Math.log(getCounter() + 1) / Math.log(2));
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
		
		// Node is a leaf?
		private boolean isLeaf()
		{
			return getLeftChild() == null && getRightChild() == null;
		}
		
		// Node has two children?
		private boolean hasTwo()
		{
			return getLeftChild() != null && getRightChild() != null;
		}
	} //END Node CLASS
}
