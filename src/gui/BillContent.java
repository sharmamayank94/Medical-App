package gui;

import dao.billDao;
import pojo.billPojo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class BillContent {
    private JTable billContentTable;
    private JScrollPane billContentSp;
    private JPanel billContentPanel;
    private JButton homeButton;
    private JButton logoutButton;
    private JButton backButton;
    private String billNo;
    private String name;
    private String email;
    private static  JFrame frame;

    public BillContent() {
        billContentTable.setRowHeight(25);
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminPanel ap = new AdminPanel();
                ap.make();
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
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerBillDetails cbd = new CustomerBillDetails();
                cbd.make(name,email);
                frame.dispose();
            }
        });
    }

    public static JTable createTable(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("S_No");
        model.addColumn("Name");
        model.addColumn("Category");
        model.addColumn("Batch no.");
        model.addColumn("Expiry date");

        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        return table;
    }

    public static void main(String[] args) {
        frame = new JFrame("Bill content");
        frame.setContentPane(new BillContent().billContentPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        billContentTable = createTable();
        billContentSp = new JScrollPane(billContentTable);
    }

    public void make(String billNo, String name, String email) {
        this.billNo = billNo;
        this.name = name;
        this.email = email;
        viewBillDetails(billNo);
        frame = new JFrame("Bill details");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setContentPane(this.billContentPanel);
        frame.setVisible(true);
    }

    private void viewBillDetails(String billNo)  {
        try {
            ArrayList<billPojo> b = billDao.getBillDetails(billNo);
            Object rows[] = new Object[5];
            DefaultTableModel dtm = (DefaultTableModel)billContentTable.getModel();
            int a =1;
            for(billPojo bp : b){
                rows[0] = a;
                rows[1] = bp.getMedicine();
                rows[2] = bp.getCategory();
                rows[3] = bp.getBatchno();
                rows[4] = bp.getExpiry();
                a++;
                dtm.addRow(rows);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"SQL Exception : "+ e);
            return;
        }
    }
}
