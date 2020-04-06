package dataaccess;

import business.*;
import java.util.*;
import org.hibernate.*;
import org.hibernate.criterion.Expression;

public class RetrieveGroceryListQuery {
    private final List<GroceryItem> groceryList = new ArrayList<>();
    private Session session;
    private UserDataStructure uSD;
    
    public RetrieveGroceryListQuery(UserDataStructure uSD){
        this.uSD = uSD;
    }
    
    public List doQuery(){
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        try {
            tx = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Expression.eq("idUser", uSD.getIdUser()));
           
            User u =(User) criteria.uniqueResult();
            tx.commit();
            
            Set groceryItems = u.getGroceryItem();
            for (Iterator i = groceryItems.iterator(); i.hasNext();){
                GroceryItem gc = (GroceryItem) i.next();
                groceryList.add(gc);        
            }
            
            return groceryList;
        }catch(HibernateException e){
            session.close();
            return null;
        }finally {
            session.close();
        }
    }
}
