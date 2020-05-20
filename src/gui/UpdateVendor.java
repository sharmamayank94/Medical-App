package gui;

import dao.Vendordao;
import pojo.vendorPojo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class UpdateVendor {
    private JPanel updateVendor;
    private JTextField vendorName;
    private JTextField vendorAddress;
    private JTextField vendorAgency;
    private JTextField vendorLicense;
    private JTextField vendorEmail;
    private JTextField vendorPhone;
    private JButton logoutButton;
    private JButton homeButton;
    private JButton backButton;
    private JButton yesButton;
    private JButton updateButton;
    private JLabel vendorimg;
    private String agency;
    private String name;
    private static JFrame frame;

    public UpdateVendor() {
        ImageIcon imageIcon = new ImageIcon("src/gui/Images/vendor.jpg"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(500, 460,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back
        vendorimg.setIcon(imageIcon);
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vendorName.setEditable(true);
                vendorAddress.setEditable(true);
                vendorEmail.setEditable(true);
                vendorAgency.setEditable(true);
                vendorLicense.setEditable(true);
                vendorPhone.setEditable(true);
                updateButton.setEnabled(true);
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                    if (vendorPhone.getText().length() != 10) {
                        JOptionPane.showMessageDialog(null, "Phone no. should be of 10 digits");
                        return;
                    }
                        vendorPojo v = new vendorPojo();
                        v.setName(vendorName.getText());
                        v.setAddress(vendorAddress.getText());
                        v.setAgency(vendorAgency.getText());
                        v.setEmail(vendorEmail.getText());
                        v.setPhone(vendorPhone.getText());
                        v.setLicensceNo(vendorLicense.getText());

                    try{
                        int result = Vendordao.updateVendor(name, agency, v);
                        if (result == 1) {
                            JOptionPane.showMessageDialog(null, "Vendor Information Updated Successfully");
                            return;
                        } else {
                            JOptionPane.showMessageDialog(null, "Cannot Update");
                            return;
                        }
                    } catch(SQLException ex){
                        JOptionPane.showMessageDialog(null, "Problem in Db. SQL Exception : " + ex);
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
        frame = new JFrame("Update Vendor");
        frame.setContentPane(new UpdateVendor().updateVendor);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }

    public void make(String name, String agency) {
        this.name = name;
        this.agency = agency;
        viewVendor();
        frame = new JFrame("Update Vendor Frame");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setContentPane(this.updateVendor);
        frame.setVisible(true);
    }

    private void viewVendor() {
        try {
            vendorPojo vp = Vendordao.getVendorDetails(name,agency);
            vendorName.setText(vp.getName());
            vendorEmail.setText(vp.getEmail());
            vendorAddress.setText(vp.getAddress());
            vendorAgency.setText(vp.getAgency());
            vendorLicense.setText(vp.getLicensceNo());
            vendorPhone.setText(String.valueOf(vp.getPhone()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
