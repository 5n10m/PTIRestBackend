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
    public String login(@QueryParam("user") String user, @QueryParam("password") String password) {
        return $login(user, password);
    }
    
    String $login(String user, String password) {
        String token = new String("user"+"password");
        
        DBConection db = null;
        //ResultSet rs = db.conectexecute("Select count (*) as contador from users where username = "+user+" and password = "+password+";");
        ResultSet rs = db.conectexecute("Select count (*) as contador from users where username =\""+user+" and password = \""+password+";");
        try {
            while(rs.next()){
                //Retrieve by column name
                int contador = rs.getInt("contador");
                token = Integer.toString(contador);
                if (contador < 0) token = "ACCES PERMES";
                //token  = Integer.toString(rs.getInt("contador"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // GENERATE NEW TOKEN WITH NEW VALIDITY
        
        return token;
    }
    
    /*@POST
    public String loginpost(@FormParam("user") String user, @FormParam("password") String password) {
        return $login(user, password);
    }
    */
    
}