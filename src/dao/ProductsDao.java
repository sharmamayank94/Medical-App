package dao;

import dbutil.DBConnection;
import pojo.ProductPojo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
