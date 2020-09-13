package gui;

import dao.ProductsDao;
import pojo.ProductPojo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Calendar;

public class AddMedicines {
    private JTextField nameTextField;
    private JPanel mainpanel;
    private JTextField productNameTextField;
    private JTextField leavesPerPackTextField;
    private JTextField noOfPacksTextField;
    private JTextField medicinesPerLeafTextField;
    private JTextField batchNoTextField;

    private JComboBox categoryComboBox;
    private JTextField noOfMedicinesTextField;
    private JTextField compositionTextField;
    private JTextField vendorTextField;
    private JTextField companyTextField;
    private JTextField expiryTextField;
    private JTextField costPriceTextField;
    private JTextField sellingPriceTextField;
    private JTextField MRPTextField;
    private JScrollPane ProductDescription;
    private JTextPane productDescriptionTextPane;
    private JButton homeButton;
    private JButton logoutButton;
    private JLabel addproduct;
    private JButton addProductButton;
    private JTextField taxTextField;
    private JLabel andLabel;
    private JLabel leavesPerPackLabel;
    private JLabel noOfPacksLabel;
    private JLabel medicinesPerLeafLabel;
    private   JScrollPane scroller;
    private static JFrame frame;
    private boolean isProductAdded;

    AddMedicines(){
        categoryComboBox.addItem("Tablet");
        categoryComboBox.addItem("Capsule");
        categoryComboBox.addItem("Syrup");
        categoryComboBox.addItem("Syringe");
        categoryComboBox.addItem("Sanitizer");
        ProductDescription.setViewportView(productDescriptionTextPane);
        ImageIcon imageIcon = new ImageIcon("src/gui/Images/addproducts.png"); // load the image to a imageIcon
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(500, 460,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);  // transform it back
        addproduct.setIcon(imageIcon);

        addProductButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                String name = nameTextField.getText();
                String noOfPacks = noOfPacksTextField.getText();
                String leavesPerPack = leavesPerPackTextField.getText();
                String medicinesPerLeaf = medicinesPerLeafTextField.getText();
                String category = categoryComboBox.getSelectedItem().toString();
                String sellingPrice = sellingPriceTextField.getText();
                String costPrice = costPriceTextField.getText();
                String MRP = MRPTextField.getText();
                String batchNo =  batchNoTextField.getText();
                String vendor = vendorTextField.getText();
                String company =  companyTextField.getText();
                String expiryString =  expiryTextField.getText();
                String productDescription = productDescriptionTextPane.getText();
                String tax = taxTextField.getText();

                if(name.isEmpty()||category.isEmpty()||sellingPrice.isEmpty()||batchNo.isEmpty()||
                expiryString.isEmpty()||vendor.isEmpty()||company.isEmpty()||
                MRP.isEmpty()||costPrice.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Please fill all the necessary details");
                    return;
                }

                Calendar expiry = Calendar.getInstance();
                expiry.set(Integer.parseInt(expiryString.substring(6)),
                        Integer.parseInt(expiryString.substring(3,5))-1,
                        Integer.parseInt(expiryString.substring(0,2)));

                int leftMedicines;
                try{
                    leftMedicines = Integer.parseInt(noOfMedicinesTextField.getText());
                }catch(NumberFormatException nfe){
                    leftMedicines = 0;
                    System.out.println("here");
                }

                if(noOfPacks.isEmpty())
                    noOfPacks="0";

                if(leavesPerPack.isEmpty())
                    leavesPerPack="1";

                if(medicinesPerLeaf.isEmpty())
                    medicinesPerLeaf = "1";


                int quantity = Integer.parseInt(noOfPacks)*Integer.parseInt(leavesPerPack)
                        * Integer.parseInt(medicinesPerLeaf) + leftMedicines;

                ProductPojo product = new ProductPojo(name, category,
                        quantity, Double.parseDouble(sellingPrice), Double.parseDouble(costPrice),
                        Double.parseDouble(MRP), batchNo, expiry, vendor,
                        company, Integer.parseInt(leavesPerPack), Integer.parseInt(medicinesPerLeaf),
                        productDescription);

                if(!compositionTextField.getText().isEmpty()){
                    product.setComposition(compositionTextField.getText());
                }

                try{
                   boolean result =  ProductsDao.addProduct(product);
                   if(result){
                       JOptionPane.showMessageDialog(null, "Product added to the Database");
                       setisProductAdded(true);
                   }
                }catch(SQLException sqle){
                    JOptionPane.showMessageDialog(null, "Problem in Database");
                    sqle.printStackTrace();
                }

            }
        });



        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });
        categoryComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if(categoryComboBox.getSelectedItem().toString().equals("Tablet")||categoryComboBox.getSelectedItem().toString().equals("Capsule")){
                    leavesPerPackTextField.setVisible(true);
                    noOfPacksTextField.setVisible(true);
                    medicinesPerLeafTextField.setVisible(true);
                    leavesPerPackLabel.setVisible(true);
                    noOfPacksLabel.setVisible(true);
                    medicinesPerLeafLabel.setVisible(true);
                    andLabel.setVisible(true);
                }else{
                    leavesPerPackTextField.setVisible(false);
                    noOfPacksTextField.setVisible(false);
                    medicinesPerLeafTextField.setVisible(false);
                    leavesPerPackLabel.setVisible(false);
                    noOfPacksLabel.setVisible(false);
                    medicinesPerLeafLabel.setVisible(false);
                    andLabel.setVisible(false);
                }
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                LoginPanel lp = new LoginPanel();
                lp.make();
                frame.dispose();
            }
        });
        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                AdminPanel ap = new AdminPanel();
                ap.make();
                frame.dispose();
            }
        });
        noOfMedicinesTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                noOfMedicinesTextField.setText("");
            }
        });

        noOfMedicinesTextField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if(noOfMedicinesTextField.getText().equals("")){
                    noOfMedicinesTextField.setText("Only those not included in pack");
                }
            }
        });
    }

    public static void main(String[] args) {
          frame = new JFrame("Add Medicines");
          JTextArea jta = new JTextArea(10, 10);
        JScrollPane scrol = new JScrollPane(jta, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);




        frame.setContentPane(new AddMedicines().mainpanel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }


    private void createUIComponents() {
        // TODO: place custom component creation code here

    }

    public boolean getisProductAdded(){
        return this.isProductAdded;
    }

    public void setisProductAdded(boolean status){
        this.isProductAdded = status;
    }

    public void setEntries(String name, String quantity, String vendor, String company){
        this.nameTextField.setText(name);
        this.vendorTextField.setText(vendor);
        this.companyTextField.setText(company);
    }

    public void make() {
        frame = new JFrame("Add Products");
        frame.setContentPane(mainpanel);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);



    }
}
