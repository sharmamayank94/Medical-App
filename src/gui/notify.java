package gui;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class notify extends JFrame {
        HashMap<String,Integer> medicines;
        JWindow w;
        List l;

        notify(){
            w = new JWindow();
            w.setBackground(Color.BLACK);



            JPanel p = new JPanel() {
                public void paintComponent(Graphics g) {

                    setSize(400, 300);
                    Font plainFont = new Font("Serif", Font.PLAIN, 16);
                    g.setFont(plainFont);
                    g.setColor(Color.WHITE);
                    g.drawString("You are getting shortage of some items.", 25, 27);
                    g.drawString("They will be added to Orders section.", 25, 45);
                    g.fillRect(25, 70, 350, 200);
                    Font f2 = new Font("Serif", Font.LAYOUT_RIGHT_TO_LEFT, 15);
                    l = new List(4);
                    l.setForeground(Color.black);
                    l.setBackground(Color.CYAN);
                    l.setSize(335,150);
                    l.add("A");
                    l.add("B");
                    l.add("c");
                    l.add("D");
                    l.setLocation(30,80);

                    add(l);
                    g.setColor(Color.BLACK);
                    g.drawString("It is advised to order them as soon as possible.",32,250);

                }
            };





//            JPanel p2 = new JPanel();
//            p2.setSize(400,50);
//            p2.setBackground(Color.GREEN);
//            addOrder = new Button("Add to Order");
//            addOrder.setBackground(Color.CYAN);
//            addOrder.setForeground(Color.YELLOW);
//            ignore = new Button("Ignore");
//            ignore.setBackground(Color.CYAN);
//            ignore.setForeground(Color.YELLOW);
//            addOrder.setSize(100,30);
//            ignore.setSize(100,30);
//
//            p.setLayout(new GridLayout());
//            p.add(addOrder);
//            p.add(ignore);

            w.add(p);

            w.setLocation(400,400);
            w.setSize(400,300);

        }
        void showToast(){
            try{
                w.setOpacity(1);
                w.setVisible(true);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

    public static void main(String[] args) {
        notify n = new notify();
        n.showToast();
    }
}
