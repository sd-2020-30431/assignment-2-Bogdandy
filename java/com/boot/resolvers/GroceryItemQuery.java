package com.boot.resolvers;

import com.boot.entities.GroceryItem;
import com.boot.repositories.GroceryItemRepository;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GroceryItemQuery implements GraphQLQueryResolver{
	
	private final GroceryItemRepository GroceryItemRepository;
    
    public Iterable<GroceryItem> items() {
        return GroceryItemRepository.findAll();
    }
    
    public Iterable<GroceryItem> userItems(Long idUserList, int listNo) {
        List<GroceryItem> groceryItems =  (List<GroceryItem>) GroceryItemRepository.findAll();
        
        groceryItems = groceryItems.stream()
        	      .filter(e -> e.getIdUserList() == idUserList && e.getListNo() == listNo)
        	      .collect(Collectors.toList());
        
		return groceryItems;
    }
}
