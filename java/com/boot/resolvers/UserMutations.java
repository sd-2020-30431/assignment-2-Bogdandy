package com.boot.resolvers;

import org.springframework.stereotype.Component;

import com.boot.entities.*;
import com.boot.repositories.*;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserMutations implements GraphQLMutationResolver {
    private final UserRepository userRepository;
    private final GroceryItemRepository groceryItemRepository;
    
    public User newUser(String username, String password, String email, String phoneNumber) {
    	User user = new User();
    	user.setUsername(username);
    	user.setPassword(password);
    	user.setEmail(email);
    	user.setPhone(phoneNumber);
    	userRepository.save(user);

        return user;
    }

    public GroceryItem newGroceryItem(int listNo, Long idUserList, String itemName, int quantity, int caloricValue, String purchaseDate, String consumptionDate, String expirationDate) {
    	GroceryItem groceryItem = new GroceryItem();
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
    
    public boolean deleteGroceryItem(int listNo, Long idUserL, String itemName) {
    	return false;
    }
}