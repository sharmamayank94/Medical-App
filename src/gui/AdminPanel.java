package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPanel {
    public JPanel adminpanel;
    private JLabel JlblAdminImage;
    private JPanel jpnlleft;
    private JPanel jpnlRight;
    private JButton jbtnAddProducts;
    private JButton jbtnGenerateBill;
    private JButton myOrdersButton;
    private JButton viewStockButton;
    private JButton manageUsersButton;
    private JButton customerDetailsButton;
    private JButton vendorInformationButton;
    private JButton addProductsButton;
    private JButton logoutButton;
    private static JFrame frame;

    AdminPanel(){
        ImageIcon icon = new ImageIcon("src/gui/Images/AdminImage.png");
        Image temp = icon.getImage();
        Image img = temp.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        icon = new ImageIcon(img);
        JlblAdminImage.setIcon(icon);

        ImageIcon iconaddpro = new ImageIcon("src/gui/Images/AddProduct.png");
        temp = iconaddpro.getImage();
        img = temp.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        addProductsButton.setIcon(new ImageIcon(img));

        icon = new ImageIcon("src/gui/Images/GenerateBill.png");
        temp = icon.getImage();
        img = temp.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        jbtnGenerateBill.setIcon(new ImageIcon(img));

        icon = new ImageIcon("src/gui/Images/MyOrders.png");
        temp = icon.getImage();
        img = temp.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        myOrdersButton.setIcon(new ImageIcon(img));

        icon = new ImageIcon("src/gui/Images/ManageUsers.png");
        temp = icon.getImage();
        img = temp.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        manageUsersButton.setIcon(new ImageIcon(img));

        icon = new ImageIcon("src/gui/Images/CustomerDetails.png");
        temp = icon.getImage();
        img = temp.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        customerDetailsButton.setIcon(new ImageIcon(img));

        icon = new ImageIcon("src/gui/Images/ViewStock.png");
        temp = icon.getImage();
        img = temp.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        viewStockButton.setIcon(new ImageIcon(img));

        icon = new ImageIcon("src/gui/Images/VendorInformation.png");
        temp = icon.getImage();
        img = temp.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        vendorInformationButton.setIcon(new ImageIcon(img));
        addProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
               AddMedicines am = new AddMedicines();
               am.make();
               frame.dispose();
            }
        });
        myOrdersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                MyOrders mo = new MyOrders();
                mo.make();
                frame.dispose();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                LoginPanel lp = new LoginPanel();
                lp.make();
                frame.dispose();
            }
        });
        vendorInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VendorsList vl = new VendorsList();
                vl.make();
                frame.dispose();
            }
        });
        customerDetailsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerList cl = new CustomerList();
                cl.make();
                frame.dispose();
            }
        });
        viewStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        jbtnGenerateBill.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e)
           {
               Bill b = new Bill();
               b.make("xyz");

           }
        });
        manageUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
    }

    public static  void main(String[] args){
        frame = new JFrame("Admin Panel");
        frame.setContentPane(new AdminPanel().adminpanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1200, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void make() {
        frame = new JFrame("Admin Panel");
        frame.setContentPane(this.adminpanel);
        frame.setVisible(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
