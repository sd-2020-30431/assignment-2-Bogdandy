package com.boot.resolvers;

import com.boot.entities.User;
import com.boot.repositories.UserRepository;

import java.util.List;
import lombok.RequiredArgsConstructor;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserQuery implements GraphQLQueryResolver {

    private final UserRepository UserRepository;
    
    public Iterable<User> users() {
        return UserRepository.findAll();
    }
    
    public boolean findUser(String username, String password) {
    	final List<User> users = (List<User>) UserRepository.findAll();
    	
    	boolean check = users.stream().anyMatch(o -> o.getUsername().equals(username) &&  o.getPassword().equals(password));
    	
    	return check;
    }
}