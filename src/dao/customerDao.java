package dao;

import dbutil.DBConnection;

import pojo.Customer;

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
                    c.setCname(rs.getString(1));
                    c.setPhone(rs.getString(2));
                    c.setAddress(rs.getString(3));
                    c.setEmail(rs.getString(4));
                    c.setNoOfVisits(rs.getInt(5));
                    c.setSno(rs.getInt(6));
                    customer.add(c);
            }
        return customer;
    }
}
