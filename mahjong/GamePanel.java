//hannah speer
package mahjong;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class GamePanel extends JPanel
{
	Hand hand = new Hand();
	//init hand for test data
	
	
  // Constructor for the initial panel.
  public GamePanel(GameControl gc)
  {
	  hand.addTile(new Tile("wind", 3, true));
	  hand.addTile(new Tile("wind", 3, true));
		hand.addTile(new Tile("wind", 3, true));
		hand.addTile(new Tile("wind", 3, true));
		hand.addTile(new Tile("wind", 3, true));
		hand.addTile(new Tile("wind", 3, true));
		hand.addTile(new Tile("wind", 3, true));
		hand.addTile(new Tile("wind", 3, true));
		hand.addTile(new Tile("wind", 3, true));
		hand.addTile(new Tile("wind", 3, true));
		hand.addTile(new Tile("wind", 3, true));
		hand.addTile(new Tile("wind", 3, true));
		hand.addTile(new Tile("wind", 3, true));
	  //static array for tile testing
	String testData[] = new String[] {"character6","bamboo9", "bamboo7", "circle1", "circle2", "circle2", "wind3", "wind2", "circle2", "bamboo5", "character7", "bamboo1", "dragon2"};
    // Create the controller.
    //InitialControl controller = new InitialControl(container);
	  
	//P2 HAND -----------------------------------------------------------------------
	  JPanel p2Hand = new JPanel();
	  p2Hand.setOpaque(false);
	  p2Hand.setLayout(new BoxLayout(p2Hand, BoxLayout.X_AXIS));

	  for (int i = 0; i < 13; i++) {
	    	JLabel temp = new JLabel(new ImageIcon("images/back.png"));
	    	temp.setSize(new Dimension(45, 54));
	    	p2Hand.add(temp);
	    }
	  
	  
	  JPanel p2Discards = new JPanel();
	  p2Discards.setOpaque(false);
	  for (int i = 0; i < 3; i++) {
	    	JLabel temp = new JLabel(new ImageIcon("images/" + hand.getTile().get(i) + ".png"));
	    	temp.setSize(new Dimension(45, 54));
	    	p2Discards.add(temp);
	    }
	  
	//CENTER TILES-------------------------------------------------------------------
	  JPanel centerTiles = new JPanel();
	  centerTiles.setOpaque(false);
	  JLabel centerTile = new JLabel(new ImageIcon("images/back.png"));
	  centerTiles.add(centerTile);
	  
	//P1 DISCARDS--------------------------------------------------------------------
	  JPanel playerDiscards = new JPanel();
	  playerDiscards.setOpaque(false);
	  for (int i = 0; i < 3; i++) {
	    	JLabel temp = new JLabel(new ImageIcon("images/" + testData[i] + ".png"));
	    	temp.setSize(new Dimension(45, 54));
	    	playerDiscards.add(temp);
	    }
	  
    
    //HAND RENDERING-----------------------------------------------------------------
    JPanel handPanel = new JPanel();
    handPanel.setOpaque(false);
    handPanel.setLayout(new BoxLayout(handPanel, BoxLayout.X_AXIS));
    //loginButtonBuffer.add(test1);
    ArrayList<JButton> tileButtons = new ArrayList();
    //loop to add buttons-- ADD WHEN HAND INTEGRATED
    for (int i = 0; i < 13; i++) {
    	JButton temp = new JButton(new ImageIcon("images/" + hand.getTile().get(i) + ".png"));
    	temp.setContentAreaFilled(false);
    	temp.setBorderPainted( false );
    	temp.setSize(new Dimension(45, 54));
    	//this errors out, ignore it (it works and runs)
    	temp.setBorder(null);
    	temp.setToolTipText(hand.getTile().get(i).getSuit() + " " + hand.getTile().get(i).getNumber());
    	temp.setActionCommand("" + i);
    	temp.addActionListener(gc);
    	tileButtons.add(temp);
    	handPanel.add(temp);
    }
    //HAND DRAW RENDERING--
	JButton handDraw = new JButton(new ImageIcon("images/wind3.png"));
	handDraw.setContentAreaFilled(false);
	handDraw.setBorderPainted(false);
	handDraw.setActionCommand("d");
	
	

    // Arrange the components in a grid.
    JPanel grid = new JPanel(new GridLayout(6, 2, 5, 5));
    grid.setOpaque(false);
    grid.add(p2Hand);
    grid.add(p2Discards);
    grid.add(centerTiles);
    grid.add(playerDiscards);
    grid.add(handPanel);
    grid.add(handDraw);
    this.add(grid);
    this.setBackground(new Color(103, 128, 101));
  }
  
  public void setHand(Hand in) {
	  hand = in;
  }
  
}