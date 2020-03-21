package gui;

import javax.swing.*;

public class LoginPanel {
    private JPanel loginpanel;
    private JTextField textField1;
    private JTextField textField2;
    private JRadioButton adminRadioButton;
    private JRadioButton userRadioButton;
    private JButton loginButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("LoginPanel");
        frame.setContentPane(new LoginPanel().loginpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
