package pojo;

import java.util.Calendar;

public class OrderPojo {
    String orderId;
    String name;
    int quantity;
    String company;
    String vendor;
    String status;
    String batchNo;
    Calendar dateOfOrder;
    Calendar dateOfOrderCompletion;

    @Override
    public String toString() {
        return "OrderPojo{" +
                "orderId='" + orderId + '\'' +
                ", name='" + name + '\'' +
                ", quantity=" + quantity +
                ", company='" + company + '\'' +
                ", vendor='" + vendor + '\'' +
                ", status='" + status + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", dateOfOrder=" + dateOfOrder +
                ", dateOfOrderCompletion=" + dateOfOrderCompletion +
                '}';
    }

    public OrderPojo(String orderId, String name, int quantity, String company, String vendor, String status,
                     Calendar dateOfOrder, Calendar dateOfOrderCompletion) {
        this.orderId = orderId;
        this.name = name;
        this.quantity = quantity;
        this.company = company;
        this.vendor = vendor;
        this.status = status;
        this.dateOfOrder = dateOfOrder;
        this.dateOfOrderCompletion  = dateOfOrderCompletion;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Calendar getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Calendar dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public Calendar getDateOfOrderCompletion() {
        return dateOfOrderCompletion;
    }

    public void setDateOfOrderCompletion(Calendar dateOfOrderCompletion) {
        this.dateOfOrderCompletion = dateOfOrderCompletion;
    }
}

