package ro.tuc.pt.Presentation;

import ro.tuc.pt.BusinessLogic.ClientBLL;
import ro.tuc.pt.DataAccess.ClientDAO;
import ro.tuc.pt.Model.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientsController {

    private final AbstractView<Client> view;

    public ClientsController(AbstractView<Client> view) {
        ClientBLL clientBLL = new ClientBLL();
        this.view = view;
        view.setContentsTable(clientBLL.getClients());
        view.insertListener(new InsertListener());
        view.editListener(new EditListener());
        view.deleteListener(new DeleteListener());

    }

    class InsertListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            ClientDAO clientDAO= new ClientDAO();
            InputClientView inputClientView = new InputClientView("Insert", "","", "", "", false);
            InputClientController inputClientController = new InputClientController(inputClientView, view, clientDAO);

            ClientBLL clientBLL = new ClientBLL();
            view.setContentsTable(clientBLL.getClients());
        }
    }

    class DeleteListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] value=null;
            try {
                value = view.getSelectedRow();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(view, "Select a client!");
            }
            assert value != null;
            Client client = new Client(Integer.parseInt(value[0]), value[1], value[2], value[3]);
            ClientBLL clientBLL = new ClientBLL();
            System.out.println(clientBLL.delete(Integer.toString(client.getId())));
            view.setContentsTable(clientBLL.getClients());
        }
    }

    class EditListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String[] value=null;
            try {
                value = view.getSelectedRow();
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(view, "Select a client!");
            }
            assert value != null;
            Client client = new Client(Integer.parseInt(value[0]), value[1], value[2], value[3]);
            ClientDAO clientDAO=new ClientDAO();
            InputClientView inputClientView = new InputClientView("Edit", value[1], value[2], value[3], value[0], true);
            InputClientController inputClientController = new InputClientController(inputClientView, view, clientDAO);


        }
    }
}
