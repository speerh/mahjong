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
    // Get the name of the button clicked.
    String command = ae.getActionCommand();
    
    System.out.println(command);
    
    if(command.equals("d")) {
    	//DISCARD DRAW
    }
    else {
    	//REMOVE ENTRY FROM ARRAYLIST BASED OFF OF ITERATOR
    	//ADD IN DRAWN TILE
    }
  }
  
  public void setHand(Hand in) {
	  client.updateHand(in);
	  GamePanel gamePanel = (GamePanel)container.getComponent(4);
	  gamePanel.setHand(in);
  }
}