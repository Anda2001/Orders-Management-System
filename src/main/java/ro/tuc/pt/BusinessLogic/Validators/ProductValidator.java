package ro.tuc.pt.BusinessLogic.Validators;

import ro.tuc.pt.Model.Product;

/**
 *  The class in charge of validating the objects of type Product.
 *
 */

public class ProductValidator {

    public ProductValidator(){}

    public boolean validateProduct(Product product){
        if(!stockValidator(product.getStock())){
            System.out.println("Bad Input! stock");
            return false;
        }
        else if(!priceValidator(product.getPrice())){
            System.out.println("Bad Input! price");
            return false;
        }
        return true;
    }

    public boolean stockValidator(Integer stock){
       return stock>=0;
    }

    public boolean priceValidator(Double price){
        return price>0;
    }
}
