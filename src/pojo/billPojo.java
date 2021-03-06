package pojo;

import java.sql.Date;

public class billPojo {
    private String Bill_No;
    private String Doctor;
    private Date sellDate;
    private String medicine;
    private String category;
    private String batchno;
    private Date expiry;
    private String description;
    private double mrp;
    private double rate;
    private int noOfMedicinesPerStrip;
    private int noOfLeavesPerPack;
    private double tax;
    private int quantity;

    public String getBill_No() {
        return Bill_No;
    }

    public void setBill_No(String bill_No) {
        Bill_No = bill_No;
    }

    public String getDoctor() {
        return Doctor;
    }

    public void setDoctor(String doctor) {
        Doctor = doctor;
    }

    public Date getSellDate() {
        return sellDate;
    }

    public void setSellDate(Date sellDate) {
        this.sellDate = sellDate;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getBatchno() {
        return batchno;
    }

    public void setBatchno(String batchno) {
        this.batchno = batchno;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMrp() {
        return mrp;
    }

    public void setMrp(double mrp) {
        this.mrp = mrp;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getNoOfMedicinesPerStrip() {
        return noOfMedicinesPerStrip;
    }

    public void setNoOfMedicinesPerStrip(int noOfMedicinesPerStrip) {
        this.noOfMedicinesPerStrip = noOfMedicinesPerStrip;
    }

    public int getNoOfLeavesPerPack() {
        return noOfLeavesPerPack;
    }

    public void setNoOfLeavesPerPack(int noOfLeavesPerPack) {
        this.noOfLeavesPerPack = noOfLeavesPerPack;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
