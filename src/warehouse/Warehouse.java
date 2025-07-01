package warehouse;

import item.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Warehouse {
    private final List<Product> productList = new ArrayList<>();

    //Product 가져오기
    public void storeProduct(Product product){
        productList.add(product);
    }

    public List<Product> getProducts() {
        return productList;
    }

    public void printList() {
        for (Product item : productList){
            item.show();
        }
    }
}
