package dataaccess;

import business.*;
import org.hibernate.*;
import org.hibernate.criterion.Expression;

public class SearchItemQuery {
    private Session session;
    private GroceryItem gc;
    private final UserDataStructure uSD;
    private final String itemName;
    private final int tableIndex;
    
    public SearchItemQuery(String itemName, int tableIndex, UserDataStructure uSD){
        this.uSD = uSD;
        this.itemName = itemName;
        this.tableIndex = tableIndex;
    }
    public boolean doQuery(){
        Query query = null;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
         
            Criteria criteria = session.createCriteria(GroceryItem.class);
            criteria.add(Expression.eq("ItemName", itemName));
            criteria.add(Expression.eq("ListNo", tableIndex));
            criteria.add(Expression.eq("idUserGroceryList", uSD.getIdUser()));
                    
            gc =(GroceryItem) criteria.uniqueResult();
            tx.commit();
            
            
            return gc != null;
        } catch(HibernateException | NullPointerException e) {
            if (tx!=null) tx.rollback();
            session.close(); 
            //e.printStackTrace(); 
            return false;
        }
    }
}
