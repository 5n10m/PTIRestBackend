/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pti;

import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 *
 * @author riogemm.panopio
 */
@Stateless
@Path("/test")
public class test {
    
    @GET
    public String hola (@QueryParam("user") String user) {
        String res = "Hola " + user;
        return res;
    }
}
