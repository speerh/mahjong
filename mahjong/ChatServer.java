package mahjong;

import java.awt.*;
import javax.swing.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class ChatServer extends AbstractServer {
	private JTextArea log;
	private JLabel status;
	private boolean running = false;
	private Database database = new Database();
	private String dml;
	private Center center = new Center();
	private Hand hand = new Hand();
	private Yaku yaku = new Yaku();
	private ConnectionToClient client1;
	private ConnectionToClient client2;
	

	// Constructor
	public ChatServer() {
		super(12345);
		this.setTimeout(500);
	}

	public boolean isRunning() {
		return running;
	}

	public void setLog(JTextArea log) {
		this.log = log;
	}

	public void setStatus(JLabel status) {
		this.status = status;
	}

	public void serverStarted() {
		running = true;
		status.setText("Listening");
		status.setForeground(Color.GREEN);
		log.append("Server started\n");
	}

	public void serverStopped() {
		status.setText("Stopped");
		status.setForeground(Color.RED);
		log.append("Server stopped accepting new clients - press Listen to start accepting new clients\n");
	}

	public void serverClosed() {
		running = false;
		status.setText("Close");
		status.setForeground(Color.RED);
		log.append("Server and all current clients are closed - press Listen to restart\n");
	}

	public void clientConnected(ConnectionToClient client) {
		log.append("Client " + client.getId() + " connected\n");
		
	}

	public void handleMessageFromClient(Object arg0, ConnectionToClient arg1) {
		System.out.println(arg0);
		if (arg0 instanceof LoginData) {
			
			if (client1 == null) {
				client1 = arg1;
			}
			else {
				client2 = arg1;
			}
			
			LoginData data = (LoginData) arg0;
			Object result = "";
			dml = "select username, aes_decrypt(password,'key') from User Where username = '" + 
					data.getUsername() + "'and aes_decrypt(password, 'key')='"+ data.getPassword()+"'";

			// Do the query.
			ArrayList<String> results = database.query(dml);

			// Print the result.
			if (results != null){
				result = "LoginSuccessful";
				log.append("Client " + arg1.getId() + " successfully logged in as " + data.getUsername() + "\n");
				try {
					arg1.sendToClient(hand);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else{
				System.out.println("Error executing query.");
				result = new Error("The username and password are incorrect.", "Login");
				log.append("Client " + arg1.getId() + " failed to log in\n");
			}
			
			try {
				arg1.sendToClient(result);
			} catch (IOException e) {
				return;
			}
			//REMOVE LATER
			if(client1 != null && client2 != null) {
				//game start
				gameStart(client1, client2);
				
			}

		} else if (arg0 instanceof CreateAccountData) {
			CreateAccountData data = (CreateAccountData) arg0;
			Object result = "";
			
			//create the dml statement
			dml = "insert into user values('" + data.getUsername() + "',aes_encrypt('" +data.getPassword()+"','key'))";
			
			// Run the DML.
			try{
				database.executeDML(dml);
				result = "CreateAccountSuccessful";
				log.append("Client " + arg1.getId() + " created a new account called " + data.getUsername() + "\n");
			} catch(SQLException sql){
				System.out.println("Error executing DML.");
				result = new Error("The username is already in use.", "CreateAccount");
				log.append("Client " + arg1.getId() + " failed to create a new account\n");
			}

			try {
				arg1.sendToClient(result);
			} catch (IOException e) {
				return;
			}
		}
		//this needs to be instanceof Tile
		//all this does is relay tile -> the other client and signify turn over for that client
		//maybe use an iterator to process turns?
		//when a tile is chosen to discard, the tile with that specific index is chosen on the client side, removed from their hand, and sent to the server
		//not the index of the tile itself
		//so the server would be receiving a tile, not an int
		//tl;dr when a tile is recieved from a played it's implied that it is on their turn, but this can be doubly enforced using input validation
		//but not entirely necessary since it's enforced strictly client side
		//client1 sends tile to server as their discard
		// THIS tile is relayed to client2 (which is then shoved into their "other player discard pile")
		//client1's turn ends
		//"TURN" symbol is sent to client2 to signify the start of their turn which sets their turn boolean
		//then the drawn tile from the center is sent to client2
		
		//additionally: when a tile is drawn clientside i added code to check for yaku
		//if it's found to be a win, it will send a full Hand obj to the server
		//the server needs to validate that hand and send "WIN" back to that client, "LOSE" back to the other client to signify end of game
		//this can be arg0 instanceof hand because I think it's the only time that we send a hand obj
		//99% of the time this SHOULD result in game end because i don't see any reason why yaku would trigger a win clientside and not the same win serverside
		else if (arg0 instanceof Tile) {
			Tile tile = new Tile("", 1, false);			
			tile = (Tile) arg0;
			
			if (arg1 == client1) {
				System.out.println("CLIENT1 SENT SERVER TILE");
				//send discard to client 2
				try {
					client2.sendToClient(tile);
					System.out.println("SENT DISCARD TILE TO CLIENT1");

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//send turn to client 2
				try {
					System.out.println("CLIENT2 SENT TURN");

					client2.sendToClient("TURN");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					wait(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//draw for client 2, send to client
				System.out.println("CLIENT2 SENT TILE");

				tile = center.getFirstTile();
				center.removeFromCenter();
				try {
					System.out.println("CLIENT2 SENT DRAW");

					client2.sendToClient(tile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if (arg1 == client2){
				System.out.println("CLIENT2 SENT TILE");
				//send discard to client 1
				try {
					System.out.println("CLIENT1 SENT DISCARD");

					client1.sendToClient(tile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//send turn to client 1
				try {
					System.out.println("CLIENT1 SENT TURN");
					client1.sendToClient("TURN");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					wait(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//draw for client 1, send to client
				tile = center.getFirstTile();
				center.removeFromCenter();
				try {
					System.out.println("CLIENT1 SENT DRAW");
					client1.sendToClient(tile);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if (arg0 instanceof String) {
				
				if (arg0 == "WIN") {
					if(arg1 == client1) {
						try {
							client1.sendToClient("WIN");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						try {
							client2.sendToClient("LOSE");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else if (arg1 == client2) {
						try {
							client2.sendToClient("WIN");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							client1.sendToClient("LOSE");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}
					
				
				
				
			}
		}

	}

	public void listeningException(Throwable exception) {
		running = false;
		status.setText("Exception occurred while listening");
		status.setForeground(Color.RED);
		log.append("Listening exception: " + exception.getMessage() + "\n");
		log.append("Press Listen to restart server\n");
	}
	
	public void gameStart(ConnectionToClient p1, ConnectionToClient p2) {
        Boolean gameEnd = false;
        Hand hand = new Hand();
        hand = center.genFirstHand();

        try {
            p1.sendToClient(hand);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        hand = center.genFirstHand();

        try {
            p2.sendToClient(hand);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            p1.sendToClient("TURN");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}