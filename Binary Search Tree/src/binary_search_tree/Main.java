package binary_search_tree;

public class Main 
{
	public static void main(String[] args)
	{
		BinaryTree tree = new BinaryTree();
	
		tree.insert("Alice");
		tree.insert("Amanda");
		tree.insert("Blaine");
		tree.insert("Chris");
		tree.insert("David");
		tree.insert("Frank");
		tree.insert("Roy");
		tree.insert("Zach");
		
		
		System.out.println("*************************");
		System.out.print(tree.print(0));
		System.out.println("The number of nodes in the tree are " + tree.getCounter());
		System.out.println("The balanced level of the tree should be " + tree.howManyLevels());
		System.out.println("The level of the tree is " + tree.depth());
		System.out.println("*************************");
		System.out.print(tree.print(1));
		
		tree.delete("David");
		tree.delete("Frank");
		tree.delete("Zach");
		tree.delete("Roy");
		tree.delete("Blaine");
		
		System.out.println("*************************");
		System.out.print(tree.print(0));
		System.out.println("The number of nodes in the tree are " + tree.getCounter());
		System.out.println("The balanced level of the tree should be " + tree.howManyLevels());
		System.out.println("The level of the tree is " + tree.depth());
		System.out.println("*************************");
		System.out.print(tree.print(1));
	}
}
