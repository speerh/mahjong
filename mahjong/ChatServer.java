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
		
		Tile tile1 = new Tile("bamboo", 2, false);
		Tile tile2 = new Tile("bamboo", 2, false);
		Tile tile3 = new Tile("bamboo", 3, false);
		Tile tile4 = new Tile("bamboo", 4, false);
		Tile tile5 = new Tile("bamboo", 5, false);
		Tile tile6 = new Tile("bamboo", 6, false);
		Tile tile7 = new Tile("bamboo", 7, false);
		Tile tile8 = new Tile("bamboo", 8, false);
		Tile tile9 = new Tile("bamboo", 8, false);
		Tile tile10 = new Tile("dragon", 1, true);
		Tile tile11 = new Tile("dragon", 2, false);
		Tile tile12 = new Tile("dragon", 3, false);
		Tile tile13 = new Tile("dragon", 4, true);
		Tile tile14 = new Tile("wind", 1, true);
		
		hand.addTile(tile1);
		hand.addTile(tile2);
		hand.addTile(tile3);
		hand.addTile(tile4);
		hand.addTile(tile5);
		hand.addTile(tile6);
		hand.addTile(tile7);
		hand.addTile(tile8);
		hand.addTile(tile9);
		hand.addTile(tile10);
		hand.addTile(tile11);
		hand.addTile(tile12);
		hand.addTile(tile13);
		hand.addTile(tile14);
		
		boolean game = false;
		
		game = yaku.checkWin(hand);
		
		System.out.println("Does Hand Win?: " + game);
		
//		Tile tile = center.getFirstTile();
//		String str = tile.getSuit();
//		log.append(str);
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
		try {
			client.sendToClient(hand);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void handleMessageFromClient(Object arg0, ConnectionToClient arg1) {
		if (arg0 instanceof LoginData) {
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

	}

	public void listeningException(Throwable exception) {
		running = false;
		status.setText("Exception occurred while listening");
		status.setForeground(Color.RED);
		log.append("Listening exception: " + exception.getMessage() + "\n");
		log.append("Press Listen to restart server\n");
	}
}