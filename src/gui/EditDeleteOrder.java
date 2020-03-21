package gui;

import dao.OrdersDao;
import pojo.OrderPojo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class EditDeleteOrder {
    private JTable table1;
    private JPanel mainpanel;
    private JPanel headpanel;
    private JScrollPane scrollpane;
    private JLabel headingLabel;
    private JButton updateButton;
    private JButton deleteButton;
    private JTextField nameTextField;
    private JTextField quantityTextField;
    private JTextField companyTextField;
    private JTextField vendorTextField;
    private JButton addRowButton;
    private JButton deleteSelectedRowButton;
    private JButton orderDevliveredButton;

    private String orderId;
    private DefaultTableModel dtm;
    private boolean isOpen;
    private JFrame frame;

    public EditDeleteOrder(String orderId){
        this.orderId = orderId;
        try {
            ArrayList<OrderPojo> order = OrdersDao.getOrder(orderId);
            for(OrderPojo med: order){
                Object[] row = new Object[5];
                row[0] = med.getName();
                row[1] = med.getQuantity();
                row[2] = med.getCompany();
                row[3] = med.getVendor();
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                row[4] = sdf.format(med.getDateOfOrder().getTime());

                dtm.addRow(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ArrayList<OrderPojo> order = new ArrayList<>();
                for(int i=0; i<dtm.getRowCount(); i++){
                    Calendar dateOfOrder = Calendar.getInstance();
                    String tempDate = dtm.getValueAt(i, 4).toString();
                    dateOfOrder.set(Integer.parseInt(tempDate.substring(6)), Integer.parseInt(tempDate.substring(3,5))-1, Integer.parseInt(tempDate.substring(0,2)));


                    OrderPojo med = new OrderPojo(orderId, dtm.getValueAt(i, 0).toString(),
                            Integer.parseInt(dtm.getValueAt(i, 1).toString()),
                            dtm.getValueAt(i, 2).toString(), dtm.getValueAt(i, 3).toString(),
                            "Pending", dateOfOrder, null);


                    order.add(med);
                }
                try {
                    boolean result = OrdersDao.updateOrder(order);
                    isOpen = false;
                    frame.dispose();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    boolean result = OrdersDao.deleteOrder(orderId);
                    if(result){
                        JOptionPane.showMessageDialog(null, "Order deleted from the Database");
                        isOpen = false;
                        frame.dispose();
                    }
                }catch(SQLException sqle){
                    JOptionPane.showMessageDialog(null,"Problem in Database");
                    sqle.printStackTrace();
                }
            }
        });
        addRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                Calendar today = Calendar.getInstance();
                String todayDate  = sdf.format(today.getTime());
                dtm.addRow(new Object[] {nameTextField.getText(), quantityTextField.getText(), companyTextField.getText(), vendorTextField.getText(), todayDate});
            }
        });
        deleteSelectedRowButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               dtm.removeRow(table1.getSelectedRow());
            }
        });
    }

    public void make(){
        frame = new JFrame("Edit/Delete Order");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setContentPane(this.mainpanel);
        frame.setVisible(true);
        this.isOpen = true;

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                isOpen = false;
                MyOrders mo = new MyOrders();
                mo.make();

            }
        });
    }

    private void createUIComponents() {
        String[][] rows = {};
        String [] cols = {"Name", "Quantity", "Company", "Vendor", "Date Of Order"};
        dtm = new DefaultTableModel(rows, cols);
        table1 = new JTable(dtm);

    }

    public boolean isOpen() {
        return this.isOpen;
    }
}
