package client.business;

import java.util.*;
import javax.swing.JOptionPane;


public class ListObserver implements Observer{

    @Override
    public void update(Observable o, Object arg) {
        int input = JOptionPane.showConfirmDialog(null, "Did you finish eating ?");
        
        if(input == 0){
            
        }else if(input == 1){
            int input2 = JOptionPane.showConfirmDialog(null, "Do you want to extend the consumption date?");
        
            if(input2 == 0){
                System.out.println("YES");
            }
        }
    }
    
}
