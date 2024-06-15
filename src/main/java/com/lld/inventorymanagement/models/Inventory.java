package com.lld.inventorymanagement.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Inventory extends BaseModel {

    @ManyToOne
    private Product product;
    private int quantity;
}
