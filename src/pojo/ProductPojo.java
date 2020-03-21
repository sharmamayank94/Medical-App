package pojo;

import java.util.Calendar;

public class ProductPojo {
    String name;
    String category;
    int quantity;
    double sellingPrice;
    double costPrice;
    double MRP;
    String batchNo;
    Calendar expiry;
    String vendor;
    String company;
    int noOfLeavesPerPack;
    int noOfMedicinesPerLeaf;
    String composition;
    String smallDescription;
    double xFactor;

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public double getMRP() {
        return MRP;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public Calendar getExpiry() {
        return expiry;
    }

    public String getVendor() {
        return vendor;
    }

    public String getCompany() {
        return company;
    }

    public int getNoOfLeavesPerPack() {
        return noOfLeavesPerPack;
    }

    public int getNoOfMedicinesPerLeaf() {
        return noOfMedicinesPerLeaf;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public String getComposition() {
        return composition;
    }

    public String getSmallDescription() {
        return smallDescription;
    }

    public ProductPojo(String name, String category, int quantity, double sellingPrice, double costPrice, double MRP,
                       String batchNo, Calendar expiry, String vendor, String company, int noOfLeavesPerPack,
                       int noOfMedicinesPerLeaf, String smallDescription) {
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.sellingPrice = sellingPrice;
        this.costPrice = costPrice;
        this.MRP = MRP;
        this.batchNo = batchNo;
        this.expiry = expiry;
        this.vendor = vendor;
        this.company = company;
        this.noOfLeavesPerPack = noOfLeavesPerPack;
        this.noOfMedicinesPerLeaf = noOfMedicinesPerLeaf;
        this.smallDescription = smallDescription;
        this.composition = null;
    }
}
