package mahjong;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class GameControl implements ActionListener
{
  // Private data field for storing the container.
  private JPanel container;
  private ChatClient client;
  private Boolean turn = false;
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
    
    if(command.equalsIgnoreCase("d")) {
    	//DISCARD 	
    	//make discard invis
    	gamePanel.drawVisible(false);
    	//send discard to server
    	try {
			client.sendToServer(gamePanel.getDraw());
			client.setTurn(false);
	    	drawDiscards(gamePanel.getDraw());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    else {
    	int index = Integer.parseInt(ae.getActionCommand());
    	//send tile to server
    	//REMOVE ENTRY FROM ARRAYLIST BASED OFF OF ITERATOR
    	try {
			client.sendToServer(gamePanel.hand.getTile().get(index));
		} catch (IOException e) {
			// TODO Auto-generated catch block
		
		}
    	drawDiscards(gamePanel.hand.getTile().get(index));
    	gamePanel.hand.removeTile(index);
    	//ADD IN DRAWN TILE
    	gamePanel.hand.addTile(gamePanel.getDraw());
    	gamePanel.drawHand();
    	gamePanel.enableButtons(false);
    	client.setTurn(false);
    	
    	
    }
  }
  
  public void setHand(Hand in) {
	  client.updateHand(in);
	  GamePanel gamePanel = (GamePanel)container.getComponent(3);
	  gamePanel.setHand(in);
  }
  
  public void setTurn(Boolean in) {
	  GamePanel gamePanel = (GamePanel)container.getComponent(3);
	  turn = in;
	  System.out.println("TURN: " + in);
	  gamePanel.enableButtons(turn);
	  
	  
  }
  public Boolean getTurn() {
	  return turn;
  }
  
  public void drawDiscards(Tile in) {
	  System.out.println("REACHED 2");
	  GamePanel gamePanel = (GamePanel)container.getComponent(3);
	  gamePanel.hand.discards.add(in);
	  gamePanel.drawDiscards();
  }
  
  public void drawDiscards2(Tile in) {
	  System.out.println("REACHED 2");
	  GamePanel gamePanel = (GamePanel)container.getComponent(3);
	  gamePanel.hand2.discards.add(in);
	  gamePanel.p2Discards();
  }
  
  
}