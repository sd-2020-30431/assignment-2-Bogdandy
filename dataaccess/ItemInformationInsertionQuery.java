package dataaccess;

import javax.persistence.*;
import org.hibernate.HibernateException;
import business.*;

public class ItemInformationInsertionQuery {
    private final ItemInformation itemInformation;
    private final UserDataStructure uSD;
    
    public ItemInformationInsertionQuery(ItemInformation itemInformation, UserDataStructure uSD){
        this.itemInformation = itemInformation;
        this.uSD = uSD;
    }
    
    public boolean doQuery(){
        boolean successful = false;
        GroceryItem groceryItem = new GroceryItem();
       
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
       
        groceryItem.setListNo(itemInformation.getTableIndex());
        groceryItem.setIdUserGroceryList(uSD.getIdUser());
        groceryItem.setItemName(itemInformation.getItemName());
        groceryItem.setQuantity(itemInformation.getQuantity());
        groceryItem.setCaloricValue(itemInformation.getCaloricValue());
        groceryItem.setPurchaseDate(itemInformation.getPurchaseDate());
        groceryItem.setExpirationDate(itemInformation.getExpirationDate());
        groceryItem.setConsumptionDate(itemInformation.getConsumptionDate());
        
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        
        entityManager.persist(groceryItem);
       
        try{
            entityManager.getTransaction().commit();
            entityManager.close();
            successful = true;
        }catch(HibernateException ex){
           
        }finally{
            entityManagerFactory.close();
            entityManagerFactory = null;
        }
        return successful;
    }
}
