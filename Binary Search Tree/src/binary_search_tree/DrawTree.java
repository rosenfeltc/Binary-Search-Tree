package binary_search_tree;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DrawTree extends JFrame
{
	public DrawTree(BinaryTree tree)
	{
		int nodes = tree.getCounter();
		
		if(nodes == 0)
		 {
			 JOptionPane.showMessageDialog(null, "The tree is empty so there is nothing to draw!");
			 return;
		 }
		
		int levels = tree.howManyLevels();
		String[] names = new String[nodes];
		int index = 0;
		tree.toDraw(names);
		 
		// The JLabel array
		JLabel[][] labels = new JLabel[4][15];
		setLayout(new GridLayout(4, 15));
		
		// Instantiate the necessary panel references
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 15; j++)
			{
				labels[i][j] = new JLabel();
				
				if(i == 0 && j == 7)
				{
					labels[i][j].setBackground(Color.BLUE);
					labels[i][j].setOpaque(true);
				}
				else if(i == 1 && (j == 3 || j == 11))
				{
					labels[i][j].setBackground(Color.BLUE);
					labels[i][j].setOpaque(true);
				}
				else if(i == 2 && (j == 1 || j == 5 || j == 9 || j == 13))
				{
					labels[i][j].setBackground(Color.BLUE);
					labels[i][j].setOpaque(true);
				}
				else if(i == 3 && j % 2 == 0)
				{
					labels[i][j].setBackground(Color.BLUE);
					labels[i][j].setOpaque(true);
				}
				
				add(labels[i][j]);
			}
		}
		
		labels[0][7].setText(names[index++]);
		labels[0][7].setHorizontalAlignment(SwingConstants.CENTER);
		labels[0][7].setVerticalAlignment(SwingConstants.CENTER);
		labels[0][7].setForeground(Color.WHITE);
				
		while(index < names.length)
		{
			
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
