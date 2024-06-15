package com.lld.inventorymanagement.services;

import com.lld.inventorymanagement.exceptions.ProductNotFoundException;
import com.lld.inventorymanagement.exceptions.UnAuthorizedAccessException;
import com.lld.inventorymanagement.exceptions.UserNotFoundException;
import com.lld.inventorymanagement.models.Inventory;
import com.lld.inventorymanagement.models.Product;
import com.lld.inventorymanagement.models.User;
import com.lld.inventorymanagement.models.UserType;
import com.lld.inventorymanagement.repositories.InventoryRepository;
import com.lld.inventorymanagement.repositories.ProductRepository;
import com.lld.inventorymanagement.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryServiceImpl(UserRepository userRepository,
                                ProductRepository productRepository,
                                InventoryRepository inventoryRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public Inventory createOrUpdateInventory(int userId, int productId, int quantity)
            throws ProductNotFoundException, UserNotFoundException, UnAuthorizedAccessException {
        /*
        1. Get user from DB with the given userId
        2. If the user is not found, throw exception
        3. Check if the user is ADMIN, if not, throw UnAuthorized error
        4. get product from DB with the given productId
        5. If the product is not found then throw exception
        3. Get the inventory of product with the obtained product
        4. if the inventory for the product is not found, create an inventory for the product
        5. else, update the inventory of the product
         */

        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("user not found");
        }
        User user = optionalUser.get();
        if (!UserType.ADMIN.equals(user.getUserType())) {
            throw new UnAuthorizedAccessException("user is not authorized to perform operations on inventory");
        }

        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException("Product not found");
        }
        Product product = optionalProduct.get();

        Inventory inventory;
        Optional<Inventory> optionalInventory = inventoryRepository.findByProduct(product);
        if (optionalInventory.isEmpty()) {
            // Inventory is not found. So, create one.
            inventory = new Inventory();
            inventory.setProduct(product);
            inventory.setQuantity(quantity);
        } else {
            // update inventory
            inventory = optionalInventory.get();
            inventory.setQuantity(quantity);
        }
        inventory = inventoryRepository.save(inventory);
        return inventory;
    }

    @Override
    public void deleteInventory(int userId, int productId) throws UserNotFoundException, UnAuthorizedAccessException {

    }
}
