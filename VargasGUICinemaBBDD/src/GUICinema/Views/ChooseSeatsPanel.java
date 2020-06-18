package GUICinema.Views;


import GUICinema.Model.CinemaModel;
import GUICinema.Model.Reservation;
import GUICinema.Model.Room;
import GUICinema.Model.User;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Roser
 */
public class ChooseSeatsPanel extends JPanel implements ActionListener{
    //Attributes
    private Room myRoom;
    private User myUser;
    private int mySession;
    private String myFilm;    
    
    private List<Reservation> myReservations;   
    private List<Reservation> provReserv;  
   
    private ActionListener myListener;
    private ActionListener outListener;
    private CinemaModel myModel;
    //Components
    private JPanel seatsPanel;
    private ButtonSeat [][] mySeats;    
    private JButton buyBut;
    private JButton refreshBut;

    
    //Constructor
    public ChooseSeatsPanel (ActionListener outListener, Room room, User user, int session, String film, CinemaModel model) {   
        this.outListener= outListener;
        this.myRoom= room;
        this.myUser= user;
        this.mySession= session;
        this.myFilm= film;
        this.mySeats = new ButtonSeat[myRoom.getTotalRow()][myRoom.getTotalCol()];
        this.myModel= model;
        this.myListener= this;
        this.provReserv= new ArrayList<Reservation>();

        //create panels, components
        initComponents();
        
        //Create buttons and add them to the panel
        for (int i=0; i<room.getTotalRow(); i++) {
            for (int j=0; j<room.getTotalCol(); j++) {
                mySeats[i][j] = new ButtonSeat(i, j);               
                mySeats[i][j].setBackground(mySeats[i][j].getBgColor()); //Set color depending of its select state
                mySeats[i][j].setActionCommand("book");                
                mySeats[i][j].addActionListener(myListener);
                seatsPanel.add(mySeats[i][j]);
            }
        }
        refreshSeats();
       
    }

    /**
     * Method for creating the view and components and data of it
     */
    private void initComponents() {
        //set the background layout
        this.setLayout(new BorderLayout());
       
        //create a Summary panel
        JPanel pane = new JPanel();
        //create a new panel
        GridLayout layoutGrid = new GridLayout(6,0);
        //set the layout to this panel created
        pane.setLayout(layoutGrid);
        
        //Content of rows and cols
        pane.add(new JLabel("Choosing seats for:"));
        pane.add(new JLabel("User: "+myUser.getUserName()));  
        pane.add(new JLabel("Room: "+ myRoom.getIdRoom()));         
        pane.add(new JLabel("Session: " + showSession(mySession)));   
        pane.add(new JLabel("Film: " +myFilm));
        
        //Add panel to the general frame
        this.add(pane, BorderLayout.NORTH);
        
        //create Seats panel
        seatsPanel = new JPanel();
        //create a new panel
        GridLayout layoutGrid2 = new GridLayout(myRoom.getTotalRow(),myRoom.getTotalCol());
        //set the layout to this panel created
        seatsPanel.setLayout(layoutGrid2);
        
        //Add panel to the general frame
        this.add(seatsPanel, BorderLayout.CENTER);
        
        
        //create a Summary panel
        JPanel paneEnd = new JPanel();
        //create a new panel
        GridLayout layoutGrid3 = new GridLayout(2,2);
        //set the layout to this panel created
        paneEnd.setLayout(layoutGrid3);
        
        paneEnd.add(new JLabel());
        paneEnd.add(new JLabel());
        //Content of rows and cols
        refreshBut= new JButton("Refresh");
        refreshBut.setHorizontalTextPosition(AbstractButton.LEFT);
        refreshBut.setActionCommand("refresh");
        refreshBut.addActionListener(myListener);
        paneEnd.add(refreshBut);
        
        buyBut= new JButton("Buy");
        buyBut.setHorizontalTextPosition(AbstractButton.RIGHT);
        buyBut.setActionCommand("buy");
        buyBut.addActionListener(myListener);
        paneEnd.add(buyBut);
        
        //Add panel to the general frame
        this.add(paneEnd, BorderLayout.SOUTH);
        
    }

    /**
     * Method for showing in details the name of the session Choosed
     * @param mySession integer that identify session 
     * @return the string of the name of the session
     */
    private String showSession(int mySession) {
        String session="";
        switch (mySession){
            case 1:
                session= "Matinal"; 
                break;
            case 2:
                session= "Tarde"; 
                break;
            case 3:
                session= "Noche";
                break;
            default:
                break;
        }
        return session;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        //to know which action selected
        String action = ae.getActionCommand();
        //and create the actions to do 
        switch (action) {
            case "refresh":
                refreshSeats();
                break;
            case "book":
                preBookSeats(ae);
                break;
            case "buy":
                buySeats();
                break;
        }        
    }   

    /**
     * Method for repaint seats with real reservations done and
     * no pre-selected seats
     */
    private void refreshSeats() {
        //refresh reservations and repaint them
        myReservations = myModel.getReservationsModel(myRoom);
        myReservations.forEach((reserv) ->{
            mySeats[reserv.getNumRow()][reserv.getNumCol()].setEnabled(false);
        });
        //set all seats to no selected and repaint them
        for (int i=0; i<myRoom.getTotalRow(); i++) {
            for (int j=0; j<myRoom.getTotalCol(); j++) {
                mySeats[i][j].setSelected(false);
                mySeats[i][j].setBackground(mySeats[i][j].getBgColor());
            }
        }
        //Clear provisional reservations to start again
        provReserv.clear();
    }

    /**
     * Method for paiting seleted seats into color of selected ones 
     * and add seleted seats in a provisional list to do reservation after when a 
     * seat is clicked
     * (or turn to normal color and delete from de provisional list, if the seat is
     * cliked again)
     * @param ae action event that will inform us with is the button clicked
     */
    private void preBookSeats(ActionEvent ae) {
        //create a button from the source of the event
        JButton prov= (JButton)ae.getSource();
        //check it to all the buttons that we have
        for (int i=0; i<myRoom.getTotalRow(); i++) {
            for (int j=0; j<myRoom.getTotalCol(); j++) {
                //when it matches
                if (mySeats[i][j]==prov) {  
                    //depending of its selected state: change the state, change color and
                    //add or delete of the povisional list
                    if (!mySeats[i][j].isSelected()){
                        mySeats[i][j].setSelected(true);
                        mySeats[i][j].setBackground(mySeats[i][j].getBgColor());
                        provReserv.add(new Reservation (myUser, myRoom, mySession, i, j));

                    } else {
                        mySeats[i][j].setSelected(false);
                        mySeats[i][j].setBackground(mySeats[i][j].getBgColor());
                        provReserv.remove(new Reservation (myUser, myRoom, mySession, i, j));

                    }                    
                }
            }
        }
    }
        
    /**
     * Method for do the reservation and notify the user of the result
     */
    private void buySeats() {
        int result= myModel.addReservation(provReserv);
        //depending of the result of the insert
        switch (result) {
            case -1:                
                JOptionPane.showMessageDialog(this, "BD connections problems", "No connection", JOptionPane.ERROR_MESSAGE);
                //repaint reservations done and clear selected seats
                refreshSeats();
                break;
            case 0:
                JOptionPane.showMessageDialog(this, "Some seats are not avaliable, please refresh view", "Some seats no avaliable", JOptionPane.ERROR_MESSAGE);
                //repaint reservations done and clear selected seats
                refreshSeats();
                break;
            case 1:
                JOptionPane.showMessageDialog(this, "You have booked " + provReserv.size()+ " seats.", "Seats booked", JOptionPane.INFORMATION_MESSAGE);
                //repaint reservations done and clear selected seats
                refreshSeats();
                break;                                
        }
    }    
}
