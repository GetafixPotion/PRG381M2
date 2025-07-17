/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prg381m2.prg381m2.Model;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.Connection;

//This class is responsible for initializing the database structure
public class Initializer {
    
    //Connection object to communicate with the database
     public Connection con;

    // Constructor that receives a Connection
    public Initializer(Connection con) {
        this.con = con;
    }
    //Creates appointment table.
    public void createTable() {
    try {
        //Creates a statement object to execyte the SQL query
        Statement stmt = con.createStatement();
        
        String sql = "CREATE TABLE Appointments (" +
                     "StudentID INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY, " +
                     "StudentName VARCHAR(25), " +
                     "CounselorName VARCHAR(25), " +
                     "Date DATE, " +
                     "Time TIME, " +
                     "Status VARCHAR(50))";
        
        stmt.execute(sql);
        System.out.println("Appointments table created.");
        
    } catch (SQLException e) {
        
        //checks if the table already exists if it does this error will appear X0Y32
        if (e.getSQLState().equals("X0Y32")) {
            System.out.println("Table already exists.");
            
        } else {
            // Print other SQL exceptions for debugging
            e.printStackTrace();
        }
    }
}

    
}
