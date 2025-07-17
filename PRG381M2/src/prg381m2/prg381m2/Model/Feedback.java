package prg381m2.prg381m2.Model;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Heiner
 */
public class Feedback{
    
    private DefaultTableModel tableModel;
    private JTable table;
    private int rating;
    private String comments;

    public Feedback(DefaultTableModel tableModel, JTable table) {
        this.tableModel = tableModel;
        this.table = table;
    }

    public void setFeedbackData(int rating, String comments) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        this.rating = rating;
        this.comments = comments != null ? comments.trim() : "";
    }

    public void addData() {
        Object[] rowData = {rating, comments};
        tableModel.addRow(rowData);
    }

    public void removeData(int row) {
        if (row >= 0 && row < tableModel.getRowCount()) {
            tableModel.removeRow(row);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a valid row to delete.");
        }
    }

    public void updateData(int row) {
        if (row >= 0 && row < tableModel.getRowCount()) {
            tableModel.setValueAt(rating, row, 0);
            tableModel.setValueAt(comments, row, 1);
        } else {
            JOptionPane.showMessageDialog(null, "Please select a valid row to update.");
        }
    }

    public Object[] getFeedbackData(int row) {
        if (row >= 0 && row < tableModel.getRowCount()) {
            return new Object[] {
                tableModel.getValueAt(row, 0),
                tableModel.getValueAt(row, 1)
            };
        }
        return null;
    }
    
}
