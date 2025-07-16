/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prg381m2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.PreparedStatement;


/**
 *
 * @author hdesign
 */
public class DBConnection {
     
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String JDBC_URL = "jdbc:derby:counselorDB;create=true";
    Connection con;

    public void connect() throws ClassNotFoundException {
        try {
            Class.forName(DRIVER);
            this.con = DriverManager.getConnection(JDBC_URL);
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
                           "CounselorName VARCHAR(20), " +
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

            while (rs.next()) {
                String name = rs.getString("CounselorName");
                String spec = rs.getString("Specialization");
                String avail = rs.getString("Availibility");
                dataList.add(new String[]{name, spec, avail});
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dataList;
    }

    public void add(String name, String specialization, String availability) {
        try {
            String query = "INSERT INTO Counselor (CounselorName, Specialization, Availibility) VALUES (?, ?, ?)";
            PreparedStatement ps = this.con.prepareStatement(query);
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
            String query = "DELETE FROM Counselor WHERE CounselorName = ?";
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setString(1, name);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(String name, String specialization, String availability) {
        try {
            String query = "UPDATE Counselor SET Specialization = ?, Availibility = ? WHERE CounselorName = ?";
            PreparedStatement ps = this.con.prepareStatement(query);
            ps.setString(1, specialization);
            ps.setString(2, availability);
            ps.setString(3, name);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
