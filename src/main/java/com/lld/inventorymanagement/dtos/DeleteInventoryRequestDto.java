package com.lld.inventorymanagement.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteInventoryRequestDto {

    private int userId;
    private int productId;
}
