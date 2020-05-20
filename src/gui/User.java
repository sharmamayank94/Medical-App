package gui;

import dao.UserDao;

import pojo.UserPojo;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;



public class User {
    public JPanel mainpanel;
    private JScrollPane userssp;
    private  JTable table;
    private JButton logoutButton;
    private JButton backButton;
    private JButton homeButton;
    private JButton addUserButton;

    public User() {
        displayUsers();
        //code for listener of the cells of table
        ListSelectionModel model = table.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if(!model.isSelectionEmpty()){
                    System.out.println("Inside the for loop of Selection Listener");
                    //GET SELECTED ROW
                    int selectedRow = model.getMinSelectionIndex();
                    final String valueInCell = (String)table.getValueAt(selectedRow, 2);
                    System.out.println(valueInCell);
                    UserDetails ud = new UserDetails();
                        ud.make(valueInCell);




                }

            }
        });





        //Back Button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Admin");

                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(new AdminPanel().adminpanel);
                frame.pack();

                frame.setLocationRelativeTo(null);
                frame.setVisible(true);





            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Admin");

                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(new AdminPanel().adminpanel);
                frame.pack();

                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

            }
        });
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Admin");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setContentPane(new AddUser().mainpanel);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });

    }

    public void displayUsers(){
        System.out.println("Inside display users");
            try {
                ArrayList<UserPojo> users = UserDao.viewUsers();
                Object[] row = new Object[5];
                DefaultTableModel UserTable = (DefaultTableModel) table.getModel();
                for (UserPojo user : users) {
                    //row[0] = user.getSNo();
                    row[1] = user.getUserName();
                    row[2] = user.getUserId();
                    row[3] = user.getUserType();
                    row[4] = user.getActive();
                    UserTable.addRow(row);

                }
            }catch (SQLException sql){
                JOptionPane.showMessageDialog(null,"Sql Exception " + sql);
            }



    }


    private void createUIComponents() {
        table = createTable();
        userssp = new JScrollPane(table);

    }
    public  JTable createTable() {
           // DefaultTableModel model = new DefaultTableModel();
           DefaultTableModel model = new DefaultTableModel(){

                @Override
                public boolean isCellEditable(int row, int column) {
                   //all cells false
                    return false;
                }
            };
            model.addColumn("S_no");
            model.addColumn("User Name");
            model.addColumn("User Id");
            model.addColumn("User Type");
            model.addColumn("Active");
            JTable table1 = new JTable(model);
            table1.setFillsViewportHeight(true);
            return table1;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Users");
        frame.setContentPane(new User().mainpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);



    }
}
