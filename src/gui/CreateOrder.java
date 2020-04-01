package gui;

import dao.OrdersDao;
import pojo.OrderPojo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class CreateOrder {
    private JTable table1;
    private JLabel CreateOrderLabel;
    private JPanel HeadingPanel;
    private JScrollPane Scroller;
    public JPanel mainpanel;
    private JButton createOrderButton;
    private JButton addButton;
    private JTextField nameTextField;
    private JTextField quantityTextField;
    private JTextField companyTextField;
    private JTextField vendorTextField;
    private JButton createMemoButton;
    private JLabel Quantity;

    private DefaultTableModel dtm;
    private boolean open;
    private String orderId;
    private ArrayList<OrderPojo> orderlist;
    private Calendar today = Calendar.getInstance();
    public CreateOrder() {

        //A function to get new order id
        try {
            orderId = OrdersDao.getNewOrderId();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        orderlist = new ArrayList<>();

        createOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    orderlist = new ArrayList<>();
                    for(int i =0; i<table1.getRowCount(); i++){
                        String[]data = new String[4];
                        for(int j=0; j<table1.getColumnCount()-1; j++){
                            data[j] = table1.getValueAt(i, j).toString();
                        }
                        System.out.println(orderId);
                        OrderPojo order = new OrderPojo(orderId, data[0], Integer.parseInt(data[1]), data[2], data[3], "Pending", today, null);
                        orderlist.add(order);
                    }

                    //adding order to database
                    boolean added = OrdersDao.addOrder(orderlist);
                    if(added){
                        JOptionPane.showMessageDialog(null, "Order Successfully Added");
                        int rowCount = dtm.getRowCount();
                        System.out.println(rowCount);
                        for(int i=0; i<rowCount; i++){

                            dtm.removeRow(0);
                        }
                        nameTextField.setText("");
                        quantityTextField.setText("");
                        companyTextField.setText("");
                        vendorTextField.setText("");
                    }


                }catch(SQLException sqle){
                    sqle.printStackTrace();
                }
                //getting data from table

            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                if(nameTextField.getText().length()>0 && quantityTextField.getText().length()>0 && companyTextField.getText().length()>0 && vendorTextField.getText().length()>0)
                    dtm.addRow(new Object[] {nameTextField.getText(), quantityTextField.getText(), companyTextField.getText(), vendorTextField.getText(), });
            }
        });
        createMemoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    orderlist = new ArrayList<>();
                    for(int i =0; i<table1.getRowCount(); i++){
                        String[]data = new String[4];
                        for(int j=0; j<table1.getColumnCount()-1; j++){
                            data[j] = table1.getValueAt(i, j).toString();
                        }
                        System.out.println(orderId);
                        OrderPojo order = new OrderPojo(orderId, data[0], Integer.parseInt(data[1]), data[2], data[3], "Unordered", null, null);
                        orderlist.add(order);
                    }

                    //adding order to database
                    boolean added = OrdersDao.addOrder(orderlist);
                    if(added){
                        JOptionPane.showMessageDialog(null, "Memo Created");
                        int rowCount = dtm.getRowCount();
                        System.out.println(rowCount);
                        for(int i=0; i<rowCount; i++){

                            dtm.removeRow(0);
                        }
                        nameTextField.setText("");
                        quantityTextField.setText("");
                        companyTextField.setText("");
                        vendorTextField.setText("");
                    }


                }catch(SQLException sqle){
                    sqle.printStackTrace();
                }
            }
        });
    }


    public void make(){
        JFrame frame = new JFrame();
        frame.setContentPane(new CreateOrder().mainpanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1200, 700);
        this.open = true;
        frame.setVisible(true);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
               open = false;
            }
        });
    }

    public boolean isOpen(){
        return this.open;
    }
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setContentPane(new CreateOrder().mainpanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1200, 700);
        frame.setVisible(true);
    }



    private void createUIComponents() {
        //Defining rows and columns for table
        String[][] rows ={};
        String[] cols = {"Name", "Quantity", "Company", "Vendor", "Delete"};

        //Default table model for table
        TableCellRenderer deleteButton;
        dtm = new DefaultTableModel(rows, cols);
        table1 = new JTable(dtm);

        table1.getColumn("Delete").setCellEditor(new DefaultCellEditor(new JCheckBox()){
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value,
                                                         boolean isSelected, int row, int column){
                JButton button = new JButton("Delete");
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                       dtm.removeRow(row);
                    }
                });
                return  button;
            }
        });

        table1.getColumn("Delete").setCellRenderer(new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasfocus, int row, int col){

                return new JButton("Delete");
            }
        });
        //Setting table renderer for table row color
        TableCellRenderer tcr = new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasfocus, int row, int col){
                Component mycomp = super.getTableCellRendererComponent(table, value, isSelected, hasfocus, row, col);
                if(hasfocus==true){
                    mycomp.setBackground(Color.YELLOW);
                }
                return mycomp;
            }
        };

        table1.setDefaultRenderer(Object.class, tcr);
    }
}
