package business;

import dataaccess.GroceryItem;
import java.util.List;

public class WeeklyReportFactory implements AbstractFactory<WeeklyReport>{

    @Override
    public WeeklyReport create(String type, List<GroceryItem> groceryList) {
        return new WeeklyReport().createReport(groceryList);
    }
}
