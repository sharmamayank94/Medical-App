package gui;

//import com.mysql.cj.x.protobuf.MysqlxCrud;
import dao.OrdersDao;
import pojo.OrderPojo;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;

public class MyOrders {

    private JPanel mainpanel;

    private JButton createOrderButton;
    private JTable table1;
    private JScrollPane scrollPane;
    private JButton homeButton;
    private JButton logoutButton;
    private JButton editOrdersButton;
    private static JFrame frame;
    
    private ArrayList<OrderPojo>orderList;
    private DefaultTableModel dtm;
    private  TableRowSorter trs;
    private String maxOrderId;
    private ArrayList<OrderPojo> newOrder;

    MyOrders(){

        try{
            maxOrderId = OrdersDao.getMaxOrderId();
        }catch(SQLException sqle){
            sqle.printStackTrace();
        }

        orderList = new ArrayList<>();

        try {
            orderList = OrdersDao.getOrders();
        } catch (SQLException e) {
            e.printStackTrace();
        }

      if(!orderList.isEmpty()){
          addAllOrders();
      }



        createOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                CreateOrder co = new CreateOrder();
                co.make();
                frame.setEnabled(false);

                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while(true){
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            if(co.isOpen()==false){
                                frame.setEnabled(true);
                                addNewOrder();
                                break;
                            }

                        }


                    }
                });
                t.start();
            }
        });


    }

    private void addAllOrders() {
        ArrayList<OrderPojo> order = new ArrayList<>();
        String currentOrderId = orderList.get(0).getOrderId();

        for(OrderPojo med: orderList){
            if(med.getOrderId().equals(currentOrderId)){
                order.add(med);
            }else{

                addOrderToTable(order);
                currentOrderId=med.getOrderId();
                order = new ArrayList<>();
                order.add(med);
            }
        }
        addOrderToTable(order);
    }


    public void addNewOrder(){



        try{
            String tempMaxOrderId = OrdersDao.getMaxOrderId();
            int noOfNewOrders = Integer.parseInt(tempMaxOrderId.substring(3)) - Integer.parseInt(maxOrderId.substring(3));

            for(int i=0; i<noOfNewOrders; i++){
                maxOrderId = "ord"+ (Integer.parseInt(maxOrderId.substring(3))+1);
                ArrayList<OrderPojo> order = OrdersDao.getOrder(maxOrderId);

                addOrderToTable(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void addOrderToTable(ArrayList<OrderPojo> order) {

        String name = "<html>", company="<html>";
        for(OrderPojo med: order){
            name+=med.getName()+"<br/>";
            company+=med.getCompany()+"<br/>";
        }

        name=name.substring(0, name.length()-5)+"</html>";
        company=company.substring(0, company.length()-5)+"</html>";

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        String dateOfOrder = sdf.format(order.get(0).getDateOfOrder().getTime());
        String dateOfOrderCompletion;
        if(order.get(0).getDateOfOrderCompletion()!=null){
            dateOfOrderCompletion = sdf.format(order.get(0).getDateOfOrderCompletion().getTime());
        }else{
            dateOfOrderCompletion = null;
        }


        dtm.addRow(new Object[]{order.get(0).getOrderId(), name, "43", company, order.get(0).getVendor(), dateOfOrder, order.get(0).getStatus(), dateOfOrderCompletion, ""});
        int count = name.split("<br/>").length;
        table1.setRowHeight(dtm.getRowCount()-1, count*30);

    }

    public static void main(String[] args) {
        frame = new JFrame("My Orders");
        frame.setContentPane(new MyOrders().mainpanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);



    }

    public void make(){
        frame = new JFrame("My Orders");
        frame.setContentPane(new MyOrders().mainpanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
    }
    private void createUIComponents() {
        String [] columns = {"Order Id", "Name", "Current Quantity", "Company", "Vendor", "Date of Order", "Status", "Date of Order Completion", "Edit/Delete"};


        dtm = new DefaultTableModel(null, columns);

        table1= new JTable(dtm);
        table1.setFillsViewportHeight(true);
        table1.setFont(new Font("Serif", Font.PLAIN, 18));

        DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column){
                Component mycomp =super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);



                if( value.toString().equals("Pending")){
                    mycomp.setBackground(Color.YELLOW);

                }

                else if(value.toString().equals("Completed"))
                    mycomp.setBackground(Color.GREEN);
                return mycomp;
            }
        };

        table1.getColumn("Status").setCellRenderer( dtcr);

//        table1.setDefaultRenderer(Object.class, dtcr);
        //adding button for editing and updating row

        table1.getColumn("Edit/Delete").setCellRenderer(new DefaultTableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int rows, int columns){
                JButton button = new JButton("Edit/Delete");
                button.setSize(50, 20);
                Dimension d = new Dimension(50, 20);
                button.setPreferredSize(d);
                button.setBackground(new Color(108, 27, 227));
                button.setForeground(Color.WHITE);
//                button.setVerticalAlignment(1);
//                return new JButton("Edit/Delete");
                return button;
            }
        });
        table1.getColumn("Edit/Delete").setCellEditor(new DefaultCellEditor(new JCheckBox()){
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column){
                JButton button = new JButton("Edit/Delete");

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {


                        String orderId = table1.getValueAt(row, 0).toString();

                        EditDeleteOrder editDeleteOrder = new EditDeleteOrder(orderId);
                        editDeleteOrder.make();
                        frame.dispose();
//                        frame.dispose();
//                        frame.setEnabled(false);
//                        Thread t = new Thread(new Runnable() {
//                            @Override
//                            public void run() {
//                                try {
//                                    System.out.println("I am insdie thread");
//
//                                    while(editDeleteOrder.isOpen())  Thread.sleep(2000);
//                                    frame.setEnabled(true);
//                                    int modelrow  = table.convertRowIndexToModel(row);
//                                    dtm.removeRow(modelrow);
//                                    ArrayList<OrderPojo> order = new ArrayList<>();
//                                    try {
//                                        order = OrdersDao.getOrder(orderId);
//                                    } catch (SQLException e) {
//                                        e.printStackTrace();
//                                    }
//                                    addOrderToTable(order);
//                                    System.out.println("Thread is over");
//
//                                } catch (InterruptedException  e) {
//                                    System.out.println("exception occured");
//                                    e.printStackTrace();
//                                }
//
//                            }
//                        });
//                        t.start();
                    }
                });
                return button;
            }
        });





//        table1.setAutoCreateRowSorter(true);
//
//        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table1.getModel());
//        table1.setRowSorter(sorter);
//
//
//        sorter.setSortKeys(sortKeys);
//        sorter.sort();

        trs = new TableRowSorter(dtm);
        trs.setComparator(0, new Comparator<String>() {
            @Override
            public int compare(String a, String b){
                a = a.substring(3);
                b = b.substring(3);

                if(Integer.parseInt(a)>Integer.parseInt(b))
                    return 1;
                else if(Integer.parseInt(a)<Integer.parseInt(b))
                    return -1;
                else
                    return 0;



            }
        });

        table1.setRowSorter(trs);
//        ArrayList<RowSorter.SortKey> sortKeys = new ArrayList<>();
////
//        int columnIndexToSort = 0;
//        sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
//        trs.setSortKeys(sortKeys);
    }
}

