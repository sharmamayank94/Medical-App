package gui;

import dao.UserDao;
import pojo.UserPojo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class AddUser {
    public  JPanel mainpanel;
    private JTextField userName;
    private JTextField userId;
    private JPasswordField password;
    private JComboBox userType;
    private JTextField phone1;
    private JTextField phone2;
    private JTextField aadharNo;
    private JTextField qualification;
    private JTextField address;
    private JButton logoutButton;
    private JButton backButton;
    private JButton homeButton;
    private JButton ADDButton;
    private JTextField dateOfJoin;
    private JTextField dateOfLeaving;
    private JComboBox active;

    public AddUser() {
        active.addItem("Yes");
        active.addItem("No");
        /* adding value to the user type combo box */
        userType.addItem("Admin");
        userType.addItem("Worker");
        newUserId();
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

        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<UserPojo> userList = new ArrayList<>();
                    UserPojo user = new UserPojo();
                    user.setUserName(userName.getText());
                    user.setUserId(userId.getText());
                    user.setPassword(String.valueOf(password.getPassword()));
                    user.setUserType((String) userType.getSelectedItem());
                    if (active.getSelectedIndex() == 0) {
                        user.setActive("Yes");
                    } else {
                        user.setActive("No");
                    }
                    user.setPhone1(phone1.getText());
                    System.out.println(phone1.getText());
                    user.setPhone2(phone2.getText());
                    user.setAadharNo(aadharNo.getText());
                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                    java.util.Date d1 = sdf.parse(dateOfJoin.getText());
                    java.sql.Date sqlDate = new java.sql.Date(d1.getTime());
                    user.setDateofJoin(sqlDate);
                    if(dateOfLeaving.getText().isEmpty()== false){
                        java.util.Date d2 = sdf.parse(dateOfLeaving.getText());
                        sqlDate = new Date(d2.getTime());
                        if (d1.compareTo(d2) > 0) {
                            JOptionPane.showMessageDialog(null, "Date of leaving should be after date of joining");
                            return;
                        }else{
                            user.setDateofLeaving(sqlDate);
                        }
                    }

                    user.setQualification(qualification.getText());
                    user.setAddress(address.getText());

                    if
                    (userName.getText().isEmpty() || userId.getText().isEmpty() || userType.getSelectedIndex() == -1 || password.getPassword().equals("") || active.getSelectedIndex() == -1 || phone1.getText().isEmpty()  || aadharNo.getText().isEmpty() || dateOfJoin.getText().isEmpty()||dateOfJoin.getText().isEmpty() || qualification.getText().isEmpty() || address.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please fill all the necessary details");
                        return;
                    }
                    userList.add(user);
                    UserDao.addUser(userList);

                } catch (ParseException parse) {
                    parse.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Enter a valid date, use  date format: dd-MM-yyyy ");

                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Sql Exception " + sqle);
                }
            }
        });
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
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Admin");
                frame.setContentPane(new User().mainpanel);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
    public void newUserId(){
        try {
            active.setSelectedIndex(0);
            userType.setSelectedIndex(0);
            userId.setText(UserDao.getNewId());
        }catch(SQLException sql){
            JOptionPane.showMessageDialog(null,sql);
        }
    }

    public static void main(String[] args) {
            JFrame frame = new JFrame("Add User");
            frame.setContentPane(new AddUser().mainpanel );
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
    }
}
