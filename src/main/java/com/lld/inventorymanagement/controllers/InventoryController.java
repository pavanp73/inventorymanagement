package com.lld.inventorymanagement.controllers;

import com.lld.inventorymanagement.dtos.CreateOrUpdateRequestDto;
import com.lld.inventorymanagement.dtos.CreateOrUpdateResponseDto;
import com.lld.inventorymanagement.dtos.ResponseStatus;
import com.lld.inventorymanagement.exceptions.ProductNotFoundException;
import com.lld.inventorymanagement.exceptions.UnAuthorizedAccessException;
import com.lld.inventorymanagement.exceptions.UserNotFoundException;
import com.lld.inventorymanagement.models.Inventory;
import com.lld.inventorymanagement.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class InventoryController {

    private final InventoryService inventoryService;

    @Autowired
    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public CreateOrUpdateResponseDto createOrUpdateInventory(CreateOrUpdateRequestDto requestDto) {
        CreateOrUpdateResponseDto responseDto = new CreateOrUpdateResponseDto();
        try {
            Inventory inventory = inventoryService.createOrUpdateInventory(
                    requestDto.getUserId(),
                    requestDto.getProductId(),
                    requestDto.getQuantity()
            );
            responseDto.setInventory(inventory);
            responseDto.setResponseStatus(ResponseStatus.SUCCESS);
        } catch (UserNotFoundException | UnAuthorizedAccessException | ProductNotFoundException e) {
            responseDto.setResponseStatus(ResponseStatus.FAIL);
        }
        return responseDto;
    }




}
