package pojo;

import com.mysql.cj.jdbc.ha.BestResponseTimeBalanceStrategy;

public class vendorPojo {
    String name;
    String address;
    String phone;
    String email;
    String agency;
    String LicensceNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getLicensceNo() {
        return LicensceNo;
    }

    public void setLicensceNo(String licensceNo) {
        LicensceNo = licensceNo;
    }
}
