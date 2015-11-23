/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package pti;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
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
            conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\david\\Desktop\\pti.sqlite");
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
            conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\david\\Desktop\\pti.sqlite");
            stmt = conn.createStatement();
            while(rs.next()){
                rs = stmt.executeQuery("update from users u, devices d SET state = \""+ newstate +"\" where u.username = "+user+" and u.password = "+password+" and u.id = d.userid and d.id = "+deviceID+";"); // Verificar la validesa del token
                
                //Retrieve by column name
                state = rs.getInt("state");
                                
                String encodedQuery = URLEncoder.encode("", "UTF-8");
                String postData = "state="+newstate+encodedQuery;
                
                // Connect to the web
                URL url = new URL("http://hers.no-ip.org/cgi-bin/file_test.py");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoOutput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestProperty("Content-Length",  String.valueOf(postData.length()));
                
                // Write data
                OutputStream os = connection.getOutputStream();
                os.write(postData.getBytes());
                
                // Read response
                StringBuilder responseSB = new StringBuilder();
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                
                String line;
                while ( (line = br.readLine()) != null){
                    responseSB.append(line);
                }
                state = Integer.parseInt(responseSB.toString());
                
                // Close streams
                br.close();
                os.close();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(devicesstate.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(devicesstate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(devicesstate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(devicesstate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return state;
    }
}
