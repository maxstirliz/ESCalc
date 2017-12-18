package lymansky.artem.escalc;

import java.util.ArrayList;

/**
 * Created by artem on 12/8/2017.
 */

public class ListItem {

    private static ArrayList<ListItem> shoppingList;

    private String name;
    private boolean isBought;

    public ListItem(String name, boolean isBought) {
        this.name = name;
        this.isBought = isBought;
    }

    public String getName() {
        return name;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }

    public boolean getBought() {
        return isBought;
    }

    public static ArrayList<ListItem> getShoppingList() {
        if (shoppingList == null) {
            shoppingList = new ArrayList<>();
        }
        return shoppingList;
    }
}
