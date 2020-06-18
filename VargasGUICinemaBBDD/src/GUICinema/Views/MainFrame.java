
package GUICinema.Views;


import GUICinema.Model.CinemaModel;
import GUICinema.Model.Room;
import GUICinema.Model.User;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;



/**
 * 
 * @author Roser
 */
public class MainFrame extends JFrame implements ActionListener{
    //attributes
    private CinemaModel model;
    private ActionListener listener;
    private User userForReservation;
    private Room roomForReserv;
    private String FilmForReserv;
    private int sessionForReserv;
    
    //Components
    private JMenuBar myBar;
    private JMenu optionMenu;
    private JMenuItem optionItem;
    
    private LoginPanel myLoginPanel;
    private ChooseSeatsPanel myChooseSeatsPanel;
       
    
    //constructor
    public MainFrame () {
        listener= this;
        initComponents();
    }
    
    //getters & setters
    public void setModel(CinemaModel model) {
        this.model = model;
    }
    
    /**
     * Method for creating view
     */
    private void initComponents() {
        //set a title
        this.setTitle("Roser's Cinema");
        // set no default action on closing window...
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //as we have our own closing method  we add a window listener type WindowAdapter
        this.addWindowListener(new WindowAdapter() {
            //override method for Window Closing
            @Override
            public void windowClosing(WindowEvent we) {
                askForConfirmationExit();
            }
        });
        //create components
        createComponents();
        //add a new panel with a listener
        getContentPane().add(new WelcomePanel(listener));
        //set size of the frame
        this.setSize(500, 500);
        
        }

    /**
     * Method for creating components
     */
    private void createComponents() {
        //create JMenuBar
        myBar = new JMenuBar();
        
        //create each option of the JMenuBar, with its options of JMenuItem, and add it to the JMenuBar
        optionMenu = new JMenu("File");
            //Menu Item: create with name, set ActionCommand, add to listener, and add it to the JMenu
            optionItem = new JMenuItem("Exit");
            optionItem.setActionCommand("exit");
            optionItem.addActionListener(listener);
            optionMenu.add(optionItem);

            //Menu Item: create with name, set ActionCommand, add to listener, and add it to the JMenu
            optionItem = new JMenuItem("Book");
            optionItem.setActionCommand("book");
            optionItem.addActionListener(listener);
            optionMenu.add(optionItem);
            
        myBar.add(optionMenu);    
        
        //Add this JMenuBar to the Frame
        this.setJMenuBar(myBar);
    }

    /**
     * Method to deal with each option selected from de GUI
     * @param ae is the actionEvent that happends
     * it will call the method related to each case
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        //to know which action selected
        String action = ae.getActionCommand();
        //and select the actions to do  depending on it
        switch (action) {
            case "exit":
                askForConfirmationExit();
                break;
            case "book":
                showLoginPanel();
                break;     
            case "submit":
                Boolean control= checkSubmit();
                if (control) {
                    showSeatPanel();
                } else {
                    showMessageNoLogin();
                }                
                break;                 
            default: 
                break;
        }
    }


    /**
     * Method to create a Confirm Dialog that ask the user to confirm if he/she wants to exit or not
     * if it is OK answer, it will exit
     */
    private void askForConfirmationExit() {
        //create a string with the question.
        String question ="Are you sure you want to exit?";
        //Create Message Dialog and receive the answer in a var
        int answer= JOptionPane.showConfirmDialog(this, question);
        if (answer==JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }
     

    /**
     * Method to create and load Show Login Panel, in order to log in and select 
     * room, session and film     
     */
    private void showLoginPanel() {
        //ask for actual Container
        Container pane = getContentPane();
        //erase all
        pane.removeAll();
        //instantiate the view (Jpanel) that we want
        myLoginPanel= new LoginPanel(listener, model);
        //add the component to the container
        pane.add(myLoginPanel);
        //And to paint it, we validate
        validate();
    }

    /**
     * Method for creating and load Seat Panel to select seats 
     */
    private void showSeatPanel() {        
        //ask for actual Container
        Container pane = getContentPane();
        //erase all
        pane.removeAll();
        //instantiate the view (Jpanel) that we want
        myChooseSeatsPanel = new ChooseSeatsPanel (listener, roomForReserv, userForReservation, sessionForReserv, FilmForReserv, model);
        //add the component to the container
        pane.add(myChooseSeatsPanel);
        //And to paint it, we validate
        validate();
    }

    /**
     * Method for recovering name and password wirtten in textfield, 
     * and asking model if they match. If it does, also recover the 
     * other information of Login Panel (room, session and film)
     * @return true if they match
     *  false otherwise
     */
    private boolean checkSubmit() {
        boolean control= false;
        String userNameProv= myLoginPanel.getUserProvName();
        String userPassProv= myLoginPanel.getUserProvPass();
        
        if (userNameProv!=null & !"".equals(userNameProv) & userPassProv!=null & !"".equals(userPassProv)){
            userForReservation=model.checkUser(userNameProv, userPassProv);
            if (userForReservation!=null){
                roomForReserv=model.searchRoomById(myLoginPanel.getSelectedRoom());
                FilmForReserv= myLoginPanel.getSelectedFilm();
                sessionForReserv= myLoginPanel.getSelectedSession();
                control=true;
            }
        }            
        return control;
    }

    /**
     * Method for notifing user his/her login is not right
     */
    private void showMessageNoLogin() {
        JOptionPane.showMessageDialog(this, "User or password incorrect", "No login", JOptionPane.ERROR_MESSAGE);
    }




    
}
