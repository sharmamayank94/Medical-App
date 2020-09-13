package gui;

import dao.OrdersDao;
import dao.billDao;
import pojo.Customer;
import pojo.billPojo;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Bill {
    private JPanel toppanel;
    private JTable billtable;
    private JScrollPane billsp;
    private JTextPane ADDRESSNEWGIRLSHOSTELTextPane;
    private JTextField billNo;
    private JTextField txtDoctor;
    private JTextField txtEmail;
    private JTextField txtAddress;
    private JTextField txtPhone;
    private JTextField txtCustomerName;
    private JTextField txtdate;
    private JTextField grossAmount;
    private JTextField totalCgst;
    private JTextField totalSgst;
    private JTextField NetAmount;
    private JTextPane KYAKRNAHAIYHATextPane;
    private JTextField txtvalue;
    private JButton logOutButton;
    private JButton printBillButton;
    private JButton homeButton;
    private JButton backButton;
    private JButton resetEntriesButton;
    private static JFrame frame;
    private boolean isOpen;
    private JTextField textbox = new JTextField();
    private int a=0;
    private String user;

    private ArrayList<String> categoriesArray;
    public Bill() {

        txtvalue.setEditable(false);
        billNo.setEditable(false);
        txtdate.setEditable(false);
        grossAmount.setEditable(false);
        NetAmount.setEditable(false);
        totalCgst.setEditable(false);
        totalSgst.setEditable(false);

        showBillNo();
        showdate();
        billtable.getColumn("S_No").setMaxWidth(50);
        billtable.getColumn("Name").setMinWidth(280);
        billtable.getColumn("Description").setMinWidth(250);
        billtable.getColumn("Discount(in %)").setMinWidth(100);
        billtable.getColumn("SGST").setMaxWidth(50);
        billtable.getColumn("CGST").setMaxWidth(50);


        //To add combo box to Category Column
        try {
            categoriesArray = billDao.getAvailableCategories();
            JComboBox combo = new JComboBox();
            TableColumn Medicinecategory = billtable.getColumnModel().getColumn(2);
            for (String s : categoriesArray) {
                combo.addItem(s);
                System.out.println(s);
            }
            Medicinecategory.setCellEditor(new DefaultCellEditor(combo));
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"SQL Exception");
        }



        //To add new Row and to delete an existing row
        billtable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = billtable.getSelectedRow();
                int column = billtable.getSelectedColumn();
                if(e.getClickCount()==1 && row > -1 && column>-1) {
                    if (e.getButton() == java.awt.event.MouseEvent.BUTTON3) {
                        String nameOfMedicine = billtable.getModel().getValueAt(row,1).toString();
                        int res;

                        if(nameOfMedicine == "") {
                            res = JOptionPane.showConfirmDialog(null, "do you want to delete the selected row");
                        }
                        else {
                            res = JOptionPane.showConfirmDialog(null, "do you want to delete the row with medicine name : " + nameOfMedicine);

                        }

                        if(res==0){

                            DefaultTableModel model = (DefaultTableModel)billtable.getModel();

                //To decrease the amount when row is deleted
                            if(billtable.getValueAt(row,3)!="" && billtable.getValueAt(row,8)!="" && billtable.getValueAt(row,9)!="" && billtable.getValueAt(row,11)!="") {
                                int quantityAtRowToRemove = Integer.parseInt((String) billtable.getValueAt(row, 3));
                                double rateAtRowToRemove = Double.parseDouble((String) billtable.getValueAt(row, 8));
                                double taxAtRowToRemove = Double.parseDouble(((String) billtable.getValueAt(row, 9)).substring(0, 3));
                                double taxRateToDecrease = quantityAtRowToRemove * rateAtRowToRemove * taxAtRowToRemove * 0.01;
                                double amountAtRowToRemove = Double.parseDouble((String) billtable.getValueAt(row, 11));

                                grossAmount.setText(String.valueOf(String.format("%.2f", Double.parseDouble(grossAmount.getText()) - (rateAtRowToRemove * quantityAtRowToRemove))));
                                totalCgst.setText(String.valueOf(String.format("%.2f", Double.parseDouble(totalCgst.getText()) - taxRateToDecrease)));
                                totalSgst.setText(String.valueOf(String.format("%.2f", Double.parseDouble(totalSgst.getText()) - taxRateToDecrease)));
                                NetAmount.setText(String.valueOf(String.format("%.2f", Double.parseDouble(NetAmount.getText()) - amountAtRowToRemove)));
                            }

                            model.removeRow(row);

                            System.out.println("value of a : "+a);
                            System.out.println("value of row : " + row);
                            a=a-1;
                            for(int i=row; i<a; i++){
                                model.setValueAt(i+1,i,0);   //To change the S_No of rows when any row is deleted
                            }
                            txtvalue.setText(String.valueOf(a));
                        }
                        billtable.getSelectionModel().clearSelection();

                    }


                }else if(e.getClickCount() == 2) {
                    a++;
                    insertRow(a);       //Adding a new row on double-click

                    txtvalue.setText(String.valueOf(a));

                }
            }
        });

        //To change the values in table according to name, category and quantity in table
        billtable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                System.out.println("column : " + e.getColumn() + "Row : " + e.getFirstRow() + e.getType());

                if (e.getType() != -1) {
                    for (int i = 0; i < a; i++) {
                        String name = (String) billtable.getModel().getValueAt(i, 1);
                        String category = (String) billtable.getModel().getValueAt(i, 2);
                        String quantity = (String) billtable.getModel().getValueAt(i,3);
                        double rate;
                        double tax;
                        if (name.length() > 0 && category.length() > 0) {
                            System.out.println(name + " " + category);
                            System.out.println("ready for row : " + i);

                            try {
                                billPojo b = billDao.availabilityOfNameCategory(name,category);  //To check whether the required item is available in stock or not
                                billtable.getModel().removeTableModelListener(this);
//
                                if(b.getQuantity()==0) {
                                    JOptionPane.showMessageDialog(null,   name + " " + category + " is not available in stock ");
                                    billtable.getModel().setValueAt("",i,1);
                                    billtable.getModel().setValueAt("",i,2);
                                    billtable.getModel().setValueAt("",i,3);
                                    billtable.getModel().setValueAt("",i,4);
                                    billtable.getModel().setValueAt("",i,5);
                                    billtable.getModel().setValueAt("",i,6);
                                    billtable.getModel().setValueAt("",i,7);
                                    billtable.getModel().setValueAt("",i,8);
                                    billtable.getModel().setValueAt("",i,9);
                                    billtable.getModel().setValueAt("",i,10);
                                    billtable.getModel().setValueAt("",i,11);
                                    rate=0;
                                    tax=0;
                                }

                            //when quantity is entered, it is checked whether sufficient quantity of same batch is available in stock or not
                                if(quantity.length()>0) {
                                    try {
                                        Integer.parseInt(quantity);


                                        billPojo bp = billDao.getDetailsBasedOnMedicine(name, category, quantity);
                                        if (bp.getQuantity() >= Integer.parseInt(quantity)) {
                                            billtable.getModel().setValueAt(bp.getDescription(), i, 4);
                                            billtable.getModel().setValueAt(String.format("%.2f", bp.getMrp() / bp.getNoOfMedicinesPerStrip()), i, 6);
                                            billtable.getModel().setValueAt(String.format("%.2f", bp.getRate() / bp.getNoOfMedicinesPerStrip()), i, 8);
                                            billtable.getModel().setValueAt(bp.getTax() + "%", i, 9);
                                            billtable.getModel().setValueAt(bp.getTax() + "%", i, 10);
                                            billtable.getModel().setValueAt(String.format("%.2f", 100 * (1 - (bp.getRate() / bp.getMrp()))) + "%", i, 7);
                                            billtable.getModel().setValueAt(bp.getBatchno(), i , 12);
                                            billtable.getModel().setValueAt(bp.getExpiry(),i,13);
                                            rate = bp.getRate() / bp.getNoOfMedicinesPerStrip();
                                            tax = bp.getTax();

                                            double netCgst = 0;
                                            double netSgst = 0;
                                            double grossAm = 0;
                                            double netAm = 0;
                                            System.out.println("quantity : " + quantity);
                                            int quant = Integer.parseInt(quantity);
                                            double price = quant * rate;
                                            double taxrate = price * tax * 0.02;
                                            billtable.getModel().setValueAt(String.format("%.2f", price + taxrate), i, 11);
//
                                            for (int j = 0; j < a; j++) {
                                                if (billtable.getValueAt(j, 3) != "" && billtable.getValueAt(j, 11) != "" && billtable.getValueAt(j, 8) != "") {
                                                    int quantityAtRowToRemove = Integer.parseInt((String) billtable.getValueAt(j, 3));
                                                    double rateOfNewRow = Double.parseDouble((String) billtable.getValueAt(j, 8));
                                                    double taxOfNewRow = Double.parseDouble(((String) billtable.getValueAt(j, 9)).substring(0, 3));
                                                    double taxRateToIncrease = quantityAtRowToRemove * rateOfNewRow * taxOfNewRow * 0.01;
                                                    double amountAtNewRow = Double.parseDouble((String) billtable.getValueAt(j, 11));
                                                    grossAm += rateOfNewRow * quantityAtRowToRemove;
                                                    netCgst += taxRateToIncrease;
                                                    netSgst += taxRateToIncrease;
                                                    netAm += amountAtNewRow;
                                                }
                                            }
                                            grossAmount.setText(String.valueOf(String.format("%.2f", grossAm)));
                                            NetAmount.setText(String.valueOf(String.format("%.2f", netAm)));
                                            totalCgst.setText(String.valueOf(String.format("%.2f", netCgst)));
                                            totalSgst.setText(String.valueOf(String.format("%.2f", netSgst)));
                                        } else {
                                            billtable.getModel().setValueAt("", i, 3);
                                            billtable.getModel().setValueAt("", i, 6);
                                            billtable.getModel().setValueAt("", i, 7);
                                            billtable.getModel().setValueAt("", i, 8);
                                            billtable.getModel().setValueAt("", i, 9);
                                            billtable.getModel().setValueAt("", i, 10);
                                            billtable.getModel().setValueAt("", i, 11);
                                            JOptionPane.showMessageDialog(null, "Maximum stock for " + name + " " + category + "is : " + b.getQuantity());
                                        }
                                    } catch (NumberFormatException ex) {
                                        JOptionPane.showMessageDialog(null,"Enter quantity in digits");
                                        billtable.getModel().setValueAt("", i, 3);
                                        billtable.getModel().setValueAt("", i, 6);
                                        billtable.getModel().setValueAt("", i, 7);
                                        billtable.getModel().setValueAt("", i, 8);
                                        billtable.getModel().setValueAt("", i, 9);
                                        billtable.getModel().setValueAt("", i, 10);
                                        billtable.getModel().setValueAt("", i, 11);

                                    } catch (SQLException ex) {
                                        ex.printStackTrace();
                                    } catch (HeadlessException ex) {
                                        ex.printStackTrace();
                                    }
                                }

                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null, "My SQL Exception");
                                return;
                            }


                            billtable.getModel().addTableModelListener(this);
                        }
                    }
                }
            }
        });


        printBillButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Customer = txtCustomerName.getText();
                String phone = txtPhone.getText();
                String address = txtAddress.getText();
                String email = txtEmail.getText();
                String doctor = txtDoctor.getText();
                String billno = billNo.getText();
                 String date = txtdate.getText();
                int noOfRows = billtable.getModel().getRowCount();
                if(validateInputs(noOfRows,Customer,phone,doctor)){

                    try {
                        billDao.addCustomer(Customer,phone,address,email);  //To add customer to customer table in database
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    Customer c = new Customer();
                    c.setCname(Customer);
                    c.setAddress(address);
                    c.setEmail(email);
                    c.setPhone(phone);
                    try {
                        billDao.addBill(billtable,billno,date,c,doctor);  //To add bill to bill table in database
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        return;
                    }
                    try {
                        billDao.reduceMedicines(billtable);    //To reduce items from stock(Medicine table in database)
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    showNotification(billtable);
                }



                showBillNo();
                showdate();
            }
        });
        resetEntriesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtCustomerName.setText("");
                txtPhone.setText("");
                txtAddress.setText("");
                txtDoctor.setText("");
                txtEmail.setText("");
                grossAmount.setText("");
                NetAmount.setText("");
                totalCgst.setText("");
                totalSgst.setText("");
                DefaultTableModel dm = (DefaultTableModel)billtable.getModel();
                int i = billtable.getRowCount();
                for (int j = i - 1; j >= 0; j--) {
                    dm.removeRow(j);
                }
                a=0;
                txtvalue.setText(String.valueOf(a));
            }
        });
        txtCustomerName.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                System.out.println("Focus lost of Customer name");
                String customerName = txtCustomerName.getText();
                if(!customerName.isEmpty()) {
                    txtCustomerName.setText(customerName.substring(0, 1).toUpperCase() + customerName.substring(1));
                }
                String customerPhone = txtPhone.getText();
                if(!customerName.isEmpty() || !customerPhone.isEmpty()) {
                    try {
                        Customer C = billDao.checkCustomer(customerName, customerPhone);
                        txtEmail.setText(C.getEmail());
                        txtAddress.setText(C.getAddress());

                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null,"SQL Exception : "+ex);
                    }
                }
            }
        });

        txtPhone.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String customerName = txtCustomerName.getText();
                String customerPhone = txtPhone.getText();
                if(customerPhone.length()!=10){
                    JOptionPane.showMessageDialog(null,"Phone no should be of 10 digits");
                }
                else {
                    try {
                        Double.parseDouble(customerPhone);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "only digits should be entered in phone no field");
                        txtPhone.setText("");
                    }
                }


                if(!customerName.isEmpty() || !customerPhone.isEmpty()) {
                    try {
                        Customer C = billDao.checkCustomer(customerName, customerPhone);
                        txtEmail.setText(C.getEmail());
                        txtAddress.setText(C.getAddress());

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }

        });

//        billtable.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(textbox));
//        textbox.addCaretListener(new CaretListener() {
//            @Override
//            public void caretUpdate(CaretEvent e) {
//                System.out.println("caret listener " + textbox.getText());
//                int row = billtable.getSelectedRow();
//                String to_check = textbox.getText();
////                String to_check = (String) billtable.getValueAt(row, 1);
//                int to_check_len = to_check.length();
//                for (String data : s) {
//                    String check_from_data = "";
//                    for (int i = 0; i < to_check_len; i++) {
//                        if (to_check_len <= data.length()) {
//                            check_from_data = check_from_data + data.charAt(i);
//                        }
//                    }
//                    System.out.println(to_check);
//                    if (check_from_data.equals(to_check)) {
//                        System.out.println("they are equal");
//                        billtable.setValueAt(data, row, 1);
//                        textbox.setSelectionStart(to_check_len);
//                        textbox.setSelectionEnd(data.length());
////                                JTextComponent edit = (JTextComponent) billtable.getCellEditor(row, 1);
////                                edit.select(to_check_len, data.length());
//                        break;
//                    }
//
//                }
//            }
//        });

//        textbox.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyTyped(KeyEvent e) {
//                System.out.println("inside key listener of textbox");
//                int row = billtable.getSelectedRow();
//                if(billtable.getSelectedColumn()==1) {
//                    if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE) {
//
//                    } else {
//                        String to_check = (String) billtable.getValueAt(row, 1);
//                        int to_check_len = to_check.length();
//                        for (String data : s) {
//                            String check_from_data = "";
//                            for (int i = 0; i < to_check_len; i++) {
//                                if (to_check_len <= data.length()) {
//                                    check_from_data = check_from_data + data.charAt(i);
//                                }
//                            }
//                            if (check_from_data.equals(to_check)) {
//                                billtable.setValueAt(data, row, 1);
//                                textbox.setSelectionStart(to_check_len);
//                                textbox.setSelectionEnd(data.length());
////                                JTextComponent edit = (JTextComponent) billtable.getCellEditor(row, 1);
////                                edit.select(to_check_len, data.length());
//                                break;
//                            }
//                        }
//                    }
//                }
//            }
//        });

//        billtable.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                if (billtable.getSelectedColumn() == 1) {
//                    int row = billtable.getSelectedRow();
//                    ((JTextComponent) billtable.getCellEditor(row,1)).getDocument().addDocumentListener(new DocumentListener() {
//                        @Override
//                        public void insertUpdate(DocumentEvent e) {
//                            System.out.println("inside focus listener of bill table");
//                            suggest();
//
//                        }
//
//                        private void suggest() {
//
//                                String to_check = (String) billtable.getValueAt(row, 1);
//                                int to_check_len = to_check.length();
//                                for (String data : s) {
//                                    String check_from_data = "";
//                                    for (int i = 0; i < to_check_len; i++) {
//                                        if (to_check_len <= data.length()) {
//                                            check_from_data = check_from_data + data.charAt(i);
//                                        }
//                                    }
//                                    if (check_from_data.equals(to_check)) {
//                                        billtable.setValueAt(data, row, 1);
//                                        JTextComponent edit = (JTextComponent) billtable.getCellEditor(row, 1);
//                                        edit.select(to_check_len, data.length());
//                                        break;
//                                    }
//                                }
//
//                        }
//
//                        @Override
//                        public void removeUpdate(DocumentEvent e) {
//                            suggest();
//                        }
//
//                        @Override
//                        public void changedUpdate(DocumentEvent e) {
//
//                        }
//                    });
//                }
//            }
//        });

        txtDoctor.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if (txtDoctor.getText() != "") {
                    String docname = txtDoctor.getText();
                    docname = docname.trim();
                    String[] a = docname.split(" ");
                    String doc = "";
                    if (a[0].equalsIgnoreCase("dr") || a[0].equalsIgnoreCase("dr.")) {
                        doc += "DR.  ";
                        for (int i = 1; i < a.length; i++) {
                            doc += (a[i].substring(0, 1).toUpperCase() + a[i].substring(1)) + " ";
                        }
                    } else {
                        doc += "DR.  ";
                        for (int i = 0; i < a.length; i++) {
                            doc += (a[i].substring(0, 1).toUpperCase() + a[i].substring(1)) + " ";
                        }
                    }
                    txtDoctor.setText(doc);
                }
            }
        });
        txtAddress.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                if(txtAddress.getText().length()>0){
                    String add = txtAddress.getText();
                    txtAddress.setText(add.substring(0,1).toUpperCase() + add.substring(1));
                }
            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(user=="admin"){
                    AdminPanel ap = new AdminPanel();
                    ap.make();
                    frame.dispose();
                }
                else if(user=="other"){
                    UserPanel up = new UserPanel();
                    //up.make();
                    frame.dispose();
                }

            }

        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(user=="admin"){
                    AdminPanel ap = new AdminPanel();
                    ap.make();
                    frame.dispose();
                }
                else if(user=="other"){
                    UserPanel up = new UserPanel();
                    //up.make();
                    frame.dispose();
                }
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPanel lp = new LoginPanel();
                lp.make();
                frame.dispose();
            }
        });
    }

    private void showNotification(JTable billtable) {
        try {
            ArrayList<billPojo> list = billDao.checkItem(billtable);
            if(list.size()>0){
                musicStuff m = new musicStuff();
                String musicPath = "src/gui/music/insight.wav";

                notification2 n = new notification2();
                m.playMusic(musicPath);
                n.make(list);
                OrdersDao.addOrderOfNotification(list);
            }
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    //before printing bill, all the inputs are checked
    private boolean validateInputs(int noOfRows,String Customer,String phone,String doctor) {

        if(noOfRows==0){
            JOptionPane.showMessageDialog(null,"Empty Bill");
            return false;
        }
        for(int i=0; i<noOfRows; i++){
            if(billtable.getValueAt(i,1).toString().isEmpty()){
                JOptionPane.showMessageDialog(null, "medicine name field cannot be empty");
                return false;
            }
            if(billtable.getValueAt(i,2).toString().isEmpty()){
                JOptionPane.showMessageDialog(null,"Category field cannot be empty");
                return false;
            }
            if(billtable.getValueAt(i,3).toString().isEmpty()){
                JOptionPane.showMessageDialog(null,"quantity of all medicines should be entered");
                return false;
            }

        }
        if(Customer.isEmpty()){
            JOptionPane.showMessageDialog(null,"Customer name is mandatory");
            return false;
        }
        if(phone.length()!=10){
            JOptionPane.showMessageDialog(null,"Phone no should be of 10 digits");
            return false;
        }

        if(doctor.isEmpty()){
            JOptionPane.showMessageDialog(null,"Doctor's name should be entered");
            return false;
        }

        return true;
    }

//code to insert new row
    private void insertRow(int a) {
        DefaultTableModel model = (DefaultTableModel)billtable.getModel();
        model.addRow(new Object[]{a,"","","","","","","","","","","",""});
    }
//code to show current date
    private void showdate() {

        try {
            txtdate.setText(billDao.getDATE());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"SQL exception " + e);
            return;
        }
    }
//code to display bill no.
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
                if (column == 0||column==6||column==7||column==8||column==9||column==10||column==11||column==12||column==13)  return false;
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
        model.addColumn("Discount(in %)");
        model.addColumn("Rate");
        model.addColumn("CGST");
        model.addColumn("SGST");
        model.addColumn("Amount");
        model.addColumn("Batch no");
        model.addColumn("Expiry Date");
        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        return table;
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            frame = new JFrame("BILL GENERATION");

            frame.setContentPane(new Bill().toppanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

        }
    });



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

    public void make(String user){
        this.user = user;
        System.out.println("Opening Bill frame");
        frame = new JFrame("Generate Bill");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setContentPane(this.toppanel);
        frame.setVisible(true);
    }
}
