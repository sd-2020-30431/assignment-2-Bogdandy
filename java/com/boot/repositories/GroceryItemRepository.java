package com.boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.entities.GroceryItem;

public interface GroceryItemRepository extends JpaRepository<GroceryItem, Long>{
	
}
