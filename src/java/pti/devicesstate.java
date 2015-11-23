/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pti;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.*;
import javax.ejb.Stateless;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author david
 */

@Stateless
@Path("/devicesstate")
    

public class devicesstate {
    
    @GET
     public Integer getdevicestate(@QueryParam("user") String user, @QueryParam("password") String password, @QueryParam("deviceID") Integer deviceID) throws SQLException {
        return $getdevicestate(user, password, deviceID);
    }
    Integer $getdevicestate(String user, String password, Integer deviceID) throws SQLException{
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Integer state = -1;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\DEFIB\\Desktop\\pti.sqlite");
            stmt = conn.createStatement();
            while(rs.next()){
                rs = stmt.executeQuery("Select state from users u, devices d where u.username = "+user+" and u.password = "+password+" and u.id = d.userid and d.id = "+deviceID+";"); // Verificar la validesa del token
                
                //Retrieve by column name
                state = rs.getInt("state");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(devicesstate.class.getName()).log(Level.SEVERE, null, ex);
        
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        state = 2;
        return state;
    }
    
    @POST
    public Integer setdevicestate(@FormParam("user") String user, @FormParam("password") String password, @FormParam("deviceID") Integer deviceID, @FormParam("newstate") Integer newstate) {
        return $setdevicestate(user, password, deviceID, newstate);
    }
    Integer $setdevicestate(String user, String password, Integer deviceID, Integer newstate){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Integer state = -1;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\DEFIB\\Desktop\\pti.sqlite");
            stmt = conn.createStatement();
            while(rs.next()){
                rs = stmt.executeQuery("update from users u, devices d SET state = \""+ newstate +"\" where u.username = "+user+" and u.password = "+password+" and u.id = d.userid and d.id = "+deviceID+";"); // Verificar la validesa del token
                
                //Retrieve by column name
                state = rs.getInt("state");
                
                /*PostMethod post = new PostMethod("http://jakarata.apache.org/");
                NameValuePair[] data = {
                new NameValuePair("user", "joe"),
                new NameValuePair("password", "bloggs")
                };
                post.setRequestBody(data);
                // execute method and handle any error responses.
                ...
                InputStream in = post.getResponseBodyAsStream();*/
                
                String url = "http://hers.no-ip.org/file_test.html";
		URL obj;
                try {
                    obj = new URL(url);
                    HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
                    con.setRequestMethod("POST");
                    con.setDoOutput(true);
                    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                    
                    String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
                    wr.writeBytes(urlParameters);
                    wr.flush();
                    wr.close();
                    con.setRequestProperty("state", Integer.toString(rs.getInt(newstate)));
                
                } catch (MalformedURLException ex) {
                    Logger.getLogger(devicesstate.class.getName()).log(Level.SEVERE, null, ex);
                }catch (IOException ex) {
                        Logger.getLogger(devicesstate.class.getName()).log(Level.SEVERE, null, ex);
                    } 
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(devicesstate.class.getName()).log(Level.SEVERE, null, ex);
        
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
        state = 2;
        return state;
    }
}
