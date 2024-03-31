package mahjong;

import java.awt.*;
import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class ChatServer extends AbstractServer
{
	// Data fields for this chat server.
	private JTextArea log;
	private JLabel status;
	private boolean running = false;
	ArrayList<String> accountUsernames = new ArrayList<String>();
	ArrayList<String> accountPasswords = new ArrayList<String>();
	//private DatabaseFile database = new DatabaseFile();
	//Create the database object.

	// Constructor for initializing the server with default settings.
	public ChatServer()
	{
		super(12345);
		this.setTimeout(500);
	}

	// Getter that returns whether the server is currently running.
	public boolean isRunning()
	{
		return running;
	}

	// Setters for the data fields corresponding to the GUI elements.
	public void setLog(JTextArea log)
	{
		this.log = log;
	}
	public void setStatus(JLabel status)
	{
		this.status = status;
	}

	// When the server starts, update the GUI.
	public void serverStarted()
	{
		running = true;
		status.setText("Listening");
		status.setForeground(Color.GREEN);
		log.append("Server started\n");
	}

	// When the server stops listening, update the GUI.
	public void serverStopped()
	{
		status.setText("Stopped");
		status.setForeground(Color.RED);
		log.append("Server stopped accepting new clients - press Listen to start accepting new clients\n");
	}

	// When the server closes completely, update the GUI.
	public void serverClosed()
	{
		running = false;
		status.setText("Close");
		status.setForeground(Color.RED);
		log.append("Server and all current clients are closed - press Listen to restart\n");
	}

	// When a client connects or disconnects, display a message in the log.
	public void clientConnected(ConnectionToClient client)
	{
		log.append("Client " + client.getId() + " connected\n");
	}

	// When a message is received from a client, handle it.
	public void handleMessageFromClient(Object arg0, ConnectionToClient arg1) {
		System.out.println("Message from server" + arg0);
		if (arg0 instanceof LoginData)
		{
			System.out.println("In Server login");
			// Check the username and password with the database.
			LoginData data = (LoginData)arg0;
			Object result = "";
			boolean results = false;
			
			String Username = data.getUsername();
			String Password = data.getPassword();
			
			for(int i = 0; i < accountUsernames.size(); i++) {
				
				if(accountUsernames.get(i).equals(Username) && accountPasswords.get(i).equals(Password)) {
					results = true;
				}
			}

			// Print the result.
			if (results == true)
			{
				result = "LoginSuccessful";
				log.append("Client " + arg1.getId() + " successfully logged in as " + data.getUsername() + "\n");

			}
			else
			{
				System.out.println("Error.");
				result = new Error("The username and password are incorrect.", "Login");
				log.append("Client " + arg1.getId() + " failed to log in\n");
			}

			// Send the result to the client.
			try
			{
				arg1.sendToClient(result);
			}
			catch (IOException e)
			{
				return;
			}
			
		}else if (arg0 instanceof CreateAccountData)
		{
			System.out.println("In Server Createaccount");
			// Try to create the account.
			CreateAccountData data = (CreateAccountData)arg0;
			Object result = "";
			String Username = data.getUsername();
			String Password = data.getPassword();
			
			System.out.println("Before array");
			accountUsernames.add(Username);
			accountPasswords.add(Password);
			System.out.println("after array");
			
			result = "CreateAccountSuccessful";
			log.append("Client " + arg1.getId() + " created a new account called " + data.getUsername() + "\n");

			// Send the result to the client.
			try
			{
				arg1.sendToClient(result);
			}
			catch (IOException e)
			{
				return;
			}
		}
		
	}

	// Method that handles listening exceptions by displaying exception information.
	public void listeningException(Throwable exception) 
	{
		running = false;
		status.setText("Exception occurred while listening");
		status.setForeground(Color.RED);
		log.append("Listening exception: " + exception.getMessage() + "\n");
		log.append("Press Listen to restart server\n");
	}
}