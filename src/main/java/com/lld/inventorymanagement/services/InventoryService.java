package com.lld.inventorymanagement.services;

import com.lld.inventorymanagement.exceptions.ProductNotFoundException;
import com.lld.inventorymanagement.exceptions.UnAuthorizedAccessException;
import com.lld.inventorymanagement.exceptions.UserNotFoundException;
import com.lld.inventorymanagement.models.Inventory;

public interface InventoryService {

    Inventory createOrUpdateInventory(int userId, int productId, int quantity) throws ProductNotFoundException, UserNotFoundException, UnAuthorizedAccessException;

    void deleteInventory(int userId, int productId) throws ProductNotFoundException, UserNotFoundException, UnAuthorizedAccessException;
}
