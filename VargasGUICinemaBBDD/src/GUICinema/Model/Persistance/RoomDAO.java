/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUICinema.Model.Persistance;

import GUICinema.Model.Room;
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
public class RoomDAO {
     //Attributes & queries:
    static final String QUERY_LIST_ALL = "SELECT * FROM room;";
    static final String QUERY_ROOM_BY_ID = "SELECT * FROM room WHERE id=?;";
    
    ResultSet myResultSet; //resultset for query ALL
    ResultSet myResultSetRoom; //resultset for query BY ROOM

    
    //Constructor
    public RoomDAO() {
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
     * Method to change a ResultSet into a Room
     * set field by field
     * @param res ResultSet to be parsed
     * @return Room with the data of the ResultSet
     * or null otherwise
     */
    private Room resultSetRoom(ResultSet res) {
        Room provRes=null;
        try {
            int idRoom=-1;
            int rowTotal=-1;
            int colTotal=-1;
            try {
                idRoom= Integer.parseInt(res.getString("id"));
                rowTotal= Integer.parseInt(res.getString("totalRows"));
                colTotal= Integer.parseInt(res.getString("totalCols"));
                provRes= new Room(idRoom, rowTotal, colTotal);
            } catch (NumberFormatException e){
                provRes= null;
            }            
        } catch (SQLException ex) {;
            provRes=null;
        }
        return provRes;
        
    }
    
     /**
     * Method for recovering the rooms of DB into a list of 
     * rooms
     * @return List of rooms (empty or full)
     * null otherwise
     */
    public List<Room> getRooms() {
        List<Room> result = new ArrayList<Room>();
        try {   
            myResultSet.first();
            Room prov= resultSetRoom(myResultSet);
            result.add(prov);
            while (myResultSet.next()){               
             prov= resultSetRoom(myResultSet);
             result.add(prov);
            }
        } catch (SQLException ex) {
            result= null;
        }
        return result;
    }
    
    /**
     * Method for ask for a Room details from its id
     * @param idRoom to ask for
     * @return Room
     * null otherwise
     */
    public Room getRoomById(int idRoom) {
        Room result=null;
        try {
            Connection con = DbConnect.getConnection();
            //if connection is not null
            if (con!=null) {
                //ask for a statement
                PreparedStatement st = con.prepareStatement(QUERY_ROOM_BY_ID,
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                st.setInt(1, idRoom);
                //execute the query
                myResultSetRoom = st.executeQuery();
                //if has one next (one room found) recover its details
                if (myResultSetRoom.next()) {
                    result=resultSetRoom(myResultSetRoom);            
                //if does not have any next, there is no result with this id    
                } else {
                    result=null;
                }                
            }
        } catch (Exception e) {
            result=null;
        }
        return result; 
    }

}
