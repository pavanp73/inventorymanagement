package com.lld.inventorymanagement.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateOrUpdateRequestDto {

    private int userId;
    private int productId;
    private int quantity;
}
