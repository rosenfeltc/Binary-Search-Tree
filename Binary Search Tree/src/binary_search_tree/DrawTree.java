/* This is the DrawTree.java class that handles the operation of creating a new window and visually displaying
 * a tree with the Nodes drawn in blue and their name displayed in white
 * Coded by Christopher Rosenfelt for CSI 213
 */
package binary_search_tree;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DrawTree extends JFrame
{
	// The Constructor that does all the work
	public DrawTree(BinaryTree tree)
	{
		// How many nodes the tree currently has
		int nodes = tree.getCounter();
		
		// Tree is empty so communicate with the user and return
		if(nodes == 0)
		{
			 JOptionPane.showMessageDialog(null, "The tree is empty so there is nothing to draw!");
			 return;
		}
		
		// The tree has more Nodes that can be displayed so communicate with the user and return
		if(nodes > 15)
		{
			 JOptionPane.showMessageDialog(null, "The tree is too big to be properly drawn!");
			 return;
		}
		
		// Create a String array to hold the names of the Nodes and an int array to hold their respective positions
		String[] names = new String[nodes];
		int[] positions = new int[nodes];
		
		// This is the counter that will iterate through the two arrays when inserting into the proper JLabel
		int index = 0;
		
		// This method will traverse the tree in preorder and insert the name of each Node
		// into the names array and its respective position into the positions array
		tree.toDraw(names, positions);
		 
		// The JLabel reference array for the window layout - 4X15
		JLabel[][] labels = new JLabel[4][15];
		setLayout(new GridLayout(4, 15));
		
		// Instantiate the panel references and add them to the window
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 15; j++)
			{
				labels[i][j] = new JLabel();				
				add(labels[i][j]);
			}
		}
		
		// Color the correct branch of the tree with the appropriate name
		while(index < names.length)
		{
			// Cases are based on the positions of each Node obtained by the toDraw method and then
			// hardcoded to their respective visual positions in the window
			switch(positions[index])
			{
				case 1:
					for(int i = 5; i < 10; i++)
					{
						labels[0][i].setBackground(Color.BLUE);
						labels[0][i].setOpaque(true);
					}
					
					labels[0][7].setForeground(Color.WHITE);
					labels[0][7].setFont(new Font("Comic Sans MS", 1, 26));
					labels[0][7].setText(names[index]);
					labels[0][7].setHorizontalAlignment(SwingConstants.CENTER);
					labels[0][7].setVerticalAlignment(SwingConstants.CENTER);
					break;
				case 4:
					for(int i = 10; i < 13; i++)
					{
						labels[1][i].setBackground(Color.BLUE);
						labels[1][i].setOpaque(true);
					}
					
					labels[1][11].setForeground(Color.WHITE);
					labels[1][11].setFont(new Font("Comic Sans MS", 1, 26));
					labels[1][11].setText(names[index]);
					labels[1][11].setHorizontalAlignment(SwingConstants.CENTER);
					labels[1][11].setVerticalAlignment(SwingConstants.CENTER);
					break;
				case 5:
					for(int i = 2; i < 5; i++)
					{
						labels[1][i].setBackground(Color.BLUE);
						labels[1][i].setOpaque(true);
					}
					
					labels[1][3].setForeground(Color.WHITE);
					labels[1][3].setFont(new Font("Comic Sans MS", 1, 26));
					labels[1][3].setText(names[index]);
					labels[1][3].setHorizontalAlignment(SwingConstants.CENTER);
					labels[1][3].setVerticalAlignment(SwingConstants.CENTER);
					break;
				case 7:
					labels[2][13].setBackground(Color.BLUE);
					labels[2][13].setForeground(Color.WHITE);
					labels[2][13].setOpaque(true);
					labels[2][13].setFont(new Font("Comic Sans MS", 1, 26));
					labels[2][13].setText(names[index]);
					labels[2][13].setHorizontalAlignment(SwingConstants.CENTER);
					labels[2][13].setVerticalAlignment(SwingConstants.CENTER);
					break;
				case 8:
					labels[2][5].setBackground(Color.BLUE);
					labels[2][5].setForeground(Color.WHITE);
					labels[2][5].setOpaque(true);
					labels[2][5].setFont(new Font("Comic Sans MS", 1, 26));
					labels[2][5].setText(names[index]);
					labels[2][5].setHorizontalAlignment(SwingConstants.CENTER);
					labels[2][5].setVerticalAlignment(SwingConstants.CENTER);
					break;
				case 10:
					labels[3][14].setBackground(Color.BLUE);
					labels[3][14].setForeground(Color.WHITE);
					labels[3][14].setOpaque(true);
					labels[3][14].setFont(new Font("Comic Sans MS", 1, 26));
					labels[3][14].setText(names[index]);
					labels[3][14].setHorizontalAlignment(SwingConstants.CENTER);
					labels[3][14].setVerticalAlignment(SwingConstants.CENTER);
					break;
				case 11:
					labels[3][6].setBackground(Color.BLUE);
					labels[3][6].setForeground(Color.WHITE);
					labels[3][6].setOpaque(true);
					labels[3][6].setFont(new Font("Comic Sans MS", 1, 26));
					labels[3][6].setText(names[index]);
					labels[3][6].setHorizontalAlignment(SwingConstants.CENTER);
					labels[3][6].setVerticalAlignment(SwingConstants.CENTER);
					break;
				case 20:
					labels[2][9].setBackground(Color.BLUE);
					labels[2][9].setForeground(Color.WHITE);
					labels[2][9].setOpaque(true);
					labels[2][9].setFont(new Font("Comic Sans MS", 1, 26));
					labels[2][9].setText(names[index]);
					labels[2][9].setHorizontalAlignment(SwingConstants.CENTER);
					labels[2][9].setVerticalAlignment(SwingConstants.CENTER);
					break;
				case 23:
					labels[3][10].setBackground(Color.BLUE);
					labels[3][10].setForeground(Color.WHITE);
					labels[3][10].setOpaque(true);
					labels[3][10].setFont(new Font("Comic Sans MS", 1, 26));
					labels[3][10].setText(names[index]);
					labels[3][10].setHorizontalAlignment(SwingConstants.CENTER);
					labels[3][10].setVerticalAlignment(SwingConstants.CENTER);
					break;
				case 25:
					labels[2][1].setBackground(Color.BLUE);
					labels[2][1].setForeground(Color.WHITE);
					labels[2][1].setOpaque(true);
					labels[2][1].setFont(new Font("Comic Sans MS", 1, 26));
					labels[2][1].setText(names[index]);
					labels[2][1].setHorizontalAlignment(SwingConstants.CENTER);
					labels[2][1].setVerticalAlignment(SwingConstants.CENTER);
					break;
				case 28:
					labels[3][2].setBackground(Color.BLUE);
					labels[3][2].setForeground(Color.WHITE);
					labels[3][2].setOpaque(true);
					labels[3][2].setFont(new Font("Comic Sans MS", 1, 26));
					labels[3][2].setText(names[index]);
					labels[3][2].setHorizontalAlignment(SwingConstants.CENTER);
					labels[3][2].setVerticalAlignment(SwingConstants.CENTER);
					break;
				case 35:
					labels[3][12].setBackground(Color.BLUE);
					labels[3][12].setForeground(Color.WHITE);
					labels[3][12].setOpaque(true);
					labels[3][12].setFont(new Font("Comic Sans MS", 1, 26));
					labels[3][12].setText(names[index]);
					labels[3][12].setHorizontalAlignment(SwingConstants.CENTER);
					labels[3][12].setVerticalAlignment(SwingConstants.CENTER);
					break;
				case 40:
					labels[3][4].setBackground(Color.BLUE);
					labels[3][4].setForeground(Color.WHITE);
					labels[3][4].setOpaque(true);
					labels[3][4].setFont(new Font("Comic Sans MS", 1, 26));
					labels[3][4].setText(names[index]);
					labels[3][4].setHorizontalAlignment(SwingConstants.CENTER);
					labels[3][4].setVerticalAlignment(SwingConstants.CENTER);
					break;
				case 100:
					labels[3][8].setBackground(Color.BLUE);
					labels[3][8].setForeground(Color.WHITE);
					labels[3][8].setOpaque(true);
					labels[3][8].setFont(new Font("Comic Sans MS", 1, 26));
					labels[3][8].setText(names[index]);
					labels[3][8].setHorizontalAlignment(SwingConstants.CENTER);
					labels[3][8].setVerticalAlignment(SwingConstants.CENTER);
					break;
				case 125:
					labels[3][0].setBackground(Color.BLUE);
					labels[3][0].setForeground(Color.WHITE);
					labels[3][0].setOpaque(true);
					labels[3][0].setFont(new Font("Comic Sans MS", 1, 26));
					labels[3][0].setText(names[index]);
					labels[3][0].setHorizontalAlignment(SwingConstants.CENTER);
					labels[3][0].setVerticalAlignment(SwingConstants.CENTER);
					break;
			}
			
			// Increment the index counter that is traversing the arrays
			index++;
		}
		
		// Window Settings
		setSize(1900, 1000);
		setLocation(0, 0);
		setTitle("Draw Tree");
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
