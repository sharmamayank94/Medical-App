package gui;

import dao.customerDao;
import pojo.Customer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerList {
    private JComboBox comboBox1;
    private JTextField textField1;
    private JTable CustomerTable;
    private JScrollPane CustomerSp;
    private JPanel CustomerPanel;
    private JButton backButton;
    private JButton homeButton;
    private JButton logoutButton;

    public CustomerList() {
        viewAllCustomers();
        ListSelectionModel model = CustomerTable.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!model.isSelectionEmpty()){
                    int selectedRow = model.getMinSelectionIndex();
                    JOptionPane.showMessageDialog(null,"row selected is : " + selectedRow);
                    String name = (String)CustomerTable.getValueAt(selectedRow,1);
                    String email = (String)CustomerTable.getValueAt(selectedRow,2);
                    CustomerBillDetails cbd = new CustomerBillDetails();
                    cbd.make(name,email);

                }
            }
        });
    }


    private void viewAllCustomers() {
        try {

            ArrayList<Customer> customerList = customerDao.getAllCustomerDetails();
            Object rows[] = new Object[6];
            DefaultTableModel dtm = (DefaultTableModel) CustomerTable.getModel();
            for(Customer c : customerList){
                rows[0] = c.getSno();
                rows[1] = c.getCname();
                rows[2] = c.getEmail();
                rows[3] = c.getPhone();
                rows[4] = c.getAddress();
                rows[5] = c.getNoOfVisits();
                dtm.addRow(rows);
            }
        }catch (SQLException sqle){
            JOptionPane.showMessageDialog(null,"Sql Exception " + sqle);
        }
    }

    public static JTable createTable()
    {

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("S_No");
        model.addColumn("Name");
        model.addColumn("Email");
        model.addColumn("Phone");
        model.addColumn("Address");
        model.addColumn("NoOfVisits");
        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        return table;
    }

    public static void main(String[] args)
    {

        JFrame frame = new JFrame("Customers List");
        frame.setContentPane(new CustomerList().CustomerPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    private void createUIComponents() {
         CustomerTable = createTable();
        CustomerSp = new JScrollPane(CustomerTable);
    }
}
