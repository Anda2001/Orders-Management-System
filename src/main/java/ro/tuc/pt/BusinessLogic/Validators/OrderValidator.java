package ro.tuc.pt.BusinessLogic.Validators;

import ro.tuc.pt.DataAccess.ProductDAO;
import ro.tuc.pt.Model.Order;
import ro.tuc.pt.Model.Product;

/**
 *  The class in charge of validating the objects of type Order.
 *
 */
public class OrderValidator {

    public OrderValidator(){}

    public boolean validateOrder(Order order){
        return validateQuantity(order);
    }

    public boolean validateQuantity(Order order){
        int productId = order.getIdProduct();
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.findById(Integer.toString(productId));
        if(order.getQuantity() > product.getStock()) {
            System.out.println("Not enough stock!");
            return false;
        }
        return true;
    }
}
