package ro.tuc.pt.BusinessLogic;

import ro.tuc.pt.Model.Client;
import ro.tuc.pt.BusinessLogic.Validators.ClientValidator;
import ro.tuc.pt.DataAccess.ClientDAO;
import ro.tuc.pt.Presentation.InputClientView;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;


/**
 * The class that uses the ClientValidator class and adds the valid elements in the Client tabel.
 */

public class ClientBLL {

    private final ClientValidator clientValidator;
    InputClientView view;

    public ClientBLL() {
        clientValidator = new ClientValidator();
    }

    /**
     * This method uses the ClientDAO class to acces the database table Client and
     * extract the object having the required id.
     * @param id the id of the object that we need to get
     * @return the Client with the required id
     */
    public Client getById(String id){
        ClientDAO clientDAO = new ClientDAO();
        return clientDAO.findById(id);
    }

    /**
     * Method that extracts all the data from tabel Client.
     * @return data from tabel Client.
     */
    public List<Client> getClients(){
        ClientDAO clientDAO = new ClientDAO();
        return clientDAO.findAll();
    }

    /**
     * Method that inserts the new client in the tabel if it is validated by the
     * client validator and displays an error message otherwise, with the help of
     * the ClientDao class.
     * @param client represents the Client instance that needs to be inserted in the table.
     * @return true if client was inserted successfully, false otherwise.
     */
    public boolean insert(Client client) {
        ClientDAO clientDAO = new ClientDAO();
        if(clientValidator.validateClient(client)) {
            ArrayList<String> values = client.toStringList();
            return clientDAO.insert(values);
        }else{
            JOptionPane.showMessageDialog(view, "Bad input!");
        }
        return false;
    }

    /**
     * Method that uses the ClientDAO class to update a Client's values in the Client table.
     * @param client  the instance containing the values that need to be updated.
     * @return true if the Data was updated succesfully, false otherwise.
     */
    public boolean update(Client client)  {
        ClientDAO clientDAO = new ClientDAO();
        if(clientValidator.validateClient(client)) {
            ArrayList<String> values = client.toStringList();
            return clientDAO.update(values);
        }else {
            JOptionPane.showMessageDialog(view, "Bad input.");
        }
        return false;
    }

    /**
     *  Method that uses the ClientDAO class to delete a row form the table Client in the database.
     * @param id the id of the instance that needs to be deleted from the table.
     * @return true if deleted succesfully, false otherwise.
     */

    public boolean delete(String id){
        ClientDAO clientDAO = new ClientDAO();
        return clientDAO.delete(id);
    }

}
