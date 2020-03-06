package gui;

import javax.swing.*;

public class Vendor_Detail {
    private JPanel MainPanel;
    private JButton button1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JLabel VendorName;


    public static void main(String[] args) {
        JFrame frame = new JFrame("VendorDetail");
        frame.setContentPane(new Vendor_Detail().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }
}
