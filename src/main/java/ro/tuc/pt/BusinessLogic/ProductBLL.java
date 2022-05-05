package ro.tuc.pt.BusinessLogic;

import ro.tuc.pt.BusinessLogic.Validators.ProductValidator;
import ro.tuc.pt.DataAccess.ProductDAO;
import ro.tuc.pt.Model.Product;
import ro.tuc.pt.Presentation.InputProductView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ProductBLL {
    private ProductValidator productValidator;
    InputProductView view;

    public ProductBLL(){
        productValidator = new ProductValidator();
    }


    public Product getById(String id){
        ProductDAO productDAO = new ProductDAO();
        return productDAO.findById(id);
    }


    public List<Product> getProducts(){
        ProductDAO productDAO = new ProductDAO();
        return productDAO.findAll();
    }

    /**
     * Method that inserts the new product in the table if it is validated by the
     * product validator and displays an error message otherwise, with the help of
     * the productDAO class.
     * @param product represents the Product instance that needs to be inserted in the table.
     * @return true if product was inserted successfully, false otherwise.
     */
    public boolean insert(Product product) {
        ProductDAO productDAO = new ProductDAO();
        if(productValidator.validateProduct(product)) {
            ArrayList<String> values = product.toStringList();
            return productDAO.insert(values);
        }else{
            JOptionPane.showMessageDialog(view, "Bad input!");
        }
        return false;
    }

    /**
     * Method that uses the ProductDAO class to update a product's values in the product table.
     * @param product  the instance containing the values that need to be updated.
     * @return true if the Data was updated successfully, false otherwise.
     */
    public boolean update(Product product) {
        ProductDAO productDAO = new ProductDAO();
        if(productValidator.validateProduct(product)){
            ArrayList<String> values = product.toStringList();
            return productDAO.update(values);
        }
        return false;
    }

    /**
     *  Method that uses the ProductDAO class to delete a row form the table Product in the database.
     * @param id the id of the instance that needs to be deleted from the table.
     * @return true if deleted successfully, false otherwise.
     */
    public boolean delete(String id){
        ProductDAO productDAO = new ProductDAO();
        return productDAO.delete(id);
    }






}
