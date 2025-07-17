/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prg381m2;

import prg381m2.prg381m2.Model.DBConnectionAppointment;
import prg381m2.prg381m2.Model.Initializer;
import prg381m2.prg381m2.View.AppointmentForm;
import javax.swing.SwingUtilities;

public class PRG381M2 {

    public static void main(String[] args) {
        try {
            DBConnectionAppointment db = new DBConnectionAppointment();
            db.connect();

            Initializer initializer = new Initializer(db.getConnection());
            initializer.createTable();

        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        // Launch the form in the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new AppointmentForm().setVisible(true);
        });
    }
}

        
            
        
        

        // TODO code application logic here
        //Testing if this will commit and push to repo
   
    

