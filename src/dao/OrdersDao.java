package dao;

import com.sun.jdi.ClassNotPreparedException;
import dbutil.DBConnection;
import pojo.OrderPojo;
import pojo.billPojo;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class OrdersDao {
    public static boolean addOrder(ArrayList<OrderPojo> order) throws SQLException {
       Connection conn = DBConnection.getConnection();

       PreparedStatement ps = conn.prepareStatement("Insert into orders(OrderId, Name, Quantity, Company, Vendor, Status, Date_Of_Order) VALUES(?, ?, ?, ?, ?, ?, ?)");
       for(OrderPojo med: order){
           ps.setString(1, med.getOrderId());
           ps.setString(2, med.getName());
           ps.setInt(3, med.getQuantity());
           ps.setString(4, med.getCompany());
           ps.setString(5, med.getVendor());
           ps.setString(6, med.getStatus());
           if(med.getDateOfOrder()==null) ps.setDate(7, null);
           else ps.setDate(7, new java.sql.Date(med.getDateOfOrder().getTimeInMillis()));

           int executed = ps.executeUpdate();
           if(executed==0){
               return false;
           }
       }

       return true;
    }

    public static String getMaxOrderId() throws SQLException {
        Connection conn = DBConnection.getConnection();
        Statement s  = conn.createStatement();
        ResultSet rs = s.executeQuery("SELECT MAX(CAST(SUBSTRING(OrderId, 4, length(ORderId)-3) AS UNSIGNED)) FROM orders");
        rs.next();
        String maxOrderId = "ord"+rs.getInt(1);
        return maxOrderId;
    }

    public static ArrayList<OrderPojo> getOrder(String orderId) throws SQLException{
        ArrayList<OrderPojo> order = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("select * from orders where OrderId = '" + orderId +"'");
        while(rs.next()){
            Calendar dateOfOrderCompletion = Calendar.getInstance();
            Calendar dateOfOrder = Calendar.getInstance();
            if(rs.getDate(8)!=null)
                dateOfOrderCompletion.setTime(rs.getDate(8));
            else
                dateOfOrderCompletion = null;

            try{
                dateOfOrder.setTime(rs.getDate(10));
            }catch(NullPointerException npe){
                dateOfOrder  =null;
            }
            OrderPojo med = new OrderPojo(rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5),
                    rs.getString(6), rs.getString(7),  dateOfOrder, dateOfOrderCompletion);

            order.add(med);

        }

        return order;
    }

    public static String getNewOrderId() throws SQLException{
        Statement s = DBConnection.getConnection().createStatement();
        ResultSet rs = s.executeQuery("SELECT MAX(CAST(SUBSTRING(OrderId, 4, length(ORderId)-3) AS UNSIGNED)) FROM orders");
        rs.next();
        String newId = "ord"+rs.getInt(1);
        newId = "ord"+(Integer.parseInt(newId.substring(3))+1);
        return newId;
    }

    public static boolean updateOrder(ArrayList<OrderPojo> order) throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement psd = conn.prepareStatement("DELETE FROM orders where OrderId=?");
        psd.setString(1, order.get(0).getOrderId());
        psd.executeUpdate();
        PreparedStatement ps = conn.prepareStatement("Insert into orders(OrderId, Name, Quantity, Company, Vendor, Status, Date_Of_Order) VALUES(?, ?, ?, ?, ?, ?, ?)");

        for(OrderPojo med: order){
            ps.setString(1, med.getOrderId());
            ps.setString(2, med.getName());
            ps.setInt(3, med.getQuantity());
            ps.setString(4, med.getCompany());
            ps.setString(5, med.getVendor());
            ps.setString(6, med.getStatus());
            if(med.getDateOfOrder()!=null) ps.setDate(7, new java.sql.Date(med.getDateOfOrder().getTimeInMillis()));
            else ps.setDate(7,null);

            int result = ps.executeUpdate();
            if(result==0){
                return false;
            }
        }
        return true;
    }

    public static boolean updateOrderStatus(String orderId, String status, String name) throws SQLException{
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("Update orders set Status = ? where OrderId = ? and Name=?");
        ps.setString(1, status);
        ps.setString(2, orderId);
        ps.setString(3, name);
        int result = ps.executeUpdate();
        return result>0;
    }

    public static boolean setDateOfOrderCompletion(String orderId, Calendar dateOfOrderCompletion) throws SQLException{
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("Update orders set Date_Of_Order_Completion = ? where OrderId=?");
        ps.setDate(1, new Date(dateOfOrderCompletion.getTimeInMillis()));
        ps.setString(2, orderId);

        int result = ps.executeUpdate();
        return result>0;
    }

    public static ArrayList<OrderPojo> getOrders() throws SQLException{
        ArrayList<OrderPojo> order = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("Select OrderId, Name, Quantity, Company, Vendor, Status, Date_Of_Order,  Date_Of_Order_Completion from orders");
        while(rs.next()){
            Calendar dateOfOrder = Calendar.getInstance();
            if(rs.getDate(7)==null) dateOfOrder = null;
            else dateOfOrder.setTime(rs.getDate(7));
            Calendar dateOfOrderCompletion = Calendar.getInstance();
            if(rs.getDate(8)!=null){
                dateOfOrderCompletion.setTime(rs.getDate(8));
            }else{
                dateOfOrderCompletion = null;
            }

            OrderPojo med = new OrderPojo(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), dateOfOrder, dateOfOrderCompletion);
            order.add(med);
        }
        return order;
    }

    public static boolean deleteOrder(String orderId) throws SQLException{
        Connection conn = DBConnection.getConnection();
        Statement s = conn.createStatement();
        int result = s.executeUpdate("Delete from orders where OrderId = '"+orderId+"'");
        System.out.println(result);
        return result>0;
    }

    public static void addOrderOfNotification(ArrayList<billPojo> list) throws SQLException {
        ArrayList<OrderPojo> ord = getOrders();
        for(billPojo bp : list) {
            boolean found = false;
            for (OrderPojo item : ord) {
                System.out.println("name : " + item.getName());
                if (item.getName().equalsIgnoreCase(bp.getMedicine())) {
                    System.out.println("found name : " + item.getName());
                    found = true;
                    break;
                }
            }
            if(!found){
                String ordid = getNewOrderId();
                PreparedStatement pst = DBConnection.getConnection().prepareStatement("select Company, Vendor from medicine where Name=?");
                pst.setString(1, bp.getMedicine());
                ResultSet rs = pst.executeQuery();
                String company = "";
                String vendor = "";
                if(rs.next()){
                    company = rs.getString(1);
                    vendor = rs.getString(2);
                }
                PreparedStatement ps = DBConnection.getConnection().prepareStatement("insert into orders(OrderId, Name, Company, Vendor, Status) values (?,?,?,?,?)");
                ps.setString(1,ordid);
                ps.setString(2, bp.getMedicine());
                ps.setString(3, company);
                ps.setString(4, vendor);
                ps.setString(5, "Unordered");

                ps.executeUpdate();
            }
        }
    }
}
