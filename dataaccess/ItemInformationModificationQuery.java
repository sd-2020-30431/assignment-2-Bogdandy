package dataaccess;

import business.*;
import org.hibernate.HibernateException;
import javax.persistence.OptimisticLockException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ItemInformationModificationQuery {
    private final ItemInformation itemInformation;
    private final UserDataStructure uSD;
    
    public ItemInformationModificationQuery(ItemInformation itemInformation, UserDataStructure uSD){
        this.itemInformation = itemInformation;
        this.uSD = uSD;
    }
    
    public boolean doQuery(){
        boolean successful = false;
        GroceryItem groceryItem = new GroceryItem();
       
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            
            groceryItem.setIdGroceryList(itemInformation.getItemId());
            groceryItem.setListNo(itemInformation.getTableIndex());
            groceryItem.setIdUserGroceryList(uSD.getIdUser());
            groceryItem.setItemName(itemInformation.getItemName());
            groceryItem.setQuantity(itemInformation.getQuantity());
            groceryItem.setCaloricValue(itemInformation.getCaloricValue());
            groceryItem.setPurchaseDate(itemInformation.getPurchaseDate());
            groceryItem.setExpirationDate(itemInformation.getExpirationDate());
            groceryItem.setConsumptionDate(itemInformation.getConsumptionDate());
            session.update(groceryItem);
                     
            tx.commit();
            successful = true;   
        } catch (HibernateException | OptimisticLockException e) {          
            session.close();
            return successful;
        } finally {
            session.close();
        }
        return successful;
    }
}
