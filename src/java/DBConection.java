
import java.sql.*;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

/**
 *
 * @author david
 */
public class DBConection {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/EMP";
    
    //  Database credentials
    static final String USER = "username";
    static final String PASS = "password";
    
    public ResultSet conectexecute(String sqlquery){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            //Register JDBC driver
            Class.forName("com.mysql.jdbc.Driver");
            
            //Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            
            //Execute a query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlquery);
                        
            //Clean-up environment
            rs.close();
            stmt.close();
            conn.close();
            
            return rs;
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Conecció a DB tancada");
        return rs;
    }//end main
}
