package dao;

import dbutil.DBConnection;

import pojo.Customer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class customerDao {


            public static ArrayList<Customer> getAllCustomerDetails() throws SQLException {
                Statement st = DBConnection.getConnection().createStatement();
                String qry = "select * from customer";
                ResultSet rs = st.executeQuery(qry);
                ArrayList<Customer> customer = new ArrayList<>();
                while(rs.next()){
                    Customer c = new Customer();
                    c.setSno(rs.getInt(1));
                    c.setCname(rs.getString(2));
                    c.setPhone(rs.getString(3));
                    c.setAddress(rs.getString(4));
                    c.setEmail(rs.getString(5));
                    c.setNoOfVisits(rs.getInt(6));

                    customer.add(c);
            }
        return customer;
    }

    public static ArrayList<Customer> getCustomerByName(String text) throws SQLException {
                PreparedStatement pst = DBConnection.getConnection().prepareStatement("select * from customer where Customer=?");
                pst.setString(1,text);
                ResultSet rs = pst.executeQuery();
                ArrayList<Customer> a = new ArrayList<>();
                while(rs.next()){
                    Customer c = new Customer();
                    c.setCname(rs.getString(2));
                    c.setPhone(rs.getString(3));
                    c.setEmail(rs.getString(5));
                    c.setAddress(rs.getString(4));
                    c.setNoOfVisits(rs.getInt(6));
                    a.add(c);
                }
                return a;
    }

    public static ArrayList<Customer> getCustomersName() throws SQLException {
                Statement st = DBConnection.getConnection().createStatement();
                String query = "select DISTINCT(Customer) from customer order by Customer";
                ResultSet rs = st.executeQuery(query);
                ArrayList<Customer> a = new ArrayList<>();
                while (rs.next()){
                    Customer c = new Customer();
                    c.setCname(rs.getString(1));
                    a.add(c);
                }
                return a;
    }

    public static ArrayList<Customer> getCustomersSortedByName() throws SQLException {
        Statement st = DBConnection.getConnection().createStatement();
        String qry = "select * from customer order by Customer";
        ResultSet rs = st.executeQuery(qry);
        ArrayList<Customer> customer = new ArrayList<>();
        while(rs.next()){
            Customer c = new Customer();
            c.setCname(rs.getString(2));
            c.setPhone(rs.getString(3));
            c.setAddress(rs.getString(4));
            c.setEmail(rs.getString(5));
            c.setNoOfVisits(rs.getInt(6));
            c.setSno(rs.getInt(1));
            customer.add(c);
        }
        return customer;
    }

    public static ArrayList<Customer> getCustomersSortedByAddress() throws SQLException {
        Statement st = DBConnection.getConnection().createStatement();
        String qry = "select * from customer order by Address";
        ResultSet rs = st.executeQuery(qry);
        ArrayList<Customer> customer = new ArrayList<>();
        while(rs.next()){
            Customer c = new Customer();
            c.setCname(rs.getString(2));
            c.setPhone(rs.getString(3));
            c.setAddress(rs.getString(4));
            c.setEmail(rs.getString(5));
            c.setNoOfVisits(rs.getInt(6));
            c.setSno(rs.getInt(1));
            customer.add(c);
        }
        return customer;
    }

    public static ArrayList<Customer> getCustomersSortedByNoOfVisits() throws SQLException {
        Statement st = DBConnection.getConnection().createStatement();
        String qry = "select * from customer order by No_Of_Visits";
        ResultSet rs = st.executeQuery(qry);
        ArrayList<Customer> customer = new ArrayList<>();
        while(rs.next()){
            Customer c = new Customer();
            c.setCname(rs.getString(2));
            c.setPhone(rs.getString(3));
            c.setAddress(rs.getString(4));
            c.setEmail(rs.getString(5));
            c.setNoOfVisits(rs.getInt(6));
            c.setSno(rs.getInt(1));
            customer.add(c);
        }
        return customer;
    }
}
