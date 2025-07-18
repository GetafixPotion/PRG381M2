package prg381m2.prg381m2.Model;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable; // Import JTable for table component
import javax.swing.JOptionPane; // Import JOptionPane for displaying error messages

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


public class Feedback{
    
    private DefaultTableModel tableModel; // The table model to manage table data
    private JTable table; // The JTable component to display the feedback data
    private int rating; // Stores the feedback rating (1-5)
    private String comments; // Stores the feedback comments

    /**
     * Constructor to initialize the Feedback object with a table model and JTable.
     */
    public Feedback(DefaultTableModel tableModel, JTable table) {
        this.tableModel = tableModel;
        this.table = table;
    }

    /**
     * Sets the feedback data (rating and comments) after validating the rating.
     * Trims the comments to remove leading/trailing whitespace.
     */
    public void setFeedbackData(int rating, String comments) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        this.rating = rating;
        this.comments = comments != null ? comments.trim() : "";
    }

    /**
     * Adds a new row to the table model containing the current rating and comments.
     */
    public void addData() {
        Object[] rowData = {rating, comments}; // Create an array with rating and comments
        tableModel.addRow(rowData); // Add the row to the table model
    }

    /**
     * Removes a row from the table model at the specified index.
     * Displays an error message if the row index is invalid.
     */
    public void removeData(int row) {
        if (row >= 0 && row < tableModel.getRowCount()) {
            tableModel.removeRow(row); // Remove the row if the index is valid
        } else {
            JOptionPane.showMessageDialog(null, "Please select a valid row to delete.");
        }
    }

    /**
     * Updates the rating and comments of an existing row in the table model.
     * Displays an error message if the row index is invalid.
     */
    public void updateData(int row) {
        if (row >= 0 && row < tableModel.getRowCount()) {
            tableModel.setValueAt(rating, row, 0); // Update rating in column 0
            tableModel.setValueAt(comments, row, 1); // Update comments in column 1
        } else {
            JOptionPane.showMessageDialog(null, "Please select a valid row to update.");
        }
    }

    /**
     * Retrieves the feedback data (rating and comments) for a specific row.
     * Returns null if the row index is invalid.
     */
    public Object[] getFeedbackData(int row) {
        if (row >= 0 && row < tableModel.getRowCount()) {
            return new Object[] {
                tableModel.getValueAt(row, 0), // Get rating from column 0
                tableModel.getValueAt(row, 1)  // Get comments from column 1
            };
        }
        return null; // Return null for invalid row index
    }
    
}
