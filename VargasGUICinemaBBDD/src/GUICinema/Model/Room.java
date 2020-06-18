
package GUICinema.Model;

/**
 *
 * @author Roser
 */
public class Room {
    //Attributtes
    private int idRoom;
    private int totalRow;
    private int totalCol;
    
    //Constructors
    public Room(int idRoom, int totalRow, int totalCol) {
        this.idRoom = idRoom;
        this.totalRow = totalRow;
        this.totalCol = totalCol;
    }
    
    public Room(){        
    }
    
    //constructor for use in DAO
    public Room(int idRoom){
        this.idRoom = idRoom;        
    }
    
    public Room (Room room) {
        this.idRoom= room.idRoom;
        this.totalCol= room.totalCol;
        this.totalRow= room.totalRow;
    }

    //Getters
    public int getIdRoom() {
        return idRoom;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public int getTotalCol() {
        return totalCol;
    }
    
    //Setters
    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public void setTotalCol(int totalCol) {
        this.totalCol = totalCol;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.idRoom;
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
                if (obj instanceof Room) { //obj is a room
                    Room other = (Room) obj;
                    b= (this.idRoom== other.idRoom); //compare idRoom of 2 instances
                } else {
                    b= false;
                }
            }
        }
        return b;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Room{");
        sb.append("[id="); sb.append(idRoom); sb.append("]");
        sb.append("[Columns="); sb.append(totalCol); sb.append("]");
        sb.append("[Rows="); sb.append(totalRow); sb.append("]");
        sb.append("}");
        return sb.toString();
    }
    
    
}
