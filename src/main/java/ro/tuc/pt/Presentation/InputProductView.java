package ro.tuc.pt.Presentation;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class InputProductView extends JFrame {

    private final JTextField nameTextField;
    private final JTextField priceTextField;
    private final JTextField stockTextField;
    private final JTextField idTextField;
    private final JButton insertButton;
    private final JButton editButton;
    Font myFont = new Font("Times New Roman", Font.PLAIN, 22);

    public InputProductView(String title, String name, String stock, String price, String id, boolean edit){
        setTitle(title);
        setSize(500, 300);
        setLocation(1400, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Container container = getContentPane();
        container.setLayout(null);
        container.setBackground(Color.pink);

        JLabel nameLabel = new JLabel("Name");
        JLabel priceLabel = new JLabel("Price");
        JLabel stockLabel = new JLabel("Stock");
        nameTextField = new JTextField(name);
        priceTextField = new JTextField(price);
        stockTextField = new JTextField(stock);
        idTextField = new JTextField(id);

        nameLabel.setBounds(10, 10, 200, 30);
        nameLabel.setForeground(Color.black);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setFont(myFont.deriveFont(Font.BOLD));

        stockLabel.setBounds(340, 10, 160, 30);
        stockLabel.setForeground(Color.black);
        stockLabel.setHorizontalAlignment(JLabel.CENTER);
        stockLabel.setFont(myFont.deriveFont(Font.BOLD));

        priceLabel.setBounds(10, 90, 460, 30);
        priceLabel.setForeground(Color.black);
        priceLabel.setHorizontalAlignment(JLabel.CENTER);
        priceLabel.setFont(myFont.deriveFont(Font.BOLD));

        idTextField.setBounds(0, 0, 1, 1);
        idTextField.setBackground(Color.lightGray);
        idTextField.setBorder(new LineBorder(Color.magenta, 5));
        idTextField.setVisible(false);

        nameTextField.setBounds(20, 50, 300, 30);
        nameTextField.setBackground(Color.lightGray);
        nameTextField.setBorder(new LineBorder(Color.magenta, 5));

        stockTextField.setBounds(370, 50, 100, 30);
        stockTextField.setBackground(Color.lightGray);
        stockTextField.setBorder(new LineBorder(Color.magenta, 5));

        priceTextField.setBounds(30, 130, 420, 30);
        priceTextField.setBackground(Color.lightGray);
        priceTextField.setBorder(new LineBorder(Color.magenta, 5));


        insertButton = new JButton("INSERT");
        insertButton.setFont(myFont.deriveFont(Font.BOLD));
        insertButton.setBounds(200, 180, 100, 40);
        insertButton.setBorder(new LineBorder(Color.gray, 5));
        insertButton.setBackground(Color.magenta);
        insertButton.setVerticalAlignment(JButton.CENTER);

        editButton = new JButton("EDIT");
        editButton.setFont(myFont.deriveFont(Font.BOLD));
        editButton.setBounds(200, 180, 100, 40);
        editButton.setBorder(new LineBorder(Color.gray, 5));
        editButton.setBackground(Color.magenta);
        editButton.setVerticalAlignment(JButton.CENTER);

        if(edit)
            insertButton.setVisible(false);
        else
            editButton.setVisible(false);


        container.add(nameLabel);
        container.add(stockLabel);
        container.add(priceLabel);
        container.add(nameTextField);
        container.add(priceTextField);
        container.add(stockTextField);
        container.add(insertButton);
        container.add(editButton);

        setVisible(true);
    }

    public String getNameField() {
        return nameTextField.getText();
    }

    public String getPriceField() {
        return priceTextField.getText();
    }

    public String getQuantityField() {
        return stockTextField.getText();
    }

    public String getIdField(){
        return idTextField.getText();
    }

    public void setNameField(String text) {
        nameTextField.setText(text);
    }

    public void setPriceField(String text) {
        priceTextField.setText(text);
    }

    public void setQuantityField(String text) {
        stockTextField.setText(text);
    }

    public void insertListener(ActionListener listenForInsert) {
        insertButton.addActionListener(listenForInsert);
    }

    public void editListener(ActionListener listenForEdit) {
        editButton.addActionListener(listenForEdit);
    }

}
