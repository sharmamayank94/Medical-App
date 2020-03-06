package gui;

import javax.swing.*;
import javax.swing.table.TableColumn;

public class Bill {
    private JPanel toppanel;
    private JTable billtable;
    private JScrollPane billsp;
    private JTextPane ADDRESSNEWGIRLSHOSTELTextPane;
    private JTextField textField1;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField3;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;
    private JTextPane KYAKRNAHAIYHATextPane;

    public static JTable createTable()
    {

        String[] columnNames = {"S.No.", "Name","Quantity","Description","Dosage","MRP","Discount","Rate","CGST","SGST","Amount"};
        Object[][] data = {};
        JTable table = new JTable(data, columnNames);
        table.setFillsViewportHeight(true);

        return table;
    }

    public static void main(String[] args)
    {

        JFrame frame = new JFrame("BILL GENERATION");
        frame.setContentPane(new Bill().toppanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
//        JFrame frame = new JFrame("JTable Test");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        System.out.println("I m here");
        billtable = createTable();
        billsp = new JScrollPane(billtable);
        System.out.println(billsp);

//        frame.pack();
//        frame.setVisible(true);
    }
}
