package ro.tuc.pt.Presentation;

import ro.tuc.pt.Presentation.ClientsView;
import ro.tuc.pt.Presentation.View;
//import presentation.view.OrdersView;
//import presentation.view.ProductsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Controllerul principal ce se ocupa cu executarea
 * comenzilor primite din meniul principal, facand conexiunea
 * intre view si bll.
 */

public class Controller {

    public Controller(View view) {

        view.clientListener(new ClientsListener());
        view.productsListener(new ProductListener());
        view.ordersListener(new OrderListener());

    }

    static class ClientsListener implements ActionListener{


        @Override
        public void actionPerformed(ActionEvent e) {
            ClientsView view = new ClientsView("Clients");
            ClientsController controller = new ClientsController(view);
        }
    }

    static class ProductListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           ProductsView productsView = new ProductsView("Products");
           ProductsController productsController = new ProductsController(productsView);

        }
    }

    static class OrderListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           OrdersView ordersView = new OrdersView("Orders");
            OrdersController ordersController = new OrdersController(ordersView);

        }
    }

}
