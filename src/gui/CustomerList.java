package gui;

import dao.customerDao;
import pojo.Customer;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;


public class CustomerList {
    private JComboBox sortcb;
    private JTextField searchByName;
    private JTable CustomerTable;
    private JScrollPane CustomerSp;
    private JPanel CustomerPanel;
    private JButton backButton;
    private JButton homeButton;
    private JButton logoutButton;
    private JComboBox cbname;
    Set<String> s;

    public CustomerList() {
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        CustomerTable.setRowHeight(30);
        CustomerTable.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        CustomerTable.getColumnModel().getColumn(5).setCellRenderer(cellRenderer);
        customerNameInComboBox();
        viewAllCustomers();
        ListSelectionModel model = CustomerTable.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if(!model.isSelectionEmpty()){
                    int selectedRow = model.getMinSelectionIndex();
                    String name = (String)CustomerTable.getValueAt(selectedRow,1);
                    String email = (String)CustomerTable.getValueAt(selectedRow,2);
                    CustomerBillDetails cbd = new CustomerBillDetails();
                    cbd.make(name,email);
                    CustomerTable.getSelectionModel().clearSelection();

                }
            }
        });


        cbname.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel dm = (DefaultTableModel)CustomerTable.getModel();
                int noOfrows = dm.getRowCount();
                for (int i = noOfrows - 1; i >= 0; i--) {
                    dm.removeRow(i);
                }
                if(cbname.getSelectedItem().toString().toLowerCase()=="show all"){
                    viewAllCustomers();
                }
                else {
                    viewCustomerByName(cbname.getSelectedItem().toString().toLowerCase());
                }
            }
        });

        sortcb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel dm = (DefaultTableModel)CustomerTable.getModel();
                int noOfrows = dm.getRowCount();
                for (int i = noOfrows - 1; i >= 0; i--) {
                    dm.removeRow(i);
                }
                String sortBy = sortcb.getSelectedItem().toString();
                if(sortBy == "Name"){
                    viewAllCustomersSortedByName();
                }
                else if(sortBy == "Address"){
                    viewAllCustomersSortedByAddress();
                }
                else if(sortBy == "No. Of Visits"){
                    viewAllCustomersSortedByNoOfVisits();
                }
            }
        });
        ((JTextComponent)cbname.getEditor().getEditorComponent()).addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) {

                }
                else {
                    System.out.println("inside cbname key listener");
                    String to_check =  ((JTextComponent)cbname.getEditor().getEditorComponent()).getText();
                    int to_check_len = to_check.length();
                    for (String data : s) {
                        String check_from_data = "";
                        for (int i = 0; i < to_check_len; i++) {
                            if (to_check_len <= data.length()) {
                                check_from_data = check_from_data + data.charAt(i);
                            }
                        }
                        System.out.println(to_check);
                        System.out.println(check_from_data);
                        if (check_from_data.equals(to_check)) {
                            ((JTextComponent) cbname.getEditor().getEditorComponent()).setText(data);
                            ((JTextComponent) cbname.getEditor().getEditorComponent()).setSelectionStart(to_check_len);
                            ((JTextComponent) cbname.getEditor().getEditorComponent()).setSelectionEnd(data.length());
                            break;
                        }
                    }
                }
            }
        });
    }

    private void viewAllCustomersSortedByNoOfVisits() {
        try {
            ArrayList<Customer> customer = customerDao.getCustomersSortedByNoOfVisits();
            Object rows[] = new Object[6];
            DefaultTableModel dtm = (DefaultTableModel)CustomerTable.getModel();
            int a = 1;
            for(Customer c : customer){
                rows[0] = a;
                rows[1] = c.getCname();
                rows[2] = c.getEmail();
                rows[3] = c.getPhone();
                rows[4] = c.getAddress();
                rows[5] = c.getNoOfVisits();
                dtm.addRow(rows);
                a++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"cannot show");
            return;
        }
    }

    private void viewAllCustomersSortedByAddress() {
        try {
            ArrayList<Customer> customer = customerDao.getCustomersSortedByAddress();
            Object rows[] = new Object[6];
            DefaultTableModel dtm = (DefaultTableModel)CustomerTable.getModel();
            int a = 1;
            for(Customer c : customer){
                rows[0] = a;
                rows[1] = c.getCname();
                rows[2] = c.getEmail();
                rows[3] = c.getPhone();
                rows[4] = c.getAddress();
                rows[5] = c.getNoOfVisits();
                dtm.addRow(rows);
                a++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"cannot show");
            return;
        }
    }

    private void viewAllCustomersSortedByName() {
        try {
            ArrayList<Customer> customer = customerDao.getCustomersSortedByName();
            Object rows[] = new Object[6];
            DefaultTableModel dtm = (DefaultTableModel)CustomerTable.getModel();
            int a = 1;
            for(Customer c : customer){
                rows[0] = a;
                rows[1] = c.getCname();
                rows[2] = c.getEmail();
                rows[3] = c.getPhone();
                rows[4] = c.getAddress();
                rows[5] = c.getNoOfVisits();
                dtm.addRow(rows);
                a++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"cannot show");
            return;
        }
    }

    private void customerNameInComboBox() {
        s = new TreeSet<>();
        try {
            ArrayList<Customer> c = customerDao.getCustomersName();
            for(Customer customer : c){
                cbname.addItem(customer.getCname());
                s.add(customer.getCname());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"SQL exception");
            return;
        }
    }


    private void viewCustomerByName(String text) {
        try {
            ArrayList<Customer> customer = customerDao.getCustomerByName(text);
            Object rows[] = new Object[6];
            DefaultTableModel dtm = (DefaultTableModel)CustomerTable.getModel();
            int a = 1;
            for(Customer c : customer){
                rows[0] = a;
                rows[1] = c.getCname();
                rows[2] = c.getEmail();
                rows[3] = c.getPhone();
                rows[4] = c.getAddress();
                rows[5] = c.getNoOfVisits();
                dtm.addRow(rows);
                a++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,"cannot show");
            return;
        }

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
            sqle.printStackTrace();
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
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    private void createUIComponents() {
         CustomerTable = createTable();
        CustomerSp = new JScrollPane(CustomerTable);
    }


    public void make(){


        JFrame frame = new JFrame("Customers List");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setContentPane(this.CustomerPanel);

        frame.setVisible(true);
    }



}
