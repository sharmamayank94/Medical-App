package gui;

import dao.UserDao;
import pojo.UserPojo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class UpdateUser {
    public JPanel mainpanel;
    private JButton logoutButton;
    private JButton backButton;
    private JButton homeButton;
    private JTextField userName;
    private JTextField userId;
    private JPasswordField password;
    private JComboBox userType;
    private JTextField phone1;
    private JTextField phone2;
    private JTextField aadharNo;
    private JTextField qualification;
    private JTextField active;
    private JTextField dateOfJoin;
    private JTextField dateOfLeaving;
    private JTextField address;
    private JButton updateButton;

    public UpdateUser() {
        userType.addItem("Admin");
        userType.addItem("Worker");

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUserDetails();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Admin");
                frame.setContentPane(new UserDetails().mainpanel);
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
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Admin");

                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(new User().mainpanel);
                frame.pack();

                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }

    public UpdateUser(String userID) {
        userType.addItem("Admin");
        userType.addItem("Worker");

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateUserDetails();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Admin");
                frame.setContentPane(new UserDetails().mainpanel);
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
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Admin");

                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(new User().mainpanel);
                frame.pack();

                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        setFields(userID);

    }

    public  void updateUserDetails(){
        try {
            ArrayList<UserPojo> userPojo = new ArrayList<>();
            UserPojo user = new UserPojo();
            user.setUserName(userName.getText());
            user.setUserId(userId.getText());
            user.setPassword(String.valueOf(password.getPassword()));
            user.setUserType(String.valueOf(userType.getSelectedItem()));
            user.setActive(active.getText());
            user.setPhone1(phone1.getText());
            user.setPhone2(phone2.getText());
            user.setAadharNo(aadharNo.getText());
            System.out.println("Exception occured before this line");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            if(dateOfJoin.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Date of join cannot be left empty");
                return;

            }
            java.util.Date d1 = sdf.parse(dateOfJoin.getText());
            java.sql.Date sqlDate = new java.sql.Date(d1.getTime());
            user.setDateofJoin(sqlDate);
            if(dateOfLeaving.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Date of Leaving cannot be left empty");
                return;

            }
            java.util.Date d2 = sdf.parse(dateOfLeaving.getText());
            if(d1.compareTo(d2) > 0) {
                JOptionPane.showMessageDialog(null, "Date of leaving should be after date of joining");
                return;
            }
            sqlDate = new java.sql.Date(d2.getTime());
            user.setDateofLeaving(sqlDate);
            user.setQualification(qualification.getText());
            user.setAddress(address.getText());
            if
            (userName.getText().isEmpty()|| userId.getText().isEmpty() || userType.getSelectedIndex()==-1 || password.getPassword().equals("")|| active.getText().isEmpty()|| phone1.getText().isEmpty()|| phone2.getText().isEmpty()||aadharNo.getText().isEmpty()|| dateOfJoin.getText().isEmpty()|| dateOfLeaving.getText().isEmpty()|| qualification.getText().isEmpty()|| address.getText().isEmpty() ){
                JOptionPane.showMessageDialog(null, "Please fill all the necessary details");
                return;
            }
            userPojo.add(user);
            UserDao.updateUser(userPojo);
        }catch(SQLException sql){
            sql.printStackTrace();
            JOptionPane.showMessageDialog(null,sql);
        } catch (ParseException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e );
        }
    }

    public static void main(String[] args)
    {

        JFrame frame = new JFrame("Update Users");
        frame.setContentPane(new UpdateUser().mainpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }


    public void setFields( String userID) {
        try {
            ArrayList<UserPojo> user = UserDao.getUserDetails(userID);
            for(UserPojo userPojo : user){
                System.out.println();
                System.out.println("Inside the for loop of UserDetails");
                userName.setText(userPojo.getUserName());
                userId.setText(userPojo.getUserId());
                password.setText(userPojo.getPassword());
                if(userPojo.getUserType() == "ADMIN"){
                    userType.setSelectedIndex(0);
                }else{
                    userType.setSelectedIndex(1);
                }
                active.setText(userPojo.getActive());
                phone1.setText(userPojo.getPhone1());
                phone2.setText(userPojo.getPhone2());
                aadharNo.setText(userPojo.getAadharNo());
                dateOfJoin.setText(String.valueOf(userPojo.getDateofJoin()));
                dateOfLeaving.setText(String.valueOf(userPojo.getDateofJoin()));
                qualification.setText(userPojo.getQualification());
                address.setText(userPojo.getAddress());
            }

        }catch(SQLException sql){
            JOptionPane.showMessageDialog(null,"Sql Exception in UserDetails class " + sql);
        }

    }
}
