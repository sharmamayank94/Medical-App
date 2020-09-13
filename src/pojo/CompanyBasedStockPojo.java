package pojo;

import java.util.Date;

public class CompanyBasedStockPojo {
    private int sNO;
    private String company;
    private String name;
    private Integer totalQuantity;
    private Date expiry;
    private String batchNo;
    private String category;
    private String vendor;

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public CompanyBasedStockPojo(int sNO, String company, String name, Integer totalQuantity, Date expiry, String batchNo, String category, String vendor) {
        this.sNO = sNO;
        this.company = company;
        this.name = name;
        this.totalQuantity = totalQuantity;
        this.expiry = expiry;
        this.batchNo = batchNo;
        this.category = category;
        this.vendor = vendor;
    }

    public Object[] getCompanyBasedStockImpl() {
        return new Object[]{sNO, company, name, totalQuantity, expiry, batchNo, category, vendor};
    }

    public int getsNO() {
        return sNO;
    }

    public void setsNO(int sNO) {
        this.sNO = sNO;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Date getExpiry() {
        return expiry;
    }

    public void setExpiry(Date expiry) {
        this.expiry = expiry;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}