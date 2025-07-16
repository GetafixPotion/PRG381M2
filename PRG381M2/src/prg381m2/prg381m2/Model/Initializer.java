/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prg381m2.prg381m2.Model;

import java.sql.SQLException;
import java.sql.Connection;

public class Initializer {
    
     private Connection con;

    // Constructor that receives a Connection
    public Initializer(Connection con) {
        this.con = con;
    }
    
    public void createTable(){
    try{
         String query = "CREATE TABLE IF NOT EXISTS Appointments ("
                + "StudentID INT PRIMARY KEY, "
                + "StudentName VARCHAR(25), "
                + "CounselorID INT, "
                + "`Date` DATE, "
                + "`Time` TIME, "
                + "Status VARCHAR(50), "
                + "FOREIGN KEY (CounselorID) REFERENCES Counselors(id))";
         
         this.con.createStatement().execute(query);
    } catch (SQLException ex) {
            ex.printStackTrace();
    }
    
    }
    
}
