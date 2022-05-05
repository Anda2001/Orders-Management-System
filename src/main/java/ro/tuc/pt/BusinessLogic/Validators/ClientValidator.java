package ro.tuc.pt.BusinessLogic.Validators;

import ro.tuc.pt.Model.Client;


/**
 *  The class in charge of validating the objects of type Client.
 *
 */

public class ClientValidator {

    public ClientValidator(){}

    public boolean validateClient(Client client){
       if(!validateName(client.getName())){
           System.out.println("Bad input! name");
            return false;
        }
        else if(!validateEmail(client.getEmail())){
           System.out.println("Bad input! email");
           return false;
        }
        else if( !validatePhone(client.getPhone())){
           System.out.println("Bad input! phone");
            return false;
       }

        return  true;
    }

    public boolean validateName(String name){
        return name.matches("[ a-zA-Z]+");
    }

    public boolean validateEmail(String email){
        if(!email.contains("@")) {
            return false;
        }
        else return email.contains(".");
    }

   public boolean validatePhone(String phone){
       if(phone.length()!=10){
           return false;
       }
       return phone.startsWith("0");
   }

}
