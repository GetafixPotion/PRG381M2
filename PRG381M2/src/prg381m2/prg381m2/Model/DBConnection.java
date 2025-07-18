/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prg381m2.prg381m2.Model;

import java.sql.Connection;//Connection is used to connect the app to the database
import java.sql.DriverManager;//helps Java talk to your specific database type --- >like PostgreSQL
import java.sql.SQLException;//handle database-related errors
import java.sql.Statement;//statement class to run SQL queries 
import java.sql.ResultSet;//Use it to go through your query results row by row
import java.util.ArrayList;//To store dynamic lists of objects
import java.sql.PreparedStatement;//Used to send parameterized SQL queries to the database 


/**
 *
 * @author hdesign
 */
public class DBConnection {
     
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";//JDBC driver vir embedded derby
    private static final String JDBC_URL = "jdbc:derby:counselorDB;create=true"; //this is the embedded url to connect and create db
    Connection con; //live db connection object

    public void connect() throws ClassNotFoundException {
        try {
            Class.forName(DRIVER); //loads the driver
            this.con = DriverManager.getConnection(JDBC_URL);//connects the DB
            if (this.con != null) {
                System.out.println("Connected to database");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void createTable() {
        try {
            String query = "CREATE TABLE Counselor (" +
                           "NameCounselor VARCHAR(20), " +
                           "Specialization VARCHAR(20), " +
                           "Availibility VARCHAR(20))";
            Statement stmt = this.con.createStatement();
            stmt.execute(query);
            System.out.println("Table created successfully.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<String[]> view() {
        ArrayList<String[]> dataList = new ArrayList<>();
        try {
            String query = "SELECT * FROM Counselor";
            ResultSet rs = this.con.createStatement().executeQuery(query);
 //this loops through ResultSet om data te collect
            while (rs.next()) {
                String name = rs.getString("NameCounselor");
                String spec = rs.getString("Specialization");
                String avail = rs.getString("Availibility");
                dataList.add(new String[]{name, spec, avail});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
         //returns a list of string arrays for each counslor row
        return dataList;
    }

    public void add(String name, String specialization, String availability) {
        try {
            String query = "INSERT INTO Counselor (NameCounselor, Specialization, Availibility) VALUES (?, ?, ?)";
            PreparedStatement ps = this.con.prepareStatement(query);//this is to avoid SQL injection/security vulnerability (a PreparedStatement is a safe way to write SQL queries in Java that separates the SQL logic from the user input.)
            ps.setString(1, name);
            ps.setString(2, specialization);
            ps.setString(3, availability);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void delete(String name) {
        try {
            String query = "DELETE FROM Counselor WHERE NameCounselor = ?";
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setString(1, name);//insert the value stored in the variable name into the first placeholder of SQL q
            ps.executeUpdate(); //executes the SQL statem,ents
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(String name, String specialization, String availability) {
        try {
            String query = "UPDATE Counselor SET Specialization = ?, Availibility = ? WHERE NameCounselor = ?";
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setString(1, specialization);
            ps.setString(2, availability);
            ps.setString(3, name);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();//exception object: prints a detailed error report
        }
    }
}
