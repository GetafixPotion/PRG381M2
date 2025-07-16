/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prg381m2.prg381m2.Model;

/**
 *
 * @author demic
 */
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

public class AppointmentDA {
    public Connection con;
    
     public AppointmentDA(Connection con) {
        this.con = con;
    }
    public boolean add(Appointment appointment){
    
        try{
        //Build query string with concatenation
        String query = "INSERT INTO Appointments VALUES (" 
                + appointment.getId() + ", '" 
                + appointment.getStudentName() + "', " 
                + getCounselorIdByName(appointment.getCounselorname()) + ", '" 
                + appointment.getAppointmentdate().toString() + "', '" 
                + appointment.getAppointmentTime().toString() + "', '" 
                + appointment.getStatus() + "')";
            
            this.con.createStatement().execute(query);
            return true;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    
    }
    
    public boolean updateAppointment(Appointment appointment){
        
        try{
            String query = "UPDATE Appointments SET "
                + "StudentName='" + appointment.getStudentName() + "', "
                + "CounselorID=" + getCounselorIdByName(appointment.getCounselorname()) + ", "
                + "Date='" + appointment.getAppointmentdate().toString() + "', "
                + "Time='" + appointment.getAppointmentTime().toString() + "', "
                + "Status='" + appointment.getStatus() + "' "
                + "WHERE StudentID=" + appointment.getId();

            this.con.createStatement().execute(query);
    
            return true;
        }catch(SQLException ex) {
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
            ex.printStackTrace();
         
            return false;
        }
    }

    public Appointment getAppointmentById(int id) {
        Appointment appt = null;
        try {
            String query = "SELECT a.StudentID, a.StudentName, c.Name AS CounselorName, a.Date, a.Time, a.Status "
                    + "FROM Appointments a JOIN Counselors c ON a.CounselorID = c.id WHERE a.StudentID = " + id;

            ResultSet rs = this.con.createStatement().executeQuery(query);
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
            ex.printStackTrace();
            
        }
        return appt;
    }

    public List<Appointment> getAllAppointments() 
    {
        List<Appointment> appointments = new ArrayList<>();
        try {
            String query = "SELECT a.StudentID, a.StudentName, c.Name AS CounselorName, a.Date, a.Time, a.Status "
                    + "FROM Appointments a JOIN Counselors c ON a.CounselorID = c.id ORDER BY a.Date, a.Time";

            ResultSet table = this.con.createStatement().executeQuery(query);
            
           while (table.next()) {
                int id = table.getInt("StudentID");
                String studentName = table.getString("StudentName");
                String counselorName = table.getString("CounselorName");
                LocalDate date = table.getDate("Date").toLocalDate();
                LocalTime time = table.getTime("Time").toLocalTime();
                String status = table.getString("Status");

                Appointment appointment = new Appointment(id, studentName, counselorName, date, time, status);
                appointments.add(appointment);
            
            }} catch (SQLException ex) {
            ex.printStackTrace();
            
        }
        return appointments;
    }

 

    private int getCounselorIdByName(String counselorName) throws SQLException {
        String query = "SELECT id FROM Counselors WHERE Name = '" + counselorName + "'";
        ResultSet rs = this.con.createStatement().executeQuery(query);
        if (rs.next()) {
            return rs.getInt("id");
        } else {
            throw new SQLException("Counselor not found: " + counselorName);
        }
    }
}
