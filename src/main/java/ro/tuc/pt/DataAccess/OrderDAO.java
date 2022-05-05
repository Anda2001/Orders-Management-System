package ro.tuc.pt.DataAccess;

import ro.tuc.pt.Model.Order;
import ro.tuc.pt.Model.Product;

public class OrderDAO extends AbstractDAO<Order>{
    public OrderDAO(){
        super();
    }

    /**
     * Method that computes the total price of an order, so it can be displayed on bill.
     * @param order the instance for which we calculate the total price
     * @return total price.
     */
    public double getTotalPrice(Order order) {
        int productId = order.getIdProduct();
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.findById(Integer.toString(productId));
        return product.getPrice()*order.getQuantity();
    }
}
