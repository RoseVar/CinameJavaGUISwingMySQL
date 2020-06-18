
package GUICinema.Model;

import java.util.Objects;

/**
 *
 * @author Roser
 */
public class Reservation {
    //Attributes
    private User myUser;
    private Room myRoom;
    private int Session;
    private int numCol;
    private int numRow;
    
    //Constructor
    public Reservation(User myUser, Room myRoom, int Session, int numRow, int numCol) {
        this.myUser = myUser;
        this.myRoom = myRoom;
        this.Session = Session;
        this.numCol = numCol;
        this.numRow = numRow;
    }

    public Reservation() {
    }
    
    public Reservation(Reservation reservation) {
        this.myUser = reservation.myUser;
        this.myRoom = reservation.myRoom;
        this.Session = reservation.Session;
        this.numCol = reservation.numCol;
        this.numRow = reservation.numRow;
    }
    
    //Getters
    public User getMyUser() {
        return myUser;
    }

    public Room getMyRoom() {
        return myRoom;
    }

    public int getSession() {
        return Session;
    }

    public int getNumCol() {
        return numCol;
    }

    public int getNumRow() {
        return numRow;
    }
    
    //Setters
    public void setMyUser(User myUser) {
        this.myUser = myUser;
    }

    public void setMyRoom(Room myRoom) {
        this.myRoom = myRoom;
    }

    public void setSession(int Session) {
        this.Session = Session;
    }

    public void setNumCol(int numCol) {
        this.numCol = numCol;
    }

    public void setNumRow(int numRow) {
        this.numRow = numRow;
    }
    
    
    
    //Override
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.myRoom);
        hash = 89 * hash + this.Session;
        hash = 89 * hash + this.numCol;
        hash = 89 * hash + this.numRow;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean b;
        if (obj == null){ //null object
            b= false;
        } else {
            if (this== obj) { //same object
                b= true;
            } else {
                if (obj instanceof Reservation) { //obj is a Reservation
                    Reservation other = (Reservation) obj;
                    b= (this.myRoom==other.myRoom & this.Session==other.Session 
                            & this.numCol== other.numCol & this.numRow== other.numRow);
                } else {
                    b= false;
                }
            }
        }
        return b;
    }
    
    
    
    
}
