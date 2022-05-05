package ro.tuc.pt.Model;

import java.util.ArrayList;

/**
 * The class that mirrors the table Product from the warehouse database.
 */

public class Product {
    public int id;
    public String name;
    public int stock;
    public double price;

    public Product(){}

    public Product(int id, String description, int stock, double price){
        this.id=id;
        this.name =description;
        this.stock=stock;
        this.price=price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ArrayList<String> toStringList() {
        ArrayList<String> list = new ArrayList<>();
        list.add(Integer.toString(id));
        list.add(name);
        list.add(Integer.toString(stock));
        list.add(Double.toString(price));

        return list;
    }
}
