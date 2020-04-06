package business;

import dataaccess.*;
import java.util.List;
import javax.swing.table.TableModel;

public class GroceryListWork{
    public boolean requestItemModification(ItemInformation itemInformation, UserDataStructure uSD) {
        boolean successful = false;
        ItemChecker itemChecker = new ItemChecker(itemInformation);
        
        if(itemChecker.checkItemInformation()){
            ItemInformationModificationQuery modificationQuery = new ItemInformationModificationQuery(itemInformation, uSD);
            successful = modificationQuery.doQuery();
        }
        
        return successful;
    }
    
    public boolean requestItemInsertion(ItemInformation itemInformation, UserDataStructure uSD) {
        boolean successful = false;
        ItemChecker itemChecker = new ItemChecker(itemInformation);
        
        if(itemChecker.checkItemInformation()){
            ItemInformationInsertionQuery insertionQuery = new ItemInformationInsertionQuery(itemInformation, uSD);
            successful = insertionQuery.doQuery();
        }
        
        return successful;
    }
    
    public boolean requestListClear(int tableIndex, UserDataStructure uSD) {
        return new ClearListQuery(tableIndex, uSD).doQuery();
    }
    
    public boolean requestItemRemoval(ItemInformation itemInformation, UserDataStructure uSD) {
        return new RemoveItemQuery(itemInformation, uSD).doQuery();
    }
    
    public boolean requestItemSearch(String itemName, int tableIndex, UserDataStructure uSD) {
        return new SearchItemQuery(itemName, tableIndex, uSD).doQuery();
    }
    
    public TableModel populateRequest(UserDataStructure uSD, int tableIndex){
        return new TableDataQuery(uSD, tableIndex).doQuery();
    }
    
    public List requestGroceryListData(UserDataStructure uSD) {
        return new RetrieveGroceryListQuery(uSD).doQuery();
    }
}
