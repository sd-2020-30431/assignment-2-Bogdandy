package dataaccess;

import business.*;
import org.hibernate.*;

public class ClearListQuery {
    private final int tableIndex;
    private final UserDataStructure uSD;
    private Session session;
    
    public ClearListQuery(int tableIndex, UserDataStructure uSD){
        this.tableIndex = tableIndex;
        this.uSD = uSD;
    }
    
    public boolean doQuery(){
        Query query = null;
        Transaction tx = null;
        
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            
            query = session.createQuery("delete from GroceryItem where ListNo=:ListNo and idUserGroceryList=:idUserGroceryList");
            query.setParameter("idUserGroceryList", uSD.getIdUser());
            query.setParameter("ListNo", tableIndex);
            
            int l = query.executeUpdate();
            tx.commit();
            
            if(l > 0){
                return true;
            }
            else{
                return false;
            }
        } catch(HibernateException | NullPointerException e) {
            //e.printStackTrace(); 
            return false;
        }finally{
            session.close();
        }
    }
}
