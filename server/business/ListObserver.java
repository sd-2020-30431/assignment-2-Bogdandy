package server.business;

import java.util.*;


public class ListObserver implements Observer{

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("Did you finish consuming your food?");
    }
    
}
