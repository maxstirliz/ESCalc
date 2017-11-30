package lymansky.artem.escalc;

import java.util.ArrayList;

/**
 * Created by artem on 11/24/2017.
 */

public class ListItem {
  
  private String name;
  private boolean bought = false;
  
  private static ArrayList<ListItem> items;
  
  public static ArrayList<ListItem> getItems() {
    if(items == null) {
      items = new ArrayList<>();
    }
    return items;
  }
  
  public ListItem(String name) {
    this.name = (name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase()).trim();
  }
  
  public ListItem(String name, boolean bought) {
    this.name = (name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase()).trim();
    this.bought = bought;
  }
  
//  private void checkItem() {
//    for (int i = 0; i < Product.getProductSet().size(); i++) {
//      if(name == Product.getProductSet().get(i).getName()) {
//        bought = true;
//        return;
//      }
//    }
//  }
  
//  public static void checkItems() {
//    for(int i = 0; i < items.size(); i++) {
//      items.get(i).setBought(false);
//      for(int j = 0; i < Product.getProductSet().size(); i++) {
//        if(items.get(i).getName() == Product.getProductSet().get(j).getName()) {
//          items.get(i).setBought(true);
//          return;
//        }
//      }
//    }
//  }
  
  public String getName() {
    return name;
  }
  
  public boolean getBought() {
    return bought;
  }
  
  public void setBought(boolean bought) {
    this.bought = bought;
  }
}
