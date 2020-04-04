package dao;

import dbutil.DBConnection;
import pojo.vendorPojo;

import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Vendordao {

    public static ArrayList<vendorPojo> getAllVendors() throws SQLException {
        Statement st = DBConnection.getConnection().createStatement();
        String qry = "select Name, Agency from vendors";
        ResultSet rs = st.executeQuery(qry);
        ArrayList<vendorPojo> a = new ArrayList<>();
        while (rs.next()){
            vendorPojo vp = new vendorPojo();
            vp.setName(rs.getString(1));
            vp.setAgency(rs.getString(2));
            a.add(vp);
        }
        return a;
    }

    public static vendorPojo getVendorDetails(String name, String agency) throws SQLException {
        PreparedStatement pst = DBConnection.getConnection().prepareStatement("select * from vendors where Name = ? and Agency = ?");
        pst.setString(1,name);
        pst.setString(2,agency);
        ResultSet rs = pst.executeQuery();
        vendorPojo v = new vendorPojo();
        if(rs.next()){
            v.setName(rs.getString(2));
            v.setAddress(rs.getString(3));
            v.setPhone(rs.getString(4));
            v.setEmail(rs.getString(5));
            v.setAgency(rs.getString(6));
            v.setLicensceNo(rs.getString(7));
        }
        return v;
    }

    public static int updateVendor(String name, String agency, vendorPojo v) throws SQLException {
        PreparedStatement pst = DBConnection.getConnection().prepareStatement("update vendors set Name=?, Address=?, Phone=?, E_Mail=?, Agency=?, License_No=? where Name=? and Agency=?");
        pst.setString(1,v.getName());
        pst.setString(2,v.getAddress());
        pst.setString(3,v.getPhone());
        pst.setString(4,v.getEmail());
        pst.setString(5,v.getAgency());
        pst.setString(6,v.getLicensceNo());
        pst.setString(7,name);
        pst.setString(8,agency);
        int result = pst.executeUpdate();
        System.out.println(result);
        return result;
    }

    public static int addVendor(vendorPojo vp) throws SQLException {
        PreparedStatement pst = DBConnection.getConnection().prepareStatement("insert into vendors(Name,Address,Phone,E_Mail,Agency,License_No) values(?,?,?,?,?,?)");
        pst.setString(1,vp.getName());
        pst.setString(2,vp.getAddress());
        pst.setString(3,vp.getPhone());
        pst.setString(4,vp.getEmail());
        pst.setString(5,vp.getAgency());
        pst.setString(6,vp.getLicensceNo());
        int result = pst.executeUpdate();
        return result;
    }
}
