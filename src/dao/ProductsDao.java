package dao;

import dbutil.DBConnection;
import pojo.ProductPojo;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ProductsDao {
    public static boolean addProduct(ProductPojo product) throws SQLException {
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("Insert into medicine(Name, Category, Total_Quantity, Selling_price" +
                ", Batch_No, Expiry, Vendor, Company, No_Of_Leaves_Per_Pack, Small_Description, No_Of_Medicines_Per_Strip, MRP, Cost_Price, Composition) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        ps.setString(1, product.getName());
        ps.setString(2, product.getCategory());
        ps.setInt(3, product.getQuantity());
        ps.setDouble(4, product.getSellingPrice());
        ps.setString(5, product.getBatchNo());
        ps.setDate(6, new java.sql.Date(product.getExpiry().getTimeInMillis()));
        ps.setString(7, product.getVendor());
        ps.setString(8, product.getCompany());
        ps.setInt(9, product.getNoOfLeavesPerPack());
        ps.setString(10, product.getSmallDescription());
        ps.setInt(11, product.getNoOfMedicinesPerLeaf());
        ps.setDouble(12, product.getMRP());
        ps.setDouble(13, product.getCostPrice());
        ps.setString(14, product.getComposition());

        int result = ps.executeUpdate();
        return result == 1;
    }

    public static HashMap getMedicineQuantity(ArrayList<String> medicines) throws SQLException{
        HashMap<String, Integer> medQuantity = new HashMap<>();
        Statement s = DBConnection.getConnection().createStatement();
        String medicinesString = "";

        for(int i =0; i<medicines.size(); i++){
            medicinesString+="'"+medicines.get(i)+"',";
        }
        medicinesString=medicinesString.substring(0, medicinesString.length()-1);
        String query = "select Name, sum(Total_Quantity) from medicine where name in ("+medicinesString+") group by Name";

        ResultSet rs = s.executeQuery(query);
        while(rs.next()){
            medQuantity.put(rs.getString(1), rs.getInt(2));
        }

        for(String medicine: medicines){
            if(!medQuantity.containsKey(medicine)){
                medQuantity.put(medicine, 0);
            }
        }

        return medQuantity;
    }
}
