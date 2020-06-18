
package GUICinema.Views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * View for welcome
 * @author Roser
 */
public class WelcomePanel extends JPanel{
    //Attributes
    ActionListener listener;
    JLabel welcomeLabel;
    
    //Constructor
    public WelcomePanel (ActionListener listener) {
        this.listener= listener;
        initComponents();
    }

    //Method to create componets of this JPanel
    private void initComponents() {
        //create a background layout
        setLayout (new BorderLayout());
        
        //instantiate the label,  center text aligment
        welcomeLabel = new JLabel("Welcome to Roser's Cinema!", SwingConstants.CENTER);    
        //Add the label to the layout, aligment center
        add(welcomeLabel, BorderLayout.CENTER);
    }
    
    
}
