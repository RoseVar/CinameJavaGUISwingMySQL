package GUICinema.Views;


import GUICinema.Model.CinemaModel;
import GUICinema.Model.Room;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * 
 * @author Roser
 */
public class LoginPanel extends JPanel{
    //Attributes  
    CinemaModel myModel;
    ActionListener listener;

    //Components
    JLabel userLabel;
    JLabel passLabel;
    JLabel roomLabel;
    JLabel sessionLabel;   
    JLabel filmLabel;
    
    JTextField userTF;
    JTextField passTF;    
    JComboBox roomCB;
    JComboBox sessionCB;
    JComboBox filmCB;
    
    JButton confirmationButton;

  
    //Constructor
    public LoginPanel(ActionListener listener,   CinemaModel model) {
        this.listener = listener;  
        this.myModel= model;
        initComponets();
    }

    /**
     * Method for create and distribute all components of this view
     */
    private void initComponets() {       
        //set the background layout
        this.setLayout(new GridLayout(0,1));

        //create a new panel
        JPanel pane = new JPanel();        
        //create a new panel
        GridBagLayout  productLayout= new GridBagLayout();
        //set the layout to this panel created
        pane.setLayout(productLayout);
        
        //Components for user: label + textfield
        GridBagConstraints constraints = new GridBagConstraints();
        userLabel = new JLabel ("User: ");
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;  
        constraints.weightx = 2.0;
        constraints.anchor= GridBagConstraints.WEST;
        constraints.insets =new Insets(0,10,10,10);
        pane.add (userLabel, constraints);
        constraints.weightx = 0.0;
        
        userTF= new JTextField(40);
        constraints.gridx = 3; 
        constraints.gridy = 0; 
        constraints.gridwidth = 2; 
        constraints.gridheight = 1; 
        constraints.weightx = 2.0;
        constraints.fill= GridBagConstraints.HORIZONTAL;
        constraints.insets =new Insets(0,10,10,10);
        pane.add (userTF, constraints);
        constraints.weightx = 0.0;
        
        
        //Components for password: label + textfield
        passLabel = new JLabel ("Password: ");
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;  
        constraints.weightx = 2.0;
        constraints.anchor= GridBagConstraints.WEST;
        constraints.insets =new Insets(0,10,10,10);
        pane.add (passLabel, constraints);
        constraints.weightx = 0.0;
        
        passTF= new JPasswordField(40);
        constraints.gridx = 3; 
        constraints.gridy = 1; 
        constraints.gridwidth = 2; 
        constraints.gridheight = 1; 
        constraints.weightx = 2.0;
        constraints.fill= GridBagConstraints.HORIZONTAL;
        constraints.insets =new Insets(0,10,10,10);
        pane.add (passTF, constraints);
        constraints.weightx = 0.0;
        
        //Components for room: label + combobox
        roomLabel = new JLabel ("Room: ");
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;  
        constraints.weightx = 2.0;        
        constraints.anchor= GridBagConstraints.WEST;
        constraints.insets =new Insets(0,10,10,10);
        pane.add (roomLabel, constraints);
        constraints.weightx = 0.0;        
        
        roomCB= new JComboBox();
        constraints.gridx = 3; 
        constraints.gridy = 2; 
        constraints.gridwidth = 2; 
        constraints.gridheight = 1; 
        constraints.weightx = 2.0;
        constraints.fill= GridBagConstraints.HORIZONTAL;
        for (Room r: myModel.getRoomsModel()){
            roomCB.addItem(r.getIdRoom());
        }      
        constraints.insets =new Insets(0,10,10,10);
        pane.add (roomCB, constraints);
        constraints.weightx = 0.0;
        
        //Components for session: label + combobox
        sessionLabel = new JLabel ("Session: ");
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;  
        constraints.weightx = 2.0;        
        constraints.anchor= GridBagConstraints.WEST; 
        constraints.insets =new Insets(0,10,10,10);
        pane.add (sessionLabel, constraints);
        constraints.weightx = 0.0;        
        
        sessionCB= new JComboBox();
        constraints.gridx = 3; 
        constraints.gridy = 3; 
        constraints.gridwidth = 2; 
        constraints.gridheight = 1; 
        constraints.weightx = 2.0;
        sessionCB.addActionListener(listener);
        constraints.fill= GridBagConstraints.HORIZONTAL;
        for (String sessionName: myModel.getSessions()){
            sessionCB.addItem(sessionName);
        }   
        constraints.insets =new Insets(0,10,10,10);
        pane.add (sessionCB, constraints);
        constraints.weightx = 0.0;
        
        //Components for film: label + combobox
        filmLabel = new JLabel ("Film: ");
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;  
        constraints.weightx = 2.0;        
        constraints.anchor= GridBagConstraints.WEST;
        constraints.insets =new Insets(0,10,10,10);
        pane.add (filmLabel, constraints);
        constraints.weightx = 0.0;        
        
        filmCB= new JComboBox();
        constraints.gridx = 3; 
        constraints.gridy = 4; 
        constraints.gridwidth = 2; 
        constraints.gridheight = 1; 
        constraints.weightx = 2.0;
        constraints.fill= GridBagConstraints.HORIZONTAL;
        for (String filmName: myModel.getFilms()){
            filmCB.addItem(filmName);
        }           
        constraints.insets =new Insets(0,10,10,10);
        pane.add (filmCB, constraints);
        constraints.weightx = 0.0;
        
        //Component confirmation button
        confirmationButton= new JButton("Submit");
        constraints.gridx = 5;
        constraints.gridy = 5;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;  
        constraints.anchor= GridBagConstraints.WEST;
        constraints.insets =new Insets(0,10,10,10);
        //set an action listener to it
        confirmationButton.setActionCommand("submit");
        //add a listener to the button
        confirmationButton.addActionListener(listener);
        pane.add (confirmationButton, constraints);
        
        //Add panel to frame
        this.add(pane);
        
    }
    
    /**
     * Method for getting the name writen in the textfield for Name
     * @return name in string
     */
    public String getUserProvName() {
        return userTF.getText();
    }
    
    /**
     * Method for getting the password writen in the textfield for Password
     * @return name in string 
     */
    public String getUserProvPass(){
        return passTF.getText();
    }
    
    /**
     * Method for getting the selected room, as they are numerated (starting with 1)
     * and index of combo box is starting with 0, we add the result 1, to have the number
     * of the room
     * @return number of room selected
     */
    public int getSelectedRoom() {
        int roomSel= roomCB.getSelectedIndex()+1;
        return roomSel;
    }

    /**
     * Method for getting the selected film, as they are String, so they are objects, 
     * we will parse it, so the method will return the name of the film
     * @return name of the film in string
     */
    public String getSelectedFilm() {
        String filmSel= (String) filmCB.getSelectedItem();
        return filmSel;        
    }

    /**
     * Method for getting the selected session, as they are numerated (starting with 1)
     * and index of combo box is starting with 0, we add the result 1, to have the number
     * of the session
     * @return number of session selected
     */    
    public int getSelectedSession() {
        int sessionSel= sessionCB.getSelectedIndex()+1;
        return sessionSel;
    }
}
