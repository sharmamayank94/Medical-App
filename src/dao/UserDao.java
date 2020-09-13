package dao;

import dbutil.DBConnection;
import pojo.UserPojo;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class UserDao {
//    public static String getNewId() throws SQLException {
//        Statement st =DBConnection.getConnection().createStatement();
//        String qry = "select max(empid) from employee";
//        ResultSet rs = st.executeQuery(qry);
//        int id =1;
//        if(rs.next()){
//            String empid = rs.getString(1);
//            int eno = Integer.parseInt(empid.substring(1));
//            System.out.println(empid.substring(1));
//            id = id + eno;
//        }
//
//        String sr = "E" + id;
//        return sr;
//    }
    public static String getNewId() throws SQLException{
        Statement st = DBConnection.getConnection().createStatement();
        String qry = "select max(User_Id) from users";
        ResultSet rs = st.executeQuery(qry);
        int id =1;
        if(rs.next()){
            String empid = rs.getString(1);
            int eno = Integer.parseInt(empid);
            System.out.println(empid);
            id = id + eno;
        }
        return String.valueOf(id);

    }
    public static void addUser(ArrayList<UserPojo> user) throws SQLException{
        PreparedStatement pst = DBConnection.getConnection().prepareStatement
                (" Insert into users(User_Name,User_Id,Password,User_Type,Active,Phone1,Phone2,Aadhar_No,Join_Date,Till,Qualification,Address) values(?,?,?,?,?,?,?,?,?,?,?,?)");
        for(UserPojo userPojo: user){
            pst.setString(1,userPojo.getUserName());
            pst.setString(2,userPojo.getUserId());
            pst.setString(3,userPojo.getPassword());
            pst.setString(4,userPojo.getUserType());
            pst.setString(5,userPojo.getActive());
            pst.setString(6,userPojo.getPhone1());
            pst.setString(7,userPojo.getPhone2());
            pst.setString(8,userPojo.getAadharNo());
            pst.setString(9, String.valueOf(userPojo.getDateofJoin()));
            System.out.println(userPojo.getDateofLeaving()
            );
//            if( String.valueOf(userPojo.getDateofLeaving())== "null"){
//                pst.setString(10, "1000-01-01");
//
//            }else{
//                pst.setString(10, String.valueOf(userPojo.getDateofLeaving()));
//            }
            pst.setString(10, String.valueOf(userPojo.getDateofLeaving()));
            pst.setString(10, String.valueOf(userPojo.getDateofLeaving()));
            pst.setString(11,userPojo.getQualification());
            pst.setString(12,userPojo.getAddress());
        }
        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "User added succesfully!!");


    }

    public static void updateUser(ArrayList<UserPojo> user) throws SQLException{
        PreparedStatement pst = DBConnection.getConnection().prepareStatement
                ("Update users set User_Name =?, User_Id = ?, Password =?, User_Type= ?, Active = ?, Phone1 = ?, Phone2 = ?,Aadhar_No = ?, Join_Date = ?, Till = ?, Qualification = ?, Address= ? where User_Id = ?");
        for(UserPojo userPojo: user) {
            System.out.println("dao");
            pst.setString(1, userPojo.getUserName());
            pst.setString(2,userPojo.getUserId());
            pst.setString(3,userPojo.getPassword());
            pst.setString(4,userPojo.getUserType());
            pst.setString(5,userPojo.getActive());
            pst.setString(6,userPojo.getPhone1());
            pst.setString(7,userPojo.getPhone2());
            pst.setString(8,userPojo.getAadharNo());
            pst.setString(9, String.valueOf(userPojo.getDateofJoin()));
            pst.setString(10, String.valueOf(userPojo.getDateofLeaving()));
            pst.setString(11,userPojo.getQualification());
            pst.setString(12,userPojo.getAddress());
            pst.setString(13,userPojo.getUserId());
        }

        int rowsEffected = pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "User updated succesfully!!");
//        if(rowsEffected == 0){
//            JOptionPane.showMessageDialog(null, "No user found with the given user id");
//        }
//        else{
//            JOptionPane.showMessageDialog(null, "User updated succesfully!!");
//            }


    }
    //delete user from the database using userId
    public static void deleteUser(String userId) throws SQLException{

        Statement st = DBConnection.getConnection().createStatement();
        String qry = ("Delete from users where User_Id=" + userId);
        st.executeUpdate(qry);
        JOptionPane.showMessageDialog(null, "User with id " + userId + " deleted succesfully!");




    }

    //getUserDetails method for UserDetails class
    public static  ArrayList<UserPojo> getUserDetails(String id) throws SQLException{

        ArrayList<UserPojo> userList = new ArrayList<>();
        Statement st;
        st = DBConnection.getConnection().createStatement();

        ResultSet rs = st.executeQuery("Select * from Users where User_Id ="+ id);
        System.out.println("statement executed");
            while(rs.next()) {
                System.out.println("rs has elements");



                    System.out.println("Inside rs.next() of user details");
                    UserPojo u = new UserPojo();
                    //u.setSNo(rs.getInt(1));
                    System.out.println("has taken one input from the frst line  ");
                    u.setUserName(rs.getString(2));
                    u.setUserId(rs.getString(3));
                    u.setPassword(rs.getString(4));
                    u.setUserType(rs.getString(5));
                    u.setActive(rs.getString(6));
                    u.setPhone1(rs.getString(7));
                    u.setPhone2(rs.getString(8));
                    u.setAadharNo(rs.getString(9));

                    u.setDateofJoin(rs.getDate(10));

                    u.setDateofLeaving(rs.getDate(11));

                    u.setQualification(rs.getString(12));
                    u.setAddress(rs.getString(13));
                    userList.add(u);
                System.out.println("has finished execution once");


            }
            return userList;
    }



    // ViewUsers method for displaying user in user table
    public static ArrayList<UserPojo> viewUsers() throws SQLException  {
        ArrayList<UserPojo> users = new ArrayList<>();

        Statement st= DBConnection.getConnection().createStatement();


        String qry = "Select S_No, User_Name, User_Id, User_Type, Active from Users";
        ResultSet rs = st.executeQuery(qry);


        while(rs.next()) {

                UserPojo u = new UserPojo();
                //u.setSNo(rs.getInt(1));
                u.setUserName(rs.getString(2));
                u.setUserId(rs.getString(3));
                u.setUserType(rs.getString(4));
                u.setActive(rs.getString(5));
                users.add(u);


        }


        return users;
    }


}
