package dao;

import dbutil.DBConnection;

import pojo.Customer;
import pojo.billPojo;

import javax.swing.*;
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
        String qry = " select Bill_No from bill where S_No=(select max(S_No) from bill)";
        ResultSet rs = s.executeQuery(qry);
        if(rs.next()){
            String id = rs.getString(1);
            int no = Integer.parseInt(id.substring(1)) + 1;
            if(no<=9){
                return "B00"+no;
            }
            else if(no<=99){
                return "B0"+no;
            }
            return "B"+no;
        }
        return "B001";
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

    public static billPojo availabilityOfNameCategory(String name, String category) throws SQLException {
        PreparedStatement pst = DBConnection.getConnection().prepareStatement("select Total_Quantity from medicine where Name=? and Category=?");
        pst.setString(1,name);
        pst.setString(2,category);
        ResultSet r = pst.executeQuery();
        billPojo b = new billPojo();
        b.setQuantity(0);
        while(r.next()) {
            if(r.getInt(1)>0) {
                b.setQuantity(r.getInt(1));
                break;
            }
        }

        return  b;
    }

    public static ArrayList<String> getAvailableCategories() throws SQLException {
        PreparedStatement pst = DBConnection.getConnection().prepareStatement("select distinct Category from medicine");
        ResultSet rs = pst.executeQuery();
        ArrayList<String> a = new ArrayList<>();
        while(rs.next()){
            a.add(rs.getString(1));
        }
        return a;
    }

    public static void addCustomer(String customer, String phone, String address, String email) throws SQLException {
        PreparedStatement pst1 = DBConnection.getConnection().prepareStatement("select No_Of_Visits from customer where Customer=? and Phone_No=?");
        pst1.setString(1,customer);
        pst1.setString(2,phone);
        ResultSet rs = pst1.executeQuery();
        int visits=1;
        if(rs.next()){
            visits += Integer.parseInt(rs.getString(1));
        }
        if(visits==1) {
            PreparedStatement pst = DBConnection.getConnection().prepareStatement("insert into customer(Customer,Phone_No,Address,Email,No_Of_Visits) values(?,?,?,?,?)");
            pst.setString(1, customer);
            pst.setString(2, phone);
            pst.setString(3, address);
            pst.setString(4, email);
            pst.setInt(5, visits);
            int result = pst.executeUpdate();

        }
        else{
            PreparedStatement pst2 = DBConnection.getConnection().prepareStatement("update customer set No_Of_Visits=?,Address=?,Email=? where Customer=? and Phone_No=?");
            pst2.setInt(1,visits);
            pst2.setString(2,address);
            pst2.setString(3,email);
            pst2.setString(4,customer);
            pst2.setString(5,phone);
            int result = pst2.executeUpdate();
        }
    }

    public static void addBill(JTable billtable, String billno, String date, Customer c, String doctor) throws SQLException {
        int a = billtable.getModel().getRowCount();
        int result=0;
        for(int i=0; i<a; i++){
            String medicine = (String) billtable.getValueAt(i,1);
            String category = (String) billtable.getValueAt(i,2);
            int quantity = Integer.parseInt((String)billtable.getValueAt(i,3));
            String batch="";
            PreparedStatement pst = DBConnection.getConnection().prepareStatement("select Batch_No,Total_Quantity from medicine where Name=? and Category=?");
            pst.setString(1,medicine);
            pst.setString(2,category);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                if(rs.getInt(2)>=quantity){
                    batch = rs.getString(1);
                    break;
                }
            }
            PreparedStatement pst1 = DBConnection.getConnection().prepareStatement("insert into bill(Bill_No,Medicine_Name,Customer,Doctor,Category,Date,Batch_No,Bill_Quantity,Rate,Discount,Amount,Email,Phone_No) values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pst1.setString(1,billno);
            pst1.setString(2,medicine);
            pst1.setString(3,c.getCname());
            pst1.setString(4,doctor);
            pst1.setString(5,category);
            pst1.setString(6,date);
            pst1.setString(7,batch);
            pst1.setInt(8,quantity);
            pst1.setDouble(9,Double.parseDouble((String)billtable.getValueAt(i,8)));
            pst1.setDouble(10,Double.parseDouble(((String) billtable.getValueAt(i, 9)).substring(0, 3)));
            pst1.setDouble(11,Double.parseDouble((String)billtable.getValueAt(i,11)));
            pst1.setString(12,c.getEmail());
            pst1.setString(13,c.getPhone());

            result = pst1.executeUpdate();
        }
        if(result==1){
            JOptionPane.showMessageDialog(null,"bill added successfully");
        }
    }

    public static billPojo getDetailsBasedOnMedicine(String name, String category, String quantity) throws SQLException {
        PreparedStatement pst = DBConnection.getConnection().prepareStatement("select Small_Description,MRP,Selling_Price,NO_Of_Leaves_Per_Pack,No_Of_Medicines_Per_Strip,tax,Total_Quantity,Batch_No from medicine where Name=? and Category=?");
        pst.setString(1,name);
        pst.setString(2,category);
        ResultSet rs = pst.executeQuery();
        billPojo b = new billPojo();
        b.setQuantity(0);
        while(rs.next()){
            if(rs.getInt(7)>=Integer.parseInt(quantity)){
                b.setDescription(rs.getString(1));
                b.setMrp(rs.getInt(2));
                b.setRate(rs.getInt(3));
                b.setNoOfLeavesPerPack(rs.getInt(4));
                b.setNoOfMedicinesPerStrip(rs.getInt(5));
                b.setTax(rs.getDouble(6));
                b.setQuantity(rs.getInt(7));
                b.setBatchno(rs.getString(8));
                break;
            }
        }
        if(b.getQuantity()==0){
            PreparedStatement pst1 = DBConnection.getConnection().prepareStatement("select max(Total_Quantity) from medicine where Name=? and Category=?");
            pst1.setString(1,name);
            pst1.setString(2,category);
            ResultSet r = pst1.executeQuery();
            if(r.next()){
                b.setQuantity(r.getInt(1));
            }
        }
        return b;
    }

    public static Customer checkCustomer(String customerName, String customerPhone) throws SQLException {
        PreparedStatement ps = DBConnection.getConnection().prepareStatement("select * from customer where Customer=? and Phone_No=?");
        ps.setString(1,customerName);
        ps.setString(2,customerPhone);
        ResultSet rs = ps.executeQuery();
        Customer c = new Customer();
        if(rs.next()){
            c.setEmail(rs.getString(4));
            c.setAddress(rs.getString(3));
        }
        return c;
    }

    public static void reduceMedicines(JTable billtable) throws SQLException {
        int rows = billtable.getRowCount();
        for(int i=0; i<rows; i++){
            PreparedStatement ps = DBConnection.getConnection().prepareStatement("select Batch_No,Total_Quantity from Medicine where Name=? and Category=?");
            ps.setString(1, (String) billtable.getValueAt(i,1));
            ps.setString(2, (String) billtable.getValueAt(i,2));
            int quantity = Integer.parseInt((String)billtable.getValueAt(i,3));
            int remainingQuantity = 0;
            String batch = "";
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getInt(2)>=quantity){
                    batch = rs.getString(1);
                    remainingQuantity = rs.getInt(2)-quantity;
                    break;
                }
            }
            PreparedStatement pst = DBConnection.getConnection().prepareStatement("update medicine set Total_Quantity=? where Batch_No=? and Name=? and Category=?");
            pst.setInt(1,remainingQuantity);
            pst.setString(2,batch);
            pst.setString(3, (String) billtable.getValueAt(i,1));
            pst.setString(4, (String) billtable.getValueAt(i,2));
            pst.executeUpdate();
        }
    }
}
