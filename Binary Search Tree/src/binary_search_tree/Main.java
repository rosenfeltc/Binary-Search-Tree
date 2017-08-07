package binary_search_tree;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class Main extends JFrame 
{
	final BinaryTree tree = new BinaryTree();
	
	public static void main(String[] args)
	{
		mainMenu();
	}
	
	public static void mainMenu()
	{
		Main window = new Main();
	}
	
	public Main()
	{	
		// The JRadioButtons and text
		JLabel type = new JLabel("Traversal type:");
		JRadioButton inorder = new JRadioButton("Inorder");
		JRadioButton preorder = new JRadioButton("Preorder");
		JRadioButton postorder = new JRadioButton("Postorder");
		
		// Add the JRadioButtons to a ButtonGroup
		ButtonGroup radio = new ButtonGroup();
		radio.add(inorder);
		radio.add(preorder);
		radio.add(postorder);
		
		// The JButtons
		JButton addNode = new JButton("Add Node");
		JButton deleteNode = new JButton("Delete Node");
		JButton display = new JButton("Display Contents");
		JButton draw = new JButton("Draw Tree");
		JButton deleteTree = new JButton("Delete Tree");
		JButton exit = new JButton("Exit");
		
		// The Panels
		JPanel radios = new JPanel();
		JPanel buttons = new JPanel();
		
		// Add the buttons to the corresponding panels
		radios.add(type);
		radios.add(inorder);
		radios.add(preorder);
		radios.add(postorder);
		
		buttons.setLayout(new GridLayout(2, 3));
		buttons.add(addNode);
		buttons.add(deleteNode);
		buttons.add(display);
		buttons.add(draw);
		buttons.add(deleteTree);
		buttons.add(exit);
		
		// The Window
		setSize(500, 150);
		setLocation(800,500);
		setTitle("Binary Search Tree");
		add(radios, BorderLayout.PAGE_START);
		add(buttons, BorderLayout.CENTER);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
