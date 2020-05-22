package gui;

import Pojo.CompanyBasedStockPojo;
import Utility.Barcode;
import dao.DBconnection;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompanyBasedStock {
    private JTable table;
    private JPanel topPanel;
    private javax.swing.JScrollPane JScrollPane;
    private JLabel homeLabel;
    private JLabel refreshLabel;
    private JLabel logoutLabel;
    private JLabel backLabel;
    private JButton button1;
    private JComboBox comboBox;
    private JTextField searchTab;
    private JButton completeStockButton;

    public void resizeImage(JLabel label, String imagePath, int wid, int ht) {
        ImageIcon img = new ImageIcon(imagePath);
        Image image = img.getImage();
        label.setIcon(new ImageIcon(image.getScaledInstance(wid, ht, Image.SCALE_SMOOTH)));
    }

    CompanyBasedStock() {
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
        table.addMouseListener(new MouseAdapter() {
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
                table.setModel(tableModel());
//                JScrollPane.setViewportView(table);

            }
        });
        table.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
                if (propertyChangeEvent.getPropertyName().equals("tableCellEditor")) {
//                    System.out.println(table.getValueAt(table.getSelectedRow(),table.getSelectedColumn()));
                    //table.
                }
            }
        });


        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if (table.getSelectedRow() < 0)
                        JOptionPane.showMessageDialog(null, "Please select medicine to print QR code", "Error", JOptionPane.ERROR_MESSAGE);
                    else {
                        Barcode.generateBarCode(table.getValueAt(table.getSelectedRow(), 1) + "  " + table.getValueAt(table.getSelectedRow(), 2) + " " + table.getValueAt(table.getSelectedRow(), 6));
                        JOptionPane.showConfirmDialog(null, " Select yes to print QRCode", null, 2);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        searchTab.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent caretEvent) {
                table.setModel(searchBar());
            }
        });


        completeStockButton.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent propertyChangeEvent) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame(" My Stock");
        frame.setContentPane(new CompanyBasedStock().topPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        table = createTable();
//        int q=table.getSelectedRowCount();
//        System.out.println(table.getAccessibleContext());
        JScrollPane = new JScrollPane(table);
    }

    public static JTable createTable() {
        JTable table = new JTable();

        //table.setMaximumSize();
        table.setAutoResizeMode(4);
        System.out.println(table.getAutoResizeMode());
        table.setModel(tableModel());
        Font font = new Font(null, Font.BOLD, 14);
        table.getTableHeader().setFont(font);
        return table;
    }

    public static TableModel tableModel() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn(" S NO.");
        model.addColumn("Medicine");
        model.addColumn("Company");
        model.addColumn("Total-Qty");
        model.addColumn("Expiry");
        model.addColumn("Category");
        model.addColumn("Batch NO.");
        model.addColumn("Vendor");


        List<CompanyBasedStockPojo> companyBasedStocks = new ArrayList<>();
        try {
            Statement st = DBconnection.getConnection().createStatement();
            String query = "select Name,Company ,Category,Total_Quantity,Expiry,Batch_NO ,Vendor from Medicine";
            ResultSet rs = st.executeQuery(query);
            int sNo = 0;
            while (rs.next()) {
                sNo++;
                companyBasedStocks.add(new CompanyBasedStockPojo(sNo, rs.getString(1), rs.getString(2), rs.getInt(4), rs.getDate(5), rs.getString(3), rs.getString(6), rs.getString(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!companyBasedStocks.isEmpty()) {
            for (CompanyBasedStockPojo stock : companyBasedStocks) {
//                JButton button = new JButton();
                model.addRow(stock.getCompanyBasedStockImpl());
            }
        }
        return model;
    }


    public TableModel searchBar() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn(" S NO.");
        model.addColumn("Medicine");
        model.addColumn("Company");
        model.addColumn("Total-Qty");
        model.addColumn("Expiry");
        model.addColumn("Category");
        model.addColumn("Batch NO.");
        model.addColumn("Vendor");


        List<CompanyBasedStockPojo> companyBasedStocks = new ArrayList<>();
        try {

            String comboBoxSelectedItem = comboBox.getSelectedItem().toString();
            String textBox = searchTab.getText();
            String query = null;
            switch (comboBoxSelectedItem) {

                case "Company":
                    query = "select Name,Company ,Category,Total_Quantity,Expiry,Batch_NO ,Vendor from Medicine where Company like ? ";
                    break;
                case "Name":
                    query = "select Name,Company ,Category,Total_Quantity,Expiry,Batch_NO ,Vendor from Medicine where Name like ? ";
                    break;
                case "Expiry":
                    query = "select Name,Company ,Category,Total_Quantity,Expiry,Batch_NO ,Vendor from Medicine where Expiry like ? ";
                    break;
                case "Category":
                    query = "select Name,Company ,Category,Total_Quantity,Expiry,Batch_NO ,Vendor from Medicine where Category like ? ";
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Please select Search criteria");
                    return tableModel();

            }
            PreparedStatement st = DBconnection.getConnection().prepareStatement(query);
//            st.setString(1,comboBoxSelectedItem);
            st.setString(1, "%" + textBox + "%");
            ResultSet rs = st.executeQuery();

            int sNo = 0;
            while (rs.next()) {
                sNo++;
                companyBasedStocks.add(new CompanyBasedStockPojo(sNo, rs.getString(1), rs.getString(2), rs.getInt(4), rs.getDate(5), rs.getString(3), rs.getString(6), rs.getString(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!companyBasedStocks.isEmpty()) {
            for (CompanyBasedStockPojo stock : companyBasedStocks) {
                model.addRow(stock.getCompanyBasedStockImpl());
            }
        }
        return model;
    }


}