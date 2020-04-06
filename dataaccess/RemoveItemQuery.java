package dataaccess;

import business.ItemInformation;
import business.UserDataStructure;
import org.hibernate.*;

public class RemoveItemQuery {
    private final ItemInformation itemInformation;
    private final UserDataStructure uSD;
    private Session session;
    
    public RemoveItemQuery(ItemInformation itemInformation, UserDataStructure uSD){
        this.itemInformation = itemInformation;
        this.uSD = uSD;
    }
    
    public boolean doQuery(){
        Query query = null;
        Transaction tx = null;
        session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            tx = session.beginTransaction();
            
            query = session.createSQLQuery("delete from grocerylist where idGroceryList=:idGroceryList and idUserGroceryList=:idUserGroceryList");
            query.setParameter("idGroceryList", itemInformation.getItemId());
            query.setParameter("idUserGroceryList", uSD.getIdUser());
            
            int l = query.executeUpdate();
            tx.commit();
            
            if(l > 0){
                return true;
            }
            else{
                return false;
            }
        } catch(HibernateException | NullPointerException e) {
            session.close();
            //e.printStackTrace(); 
            return false;
        }finally{
            session.close();
        }
    }
}
