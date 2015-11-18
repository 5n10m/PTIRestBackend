package pti;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
import java.sql.*;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.ws.rs.*;
/**
 *
 * @author david
 */

@Stateless
@Path("/login")
public class login {
    
    
    @GET
    public StringTokenizer login(@QueryParam("user") String user, @QueryParam("password") String password) {
        return $login(user, password);
    }
    
    StringTokenizer $login(String user, String password) {
        StringTokenizer token = new StringTokenizer("user"+"password");
        
        DBConection db = null;
        //ResultSet rs = db.conectexecute("Select count (*) as contador from users where username = "+user+" and password = "+password+";");
        ResultSet rs = db.conectexecute("Select * from users;");
        try {
            while(rs.next()){
                //Retrieve by column name
                int result  = rs.getInt("contador");
            }
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // GENERATE NEW TOKEN WITH NEW VALIDITY
        
        return token;
    }
    
    @POST
    public StringTokenizer loginpost(@FormParam("user") String user, @FormParam("password") String password) {
        return $login(user, password);
    }
    
    StringTokenizer $loginpost(String user, String password) {
        StringTokenizer token = new StringTokenizer("user"+"password");
        /*DBConection db = null;
        ResultSet rs = db.conectexecute("Select count (*) as contador from users where username = "+user+" and password = "+password+";");
        try {
            while(rs.next()){
                //Retrieve by column name
                int result  = rs.getInt("contador");
            }
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        return token;
    }
    
}