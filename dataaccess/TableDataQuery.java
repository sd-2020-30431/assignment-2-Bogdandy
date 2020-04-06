package dataaccess;

import business.UserDataStructure;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.*;
import org.hibernate.*;
import org.hibernate.criterion.Expression;

public class TableDataQuery {
    private final UserDataStructure data;
    private final int groceryListId;
    private User u;
    
    public TableDataQuery(UserDataStructure data, int groceryListId){
        this.data = data;
        this.groceryListId = groceryListId;
    }
    
    public TableModel doQuery(){
        DefaultTableModel tblModel = new DefaultTableModel(); 
        String[] columnNames = {"Item Id", "Item Name", "Quantity", "Calorie", "Purchase Date", "Expiration Date", "Consumption Date"}; 
        tblModel.setColumnIdentifiers(columnNames);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
         
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Expression.eq("idUser", data.getIdUser()));
           
            u =(User) criteria.uniqueResult();
            tx.commit();
            
            Set groceryItems = u.getGroceryItem();
            for (Iterator iterator2 = groceryItems.iterator(); iterator2.hasNext();){
                GroceryItem gc = (GroceryItem) iterator2.next();
                if(gc.getListNo() == groceryListId)
                    tblModel.addRow(new Object[]{
                        gc.getIdGroceryList(),
                        gc.getItemName(),
                        gc.getQuantity(),
                        gc.getCaloricValue(),
                        gc.getPurchaseDate(),
                        gc.getExpirationDate(),
                        gc.getConsumptionDate()});
            }
            return tblModel;
        } catch (HibernateException e) {          
            session.close();
            JOptionPane.showMessageDialog(null, "Couldn't populate table!\nCheck connection to the server.", "Warning", JOptionPane.WARNING_MESSAGE);
        } finally {
            session.close();
        }
        return tblModel;
    }
}
