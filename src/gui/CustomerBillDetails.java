package gui;

import com.mysql.cj.log.Log;
import dao.billDao;
import pojo.billPojo;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBillDetails {
    private JButton homeButton;
    private JButton backButton;
    private JButton logoutButton;
    private JTable CustomerBillsTable;
    private JScrollPane billSP;
    private JPanel CustomerBills;
    private String name;
    private String email;
    private static JFrame frame;

    public CustomerBillDetails() {
        CustomerBillsTable.setRowHeight(30);
        System.out.println("inside constructor of customer bill details");
        ListSelectionModel model = CustomerBillsTable.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!model.isSelectionEmpty()){
                    int selectedRow = model.getMinSelectionIndex();
                    JOptionPane.showMessageDialog(null,"row selected is : " + selectedRow);
                    String billNo = (String)CustomerBillsTable.getValueAt(selectedRow,1);
                    BillContent bc = new BillContent();
                    bc.make(billNo, name, email);
                    frame.dispose();
                    CustomerBillsTable.getSelectionModel().clearSelection();

                }
            }
        });

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminPanel ap = new AdminPanel();
                ap.make();
                frame.dispose();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerList cl = new CustomerList();
                cl.make();
                frame.dispose();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPanel lp = new LoginPanel();
                lp.make();
                frame.dispose();
            }
        });
    }

    private void viewBills() {
        try {
            ArrayList<billPojo> bills = billDao.getBills(name,email);
            Object rows[] = new Object[4];
            DefaultTableModel dtm = (DefaultTableModel) CustomerBillsTable.getModel();
            int a = 1;
            for(billPojo b : bills){
                rows[0] = a;
                rows[1] = b.getBill_No();
                rows[2] = b.getDoctor();
                rows[3] = b.getSellDate();
                dtm.addRow(rows);
                a++;
            }
        } catch (SQLException e) {
           JOptionPane.showMessageDialog(null,"SQL Exception " + e);
           return;
        }

    }

    private static JTable createTable(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("S_No");
        model.addColumn("Bill_No");
        model.addColumn("Doctor");
        model.addColumn("Date Of Selling");
        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        return table;
    }
    public static void main(String[] args) {
        frame = new JFrame("Customer Bills Panel");
        frame.setContentPane(new CustomerBillDetails().CustomerBills);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        CustomerBillsTable = createTable();
        billSP = new JScrollPane(CustomerBillsTable);
    }

    public void make(String name,String email){
        this.name = name;
        this.email = email;
        viewBills();
        System.out.println("inside make of customer bill details");
        frame = new JFrame("Customer Bills");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setContentPane(this.CustomerBills);
        frame.setVisible(true);
    }
}
