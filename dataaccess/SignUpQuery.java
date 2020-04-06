package dataaccess;

import javax.persistence.*;
import org.hibernate.HibernateException;
import business.UserDataStructure;

public class SignUpQuery {
    private final UserDataStructure data;
    
    public SignUpQuery(UserDataStructure data){
        this.data = data;
    }
    
    public boolean doQuery(){
        boolean successful = false;
        User u = new User();
       
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.hibernate.tutorial.jpa");
       
        u.setUsername(data.getUsername());
        u.setPassword(data.getPassword());
        u.setEmailAddress(data.getEmailAddress());
        u.setPhoneNumber(data.getPhoneNumber());
        
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        
        entityManager.persist(u);
       
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
