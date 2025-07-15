/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prg381m2.prg381m2.Model;

/**
 *
 * @author demic
 */
//Imports so that LocalTime and LocalDate variable types work
import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {

    //variables
    private int id;
    private String StudentName;
    private String Counselorname;
    private LocalDate Appointmentdate;
    private LocalTime AppointmentTime;
    private String Status;
    
    //Constructor
    public Appointment(int id, String StudentName, String Counselorname, LocalDate Appointmentdate, LocalTime AppointmentTime, String Status) {
        this.id = id;
        this.StudentName = StudentName;
        this.Counselorname = Counselorname;
        this.Appointmentdate = Appointmentdate;
        this.AppointmentTime = AppointmentTime;
        this.Status = Status;
    }
    
    //Default constructor for objrct instance creation
    public Appointment(){}
    
    //Encapsulation-get and set methods
        public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String StudentName) {
        this.StudentName = StudentName;
    }

    public String getCounselorname() {
        return Counselorname;
    }

    public void setCounselorname(String Counselorname) {
        this.Counselorname = Counselorname;
    }

    public LocalDate getAppointmentdate() {
        return Appointmentdate;
    }

    public void setAppointmentdate(LocalDate Appointmentdate) {
        this.Appointmentdate = Appointmentdate;
    }

    public LocalTime getAppointmentTime() {
        return AppointmentTime;
    }

    public void setAppointmentTime(LocalTime AppointmentTime) {
        this.AppointmentTime = AppointmentTime;
    }

    public String getStatus() {
        return Status;
    }
    
    public void setStatus(String Status) {
        this.Status = Status;
    }
    
    
}
