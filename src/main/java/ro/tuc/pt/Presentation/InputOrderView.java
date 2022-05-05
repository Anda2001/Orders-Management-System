package ro.tuc.pt.Presentation;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class InputOrderView extends JFrame {

    private final JComboBox<String> clientBox;
    private final JComboBox<String> productBox;
    private final JTextField quantityTextField;
    private final JTextField idTextField;
    private final JButton insertButton;
    private final JButton editButton;
    private final String setProduct;
    private final String setClient;
    Font myFont = new Font("Times New Roman", Font.PLAIN, 22);

    public InputOrderView(String title, String idClient, String idProduct, String quantity, String id, boolean edit) {
        setProduct = idProduct;
        setClient = idClient;


        setTitle(title);
        setSize(500, 300);
        setLocation(1400, 100);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Container container = getContentPane();
        container.setLayout(null);
        container.setBackground(Color.pink);

        JLabel clientLabel = new JLabel("Client");
        JLabel productLabel = new JLabel("Product");
        JLabel quantityLabel = new JLabel("Quantity");
        clientBox = new JComboBox<>();

        productBox = new JComboBox<>();
        productBox.setSelectedItem(idProduct);
        quantityTextField = new JTextField(quantity);
        idTextField = new JTextField(id);

        productLabel.setBounds(10, 10, 200, 30);
        productLabel.setForeground(Color.BLACK);
        productLabel.setHorizontalAlignment(JLabel.CENTER);
        productLabel.setFont(myFont.deriveFont(Font.BOLD));

        quantityLabel.setBounds(340, 10, 160, 30);
        quantityLabel.setForeground(Color.BLACK);
        quantityLabel.setHorizontalAlignment(JLabel.CENTER);
        quantityLabel.setFont(myFont.deriveFont(Font.BOLD));

        clientLabel.setBounds(10, 90, 460, 30);
        clientLabel.setForeground(Color.BLACK);
        clientLabel.setHorizontalAlignment(JLabel.CENTER);
        clientLabel.setFont(myFont.deriveFont(Font.BOLD));

        productBox.setBounds(20, 50, 300, 30);
        productBox.setBackground(Color.lightGray);
        productBox.setBorder(new LineBorder(Color.magenta, 5));

        quantityTextField.setBounds(370, 50, 100, 30);
        quantityTextField.setBackground(Color.lightGray);
        quantityTextField.setBorder(new LineBorder(Color.magenta, 5));


        clientBox.setBounds(30, 130, 420, 30);
        clientBox.setBackground(Color.lightGray);
        clientBox.setBorder(new LineBorder(Color.magenta, 5));


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

        container.add(editButton);
        container.add(insertButton);
        container.add(clientLabel);
        container.add(productLabel);
        container.add(quantityLabel);
        container.add(clientBox);
        container.add(productBox);
        container.add(quantityTextField);

        setVisible(true);

    }

    public void setClient(String[] client){
        clientBox.setModel(new DefaultComboBoxModel<>(client));
        clientBox.setSelectedItem(setClient);
    }
    public void setProduct(String[] product){
        productBox.setModel(new DefaultComboBoxModel<>(product));
        productBox.setSelectedItem(setProduct);
    }
    public void setId(String id) {
        idTextField.setText(id);
    }

    public String getId() {
        return idTextField.getText();
    }

    public void setQuantity(String quantity){
        quantityTextField.setText(quantity);
    }

    public String getClient(){
        String clientIdName= (String) clientBox.getSelectedItem();
        assert clientIdName != null;
        return Character.toString(clientIdName.charAt(0) );
    }

    public String getProduct(){
        String productIdName= (String) productBox.getSelectedItem();
        assert productIdName != null;
        return Character.toString(productIdName.charAt(0) );
    }
    public String getQuantity(){
        return quantityTextField.getText();
    }

    public void insertListener(ActionListener listenForInsert) {
        insertButton.addActionListener(listenForInsert);
    }

    public void editListener(ActionListener listenForEdit) {
        editButton.addActionListener(listenForEdit);
    }


}
