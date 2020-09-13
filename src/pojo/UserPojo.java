 package pojo;

import java.sql.Date;

public class UserPojo {
    String UserName;
    String UserId;
    String Password;
    String UserType;
    String Active;
    String Phone1;
    String Phone2;
    String AadharNo;
    java.util.Date DateofJoin;
    java.util.Date DateofLeaving;
    String Qualification;
    String Address;

    public String getPhone1() {
        return Phone1;
    }

    public void setPhone1(String phone1) {
        Phone1 = phone1;
    }

    public String getPhone2() {
        return Phone2;
    }

    public void setPhone2(String phone2) {
        Phone2 = phone2;
    }

    public String getAadharNo() {
        return AadharNo;
    }

    public void setAadharNo(String aadharNo) {
        AadharNo = aadharNo;
    }

    public java.util.Date getDateofJoin() {
        return DateofJoin;
    }

    public void setDateofJoin(Date dateofJoin) {
        DateofJoin = dateofJoin;
    }

    public java.util.Date getDateofLeaving() {
        return DateofLeaving;
    }

    public void setDateofLeaving(Date dateofLeaving) {
        DateofLeaving = dateofLeaving;
    }

    public String getQualification() {
        return Qualification;
    }

    public void setQualification(String qualification) {
        Qualification = qualification;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

//    public int getSNo() {
//        return SNo;
//    }

//    public void setSNo(int SNo) {
//        this.SNo = SNo;
//    }
    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    public String getActive() {
        return Active;
    }

    public void setActive(String active) {
        Active = active;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

}
