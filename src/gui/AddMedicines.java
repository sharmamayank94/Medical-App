package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class AddMedicines {
    private JPanel addmedicines;
    private JTextField textField1;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JComboBox comboBox1;
    private JTextField onlyThoseNotIncludedTextField;
    private JTextField textField2;
    private JTextField textField7;
    private JTextField textField8;
    private JTextField textField9;
    private JTextField textField10;
    private JTextField textField11;
    private JScrollPane ProductDescription;
    private JTextPane textPane1;
    private JButton homeButton;
    private JButton logoutButton;
    private JLabel addproduct;
    private JButton addProductButton;


    private   JScrollPane scroller;
    AddMedicines(){


        ProductDescription.setViewportView(textPane1);
        ImageIcon imageIcon = new ImageIcon("src/gui/addproducts.png"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(500, 460,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back
        addproduct.setIcon(imageIcon);

    }

    public static void main(String[] args) {
          JFrame  frame = new JFrame("Add Medicines");
          JTextArea jta = new JTextArea(10, 10);
        JScrollPane scrol = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);




        frame.setContentPane(new AddMedicines().addmedicines);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }




}
