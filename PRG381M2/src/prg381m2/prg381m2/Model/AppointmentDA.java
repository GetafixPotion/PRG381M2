/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prg381m2.prg381m2.Model;


import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

public class AppointmentDA {
    
    //Connection object
    public Connection con;
    
    // Constructor that receives the database connection
     public AppointmentDA(Connection con) {
        this.con = con;
    }
     
    public boolean add(Appointment appointment){
    
        String query = "INSERT INTO Appointments (StudentName, CounselorName, Date, Time, Status) VALUES (?, ?, ?, ?, ?)";

    try (PreparedStatement pstmt = con.prepareStatement(query)) {
        pstmt.setString(1, appointment.getStudentName());
        pstmt.setString(2, appointment.getCounselorname());
        pstmt.setDate(3, java.sql.Date.valueOf(appointment.getAppointmentdate()));
        pstmt.setTime(4, java.sql.Time.valueOf(appointment.getAppointmentTime()));
        pstmt.setString(5, appointment.getStatus());

        pstmt.executeUpdate();
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
    
    }
    
    public boolean updateAppointment(Appointment appointment){
        
        String sql = "UPDATE Appointments SET StudentName=?, CounselorName=?, Date=?, Time=?, Status=? WHERE StudentID=?";
    try (PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, appointment.getStudentName());
        ps.setString(2, appointment.getCounselorname());
        ps.setDate(3, java.sql.Date.valueOf(appointment.getAppointmentdate()));
        ps.setTime(4, java.sql.Time.valueOf(appointment.getAppointmentTime()));
        ps.setString(5, appointment.getStatus());
        ps.setInt(6, appointment.getId());
        int rowsUpdated = ps.executeUpdate();
        
        //return true if atleast one row was updated successfully
        return rowsUpdated > 0;
    } catch (SQLException ex) {
        ex.printStackTrace();
        return false;
    }
    }

    public boolean deleteAppointment(int id) {
        try {
            String query = "DELETE FROM Appointments WHERE StudentID=" + id;
            
            this.con.createStatement().execute(query);
           
            return true;
            
        } catch (SQLException ex) {
            
            //SQL errors,and returns a false if theres errors
            ex.printStackTrace();
         
            return false;
        }
    }

    public Appointment getAppointmentById(int id) {
        
        //appointment variable created and set to 0 
        Appointment appt = null;
        try {
            String query = "SELECT * FROM Appointments WHERE StudentID= " + id;

            //execute the query and store result
            ResultSet rs = this.con.createStatement().executeQuery(query);
            
            // If a matching appointment is found, extract data and build Appointment object
            if (rs.next()) {
                int studentId = rs.getInt("StudentID");
                String studentName = rs.getString("StudentName");
                String counselorName = rs.getString("CounselorName");
                LocalDate date = rs.getDate("Date").toLocalDate();
                LocalTime time = rs.getTime("Time").toLocalTime();
                String status = rs.getString("Status");

                appt = new Appointment(studentId, studentName, counselorName, date, time, status);
            }
        } catch (SQLException ex) {
            
            // Print any exceptions that occur while querying
            ex.printStackTrace();
            
        }
        
        //returning appointment object
        return appt;
    }

    public List<Appointment> getAllAppointments() 
    {
        List<Appointment> appointments = new ArrayList<>();
        try {
            String query = "SELECT * FROM Appointments ORDER BY Date, Time";

            //result set holds all rows returned by the query
            ResultSet table = this.con.createStatement().executeQuery(query);
           
            //looping through the resultsets
           while (table.next()) {
                int id = table.getInt("StudentID");
                String studentName = table.getString("StudentName");
                String counselorName = table.getString("CounselorName");
                LocalDate date = table.getDate("Date").toLocalDate();
                LocalTime time = table.getTime("Time").toLocalTime();
                String status = table.getString("Status");

                //Create Appointment object and add it to the list
                Appointment appointment = new Appointment(id, studentName, counselorName, date, time, status);
                appointments.add(appointment);
            
            }} catch (SQLException ex) {
                
                //handles errors during fetching
                ex.printStackTrace();
            
             }
                //returns the list of appointments
                return appointments;
    }

 

    
}
