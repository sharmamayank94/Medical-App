package gui;

import dao.billDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
public class Bill {
    private JPanel toppanel;
    private JTable billtable;
    private JScrollPane billsp;
    private JTextPane ADDRESSNEWGIRLSHOSTELTextPane;
    private JTextField billNo;
    private JTextField txtDoctor;
    private JTextField txtEmail;
    private JTextField txtAddress;
    private JTextField textField7;
    private JTextField txtCustomerName;
    private JTextField txtdate;
    private JTextField textField3;
    private JTextField textField10;
    private JTextField textField11;
    private JTextField textField12;
    private JTextPane KYAKRNAHAIYHATextPane;
    private JTextField txtvalue;
    private JButton logOutButton;
    private JButton printBillButton;
    private JButton homeButton;
    private JButton backButton;
    private int a=0;
    public Bill() {
        txtvalue.setEditable(false);
        billNo.setEditable(false);
        txtdate.setEditable(false);
        showBillNo();
        showdate();
        billtable.getColumn("S_No").setMaxWidth(50);
        billtable.getColumn("Name").setMinWidth(300);
        billtable.getColumn("Description").setMinWidth(300);
        billtable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = billtable.getSelectedRow();

                if(e.getClickCount()==1 && row > -1) {
                    if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                        String nameOfMedicine = billtable.getModel().getValueAt(row,1).toString();
                        int res;
                        if(nameOfMedicine == "") {
                            res = JOptionPane.showConfirmDialog(null, "do you want to delete the selected row");
                        }
                        else {
                            res = JOptionPane.showConfirmDialog(null, "do you want to delete the row with medicine name : " + nameOfMedicine);

                        }
                        System.out.println("value of res is " + res);
                        if(res==0){

                            DefaultTableModel model = (DefaultTableModel)billtable.getModel();
                            model.removeRow(row);
                            System.out.println("value of a : "+a);
                            System.out.println("value of row : " + row);
                            a=a-1;
                            for(int i=row; i<a; i++){
                                model.setValueAt(i+1,i,0);
                            }
                            txtvalue.setText(String.valueOf(a));

                        }
                        billtable.getSelectionModel().clearSelection();
                        
                    }

                }else if(e.getClickCount() == 2) {
                    a++;
                    insertRow(a);

                    txtvalue.setText(String.valueOf(a));

                }
            }
        });

    }


    private void insertRow(int a) {
        DefaultTableModel model = (DefaultTableModel)billtable.getModel();
        model.addRow(new Object[]{a,"","","","","","","","","",""});
    }

    private void showdate() {

        try {
            txtdate.setText(billDao.getDATE());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"SQL exception " + e);
            return;
        }
    }

    private void showBillNo() {

        try {
            billNo.setText(billDao.getBillNo());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"SQL exception");
            return;
        }
    }

    public static JTable createTable()
    {

//        String[] columnNames = {"S.No.", "Name","Quantity","Description","Dosage","MRP","Discount","Rate","CGST","SGST","Amount"};
//        Object[][] data = {};
//        JTable table = new JTable(data, columnNames);
//        table.setFillsViewportHeight(true);
//
//        return table;
        DefaultTableModel model = new DefaultTableModel(){

            public boolean isCellEditable(int row,int column)  {
                if (column == 0)  return false;
                return true;
            }
        };
        model.addColumn("S_No");
        model.addColumn("Name");
        model.addColumn("Category");
        model.addColumn("Quantity");
        model.addColumn("Description");
        model.addColumn("Dosage");
        model.addColumn("MRP");
        model.addColumn("Discount");
        model.addColumn("Rate");
        model.addColumn("CGST");
        model.addColumn("SGST");
        model.addColumn("Amount");
        JTable table = new JTable(model);
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

        billtable = createTable();
        billsp = new JScrollPane(billtable);


//        frame.pack();
//        frame.setVisible(true);
    }
}
