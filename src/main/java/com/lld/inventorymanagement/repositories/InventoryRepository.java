package com.lld.inventorymanagement.repositories;

import com.lld.inventorymanagement.models.Inventory;
import com.lld.inventorymanagement.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    Optional<Inventory> findByProduct(Product product);
}
