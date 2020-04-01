package dao;

import com.sun.jdi.ClassNotPreparedException;
import dbutil.DBConnection;
import pojo.OrderPojo;

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
           ps.setString(6, "Pending");
           ps.setDate(7, new java.sql.Date(med.getDateOfOrder().getTimeInMillis()));

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

            dateOfOrder.setTime(rs.getDate(10));
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
            ps.setString(6, "Pending");
            ps.setDate(7, new java.sql.Date(med.getDateOfOrder().getTimeInMillis()));

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

    public static ArrayList<OrderPojo> getOrders() throws SQLException{
        ArrayList<OrderPojo> order = new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("Select OrderId, Name, Quantity, Company, Vendor, Status, Date_Of_Order,  Date_Of_Order_Completion from orders");
        while(rs.next()){
            Calendar dateOfOrder = Calendar.getInstance();
            dateOfOrder.setTime(rs.getDate(7));
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
}
