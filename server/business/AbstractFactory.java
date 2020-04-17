package server.business;

import server.dataaccess.GroceryItem;
import java.util.List;

public interface AbstractFactory<T> {
    T create(String type, List<GroceryItem> groceryItem);
}
