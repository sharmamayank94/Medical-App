package pojo;

import java.util.Date;

public class ViewStockPojo {
    private String S_No;
    private String Name;
    private String Category;
    private int Total_Quantity;
    private int Selling_price;
    private String Batch_NO;
    private Date Expiry;
    private String Vendor;
    private String Company;
    private int No_Of_Leaves_Per_Pack;
    private int No_Of_Medicines_Per_Strip;
    private int MRP;
    private int Cost_Price;
    private String Small_Description;
    private int X_Factor;

    public Object[] getCompanyBasedStockImpl()
    {
        return new Object[]{S_No,
                Name,
                Category,
                Total_Quantity,
                Selling_price,
                Batch_NO,
                Expiry,
                Vendor,
                Company,
                No_Of_Leaves_Per_Pack,
                No_Of_Medicines_Per_Strip,
                MRP,
                Cost_Price,
                Small_Description,
                X_Factor};
    }

    public ViewStockPojo(String s_No, String name, String category, int total_Quantity, int selling_price, String batch_NO, Date expiry, String vendor, String company, int no_Of_Leaves_Per_Pack, int no_Of_Medicines_Per_Strip, int MRP, int cost_Price, String small_Description, int x_Factor) {
        S_No = s_No;
        Name = name;
        Category = category;
        Total_Quantity = total_Quantity;
        Selling_price = selling_price;
        Batch_NO = batch_NO;
        Expiry = expiry;
        Vendor = vendor;
        Company = company;
        No_Of_Leaves_Per_Pack = no_Of_Leaves_Per_Pack;
        No_Of_Medicines_Per_Strip = no_Of_Medicines_Per_Strip;
        this.MRP = MRP;
        Cost_Price = cost_Price;
        Small_Description = small_Description;
        X_Factor = x_Factor;
    }

    public String getS_No() {
        return S_No;
    }

    public void setS_No(String s_No) {
        S_No = s_No;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public int getTotal_Quantity() {
        return Total_Quantity;
    }

    public void setTotal_Quantity(int total_Quantity) {
        Total_Quantity = total_Quantity;
    }

    public int getSelling_price() {
        return Selling_price;
    }

    public void setSelling_price(int selling_price) {
        Selling_price = selling_price;
    }

    public String getBatch_NO() {
        return Batch_NO;
    }

    public void setBatch_NO(String batch_NO) {
        Batch_NO = batch_NO;
    }

    public Date getExpiry() {
        return Expiry;
    }

    public void setExpiry(Date expiry) {
        Expiry = expiry;
    }

    public String getVendor() {
        return Vendor;
    }

    public void setVendor(String vendor) {
        Vendor = vendor;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
    }

    public int getNo_Of_Leaves_Per_Pack() {
        return No_Of_Leaves_Per_Pack;
    }

    public void setNo_Of_Leaves_Per_Pack(int no_Of_Leaves_Per_Pack) {
        No_Of_Leaves_Per_Pack = no_Of_Leaves_Per_Pack;
    }

    public int getNo_Of_Medicines_Per_Strip() {
        return No_Of_Medicines_Per_Strip;
    }

    public void setNo_Of_Medicines_Per_Strip(int no_Of_Medicines_Per_Strip) {
        No_Of_Medicines_Per_Strip = no_Of_Medicines_Per_Strip;
    }

    public int getMRP() {
        return MRP;
    }

    public void setMRP(int MRP) {
        this.MRP = MRP;
    }

    public int getCost_Price() {
        return Cost_Price;
    }

    public void setCost_Price(int cost_Price) {
        Cost_Price = cost_Price;
    }

    public String getSmall_Description() {
        return Small_Description;
    }

    public void setSmall_Description(String small_Description) {
        Small_Description = small_Description;
    }

    public int getX_Factor() {
        return X_Factor;
    }

    public void setX_Factor(int x_Factor) {
        X_Factor = x_Factor;
    }
}
