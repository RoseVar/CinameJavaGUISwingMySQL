package GUICinema.Model;

import GUICinema.Model.Persistance.ReservationDAO;
import GUICinema.Model.Persistance.RoomDAO;
import GUICinema.Model.Persistance.UserDAO;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author Roser
 */
public class CinemaModel {
    //Attributes
    UserDAO myClientsDAO;
    RoomDAO myRoomsDAO;
    ReservationDAO myReservationsDAO;
    List<String> sessions;
    List<String>films;
    
    

    //Constructor
    public CinemaModel() {
        this.myClientsDAO = new UserDAO();
        this.myRoomsDAO = new RoomDAO();
        this.myReservationsDAO = new ReservationDAO();
        sessions= new ArrayList();
        sessions.add("Matinal"); 
        sessions.add("Tarde"); 
        sessions.add("Noche");
        films= new ArrayList();
        films.add("Frozen");
        films.add("Jumanji");
        films.add("Hook");
   
    }

    //Getters
    public List<String> getSessions() {
        return sessions;
    }

    public List<String> getFilms() {
        return films;
    }   
    
    public List<Reservation> getReservationsModel(Room room) {
        List<Reservation> prov= new ArrayList<Reservation>();
            prov= myReservationsDAO.getReservationsByRoom(room);
        return prov;
    }
    
    public List<Room> getRoomsModel() {
        return myRoomsDAO.getRooms();  
    }

    /**
     * Method for validating an user with his/her password
     * @param userNameProv name of the user
     * @param userPassProv password of the user
     * @return 
     */
    public User checkUser(String userNameProv, String userPassProv) {
        return myClientsDAO.checkUser(userNameProv, userPassProv);
    }

    /**
     * Method for recovering all data of a room by its id
     * @param idRoom Id of the room to ask for
     * @return Room with complete info
     */
    public Room searchRoomById(int idRoom) {
        Room res= null;
        if (idRoom>0) {
            res= myRoomsDAO.getRoomById(idRoom);
        }
        return res;
    }
    
    /**
     * Method for adding a reservation into DB
     * @param newReservations list of new reservations to add
     * @return 1 if all reservations can be done (all seats are avaliable)
     *          0 if all reservations can not be done (any of the seats are not avaliable)
     *          -1 if there is a problem in connection with DB
     */
    public int addReservation(List<Reservation> newReservations) {
        int control= -1; 
        control = ReservationDAO.setReservations(newReservations);
        return control;
    }
    
 }

