package gui;

import javax.swing.*;

public class UpdateVendor {
    private JPanel MainPanel;
    private JButton updateButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Update Vendor");
        frame.setContentPane(new UpdateVendor().MainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }
}
