package com.boot.resolvers;

import com.boot.entities.User;
import com.boot.repositories.UserRepository;

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
}