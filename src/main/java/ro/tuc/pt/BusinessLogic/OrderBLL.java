package ro.tuc.pt.BusinessLogic;

import ro.tuc.pt.BusinessLogic.Validators.OrderValidator;
import ro.tuc.pt.DataAccess.OrderDAO;
import ro.tuc.pt.DataAccess.ProductDAO;
import ro.tuc.pt.Model.Client;
import ro.tuc.pt.Model.Order;
import ro.tuc.pt.Model.Product;
import ro.tuc.pt.Presentation.InputOrderView;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * The class that uses the OrderValidator class and adds the valid elements in the Order tabel.
 * It is also used to generate the bill for every order.
 */

public class OrderBLL {

    OrderValidator orderValidator;
    InputOrderView view;

    public OrderBLL() {orderValidator = new OrderValidator();}

    /**
     * Method that calls the insert method and generates the bill for the last added order.
     * @param order the instance that need to be inserted in the Order table.
     * @return true if inserted succesfully, false otherwise.
     */
    public boolean makeOrders(Order order){
            if(insert(order)){

                OrderBLL orderBLL = new OrderBLL();
                List<Order> orders = orderBLL.getOrders();
                Order lastOrder = orders.get(orders.size()-1);
                int orderNo = lastOrder.getId();
                generateBill(orderNo, order);
                return true;
            }
            return false;
    }


    /**
     *Method that extracts all the data from tabel Order.
     * @return data from tabel Order.
     */
    public List<Order> getOrders(){
        OrderDAO orderDAO = new OrderDAO();
        return orderDAO.findAll();
    }

    /**
     * Method that inserts the new order in the tabel if it is validated by the
     * order validator and displays an error message otherwise, with the help of
     * the OrderDao class.
     * It also decrements the product's stock when an order is made.
     * @param order represents the Order instance that needs to be inserted in the table.
     * @return true if the order was inserted succsesfully, false otherwise.
     */
    public boolean insert(Order order) {
        OrderDAO orderDAO = new OrderDAO();
        if(orderValidator.validateOrder(order)) {
            ArrayList<String> values = order.toStringList();
            ProductDAO productDAO=new ProductDAO();
            int pid=order.getIdProduct();
            String prodID=String.valueOf(pid);
            Product product=productDAO.findById(prodID);
            int pStock=product.getStock();
            product.setStock(pStock-order.getQuantity());
            productDAO.update(product.toStringList());
            return orderDAO.insert(values);

        }else{
            JOptionPane.showMessageDialog(view, "Not enough stock.");
        }
        return false;

    }


    /**
     * Method that uses the OrderDAO class to update an order's values in the Order table.
     * @param order the instance containing the values that need to be updated.
     * @return true if the Data was updated succesfully, false otherwise.
     */
    public boolean update(Order order){
        OrderDAO orderDAO = new OrderDAO();
        if(orderValidator.validateOrder(order)){
            ArrayList<String> values = order.toStringList();
            return orderDAO.update(values);
        }else{
            JOptionPane.showMessageDialog(view, "Not enough stock.");
        }
        return false;

    }

    /**
     *  Method that uses the OrderDAO class to delete a row form the table Order in the database.
     *  It also increments the stock of a product when an order containing that product id deleted.
     * @param id the id of the instance that needs to be deleted from the table.
     * @return true if deleted successfully, false otherwise.
     */

    public boolean delete(String id){
        OrderDAO orderDAO = new OrderDAO();
        ProductDAO productDAO=new ProductDAO();
        Order order= orderDAO.findById(id);
        int pid=order.getIdProduct();
        String prodID=String.valueOf(pid);
        Product product=productDAO.findById(prodID);
        int pStock=product.getStock();
        product.setStock(pStock+order.getQuantity());
        productDAO.update(product.toStringList());
        return orderDAO.delete(id);
    }


    /**
     * Method that gets the total price of an order.
     * @param order is the order for which we have to calculate the price.
     * @return true if calculated successfully, false otherwise.
     */

    public double getTotalPrice(Order order) {
        OrderDAO orderDAO = new OrderDAO();
        return orderDAO.getTotalPrice(order);
    }

    /**
     * Method that generates the bill for an order and prints the data into a text file.
     * @param id is the id of the last placed order.
     * @param order the order we need to generate the bill for.
     */
    public void generateBill(int id, Order order) {
        File file = new File("bill" + id + ".txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        assert fw != null;
        PrintWriter pw = new PrintWriter(fw);
        ClientBLL clientBLL = new ClientBLL();
        Client client = clientBLL.getById(Integer.toString(order.getIdClient()));

        ProductBLL productBLL = new ProductBLL();
        Product product = productBLL.getById(Integer.toString(order.getIdProduct()));

        OrderBLL orderBLL = new OrderBLL();

        pw.println("Order number: " + id);
        pw.println("Client: " + client.getName());
        pw.println("Product: "+ product.getName());
        pw.println("Quantity :" + order.getQuantity());
        pw.println("Total price: " + orderBLL.getTotalPrice(order));
        pw.close();

        System.out.println("bill printed!");

    }


}
