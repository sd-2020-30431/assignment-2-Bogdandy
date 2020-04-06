package business;

import dataaccess.SignUpQuery;

public class SignUpRequest implements RequestService {
    
    @Override
    public boolean userRequest(UserDataStructure data) {
        boolean successful = false;
       
        AccountChecker accountChecker = new AccountChecker(data);
        
        if(accountChecker.checkAllFields() == true){
            SignUpQuery signUpQuery = new SignUpQuery(data);
            successful = signUpQuery.doQuery();
        }
        
        return successful;
    }
}

