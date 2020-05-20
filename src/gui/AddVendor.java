package gui;

import dao.Vendordao;
import pojo.vendorPojo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddVendor {
    private JPanel MainPanel;
    private JTextField vendorName;
    private JTextField vendorPhone;
    private JTextField vendorAddress;
    private JTextField vendorAgency;
    private JTextField vendorEmail;
    private JTextField vendorLicense;
    private JButton addVendorButton;
    private JButton homeButton;
    private JButton backButton;
    private JButton logoutButton;
    private JLabel vendorimg;
    private static JFrame frame;

    public AddVendor() {
        ImageIcon imageIcon = new ImageIcon("src/gui/Images/vendor.jpg"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(500, 460,  Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back
        vendorimg.setIcon(imageIcon);
        addVendorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validateInputs()){
                    vendorPojo vp = new vendorPojo();
                    vp.setName(vendorName.getText());
                    vp.setEmail(vendorEmail.getText());
                    vp.setAgency(vendorAgency.getText());
                    vp.setAddress(vendorAddress.getText());
                    vp.setPhone(vendorPhone.getText());
                    vp.setLicensceNo(vendorLicense.getText());
                    try {
                        int result = Vendordao.addVendor(vp);
                        if(result==1){
                            JOptionPane.showMessageDialog(null,"Vendor added successfully");
                            vendorName.setText("");
                            vendorAddress.setText("");
                            vendorAgency.setText("");
                            vendorLicense.setText("");
                            vendorPhone.setText("");
                            vendorEmail.setText("");
                            return;
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"cannot add vendor");
                            return;
                        }
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null,"SQL Exception");
                        return;
                    }
                }
            }

            private boolean validateInputs() {
                try{
                    Double phone = Double.valueOf(vendorPhone.getText());
                }
                catch(NumberFormatException n){
                    JOptionPane.showMessageDialog(null,"Only 10 digits number should be entered in phone no field");
                    return false;
                }
                if(vendorPhone.getText().length()!=10){
                    JOptionPane.showMessageDialog(null,"Phone no should be of 10 digits");
                    return false;
                }
                if(vendorName.getText().isEmpty()||vendorEmail.getText().isEmpty()||vendorPhone.getText().isEmpty()||vendorLicense.getText().isEmpty()||vendorAgency.getText().isEmpty()||vendorAddress.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Please fill all the fields");
                    return false;
                }
                else {
                    return true;
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VendorsList vl = new VendorsList();
                vl.make();
                frame.dispose();
            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminPanel ap = new AdminPanel();
                //ap.make();
                frame.dispose();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPanel lp = new LoginPanel();
                //lp.make();
                frame.dispose();
            }
        });
    }


    public static void main(String[] args) {
        frame = new JFrame("VendorDetail");
        frame.setContentPane(new AddVendor().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

    public void make() {
        frame = new JFrame("Add Vendor Frame");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setContentPane(this.MainPanel);
        frame.setVisible(true);
    }
}
