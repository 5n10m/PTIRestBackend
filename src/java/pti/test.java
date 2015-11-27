/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package pti;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        String returnable="vacio";
        try {
            String res = "Hola " + user;
            
            String encodedQuery = URLEncoder.encode("", "UTF-8");
            String postData = "state="+user+ encodedQuery;
            
            // Connect to google.com
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
            returnable = responseSB.toString();
            
            // Close streams
            br.close();
            os.close();
            
            /*String url = "http://hers.no-ip.org/file_test.html";
            HttpClient httpClient = new HttpClient();
            PostMethod postMethod = new PostMethod(url);
            postMethod.addParameter("Email", email);
            postMethod.addParameter("fname", fname);
            try {
            httpClient.executeMethod(postMethod);
            } catch (HttpException e) {
            e.printStackTrace();
            } catch (IOException e) {
            e.printStackTrace();
            }
            */
            //return res;
        } catch (MalformedURLException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        return returnable;
    }
}