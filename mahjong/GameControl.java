package mahjong;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class GameControl implements ActionListener
{
  // Private data field for storing the container.
  private JPanel container;
  private ChatClient client;
  // Constructor for the initial controller.
  public GameControl(JPanel container, ChatClient client)
  {
    this.container = container;
    this.client = client;
  }
  
  // Handle button clicks.
  public void actionPerformed(ActionEvent ae)
  {
	  GamePanel gamePanel = (GamePanel)container.getComponent(3);
    // Get the name of the button clicked.
    String command = ae.getActionCommand();
    
    System.out.println(command);
    
    if(command.equalsIgnoreCase("d")) {
    	//DISCARD 	
    	gamePanel.discardDraw();
    }
    else {
    	int index = Integer.parseInt(ae.getActionCommand());
    	//REMOVE ENTRY FROM ARRAYLIST BASED OFF OF ITERATOR
    	gamePanel.hand.removeTile(index);
    	//ADD IN DRAWN TILE
    	gamePanel.hand.addTile(gamePanel.getDraw());
    	gamePanel.drawHand();
    }
  }
  
  public void setHand(Hand in) {
	  client.updateHand(in);
	  GamePanel gamePanel = (GamePanel)container.getComponent(3);
	  gamePanel.setHand(in);
	  System.out.println("HAND UPDATED");
  }
}