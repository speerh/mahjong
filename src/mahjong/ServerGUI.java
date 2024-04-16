package mahjong;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class ServerGUI extends JFrame {

	private JLabel status; //Initialized to “Not Connected”
	private JLabel statusText;
	private String[] labels = {"Port #", "Timeout"};
	private JTextField[] textFields = new JTextField[labels.length];
	private JTextArea log;
	private JButton listen;
	private JButton close;
	private JButton stop;
	private JButton quit;
	private ChatServer server;

	public ServerGUI() {	
		int i = 0;
		
	    this.setTitle("Server");
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    //create handler
	    EventHandler handler = new EventHandler();
	    
	    //Jpanel
	    JPanel north = new JPanel(new FlowLayout());
	    JPanel center = new JPanel(new FlowLayout());
	    center.setLayout(new BoxLayout(center,BoxLayout.PAGE_AXIS));
	    center.setBorder(new EmptyBorder(0, 75, 10, 75));
	    JPanel south = new JPanel(new FlowLayout());
	    
	    //jlabel
	    statusText = new JLabel("Status:",JLabel.CENTER);
	    status = new JLabel("Not Connected",JLabel.CENTER);
	    status.setForeground(Color.red);
	    
	    //nested panels
	    JPanel nested1 = new JPanel(new FlowLayout());
	    JPanel nested2 = new JPanel(new FlowLayout());
	    
	    for(i = 0; i < textFields.length; i++)
	    {
	    	//Label for textfields
	        JLabel clientInfo = new JLabel(labels[i]);
	        center.add(clientInfo);
	        
	        textFields[i] = new JTextField(labels.length);
	        textFields[i].setColumns(10);
	        center.add(textFields[i]);
	        
	        if(i == 0) {
	        	nested1.add(clientInfo, BorderLayout.CENTER);
	        	nested1.add(textFields[i], BorderLayout.CENTER);
	        } else if(i == 1) {
	        	nested2.add(clientInfo, BorderLayout.CENTER);
	        	nested2.add(textFields[i], BorderLayout.CENTER);
	        }
	    }
	    
	    center.add(nested1);
	    center.add(nested2);
	    
	    
	    //ScrollPane and Text Area
	    JLabel ServerLog = new JLabel("Server Log Below",JLabel.CENTER);
	    log = new JTextArea(10,35);
	    JScrollPane LogScroll = new JScrollPane(log);
	    
	    //add text area
	    center.add(ServerLog);
	    center.add(LogScroll);
	    
	    //add label to panel
	    north.add(statusText);
	    north.add(status);
	    
	    
	    //jbuttons
	    listen = new JButton("Listen");
	    close = new JButton("Close");
	    stop = new JButton("Stop");
	    quit = new JButton("Quit");
	    
	    //add listener
	    listen.addActionListener(handler);
	    close.addActionListener(handler);
	    stop.addActionListener(handler);
	    quit.addActionListener(handler);
	    
	    //add button to panel
	    south.add(listen);
	    south.add(close);
	    south.add(stop);
	    south.add(quit);
	    
	    //add panel to frame
	    this.add(north, BorderLayout.NORTH);
	    this.add(center, BorderLayout.CENTER);
	    this.add(south, BorderLayout.SOUTH);
	    
	    //Make visible
	    this.setSize(450,450);
	    this.setVisible(true);
	    
	    server = new ChatServer();
	    server.setLog(log);
	    server.setStatus(status);
	}

	public static void main(String[] args) {
		new ServerGUI(); // args[0] represents the title of the GUI
	}

	// Event handler class
	class EventHandler implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			Object buttonClicked = e.getSource();

			//Listen button listener
			if (buttonClicked == listen) {
				System.out.println("Listen button pressed");
				
				int port = 0;
				int timeout = 0;
				
				String portValue = textFields[0].getText();
				String timeoutValue = textFields[1].getText();
				
				
				if (portValue.equals("") || timeoutValue.equals("")) {
					log.append("Port Number/timeout not entered before pressing Listen\n");
				}
				else {
					port = Integer.parseInt(textFields[0].getText());
					timeout = Integer.parseInt(textFields[1].getText());
					
					server.setPort(port);
					server.setTimeout(timeout);
					
					try {
						server.listen();
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
				
			//Close button listener
			} else if (buttonClicked == close) {
				System.out.println("Close button pressed");
				
				//check if server is listening
				 if (!server.isRunning()) {
			          log.append("Server not currently started\n");
			     }
				 else {
					 try {
						server.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				 }
			
			//Stop button listener
			} else if (buttonClicked == stop) {
				System.out.println("Stop button pressed");
				
				//check if server is listening
				if (!server.isRunning()) {
			          log.append("Server not currently started\n");
			     }
				else {
					 server.stopListening();
				}
			
			//Quit button listener
			} else if (buttonClicked == quit) {
				try {
					server.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
			}

		}
	}
}
