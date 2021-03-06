package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel {
    private JPanel loginpanel;
    private JTextField usernameTextField;
    private JRadioButton adminRadioButton;
    private JRadioButton userRadioButton;
    private JButton loginButton;
    private JLabel LoginImage;
    private JPasswordField passwordField;
    private JPanel gradpanel;
   LoginPanel(){
        ImageIcon img = new ImageIcon("src/gui/Images/LoginImage.png");

        Image image = img.getImage();
        Image resized = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        img = new ImageIcon(resized);
        LoginImage.setIcon(img);

       gradpanel= new JPanel(){
           @Override
           protected void paintComponent(Graphics g) {
               super.paintComponent(g);
               Graphics2D g2d = (Graphics2D) g;
               g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
               int w = getWidth();
               int h = getHeight();
               Color color1 = Color.CYAN;
               Color color2 = new Color(255, 99, 252);
               GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
               g2d.setPaint(gp);
               g2d.fillRect(0, 0, w, h);
           }
       };
       gradpanel.add(loginpanel);
       gradpanel.setSize(900, 300);
       loginButton.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent actionEvent) {
                
           }
       });
   }
    public static void main(String[] args) {
        JFrame frame = new JFrame("LoginPanel");
        frame.setContentPane(new LoginPanel().gradpanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void make() {
       JFrame frame = new JFrame("Login Panel");
       frame.setContentPane(this.gradpanel);
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.pack();
       frame.setLocationRelativeTo(null);
       frame.setVisible(true);
    }
}
