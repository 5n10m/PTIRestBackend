/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pti;

import javax.ws.rs.*;
import javax.ejb.Stateless;

/**
 *
 * @author david
 */

@Stateless
@Path("/devicesstate")
    

public class devicesstate {
    
    @GET
     public Integer getdevicestate(@QueryParam("user") String user, @QueryParam("token") String token, @QueryParam("deviceID") Integer deviceID) {
        return $getdevicestate(user, token, deviceID);
    }
    Integer $getdevicestate(String user, String token, Integer deviceID){
        Integer state = null;
        //Connect to DB
        /* DBConection db = null;
        ResultSet rs = db.conectexecute("Select state from users u, devices d where u.username = "+user+" and u.token = "+token+" and u.id = d.userid and d.id = "+deviceID+";"); // Verificar la validesa del token
        try{
        while(rs.next()){
                //Retrieve by column name
                state = rs.getInt("state");
            }
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        state = 2;
        return state;
    }
    
    @POST
    public Integer setdevicestate(@FormParam("user") String user, @FormParam("token") String token, @FormParam("deviceID") Integer deviceID, @FormParam("newstate") Integer newstate) {
        return $setdevicestate(user, token, deviceID, newstate);
    }
    Integer $setdevicestate(String user, String token, Integer deviceID, Integer newstate){
        
        
        return newstate;
    }
}
