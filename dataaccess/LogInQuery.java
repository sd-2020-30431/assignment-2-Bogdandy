package dataaccess;

import business.UserDataStructure;
import org.hibernate.criterion.Expression;
import org.hibernate.*;

public class LogInQuery {
    private final UserDataStructure data;
    private Session session;
    
    public LogInQuery(UserDataStructure data){
        this.data = data;
    }
    
    public boolean doQuery(){
        User u = null;
       
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
         
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Expression.eq("Username", data.getUsername()));
            criteria.add(Expression.eq("Password", data.getPassword()));
                    
            u =(User) criteria.uniqueResult();
            data.setIdUser(u.getIdUser());
            tx.commit();
            return u != null;
        } catch(HibernateException | NullPointerException e) {
            if (tx!=null) tx.rollback();
            session.close(); 
            //e.printStackTrace(); 
            return false;
        }
    }
}