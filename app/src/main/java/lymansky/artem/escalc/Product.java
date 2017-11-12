package lymansky.artem.escalc;

import java.util.ArrayList;

/**
 * Created by artem on 11/2/2017.
 */

public class Product {
  
  private String name;
  private double price;
  private double number;
  private double total;
  private boolean isIncluded;
  
  private static ArrayList<Product> productSet;
  
  public static ArrayList<Product> getProductSet() {
    if (productSet == null) {
      productSet = new ArrayList<>();
    }
    return productSet;
  }
  
  public Product(String name, double price, double number, boolean isIncluded) {
    this.name = name;
    this.price = price;
    this.number = number;
    this.isIncluded = isIncluded;
    total = price * number;
  }
  
  public String getName() {
    return name;
  }
  
  public double getPrice() {
    return price;
  }
  
  public double getNumber() {
    return number;
  }
  
  public double getTotal() {
    return total;
  }
  
  public boolean getIsIncluded() {
    return isIncluded;
  }
  
}
