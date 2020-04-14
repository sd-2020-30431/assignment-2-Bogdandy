package server.business;

import java.util.*;

public class CheckList extends TimerTask{
    private ObservedList observedList;
    private UserDataStructure userDataStructure;
    private GroceryListWork groceryListWork;
    private ListObserver listObserver;
    
    public CheckList(UserDataStructure userDataStructure, GroceryListWork groceryListWork){
        this.userDataStructure=userDataStructure;
        this.groceryListWork = groceryListWork;
        listObserver = new ListObserver();
    }
    
    @Override
    public void run(){
            observedList = new ObservedList(groceryListWork.requestGroceryListData(userDataStructure));
            observedList.addObserver(listObserver);
            observedList.checkDate();
       }
}
