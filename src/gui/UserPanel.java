package gui;

import javax.swing.*;
import java.awt.*;

public class UserPanel {
    private static JFrame frame;
    private JLabel userImageLabel;
    private JPanel mainpanel;
    private JButton generateBillButton;
    private JButton viewStockButton;

    public UserPanel(){

            ImageIcon ic = new ImageIcon("src/gui/Images/AdminImage.png");
            Image img = ic.getImage();
            img = img.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
            ic.setImage(img);
            userImageLabel.setIcon(ic);

            ic = new ImageIcon("src/gui/Images/GenerateBill.png");
            img = ic.getImage();
            img = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            ic.setImage(img);
            generateBillButton.setIcon(ic);

            ic = new ImageIcon("src/gui/Images/ViewStock.png");
            img = ic.getImage();
            img = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
            ic.setImage(img);
            viewStockButton.setIcon(ic);


    }
    public static void main(String[] args){
        frame = new JFrame("User Panel");
        frame.setContentPane(new UserPanel().mainpanel);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(600, 600);
        frame.setVisible(true);
    }

    public static void make()
    {
        JFrame frame = new JFrame("User Panel");
        frame.setContentPane(new UserPanel().mainpanel);
        frame.setSize(600, 600);
        frame.setVisible(true);

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
