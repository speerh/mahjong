package mahjong;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ClientGUI extends JFrame
{
  
  
  // Constructor that creates the client GUI.
  public ClientGUI()
  {
    // Set up the chat client.
   ChatClient client = new ChatClient();
    client.setHost("localhost");
    client.setPort(8300);
    try
    {
      client.openConnection();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    
    
    // Set the title and default close operation.
    this.setTitle("Chat Client");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    
    // Create the card layout container.
    CardLayout cardLayout = new CardLayout();
    JPanel container = new JPanel(cardLayout);
    
    //Create the Controllers next
    //Next, create the Controllers
    InitialControl ic = new InitialControl(container, client);
    GameControl gc = new GameControl(container, client);
    LoginControl lc = new LoginControl(container, client);
    CreateAccountControl cac = new CreateAccountControl(container, client);
    
    
    //Set the client info
    client.setLoginControl(lc);
    client.setCreateAccountControl(cac);
    client.setGameControl(gc);
   
    
    // Create the four views. (need the controller to register with the Panels
    JPanel view1 = new InitialPanel(ic);	
    JPanel view2 = new LoginPanel(lc);
    JPanel view3 = new CreateAccountPanel(cac);
    JPanel view4 = new ContactsPanel();
    JPanel view5 = new GamePanel(gc);
    JPanel winV = new WinPanel();
    JPanel loseV = new LosePanel();
    
    // Add the views to the card layout container.
    container.add(view1, "1");
    container.add(view2, "2");
    container.add(view3, "3");
    container.add(view5, "4");
    container.add(winV, "5");
    container.add(loseV, "6");
   
    
    // Show the initial view in the card layout.
    cardLayout.show(container, "1");
    
    // Add the card layout container to the JFrame.
    // GridBagLayout makes the container stay centered in the window.
    this.setLayout(new GridBagLayout());
    this.add(container);

    // Show the JFrame.
    this.setSize(720, 500);
    this.setVisible(true);
    this.getContentPane().setBackground(new Color(103, 128, 101));
    
  }

  // Main function that creates the client GUI when the program is started.
  public static void main(String[] args)
  {
    new ClientGUI();
  }
}