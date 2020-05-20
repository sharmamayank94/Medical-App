package gui;

import dao.UserDao;
import pojo.UserPojo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class UserDetails {
    public JPanel mainpanel;
    private JTextField username;
    private JTextField userid;
    private JPasswordField password;
    private JTextField phone1;
    private JTextField phone2;
    private JTextField aadharno;
    private JTextField qualification;
    private JButton updateUserButton;
    private JButton logoutButton;
    private JButton backButton;
    private JButton homeButton;
    private JButton deleteUserButton;
    private JTextField dateofjoin;
    private JTextField dateofleaving;
    private JTextField active;
    private JTextField usertype;
    private JTextField address;

    UserDetails(){

        System.out.println("Inside the constructor of User Details");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Admin");
                frame.setContentPane(new User().mainpanel);
                frame.pack();
                frame.setVisible(true);
            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Admin");
                frame.setContentPane(new User().mainpanel);
                frame.pack();
                frame.setVisible(true);

            }
        });
        updateUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                 String userID = userid.getText();
                ArrayList<UserPojo> userPojo = new ArrayList<>();
                UserPojo user = new UserPojo();
                user.setUserName(username.getText());
                user.setUserId(userid.getText());
                user.setPassword(String.valueOf(password.getPassword()));
                user.setUserType(String.valueOf(usertype.getText()));
                user.setActive(active.getText());
                user.setPhone1(phone1.getText());
                user.setPhone2(phone2.getText());
                user.setAadharNo(aadharno.getText());
                System.out.println("Exception occured before this line");
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                java.util.Date d1 = null;
                d1 = sdf.parse(dateofjoin.getText());
                Date sqlDate = new Date(d1.getTime());
                user.setDateofJoin(sqlDate);
                if (dateofleaving.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Date of Leaving cannot be left empty");
                    return;
                }
                java.util.Date d2 = sdf.parse(dateofleaving.getText());
                if (d1.compareTo(d2) > 0) {
                    JOptionPane.showMessageDialog(null, "Date of leaving should be after date of joining");
                    return;
                }
                sqlDate = new Date(d2.getTime());
                user.setDateofLeaving(sqlDate);
                user.setQualification(qualification.getText());
                user.setAddress(address.getText());
                userPojo.add(user);
                JFrame frame = new JFrame("Admin");
                frame.setContentPane(new UpdateUser(userID).mainpanel);
                frame.pack();
                frame.setVisible(true);


                }catch(ParseException ex){
                    ex.printStackTrace();

                }
            }
        });


        deleteUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = userid.getText();
                    UserDao.deleteUser(id);
                }catch(SQLException sql){
                    System.out.println(sql);

                }
            }
        });
    }
//    public void newEmpId(){
//        try {
//            txtEmpId.setText("");
//            txtEmpName.setText("");
//            jcbJob.setSelectedItem(0);
//            txtEmpSal.setText("");
//            txtEmpId.setText(EmpDao.getNewId());
//
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(null,"sql exception");
//
//        }
//
//    }

    public void newUserId(){
        try {
            dateofleaving.setText("dd-MM-yy");
            dateofleaving.setForeground(Color.GRAY);
            userid.setText(UserDao.getNewId());
        }catch(SQLException sql){
            JOptionPane.showMessageDialog(null,"sql exception");
        }

    }
    /* placeholder for text fields */
//        dateOfJoin = new JTextField("Search");
//        dateOfJoin.setForeground(Color.GREEN);
//        dateOfJoin.addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent e) {
//                if (dateOfJoin.getText().equals("Search")) {
//                    dateOfJoin.setText("");
//                    dateOfJoin.setForeground(Color.BLACK);
//                }
//            }
//
//            @Override
//            public void focusLost(FocusEvent e) {
//                if (dateOfJoin.getText().isEmpty()) {
//                    dateOfJoin.setForeground(Color.GRAY);
//                    dateOfJoin.setText("Search");
//                  }
//                }
//        });


    public void make(String userid){
        System.out.println("Inside make of User Details");
        displayUserDetails(userid);
        JFrame frame = new JFrame("UserDetails");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(this.mainpanel);
        frame.setSize(1366,768);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


    public void displayUserDetails(String userId){
        try {
            ArrayList<UserPojo> user = UserDao.getUserDetails(userId);
            for(UserPojo userPojo : user){
                System.out.println();
                System.out.println("Inside the for loop of UserDetails");
                username.setText(userPojo.getUserName());
                userid.setText(userPojo.getUserId());
                password.setText(userPojo.getPassword());
                usertype.setText(userPojo.getUserType());
                active.setText(userPojo.getActive());
                phone1.setText(userPojo.getPhone1());
                phone2.setText(userPojo.getPhone2());
                aadharno.setText(userPojo.getAadharNo());
                dateofjoin.setText(String.valueOf(userPojo.getDateofJoin()));
                dateofleaving.setText(String.valueOf(userPojo.getDateofJoin()));
                qualification.setText(userPojo.getQualification());
                address.setText(userPojo.getAddress());
            }

        }catch(SQLException sql){
            JOptionPane.showMessageDialog(null,"Sql Exception in UserDetails class " + sql);
        }

    }

    public static void main(String[] args)
    {

        JFrame frame = new JFrame("User Details");
        frame.setContentPane(new UserDetails().mainpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


}
