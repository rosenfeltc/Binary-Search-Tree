/* This is the Main.java class that contains the main method that uses BinaryTree.java class to create the tree
 * and uses GUI.java class to handle the user interaction through a graphical interface. 
 * Coded by Christopher Rosenfelt for CSI 213
 */
package binary_search_tree;

public class Main
{
	public static void main(String[] args)
	{
		// Create Binary Tree reference and instantiate the tree
		BinaryTree tree = new BinaryTree();
		
		// Create GUI reference and instantiate it by passing the tree as a parameter
		GUI window = new GUI(tree);
	}
}
