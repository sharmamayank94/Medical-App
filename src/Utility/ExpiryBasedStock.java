package Utility;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ExpiryBasedStock {
    private JPanel topPanel;
    private JTable table;
    private JButton LOGOUTButton;
    private JButton BACKButton;
    private JButton HOMEButton;
    private JScrollPane JScrollPane1;

    private void createUIComponents() {
        // TODO: place custom component creation code here
        table= CreateTable();
        JScrollPane1 = new JScrollPane(table);

    }

    public static JTable CreateTable() {
        DefaultTableModel model= new DefaultTableModel();
        model.addColumn(" S_No");
        model.addColumn("Name");
        model.addColumn( "Expiry");
        model.addColumn("Batch_NO");
        model.addColumn("Company");
        model.addColumn( "Total_Quantity");
        model.addColumn("Category");
        JTable t = new JTable(model);
        t.setFillsViewportHeight(true);
        return t;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sorted By Expiry");
        frame.setContentPane(new ExpiryBasedStock().topPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
