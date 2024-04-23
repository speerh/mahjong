//hannah speer
package mahjong;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class GamePanel extends JPanel
{
	public Hand hand = new Hand();
	public Hand hand2 = new Hand();
	GameControl gc;
	JPanel handPanel, playerDiscards, handDrawPanel, p2DiscardPanel;
	JButton handDraw;
	Tile drawn = new Tile("dragon", 3, true);
	//init hand for test data
	
	
  // Constructor for the initial panel.
  public GamePanel(GameControl gc)
  {
	  this.gc = gc;
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
	  
	  
	  p2DiscardPanel = new JPanel();
	  p2DiscardPanel.setBackground(Color.GREEN);
	  p2Discards();
	  
	  
	//CENTER TILES-------------------------------------------------------------------
	  JPanel centerTiles = new JPanel();
	  centerTiles.setOpaque(false);
	  JLabel centerTile = new JLabel(new ImageIcon("images/back.png"));
	  centerTiles.add(centerTile);
	  
	//P1 DISCARDS--------------------------------------------------------------------
	  playerDiscards = new JPanel();
	  playerDiscards.setOpaque(false);
	  drawDiscards();
	  
	  
    
    //HAND RENDERING-----------------------------------------------------------------
    handPanel = new JPanel();
    handPanel.setOpaque(false);
    handPanel.setLayout(new BoxLayout(handPanel, BoxLayout.X_AXIS));
    //loginButtonBuffer.add(test1);
    
    //HAND DRAW RENDERING--
    handDrawPanel = new JPanel();
    String fn = this.getDraw().getSuit()+ this.getDraw().getNumber() + ".png";
    System.out.println(fn);
	handDraw = new JButton(new ImageIcon("images/" + this.getDraw().getSuit()+ this.getDraw().getNumber() + ".png"));
	handDraw.addActionListener(gc);
	handDraw.setContentAreaFilled(false);
	handDraw.setBorderPainted(false);
	handDraw.setActionCommand("d");
	handDrawPanel.add(handDraw);
	
	

    // Arrange the components in a grid.
    JPanel grid = new JPanel(new GridLayout(6, 2, 5, 5));
    grid.setOpaque(false);
    grid.add(p2Hand);
    grid.add(p2DiscardPanel);
    grid.add(centerTiles);
    grid.add(playerDiscards);
    grid.add(handPanel);
    grid.add(handDrawPanel);
    this.add(grid);
    this.setBackground(new Color(103, 128, 101));
  }
  
  public void setHand(Hand in) {
	  hand = in;
	  drawHand();
	 
  }
  
  public void drawHand() {
	  handPanel.removeAll();
	  ArrayList<JButton> tileButtons = new ArrayList();
	    for (int i = 0; i < 13; i++) {
	    	JButton temp = new JButton(new ImageIcon("images/" + hand.getTile().get(i).getSuit() + hand.getTile().get(i).getNumber() + ".png"));
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
	    enableButtons(gc.getTurn());
	    handPanel.revalidate();
	    
  }
  
  public void setDraw(Tile in) {
	  drawn = in;
  }
  
  public Tile getDraw() {
	  return drawn;
  }
  public void drawVisible(Boolean in) {
	  handDraw.setVisible(in);
	  //handDrawPanel.setVisible(false);
	  handDrawPanel.revalidate();
	  
  }
  public void drawDiscards() {
	  playerDiscards.removeAll();
	  ArrayList<JLabel> playerDiscardsList = new ArrayList();
	  System.out.println("-----------------------");
	  if(hand.discards.size() - 1 < 3) {
		  System.out.println("LESS THAN 3");
		  for (int i = 0; i < hand.discards.size(); i++) {
			  System.out.println(hand.discards.get(0));
			  System.out.println("*****************");
		    	JLabel temp = new JLabel(new ImageIcon("images/" + hand.discards.get(i).getSuit() + hand.discards.get(i).getNumber()  + ".png"));
		    	temp.setSize(new Dimension(45, 54));
		    	playerDiscardsList.add(temp);
		    	playerDiscards.add(temp);
		    }
	  }
	  else {
		  for (int i = 1; i <= 3; i++) {
			  System.out.println("3+");
			  System.out.println("***********2222222222222******");
		    	JLabel temp = new JLabel(new ImageIcon("images/" + hand.discards.get(hand.discards.size() - i).getSuit() + hand.discards.get(hand.discards.size() - i).getNumber()  + ".png"));
		    	temp.setSize(new Dimension(45, 54));
		    	playerDiscardsList.add(temp);
		    	playerDiscards.add(temp);
		    }
		  playerDiscards.revalidate();
	  }
  }
  
  public void p2Discards() {
	  
	    p2DiscardPanel.removeAll();
	    ArrayList<JLabel> p2DiscardTiles = new ArrayList();
	  if(hand2.discards.size() - 1 < 3) {
		  for (int i = 0; i < hand2.discards.size(); i++) {
		    	JLabel temp = new JLabel(new ImageIcon("images/" + hand2.discards.get(i).getSuit() + hand2.discards.get(i).getNumber()  + ".png"));
		    	temp.setSize(new Dimension(45, 54));
		    	p2DiscardTiles.add(temp);
		    	p2DiscardPanel.add(temp);
		    }
	  }
	  else {
		  for (int i = 1; i <= 3; i++) {
		    	JLabel temp = new JLabel(new ImageIcon("images/" + hand2.discards.get(hand2.discards.size() - i).getSuit() + hand2.discards.get(hand2.discards.size() - i).getNumber()  + ".png"));
		    	temp.setSize(new Dimension(45, 54));
		    	p2DiscardTiles.add(temp);
		    	p2DiscardPanel.add(temp);
		    }
		  p2DiscardPanel.revalidate();
	  }
  }
  
  public void enableButtons(boolean enable) {
	  Component[] components = handPanel.getComponents();
	  
      for (Component i : components) {
          i.setEnabled(enable);
      }
      if(!enable) {
    	  drawVisible(false);
      }
  }
  
}