package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Users {

    private JTable  users;
    private JScrollPane usersSP;
    private JPanel mainpanel;
    private JButton LOGOUTButton;
    private JButton HOMEButton;
    private JButton BACKButton;
    private JPanel label;
    private JButton ADDUSERButton;

    private void createUIComponents() {
        users = createTable();
        usersSP = new JScrollPane(users);
    }

    public static JTable createTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("S_No");
        model.addColumn("User Name");
        model.addColumn("User Id");
        model.addColumn("User Type");
        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        return table;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Users");
        frame.setContentPane(new Users().mainpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
