package com.boot.resolvers;

import com.boot.entities.GroceryItem;
import com.boot.repositories.GroceryItemRepository;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;

import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GroceryItemQuery implements GraphQLQueryResolver{
	
	private final GroceryItemRepository GroceryItemRepository;
    
    public Iterable<GroceryItem> items() {
        return GroceryItemRepository.findAll();
    }
}
