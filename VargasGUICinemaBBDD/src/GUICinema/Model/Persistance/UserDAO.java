
package GUICinema.Model.Persistance;

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
public class UserDAO {
    //Attributes & queries:
    static final String QUERY_LIST_ALL = "select * from users;";
    static final String QUERY_FIND_ONE = "select * from users WHERE userName = ? AND userPass=?;";
    
    private ResultSet myResultSet; //resultset for query ALL
    private ResultSet myResultSetFindOne; //resultset for query ONE

    
    //Constructor
    public UserDAO() {
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
     * Method to change a ResultSet into a User
     * set field by field
     * @param res ResultSet to be parsed
     * @return User with the data of the ResultSet
     * or null otherwise
     */
    private User resultSetUser(ResultSet res) {
        User provRes=null;
        try {
            String userName="";
            String userPass="";
            int userId=-1;
            try {
                userName= res.getString("userName");
                userId= Integer.parseInt(res.getString("id"));
                userPass= res.getString("userPass");
                provRes= new User(userId, userName, userPass);
            } catch (NumberFormatException e){
                provRes= null;
            }            
        } catch (SQLException ex) {
            provRes=null;
        }
        return provRes;
        
    }
    
    /**
     * Method for recovering the users of DB into a list of 
     * users
     * @return List of users (empty or full)
     * null otherwise
     */
    public List<User> getUsers() {
        List<User> result = new ArrayList<User>();
        try {            
            while (myResultSet.next()){
             User prov= resultSetUser(myResultSet);
             result.add(prov);
            }
        } catch (SQLException ex) {
            result= null;
        }
        return result;
    }

    /**
     * Method for validating a user (match username with userpassword)
     * @param userNameProv name of the user to find
     * @param userPassProv given password of the user
     * @return an User if there is an user whose username and pass matches with
     * the given name and pass
     *          null otherwise.
     */
    public User checkUser(String userNameProv, String userPassProv) {
        User result=null;
        try {
            Connection con = DbConnect.getConnection();
            //if connection is not null
            if (con!=null) {
                //ask for a statement
                PreparedStatement st = con.prepareStatement(QUERY_FIND_ONE,
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
                st.setString(1, userNameProv);
                st.setString(2, userPassProv);
                //execute the query
                myResultSetFindOne = st.executeQuery();
                
                if(myResultSetFindOne!= null) {
                    if (myResultSetFindOne.next()){
                        int id= myResultSetFindOne.getInt("id");
                        String name= myResultSetFindOne.getString("userName");
                        String pass = myResultSetFindOne.getString("userPass");
                        result = new User (id, name, pass);                        
                    }
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
