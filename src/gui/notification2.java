package gui;

import pojo.billPojo;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class notification2 {
    private JTextPane youAreGettingShortageTextPane;
    private JPanel panel1;
    private JTable itemList;
    private JButton OKButton;
    private JScrollPane itemlistSp;
    private static JFrame frame;
    private ArrayList<billPojo> med;
    private Timer tm = null;
    private Timer tm1 = null;
    private Timer tm2 = null;

    public notification2(){
        System.out.println("constructor of notification");
        itemList.getColumn("S.No.").setMaxWidth(50);
        itemList.getColumn("Category").setMaxWidth(130);
        itemList.getColumn("Category").setMinWidth(130);
        itemList.getColumn("Remaining").setMaxWidth(100);
        System.out.println(itemList.getTableHeader().getHeight());

        itemList.getTableHeader().setBackground(new Color(242, 125, 111));
        itemList.setBackground(new Color(242, 125, 111));
        itemList.setForeground(Color.WHITE);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        itemList.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        itemList.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        itemList.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        OKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        tm = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    tm1.start();
            }
        });

        tm1 = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float opacity = frame.getOpacity();
                if(frame.getOpacity()<=0.3){
                    frame.dispose();
                    return;
                }
                frame.setOpacity((float) (opacity-0.2));
                System.out.println("opacity 1 : " + frame.getOpacity());
                tm2.start();
            }
        });

        tm2 = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                float opacity = frame.getOpacity();
                if(frame.getOpacity()<=0.3){
                    frame.dispose();
                    return;
                }
                frame.setOpacity((float) (opacity-0.2));
                System.out.println("opacity 2 : " + frame.getOpacity());
                tm1.start();
            }
        });
    }

    public static void main(String[] args) {
        frame = new JFrame("Customer Bills Panel");
        frame.setContentPane(new notification2().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.pack();
        frame.setLocation(200,500);
        frame.setVisible(true);

    }

    public void make(ArrayList<billPojo> med1) throws InterruptedException {
        System.out.println("make of notification");
        med = new ArrayList<>();
        for(billPojo items : med1) {
            med.add(items);
        }
        viewList();
        frame = new JFrame("notification");
        frame.setContentPane(this.panel1);
        frame.setUndecorated(true);
        //frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500,200);
        frame.setLocation(200,500);
        frame.setVisible(true);
        tm.start();
    }

    private void viewList() {
        Object rows[] = new Object[4];
        DefaultTableModel dtm = (DefaultTableModel) itemList.getModel();
        int a = 1;

        for(billPojo b : med){
            System.out.println(a+ " " + b.getCategory() + b.getMedicine());
            rows[0] = a;
            rows[1] = b.getMedicine();
            rows[2] = b.getCategory();
            rows[3] = b.getQuantity();
            dtm.addRow(rows);
            a++;
        }
        System.out.println("row count : " + dtm.getRowCount());
        if(70/(a-1) >= 23) {
            itemList.setRowHeight(70 / (a - 1));
        }
        else{
            itemList.setRowHeight(23);
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        System.out.println("create ui components");
        itemList = createTable();
        itemlistSp = new JScrollPane(itemList);
    }

    private static JTable createTable(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("S.No.");
        model.addColumn("Item Name");
        model.addColumn("Category");
        model.addColumn("Remaining");
        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        return table;

    }
}
