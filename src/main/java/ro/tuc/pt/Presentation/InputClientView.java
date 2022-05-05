package ro.tuc.pt.Presentation;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;


public class InputClientView extends JFrame {

    private final JTextField nameTextField;
    private final JTextField emailTextField;
    private final JTextField phoneTextField;
    private final JTextField idTextField;
    private final JButton insertButton;
    private final JButton editButton;
    Font myFont = new Font("Times New Roman", Font.PLAIN, 22);

    public InputClientView(String title, String name, String email, String phone, String id, boolean edit){
        setTitle(title);
        setSize(600, 350);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Container container = getContentPane();
        container.setLayout(null);
        container.setBackground(Color.pink);

        JLabel emailLabel = new JLabel("Email address");

        nameTextField = new JTextField(name);
        emailTextField = new JTextField(email);
        phoneTextField = new JTextField(phone);
        idTextField = new JTextField(id);


        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(60, 10, 180, 30);
        nameLabel.setForeground(Color.black);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);
        nameLabel.setFont(myFont.deriveFont(Font.BOLD));

        JLabel phoneLabel = new JLabel("Phone number");
        phoneLabel.setBounds(340, 10, 160, 30);
        phoneLabel.setForeground(Color.BLACK);
        phoneLabel.setHorizontalAlignment(JLabel.CENTER);
        phoneLabel.setFont(myFont.deriveFont(Font.BOLD));

        emailLabel.setBounds(10, 90, 460, 30);
        emailLabel.setForeground(Color.BLACK);
        emailLabel.setHorizontalAlignment(JLabel.CENTER);
        emailLabel.setFont(myFont.deriveFont(Font.BOLD));

        nameTextField.setBounds(20, 50, 250, 30);
        nameTextField.setBackground(Color.lightGray);
        nameTextField.setBorder(new LineBorder(Color.magenta, 5));

        phoneTextField.setBounds(350, 50, 150, 30);
        phoneTextField.setBackground(Color.lightGray);
        phoneTextField.setBorder(new LineBorder(Color.magenta, 5));

        emailTextField.setBounds(30, 130, 400, 30);
        emailTextField.setBackground(Color.lightGray);
        emailTextField.setBorder(new LineBorder(Color.magenta, 5));


        insertButton = new JButton("INSERT");
        insertButton.setFont(myFont);
        insertButton.setBounds(200, 180, 100, 40);
        insertButton.setBorder(new LineBorder(Color.gray, 5));
        insertButton.setBackground(Color.magenta);
        insertButton.setVerticalAlignment(JButton.CENTER);

        editButton = new JButton("Edit");
        editButton.setFont(myFont);
        editButton.setBounds(200, 180, 100, 40);
        editButton.setBorder(new LineBorder(Color.gray, 5));
        editButton.setBackground(Color.magenta);
        editButton.setVerticalAlignment(JButton.CENTER);

        if(edit)
            insertButton.setVisible(false);
        else
            editButton.setVisible(false);

        container.add(nameLabel);
        container.add(phoneLabel);
        container.add(emailLabel);
        container.add(nameTextField);
        container.add(emailTextField);
        container.add(phoneTextField);
        container.add(insertButton);
        container.add(editButton);

        setVisible(true);
    }

    public String getNameField() {
        return nameTextField.getText();
    }

    public String getEmailField() {
        return emailTextField.getText();
    }

    public String getPhoneField() {
        return phoneTextField.getText();
    }

    public String getIdField(){
        return idTextField.getText();
    }

    public void setNameField(String text) {
        nameTextField.setText(text);
    }

    public void setAddressField(String text) {
        emailTextField.setText(text);
    }

    public void setAgeField(String text) {
        phoneTextField.setText(text);
    }

    public void insertListener(ActionListener listenForInsert) {
        insertButton.addActionListener(listenForInsert);
    }

    public void editListener(ActionListener listenForEdit) {
        editButton.addActionListener(listenForEdit);
    }


}
