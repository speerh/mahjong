package mahjong;

import java.awt.*;
import javax.swing.*;

public class WinPanel extends JPanel
{
  // Constructor for the initial panel.
  public WinPanel()
  {
    // Create the controller.
    //InitialControl controller = new InitialControl(container);
    
    // Create the information label.
	  
	  //set string using IC
    JLabel label = new JLabel("YOU WIN", JLabel.CENTER);
    
    
    // Arrange the components in a grid.
    JPanel grid = new JPanel(new GridLayout(1, 1, 5, 5));
    grid.setOpaque(false);
    grid.add(label);
    this.add(grid);
    this.setBackground(new Color(103, 128, 101));
  }
}