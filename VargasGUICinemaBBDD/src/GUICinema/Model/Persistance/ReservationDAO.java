
package GUICinema.Model.Persistance;



import GUICinema.Model.Reservation;
import GUICinema.Model.Room;
import GUICinema.Model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Roser
 */
public class ReservationDAO {
         
    //Attributes & queries:
    static final String QUERY_LIST_ALL = "SELECT * FROM booking;";
    static final String QUERY_RESERVATION_BY_ROOM= "SELECT * FROM booking WHERE idRoom=?;";
    static final String QUERY_INSERT= "INSERT INTO booking (`idUser`, `idRoom`,`session`,`row`,`col`) VALUES (?, ?,?, ?, ?);";

    ResultSet myResultSet;//resultset for query ALL
    ResultSet myResultSetReservationRoom; //resultset for query BY ROOM

    
    //Constructor
    public ReservationDAO() {
        try {
            Connection con = DbConnect.getConnection();
            //if connection is not null
            if (con!=null) {
                //ask for a statement
                Statement st = con.createStatement(
                 ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                //execute the query
                myResultSet = st.executeQuery(QUERY_LIST_ALL);
            }
        } catch (Exception e) {
            myResultSet=null;

        }
    }
    
    
    /**
     * Method for transforming a ResultSet into a Reservation
     * set field by field
     * @param res ResultSet to be parsed
     * @return Reservation with the data of the ResultSet
     * or null otherwise
     */
    private Reservation resultSetReservation(ResultSet res) {
        Reservation provRes=null;
        try {
            int roomId=-1;
            int userId=-1;
            int sess=-1;
            int seatCol=-1;
            int seatRow=-1;
            //get attributes for a DB reservation  into a reservation object
            try {
                roomId= Integer.parseInt(res.getString("idRoom"));
                userId= Integer.parseInt(res.getString("idUser"));
                sess= Integer.parseInt(res.getString("session"));
                seatCol= Integer.parseInt(res.getString("col"));
                seatRow= Integer.parseInt(res.getString("row"));
                provRes= new Reservation(new User(userId), new Room (roomId), sess, seatCol, seatRow);
            } catch (NumberFormatException e){
                provRes= null;
            }            
        } catch (SQLException ex) {
            provRes=null;
        }
        return provRes;
        
    }
    
    /**
     * Method for recovering the reservations of DB into a list of 
     * reservations
     * @return List of reservations (empty or full)
     * null otherwise
     */
    public List<Reservation> getReservations() {
        List<Reservation> result = new ArrayList<Reservation>();
        //use the resultset of the query for all reservations to create a list of reservations
        try {            
            while (myResultSet.next()){
             Reservation prov= resultSetReservation(myResultSet);
             result.add(prov);
            }
        } catch (SQLException ex) {
            result= null;
        }
        return result;
    }

    /**
     * Method for recovering a list of reservations of a given room
     * @param room to make the query
     * @return a list of reservations for a list (empty or not)
     * null otherwise
     */
    public List<Reservation> getReservationsByRoom(Room room) {
        List<Reservation> result=new ArrayList<Reservation>();
        try {
            Connection con = DbConnect.getConnection();
            //if connection is not null
            if (con!=null) {
                //ask for a statement
                PreparedStatement st2 = con.prepareStatement(QUERY_RESERVATION_BY_ROOM,
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                st2.setInt(1, room.getIdRoom());                
                //execute the query
                myResultSetReservationRoom = st2.executeQuery();
                while (myResultSetReservationRoom.next()) {
                    result.add(resultSetReservation(myResultSetReservationRoom));                    
                };              
            }
        //error with connection or with the query    
        } catch (SQLException e) {            
            result=null;
        }
        return result;
    }
    
    
    /**
     * Method for inserting reservations into DB
     * @param newReservations list of new reservations 
     * @return 1 if all reservations can be done (all seats are avaliable)
     *          0 if all reservations can not be done (any of the seats are not avaliable)
     *          -1 if there is a problem in connection with DB
     */
    public static int setReservations(List<Reservation> newReservations) {
        int result=0;
        try {
            Connection con = DbConnect.getConnection();
            //if connection is not null
            if (con!=null) {
                //ask for a statement
                PreparedStatement st3 = con.prepareStatement(QUERY_INSERT,
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                con.setAutoCommit(false);
                //set parameters for each reservation adn execute query
                try {
                    for (Reservation res: newReservations) {
                        st3.setInt(1, res.getMyUser().getId());                
                        st3.setInt(2, res.getMyRoom().getIdRoom()); 
                        st3.setInt(3, res.getSession()); 
                        st3.setInt(4, res.getNumCol()); 
                        st3.setInt(5, res.getNumRow()); 
                        result = result+ st3.executeUpdate();
                    }
                    //If there is as many rows affected as reservations, it's ok, so commit
                    if (result==newReservations.size()){
                        result=1;
                        con.commit();
                        con.setAutoCommit(true);
                    } 
                //if there is an error in inserting a reservartion    
                } catch (SQLException ex){
                        result=0;
                        con.rollback();
                        con.setAutoCommit(true);
                }
            //Connection is null    
            } else {
                result=-1;
            }
        //Error in getting a connection    
        }catch (Exception e) {
                result= -1;
        }
       
        return result;
    }
                
}

