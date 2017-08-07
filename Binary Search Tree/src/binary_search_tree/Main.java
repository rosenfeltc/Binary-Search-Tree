package binary_search_tree;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Main extends JFrame 
{
	// The tree
	final BinaryTree tree = new BinaryTree();
	
	// The radio box selection
	private int selection;
	
	// Main method
	public static void main(String[] args)
	{
		mainMenu();
	}
	
	public static void mainMenu()
	{
		Main window = new Main();
	}
	
	// Selection Getter
	private int getSelection()
	{
		return this.selection;
	}
	
	// Selection Setter
	private void setSelection(int selection)
	{
		this.selection = selection;
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
		display.setEnabled(false);
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
		
		// Action Listeners for the Buttons
		// Add Node button
		addNode.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Create the String that will be used to create the new Node
				String toCreate = new String();
				
				do
				{
					toCreate = JOptionPane.showInputDialog("Please enter the name of the user you want to add into the Binary Tree"
							+ "\n(maximum 6 characters)");
					
					if(toCreate.length() > 6)
					{
						JOptionPane.showMessageDialog(null, "The name entered is too long.\nPlease try again and remember to limit the"
								+ " maximum characters to 6");
					}
				}while(toCreate.length() > 6);
				
				// Ensure the proper capitalization of the name
				toCreate = toCreate.toUpperCase().charAt(0) + toCreate.toLowerCase().substring(1);
				
				tree.insert(toCreate);
			}
		});
		
		// Delete Node button
		deleteNode.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Create the String that will be used to delete the Node
				String toDelete = JOptionPane.showInputDialog("Please enter the name of the user you want to delete from the Binary Tree");
				
				if(!tree.delete(toDelete))
				{
					JOptionPane.showMessageDialog(null, "Unable to delete " + toDelete + "\nMember not found in the Binary Tree!");
				}
			}
		});
		// Display Button
		// First the radio buttons
		inorder.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				setSelection(0);
				display.setEnabled(true);
			}
		});
		
		preorder.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				setSelection(1);
				display.setEnabled(true);
			}
		});
		
		postorder.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				setSelection(2);
				display.setEnabled(true);
			}
		});
		
		// Finally the display button
		display.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Create the String that will store the Binary Tree information
				String content = new String();
				
				switch(getSelection())
				{
					case 0:
						content = tree.print(0);
						break;
					case 1:
						content = tree.print(1);
						break;
					case 2:
						content = tree.print(2);
						break;					
				}
				
				JTextArea text = new JTextArea(content);
				text.setEditable(false);
				JScrollPane scroll = new JScrollPane(text);
				JFrame newWindow = new JFrame("Binary Tree Contents:");
				newWindow.setLocation(800, 500);
				newWindow.setSize(100, 200);
				newWindow.setVisible(true);
				newWindow.add(scroll);
				newWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		
		// TO DO DRAW TREE
		///////////////////////////////
		
		// Delete Tree
		deleteTree.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the tree?", "Warning!", JOptionPane.YES_NO_OPTION);
				
				if(choice == 0)
				{
					tree.destroy();
				}
			}
		});
		
		// Exit
		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
	}
}
