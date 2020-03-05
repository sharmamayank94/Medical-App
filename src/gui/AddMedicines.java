package gui;

import javax.swing.*;

public class AddMedicines {
    private JPanel addmedicines;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextArea textArea1;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Add Medicines");
        frame.setContentPane(new AddMedicines().addmedicines);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
