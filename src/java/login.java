/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.StringTokenizer;
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
        //Conect to DB
        return token;
    }
    
    @POST
    public StringTokenizer loginpost(@QueryParam("user") String user, @QueryParam("password") String password) {
        return $login(user, password);
    }

    StringTokenizer $loginpost(String user, String password) {
        StringTokenizer token = new StringTokenizer("user"+"password");
        //Conect to DB
        return token;
    }
    
}