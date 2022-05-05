package ro.tuc.pt.Presentation;

import ro.tuc.pt.BusinessLogic.ClientBLL;
import ro.tuc.pt.BusinessLogic.OrderBLL;
import ro.tuc.pt.BusinessLogic.ProductBLL;
import ro.tuc.pt.DataAccess.OrderDAO;
import ro.tuc.pt.Model.Client;
import ro.tuc.pt.Model.Order;
import ro.tuc.pt.Model.Product;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class InputOrderController {

    InputOrderView view;
    OrderDAO orderDAO;

    public InputOrderController(InputOrderView view, AbstractView<Order> tableView, OrderDAO orderDAO) {
        this.view = view;
        this.orderDAO=orderDAO;
        ClientBLL clientBLL = new ClientBLL();
        List<Client> clients = clientBLL.getClients();
        String[] clientArray= new String[clients.size()];
        int i=0;
        for(Client c : clients) {
            clientArray[i]=(c.getId())+"("+c.getName()+")";
            i++;
        }

        ProductBLL productBLL = new ProductBLL();
        List<Product> products=productBLL.getProducts();
        String[] productArray = new String[products.size()];
        i=0;
        for(Product p : products) {
            if(p.getStock()>0) {
                productArray[i] = (p.getId())+" ("+ p.getName()+")";
                i++;
            }
        }

        view.setClient(clientArray);
        view.setProduct(productArray);

        view.insertListener(new InsertListener(tableView));
        view.editListener(new EditListener(tableView));
    }

    class InsertListener implements ActionListener {
        AbstractView<Order>  tableview;

        public InsertListener(AbstractView<Order>  view) {
            this.tableview = view;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int client, product, quantity;
            if(view.getClient().isEmpty()){
                client = -1;
            } else{
                client = Integer.parseInt(view.getClient());
            }

            if(view.getProduct().isEmpty()){
                product = -1;
            } else{
                product = Integer.parseInt(view.getProduct());
            }

            if(view.getQuantity().isEmpty()){
                quantity = -1;
            } else{
                quantity = Integer.parseInt(view.getQuantity());
            }

            int id=0;
            try {
                id = orderDAO.getLastID();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            Order orders = new Order(id, client, product, quantity);
            OrderBLL orderBLL = new OrderBLL();
            System.out.println(orders);

            try {
                System.out.println(orderBLL.makeOrders(orders));
            } catch (Exception notEnoughStock) {
                JOptionPane.showMessageDialog(view, "Not enough stock.");
            }
            view.dispose();
            tableview.setContentsTable(orderBLL.getOrders());
        }
    }

    class EditListener implements ActionListener{
        AbstractView<Order>  tableview;

        public EditListener(AbstractView<Order>  view) {
            this.tableview = view;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(view.getId());
            int idClient, idProduct, quantity;

            if(view.getClient().isEmpty()){
                idClient = -1;
            } else{
                idClient = Integer.parseInt(view.getClient());
            }

            if(view.getProduct().isEmpty()){
                idProduct = -1;
            }else{
                idProduct = Integer.parseInt(view.getProduct());
            }

            if(view.getQuantity().isEmpty()){
                quantity = -1;
            }else{
                quantity = Integer.parseInt(view.getQuantity());
            }

            Order orders = new Order(id, idClient, idProduct, quantity);
            OrderBLL orderBLL = new OrderBLL();
            System.out.println(orderBLL.update(orders));
            view.dispose();
            tableview.setContentsTable(orderBLL.getOrders());
        }
    }

}
