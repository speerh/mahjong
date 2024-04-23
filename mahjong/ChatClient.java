package mahjong;

import java.io.IOException;

import ocsf.client.AbstractClient;

public class ChatClient extends AbstractClient
{
  // Private data fields for storing the GUI controllers.
  private LoginControl loginControl;
  private CreateAccountControl createAccountControl;
  private GameControl gameControl;
  private Hand hand;
  private Hand hand2;
  private Boolean turn = false;
  private Yaku check;

  // Setters for the GUI controllers.
  public void setLoginControl(LoginControl loginControl)
  {
    this.loginControl = loginControl;
  }
  public void setCreateAccountControl(CreateAccountControl createAccountControl)
  {
    this.createAccountControl = createAccountControl;
  }
  public void setGameControl(GameControl in) {
	  gameControl = in;
  }

  // Constructor for initializing the client with default settings.
  public ChatClient()
  {
    super("localhost", 8300);
  }
  
  // Method that handles messages from the server.
  public void handleMessageFromServer(Object arg0)
  {
    // If we received a String, figure out what this event is.
    if (arg0 instanceof String)
    {
      // Get the text of the message.
      String message = (String)arg0;
      
      // If we successfully logged in, tell the login controller.
      if (message.equals("LoginSuccessful"))
      {
        loginControl.loginSuccess();
      }
      
      // If we successfully created an account, tell the create account controller.
      else if (message.equals("CreateAccountSuccessful"))
      {
        createAccountControl.createAccountSuccess();
      }
      else if(message.equals("TURN")) {
    	  //set player's turn to true
    	  setTurn(true);
    	  gameControl.setTurn(true);
      }
      
    }
    //if msg is hand
    else if(arg0 instanceof Hand) {
  	  gameControl.setHand((Hand)arg0);
    }
    
    // If we received an Error, figure out where to display it.
    else if (arg0 instanceof Error)
    {
      // Get the Error object.
      Error error = (Error)arg0;
      
      // Display login errors using the login controller.
      if (error.getType().equals("Login"))
      {
        loginControl.displayError(error.getMessage());
      }
      
      // Display account creation errors using the create account controller.
      else if (error.getType().equals("CreateAccount"))
      {
        createAccountControl.displayError(error.getMessage());
      }
    }
    else if(arg0 instanceof Tile) {
    	//is player turn?
    	if(getTurn()) {
    		Hand test = hand;
    		test.addTile((Tile) arg0);
    		System.out.println("DRAWN RECEIVED");
    		//if turn, this tile is the drawn tile
    		//update drawn tile
    		
    	}
    	else if(!getTurn()) {
    		//if NOT turn, this is the other player's discard
    		//add to other player discard
    		System.out.println("DISCARD RECEIVED");
    		
    	}
    }
  }
  
  public void updateHand(Hand in) {
	  hand = in;
  }
  
  public void setTurn(Boolean in) {
	  turn = in;
	  
  }
  public Boolean getTurn() {
	  return turn;
  }
}