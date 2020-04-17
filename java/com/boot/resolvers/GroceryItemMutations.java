package com.boot.resolvers;

import java.util.List;
import java.io.IOException;
import com.boot.entities.*;
import com.boot.repositories.*;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GroceryItemMutations implements GraphQLMutationResolver {
    private final GroceryItemRepository groceryItemRepository;

    public GroceryItem newGroceryItem(int listNo, Long idUserList, String itemName, int quantity, int caloricValue, String purchaseDate, String consumptionDate, String expirationDate) {
    	final GroceryItem groceryItem = new GroceryItem();
    	
    	groceryItem.setIdUserList(idUserList);
    	groceryItem.setListNo(listNo);
    	groceryItem.setItemName(itemName);
    	groceryItem.setQuantity(quantity);
    	groceryItem.setCaloricValue(caloricValue);
    	groceryItem.setPurchaseDate(purchaseDate);
    	groceryItem.setConsumptionDate(consumptionDate);
    	groceryItem.setExpirationDate(expirationDate);
    	groceryItemRepository.save(groceryItem);

        return groceryItem;
    }
    
    public Long deleteGroceryItem(int listNo, Long idUserL, String itemName) {
    	final List<GroceryItem> groceryItems= (List<GroceryItem>) groceryItemRepository.findAll();
    	
    	for (GroceryItem groceryItem : groceryItems) {
            if (groceryItem.getIdUserList().equals(idUserL)
            		&& groceryItem.getItemName().equals(itemName) 
            		&& groceryItem.getListNo() == listNo) {
            	groceryItemRepository.delete(groceryItem);
            	return groceryItem.getId();
            }
        }
    	
    	return null;
    }
    
    public GroceryItem modifyGroceryItem(Long id, String itemName, int quantity, int caloricValue, String purchaseDate, String consumptionDate, String expirationDate) throws IOException {
    	GroceryItem groceryItem = groceryItemRepository.findById(id).orElseThrow(IOException::new);
    	
    	groceryItem.setId(id);
    	groceryItem.setItemName(itemName);
    	groceryItem.setQuantity(quantity);
    	groceryItem.setCaloricValue(caloricValue);
    	groceryItem.setPurchaseDate(purchaseDate);
    	groceryItem.setConsumptionDate(consumptionDate);
    	groceryItem.setExpirationDate(expirationDate);
    	groceryItemRepository.save(groceryItem);
    	
    	return groceryItem;
    }
}