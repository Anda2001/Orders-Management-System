package ro.tuc.pt.Presentation;

import ro.tuc.pt.BusinessLogic.OrderBLL;
import ro.tuc.pt.DataAccess.OrderDAO;
import ro.tuc.pt.Model.Order;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrdersController {
    private final AbstractView<Order> view;

    public OrdersController(AbstractView<Order> view) {
        OrderBLL orderBLL = new OrderBLL();
        this.view = view;
        view.setContentsTable(orderBLL.getOrders());

        view.insertListener(new InsertListener());
        view.deleteListener(new DeleteListener());
        view.editListener(new EditListener());
    }

    class InsertListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            OrderDAO orderDAO= new OrderDAO();

            InputOrderView inputOrderView = new InputOrderView("Insert",  "","", "", "", false);
            InputOrderController inputOrderController = new InputOrderController(inputOrderView, view, orderDAO);

            OrderBLL orderBLL = new OrderBLL();

            view.setContentsTable(orderBLL.getOrders());
        }
    }

    class DeleteListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] value=null;
            try {
                value = view.getSelectedRow();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(view, "Select an order!");
            }
            assert value != null;
            Order orders = new Order(Integer.parseInt(value[0]), Integer.parseInt(value[1]), Integer.parseInt(value[2]), Integer.parseInt(value[3]));
            OrderBLL orderBLL = new OrderBLL();
            System.out.println(orderBLL.delete(Integer.toString(orders.getId())));

            view.setContentsTable(orderBLL.getOrders());

        }
    }

    class EditListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            OrderDAO orderDAO= new OrderDAO();
            String[] value=null;
            try {
                value = view.getSelectedRow();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(view, "Select an order!");
            }
            assert value != null;
            Order orders = new Order(Integer.parseInt(value[0]), Integer.parseInt(value[1]), Integer.parseInt(value[2]), Integer.parseInt(value[3]));
            System.out.println(value[0] + " "+ value[1] + " "+value[2] + " " + value[3]);
            OrderBLL orderBLL = new OrderBLL();

            InputOrderView inputOrderView = new InputOrderView("Inserare", value[1], value[2], value[3], value[0],true);
            InputOrderController inputOrderController = new InputOrderController(inputOrderView, view, orderDAO);

            view.setContentsTable(orderBLL.getOrders());
        }
    }
}
