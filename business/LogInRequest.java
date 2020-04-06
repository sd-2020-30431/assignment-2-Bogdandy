package business;

import dataaccess.LogInQuery;

public class LogInRequest implements RequestService{
    
    @Override
    public boolean userRequest(UserDataStructure data){
       boolean successful = false;
       
       AccountChecker accountChecker = new AccountChecker(data);
       
       if(accountChecker.checkAllFields()){
           LogInQuery logInQuery = new LogInQuery(data);
           successful = logInQuery.doQuery();
       }
       
       return successful;
    }
}
