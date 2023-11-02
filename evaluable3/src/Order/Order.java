package Order;

import Manufacture.Manufacture; 
import javax.swing.*;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Order {
    private JFrame frame;
    private JTextField typeField;
    private JTextField quantityField;
    private JCheckBox fileOutputCheckbox;
    private JTextField filenameField;


    public Order() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("tipo pieza");
        lblNewLabel.setBounds(29, 38, 61, 16);
        frame.getContentPane().add(lblNewLabel);

        typeField = new JTextField();
        typeField.setBounds(102, 33, 130, 26);
        frame.getContentPane().add(typeField);
        typeField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("cantidad");
        lblNewLabel_1.setBounds(29, 76, 61, 16);
        frame.getContentPane().add(lblNewLabel_1);

        quantityField = new JTextField();
        quantityField.setBounds(102, 71, 130, 26);
        frame.getContentPane().add(quantityField);
        quantityField.setColumns(10);

        fileOutputCheckbox = new JCheckBox("en fichero");
        fileOutputCheckbox.setBounds(29, 113, 128, 23);
        frame.getContentPane().add(fileOutputCheckbox);

        filenameField = new JTextField();
        filenameField.setBounds(29, 149, 203, 26);
        frame.getContentPane().add(filenameField);
        filenameField.setColumns(10);

        JButton btnNewButton = new JButton("realizar pedido");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String type = typeField.getText();
                int quantity = Integer.parseInt(quantityField.getText());
                boolean writeToFile = fileOutputCheckbox.isSelected();
                String filename = filenameField.getText();


                Manufacture manufacture = new Manufacture();
                manufacture.startManufacturing(type, quantity, writeToFile, filename);
            }
        });
        btnNewButton.setBounds(29, 187, 117, 29);
        frame.getContentPane().add(btnNewButton);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Order window = new Order();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}