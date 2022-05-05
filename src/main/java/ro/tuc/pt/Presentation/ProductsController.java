package ro.tuc.pt.Presentation;

import ro.tuc.pt.BusinessLogic.ProductBLL;
import ro.tuc.pt.DataAccess.ProductDAO;
import ro.tuc.pt.Model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductsController {

    private final AbstractView<Product> view;

    public ProductsController(AbstractView<Product> view) {
        ProductBLL productBLL = new ProductBLL();
        this.view = view;
        view.setContentsTable(productBLL.getProducts());

        view.insertListener(new InsertListener());
        view.deleteListener(new DeleteListener());
        view.editListener(new EditListener());
    }

    class InsertListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            ProductDAO productDao=new ProductDAO();
            InputProductView addEditProductsView = new InputProductView("Insert", "","", "", "", false);
            InputProductController addEditProductsController = new InputProductController(addEditProductsView, view, productDao);

            ProductBLL productBLL = new ProductBLL();
            view.setContentsTable(productBLL.getProducts());
        }
    }

    class DeleteListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] value=null;
            try {
                value = view.getSelectedRow();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(view, "Select a product!");
            }
            assert value != null;
            Product products = new Product(Integer.parseInt(value[0]), value[1], Integer.parseInt(value[2]), Double.parseDouble(value[3]));
            ProductBLL productBLL = new ProductBLL();
            System.out.println(productBLL.delete(Integer.toString(products.getId())));
            view.setContentsTable(productBLL.getProducts());
        }
    }

    class EditListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            String[] value=null;
            try {
                value = view.getSelectedRow();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(view, "Select a product!");
            }
            ProductDAO productDao=new ProductDAO();
            assert value != null;
            Product products = new Product(Integer.parseInt(value[0]),  value[1],Integer.parseInt(value[2]), Double.parseDouble(value[3]));

            InputProductView addEditProductsView = new InputProductView("Edit", value[1], value[2], value[3], value[0], true);
            InputProductController addEditProductsController = new InputProductController(addEditProductsView, view, productDao);


        }
    }
}
