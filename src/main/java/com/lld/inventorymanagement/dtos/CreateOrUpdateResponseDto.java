package com.lld.inventorymanagement.dtos;

import com.lld.inventorymanagement.models.Inventory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrUpdateResponseDto {

    private Inventory inventory;
    private ResponseStatus responseStatus;
}
