package ro.tuc.pt.Presentation;

import ro.tuc.pt.BusinessLogic.ProductBLL;
import ro.tuc.pt.DataAccess.ProductDAO;
import ro.tuc.pt.Model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class InputProductController {
    InputProductView view;
    ProductDAO productDAO;

    public InputProductController(InputProductView view, AbstractView<Product> tableView, ProductDAO productDAO){
        this.view = view;
        this.productDAO=productDAO;

        view.insertListener(new InsertListener(tableView));
        view.editListener(new EditListener(tableView));

    }

    class InsertListener implements ActionListener {

        AbstractView<Product> tableView;

        public InsertListener(AbstractView<Product> tableView){
            this.tableView = tableView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameField();
            if(name.isEmpty())
                name=null;
            double price;
            if(view.getPriceField().isEmpty())
                price = -1.0;
            else
                price = Double.parseDouble(view.getPriceField());
            int quantity;
            if(view.getQuantityField().isEmpty())
                quantity = -1;
            else
                quantity = Integer.parseInt(view.getQuantityField());
            System.out.println(name + " " + price + " " + quantity);

            int id= 0;
            try {
                id = productDAO.getLastID();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }


            Product products = new Product(id, name, quantity, price);
            System.out.println(products.toString());

            ProductBLL productBLL = new ProductBLL();
            try {
                System.out.println(productBLL.insert(products));
            } catch (Exception badInput) {
                JOptionPane.showMessageDialog(view, "Bad input!");
            }

            view.dispose();
            tableView.setContentsTable(productBLL.getProducts());
        }
    }

    class EditListener implements ActionListener{

        AbstractView<Product> tableView;

        public EditListener(AbstractView<Product> tableView){
            this.tableView = tableView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(view.getIdField());
            String name = view.getNameField();
            if(name.isEmpty())
                name=null;

            int quantity;
            if(view.getQuantityField().isEmpty())
                quantity = -1;
            else
                quantity = Integer.parseInt(view.getQuantityField());

            double price;
            if(view.getPriceField().isEmpty())
                price = -1.0;
            else
                price = Double.parseDouble(view.getPriceField());

            Product products = new Product(id, name, quantity, price);
            System.out.println(id + name + " " + quantity + " " + price);

            ProductBLL productBLL = new ProductBLL();
            try {
                System.out.println(productBLL.update(products));
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(view, "Bad input!");
            }

            view.dispose();
            tableView.setContentsTable(productBLL.getProducts());
        }
    }
}
