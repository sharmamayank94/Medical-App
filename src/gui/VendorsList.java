package gui;

import dao.Vendordao;
import pojo.vendorPojo;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class VendorsList {
    private JTable vendorListTable;
    private JButton homeButton;
    private JButton logOutButton;
    private JButton backButton;
    private JButton addNewVendorButton;
    private JPanel vendorListPanel;
    private JScrollPane vendorListSP;
    private static JFrame frame;

    public VendorsList() {
        vendorListTable.getColumn("S_No").setMaxWidth(50);
        vendorListTable.setRowHeight(30);
       vendorListTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer(){
           public Component getTableCellRendererComponent(JTable table,Object value,boolean isSelected, boolean hasFocus, int row, int column){
               final Component c = super.getTableCellRendererComponent(table,value,isSelected,hasFocus,row,column);
               c.setBackground(row%2==0 ?Color.white: Color.pink);
               return c;
           }
       });

        viewVendors();

        ListSelectionModel model = vendorListTable.getSelectionModel();
        model.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                if(!model.isSelectionEmpty()){
                    int selectedRow = model.getMinSelectionIndex();
                    String name = (String)vendorListTable.getValueAt(selectedRow,1);
                    String agency = (String)vendorListTable.getValueAt(selectedRow,2);
                    UpdateVendor uv = new UpdateVendor();
                    uv.make(name,agency);
                    frame.dispose();
                }
            }
        });

        addNewVendorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddVendor av = new AddVendor();
                av.make();
                frame.dispose();
            }
        });

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminPanel ap = new AdminPanel();
                ap.make();
                frame.dispose();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AdminPanel ap = new AdminPanel();
                ap.make();
                frame.dispose();
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginPanel lp = new LoginPanel();
                lp.make();
                frame.dispose();
            }
        });
    }

    private void viewVendors() {
        try {
            ArrayList<vendorPojo> vendors = Vendordao.getAllVendors();
            Object rows[] = new Object[3];
            DefaultTableModel dtm = (DefaultTableModel)vendorListTable.getModel();
            int a = 1;
            for(vendorPojo vendor : vendors){
                rows[0] = a;
                rows[1] = vendor.getName();
                rows[2] = vendor.getAgency();
                a++;
                dtm.addRow(rows);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Sql Exception " + e);
        }
    }

    public static void main(String[] args) {
        frame = new JFrame("Vendors List");
        frame.setContentPane(new VendorsList().vendorListPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        vendorListTable = createTable();
        vendorListSP = new JScrollPane(vendorListTable);
    }

    public static JTable createTable(){
        DefaultTableModel model = new DefaultTableModel(){

            public boolean isCellEditable(int row,int column)  {
                if (column == 0)  return false;
                return true;
            }
        };

        model.addColumn("S_No");
        model.addColumn("Vendor Name");
        model.addColumn("Agency");
        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        return table;
    }

    public void make() {
        frame = new JFrame("Vendors List Frame");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.setContentPane(this.vendorListPanel);
        frame.setVisible(true);
    }
}
