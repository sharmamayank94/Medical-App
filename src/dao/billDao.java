package dao;

import dbutil.DBConnection;

import pojo.billPojo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class billDao {
    public static ArrayList<billPojo> getBills(String name, String email) throws SQLException {
        PreparedStatement pst = DBConnection.getConnection().prepareStatement(" select DISTINCT(Bill_No),Doctor,Date from bill where Customer=? and Email=?");
        pst.setString(1,name);
        pst.setString(2,email);
        ResultSet rs = pst.executeQuery();
        ArrayList<billPojo> a  = new ArrayList<>();
        while(rs.next()){
            billPojo b = new billPojo();
            b.setBill_No(rs.getString(1));
            b.setDoctor(rs.getString(2));
            b.setSellDate(rs.getDate(3));
            a.add(b);
        }
        return a;
    }

    public static ArrayList<billPojo> getBillDetails(String billNo) throws SQLException {
         PreparedStatement pst = DBConnection.getConnection().prepareStatement("select Medicine_Name,Category,Batch_No from bill where Bill_No=?");
         pst.setString(1,billNo);
         ResultSet rs = pst.executeQuery();
         ArrayList<billPojo> a = new ArrayList<>();
         while(rs.next()){
             PreparedStatement ps = DBConnection.getConnection().prepareStatement("select Expiry from Medicine where Name=? and Category=? and Batch_No=?");
             ps.setString(1,rs.getString(1));
             ps.setString(2,rs.getString(2));
             ps.setString(3,rs.getString(3));
             ResultSet r = ps.executeQuery();
             billPojo b = new billPojo();
             if(r.next()){
                 b.setExpiry(r.getDate(1));
             }
             b.setMedicine(rs.getString(1));
             b.setCategory(rs.getString(2));
             b.setBatchno(rs.getString(3));
             a.add(b);

         }
         return a;
    }

    public static String getBillNo() throws SQLException {
        Statement s = DBConnection.getConnection().createStatement();
        String qry = "select max(Bill_No) from bill";
        ResultSet rs = s.executeQuery(qry);
        if(rs.next()){
            String id = rs.getString(1);
            int no = Integer.parseInt(id.substring(1)) + 1;
            return "B"+no;
        }
        return "B101";
    }

    public static String getDATE() throws SQLException {
        Statement s = DBConnection.getConnection().createStatement();
        String qry = "select CURDATE()";
        ResultSet rs = s.executeQuery(qry);
        System.out.println(rs);
        if(rs.next()) {
            return rs.getString(1);
        }
        else {
            return "a";
        }
    }
}
