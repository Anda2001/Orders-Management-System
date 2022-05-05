package ro.tuc.pt.Presentation;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionListener;

import static java.awt.Color.*;

public class View extends JFrame {

    private JButton clientsButton, productsButton, ordersButton;
    Font myFont = new Font("Times New Roman", Font.PLAIN, 22);

    public View(String name) {
        super(name);
        this.setLayout(null);
        this.prepareGui();

    }

    public void prepareGui(){
        this.setSize(500, 200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        Container container = getContentPane();
        container.setLayout(null);
        container.setBackground(pink);

        clientsButton = new JButton("Clients");
        clientsButton.setFont(myFont);
        clientsButton.setBounds(45, 50, 100, 50);
        clientsButton.setBorder(new LineBorder(gray, 5));
        clientsButton.setBackground(magenta);
        clientsButton.setVerticalAlignment(JButton.CENTER);

        productsButton = new JButton("Products");
        productsButton.setFont(myFont);
        productsButton.setBounds(190, 50, 100, 50);
        productsButton.setBorder(new LineBorder(gray, 5));
        productsButton.setBackground(magenta);
        productsButton.setVerticalAlignment(JButton.CENTER);

        ordersButton = new JButton("Orders");
        ordersButton.setFont(myFont);
        ordersButton.setBounds(335, 50, 100, 50);
        ordersButton.setBorder(new LineBorder(gray, 5));
        ordersButton.setBackground(magenta);
        ordersButton.setVerticalAlignment(JButton.CENTER);

        container.add(clientsButton);
        container.add(productsButton);
        container.add(ordersButton);

        setVisible(true);
    }

    public void clientListener(ActionListener listenForClients) {
        clientsButton.addActionListener(listenForClients);
    }

    public void productsListener(ActionListener listenForProducts) {
        productsButton.addActionListener(listenForProducts);
    }
    public void ordersListener(ActionListener listenForOrders) {
        ordersButton.addActionListener(listenForOrders);
    }

}
