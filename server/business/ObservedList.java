package server.business;

import java.util.*;
import server.dataaccess.GroceryItem;

public class ObservedList extends Observable{
    private List<GroceryItem> groceryList;
    private Date date;
    
    public ObservedList(List<GroceryItem> groceryList){
        this.groceryList = groceryList;
        date = new Date();
        
    }
    
    public void checkDate(){
        boolean successful = true;
                
        for(GroceryItem itemInformation: groceryList){
            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();
            cal1.setTime(itemInformation.getConsumptionDate());
            cal2.setTime(date);
            if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR))
            successful = false;
        }
        
        if(successful == false){
            setChanged();
            notifyObservers(); 
        }
    }
}
