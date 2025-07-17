/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prg381m2.prg381m2.Model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionAppointment {
    
    //Embedded driver class
    private static final String Driver = "org.apache.derby.jdbc.EmbeddedDriver";
    
    //Used to connect to Apache Derby database
    private static final String JDBC_URL ="jdbc:derby:WellnessDB;create=true";
    
    //Connection object to connect to Apache Derby database
    Connection con;
    
    //constructor
    public DBConnectionAppointment(){}
    
    public void connect() throws ClassNotFoundException{

    try{
 
        //Dynamically load JDBC driver class at runtime
         Class.forName(Driver);
         
         //establish connection to database
        this.con = DriverManager.getConnection(JDBC_URL);
        
        // Check if the connection was successful
        if(this.con !=null){
            System.out.println("Connected to database");
        }
    
       }catch(SQLException ex){
       
           // Print other SQL exceptions for debugging
           ex.printStackTrace();
       }

}
    
  public Connection getConnection() {
    return this.con;
}  
}

