package ro.tuc.pt.Model;

import java.util.ArrayList;

/**
 * The class that mirrors the table Client from the warehouse database.
 */
public class Client {
    private int id;
    private String name;
    private String email;
    private String phone;

    public Client(){}


    public Client(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int idClient) {
        this.id = idClient;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<String> toStringList(){
        ArrayList<String> list = new ArrayList<>();
        list.add(Integer.toString(id));
        list.add(name);
        list.add(email);
        list.add(phone);
        return list;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", username='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone=" + phone +
                '}';
    }
}
