package ro.tuc.pt.Model;

import java.util.ArrayList;


/**
 * The class that mirrors the table Order from the warehouse database.
 */
public class Order {
    public int id;
    public int idClient;
    public int idProduct;
    public int quantity;

    public Order (){}

    public Order(int id, int idClient, int idProduct, int quantity){
        this.id=id;
        this.idClient=idClient;
        this.idProduct =idProduct;
        this.quantity=quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ArrayList<String> toStringList() {
        ArrayList<String> list = new ArrayList<>();
        list.add(Integer.toString(id));
        list.add(Integer.toString(idClient));
        list.add(Integer.toString(idProduct));
        list.add(Integer.toString(quantity));
        return list;
    }
}
