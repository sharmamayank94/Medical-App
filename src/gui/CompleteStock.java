package gui;

import dbutil.DBConnection;
import pojo.ViewStockPojo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompleteStock {

    private JTable fullStock;
    private JScrollPane Stock;
    public JPanel topPanel;
    private JLabel homeLabel;
    private JLabel refreshLabel;
    private JLabel logoutLabel;
    private JLabel backLabel;
    private JPanel panel;

    public static void main(String[] args) {

        JFrame frame = new JFrame(" My Stock");
        frame.setContentPane(new CompleteStock().topPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        fullStock = createTable();
        Stock = new JScrollPane(fullStock);

    }

    public static JTable createTable() {

        // String[] columnNames = new String[]{" S_No", "Name", "Category", "Total_Quantity", "Selling_price", "Batch_NO", "Expiry", "Vendor", "Company", "No_Of_Leaves_Per_Pack", "No_Of_Medicines_Per_Strip", "MRP", "Cost_Price", "Small_Description", "X_Factor"};
        // Object[][] data = new Object[][]{};
        JTable table = new JTable(tableModel());
        table.setFillsViewportHeight(true);
        return table;

    }

    public void resizeImage(JLabel label, String imagePath, int wid, int ht) {
        ImageIcon img = new ImageIcon(imagePath);
        Image image = img.getImage();
        label.setIcon(new ImageIcon(image.getScaledInstance(wid, ht, Image.SCALE_SMOOTH)));
    }


    CompleteStock() {
        resizeImage(homeLabel, "src/gui/Icons/homeButtonIcon.png", 45, 45);
        resizeImage(refreshLabel, "src/gui/Icons/refreshIcon.png", 45, 45);
        resizeImage(logoutLabel, "src/gui/Icons/logoutIcon.png", 50, 50);
        resizeImage(backLabel, "src/gui/Icons/backIcon.png", 50, 50);
        homeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                resizeImage(homeLabel, "src/gui/Icons/homeButtonIcon1.png", 45, 45);
            }
        });
        homeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                resizeImage(homeLabel, "src/gui/Icons/homeButtonIcon1.png", 50, 50);
            }
        });
        homeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                resizeImage(homeLabel, "src/gui/Icons/homeButtonIcon.png", 50, 50);
            }
        });
        homeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                resizeImage(homeLabel, "src/gui/Icons/homeButtonIcon.png", 45, 45);
            }
        });
        refreshLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                resizeImage(refreshLabel, "src/gui/Icons/refreshIcon.png", 50, 50);
            }
        });
        refreshLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                resizeImage(refreshLabel, "src/gui/Icons/refreshIcon.png", 45, 45);
            }
        });

        refreshLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                resizeImage(refreshLabel, "src/gui/Icons/refreshIcon1.png", 50, 50);
            }
        });
        refreshLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                resizeImage(refreshLabel, "src/gui/Icons/refreshIcon.png", 50, 50);
            }
        });
        logoutLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                resizeImage(logoutLabel, "src/gui/Icons/logoutIcon.png", 45, 45);
            }
        });
        logoutLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                resizeImage(logoutLabel, "src/gui/Icons/logoutIcon.png", 50, 50);
            }
        });
        fullStock.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                resizeImage(homeLabel, "src/gui/Icons/homeButtonIcon.png", 45, 45);
            }
        });

        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                resizeImage(backLabel, "src/gui/Icons/backIcon1.png", 50, 50);
            }
        });
        backLabel.addMouseListener(new MouseAdapter() {
        });
        backLabel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                // TODO: 26-03-2020 add back functionality

            }
        });
        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                resizeImage(backLabel, "src/gui/Icons/backIcon.png", 50, 50);
            }
        });
        backLabel.addMouseListener(new MouseAdapter() {


            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                resizeImage(backLabel, "src/gui/Icons/backIcon1.png", 45, 45);
            }
        });

        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                resizeImage(backLabel, "src/gui/Icons/backIcon1.png", 50, 50);
            }
        });
        refreshLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
//                table = createTable();
                fullStock.setModel(tableModel());
//                JScrollPane.setViewportView(table);

            }
        });
        fullStock.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                if (propertyChangeEvent.getPropertyName().equals("tableCellEditor")) {
//                    System.out.println(table.getValueAt(table.getSelectedRow(),table.getSelectedColumn()));
                    //table.
                }
            }
        });


    }


    public static TableModel tableModel() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn(" S_No");
        model.addColumn("Name");
        model.addColumn("Category");
        model.addColumn("Total_Quantity");
        model.addColumn("Selling_price");
        model.addColumn("Batch_NO");
        model.addColumn("Expiry");
        model.addColumn("Vendor");
        model.addColumn("Company");
        model.addColumn("No_Of_Leaves_Per_Pack");
        model.addColumn("No_Of_Medicines_Per_Strip");
        model.addColumn("MRP");
        model.addColumn("Cost_Price");
        model.addColumn("Small_Description");
        model.addColumn("X_Factor");

        List<ViewStockPojo> viewStockBeans = new ArrayList<>();
        try {
            Statement st = DBConnection.getConnection().createStatement();
            String query = "select \n" +
                    "                Name,\n" +
                    "                Category,\n" +
                    "                Total_Quantity,\n" +
                    "                Selling_price,\n" +
                    "                Batch_NO,\n" +
                    "                Expiry,\n" +
                    "                Vendor,\n" +
                    "                Company,\n" +
                    "                No_Of_Leaves_Per_Pack,\n" +
                    "                No_Of_Medicines_Per_Strip,\n" +
                    "                MRP,\n" +
                    "                Cost_Price,\n" +
                    "                Small_Description,\n" +
                    "                X_Factor from Medicine";
            ResultSet rs = st.executeQuery(query);
            int sNo = 0;
            while (rs.next()) {
                sNo++;
                viewStockBeans.add(new ViewStockPojo(String.valueOf(sNo), rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getDate(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getInt(10), rs.getInt(11), rs.getInt(12), rs.getString(13), rs.getInt(14)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!viewStockBeans.isEmpty()) {
            for (ViewStockPojo stock : viewStockBeans) {
                model.addRow(stock.getCompanyBasedStockImpl());
            }

        }
        return model;
    }
}


