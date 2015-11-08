/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import javax.ejb.Stateless;
import javax.ws.rs.*;
/**
 *
 * @author david
 */

@Stateless
@Path("/devices")
public class devices {
    
    
    @GET
    public ArrayList getdevicesbyuser(@QueryParam("user") String user, @QueryParam("token") String token) {
        return $getdevicesbyuser(user, token);
    }

    ArrayList $getdevicesbyuser(String user, String token) {
        ArrayList<String> returnable =  new ArrayList<String>();
        //Conect to DB
        return returnable;
    }
    
    @POST
    public Integer getdevicestate(@QueryParam("user") String user, @QueryParam("token") String token, @QueryParam("deviceID") Integer deviceID) {
        return $getdevicestate(user, token, deviceID);
    }
    Integer $getdevicestate(String user, String token, Integer deviceID){
        Integer state = null;
        //Connect to DB
        return state;
    }
    
    
    @PUT
    public Integer updatedevicestate(@QueryParam("user") String user,  @QueryParam("token") String token, @QueryParam("deviceID") Integer deviceID, @QueryParam("state") Integer state) {
        return $updatedevicestate(user, token, deviceID, state);
    }

    Integer $updatedevicestate(String user, String token, Integer deviceID, Integer state) {
        
        //Conect to DB
        return state;
    }
    
}

/*CREATE TABLE users (
    id int NOT NULL PRIMARY KEY,
    username varchar(50) UNIQUE NOT NULL,
    password varchar(50) NOT NULL,
    token varchar(50),
    token_since timestamp,
    token_until timestamp,
    
)

CREATE TABLE devices(
    id int NOT NULL,
    userid int NOT NULL,
    description tinytext or text,
    activated bool,
    state int
)
*/