package ro.tuc.pt.Presentation;

import ro.tuc.pt.BusinessLogic.ClientBLL;
import ro.tuc.pt.DataAccess.ClientDAO;
//import exceptions.InvalidDataEnteredException;
import ro.tuc.pt.Model.Client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;


public class InputClientController {
    InputClientView view;
    ClientDAO clientDAO;

    public InputClientController(InputClientView view, AbstractView<Client> tableView, ClientDAO clientDAO){
        this.view = view;
        this.clientDAO= clientDAO;

        view.insertListener(new InsertListener(tableView));
        view.editListener(new EditListener(tableView));

    }


    class InsertListener implements ActionListener{

        AbstractView<Client> tableView;

        public InsertListener(AbstractView<Client> tableView){
            this.tableView = tableView;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = view.getNameField();
            if(name.isEmpty())
                name = null;
            String address = view.getEmailField();
            if(address.isEmpty())
                address = null;
            String phone=view.getPhoneField();
            if(phone.isEmpty())
                phone = null;

            int id= 0;
            try {
                id = clientDAO.getLastID();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            Client clients = new Client(id, name, address, phone);
            System.out.println(clients);

            ClientBLL clientBLL = new ClientBLL();
            try {
                System.out.println(clientBLL.insert(clients));
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(view, "Incorrect input data.");
            }
            view.dispose();
            tableView.setContentsTable(clientBLL.getClients());
        }
    }

    class EditListener implements ActionListener{

        AbstractView<Client> tableView;

        public EditListener(AbstractView<Client> tableView){
            this.tableView = tableView;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            int id = Integer.parseInt(view.getIdField());
            String name = view.getNameField();
            if(name.isEmpty())
                name = null;
            String email = view.getEmailField();
            if(email.isEmpty())
                email = null;
            String phone= view.getPhoneField();
            if(phone.isEmpty())
                phone = null;

            Client clients = new Client(id, name, email, phone);
            System.out.println(id + name + " " + email + " " + phone);

            ClientBLL clientBLL = new ClientBLL();
            try {
                System.out.println(clientBLL.update(clients));
            }
            catch (Exception exception) {
                JOptionPane.showMessageDialog(view, "Incorrect input data.");
            }
            view.dispose();
            tableView.setContentsTable(clientBLL.getClients());
        }
    }
}
