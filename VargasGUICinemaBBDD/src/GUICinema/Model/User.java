
package GUICinema.Model;

import java.util.Objects;

/**
 *
 * @author Roser
 */
public class User {
    //Attributes
    private int id;
    private String userName;
    private String userPass;
    
    //Constructors
    public User(int id, String userName, String userPass) {
        this.id= id;
        this.userName = userName;
        this.userPass = userPass;
    }

    public User() {
    }
    
    //Constructor for DAOReservations
    public User(int id) {
        this.id= id;
    }
    
    public User(User user) {
        this.id= user.id;
        this.userName= user.userName;
        this.userPass= user.userPass;        
    }
    
    //Getters
    public String getUserName() {
        return userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public int getId() {
        return id;
    }
    
    
    //Setters
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
    
    
    
    //Override
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.userName);
        hash = 23 * hash + Objects.hashCode(this.userPass);
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
                if (obj instanceof User) { //obj is a user
                    User other = (User) obj;
                    b= (this.userName.equals(other.userName) & this.userPass.equals(other.userPass));
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
        sb.append("User{");
        sb.append("[Name="); sb.append(userName); sb.append("]");
        sb.append("[Password="); sb.append(userPass); sb.append("]");
        sb.append("}");
        return sb.toString();
    }
    
    
}
